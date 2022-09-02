from qiskit_ibm_runtime import QiskitRuntimeService

options = {
	'backend_name': 'ibmq_qasm_simulator'
}

runtime_inputs = {
	# A PyTorch data loader object
	# containing the inference dataset.
	'data': None, # object (required)

	# A hybrid QNN model to
	# be used for inference.
	'model': None, # object (required)

	# Whether to apply measurement error
	# mitigation in form of a
	# complete measurement fitter to the
	# measurements. Defaults to False.
	# 'measurement_error_mitigation': False, # boolean

	# Optional Scoring function for ``score``
	# functionality.
	# 'score_func': None, # [string,object]

	# The number of shots used
	# for each circuit evaluation. Defaults
	# to 1024.
	# 'shots': 1024 # integer
}

service = QiskitRuntimeService(
	channel='ibm_quantum'
)

job = service.run(
	program_id='torch-infer',
	options=options,
	inputs=runtime_inputs,
	instance='ibm-q/open/main'
)

# Job id
print(job.job_id)
# See job status
print(job.status())

# Get results
result = job.result()
