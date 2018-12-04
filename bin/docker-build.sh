#!/usr/bin/env bash

set -o errexit
# set -o xtrace

# Give script sane defaults
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ROOT_DIR=$(cd ${SCRIPT_DIR}/..; pwd)

# DEFAULT VARS
PROJECTS=("bolt", "explorer", "api", "ethereumj", "kafka-connect", "kafka-connect-ethvm-init", "kafka-ethvm-init", "mongodb-install", "mongodb-ethvm-init", "zookeeper")

ORG="enkryptio"
DOCKER_PATH="docker/images"

# Usage prints the help for this command
usage() {
  >&2 echo "Usage:"
  >&2 echo "    docker-build <command>"
  >&2 echo ""
  >&2 echo "Commands:"
  >&2 echo "    build <image>  Build a docker image from this repo."
  >&2 echo "    push  <image>  Push the built image to the docker registry."
  >&2 echo ""
  >&2 echo "Images:"
  >&2 echo "    [${PROJECTS[*]}]"
  exit 1
}

# ensure checks that whe have corresponding utilities installed
ensure() {
  if ! [ -x "$(command -v jq)" ]; then
    >&2 echo "jq is necessary to be installed to run this script!"
    exit 1
  fi
}

# Build builds the docker image
build() {
  local name="$1"
  local version="$2"
  local dockerfile="$3"
  local path="$4"
  docker build -t "${ORG}/${name}:${version}" -f ${dockerfile} ${path}
}

# Push pushes the built docker image
push() {
  local repo="$1"
  docker push "$repo"
}

prop() {
  grep $1 $2 | cut -d '=' -f2
}

run() {
  ensure
  case "$1" in
    build)
      case "$2" in
        bolt|ethereumj) build "${2}" "$(prop 'version' "apps/${2}/version.properties")" "apps/${2}/Dockerfile" "apps/${2}" ;;
        explorer) build "${2}" "$(jq .version apps/ethvm/package.json -r)" "apps/ethvm/Dockerfile" "apps/" ;;
        api) build "${2}" "$(jq .version apps/server/package.json -r)" "apps/server/Dockerfile" "apps/" ;;
        kafka-connect|kafka-connect-ethvm-init|kafka-ethvm-init|mongodb-install|mongodb-ethvm-init|zookeeper) build "$2" "$(prop 'version' "${DOCKER_PATH}/$2/version.properties")" "${DOCKER_PATH}/${2}/Dockerfile" "${DOCKER_PATH}/${2}/" ;;
      esac
      ;;
    push)
      case "$2" in
        bolt|ethereumj) push "${ORG}/${2}:$(prop 'version' "apps/${2}/version.properties")" ;;
        explorer) push "${ORG}/${2}:$(jq .version apps/${2}/package.json -r)" ;;
        api) push "${ORG}/${2}:$(jq .version apps/server/package.json -r)" ;;
        kafka-connect|kafka-connect-ethvm-init|kafka-ethvm-init|mongodb-install|mongodb-ethvm-init|zookeeper) push "${ORG}/${2}:$(prop 'version' "${DOCKER_PATH}/${2}/version.properties")" ;;
      esac
      ;;
    *) usage ;;
  esac
}
run "$@"