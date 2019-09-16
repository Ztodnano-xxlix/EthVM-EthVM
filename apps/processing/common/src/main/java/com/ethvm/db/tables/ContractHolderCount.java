/*
 * This file is generated by jOOQ.
 */
package com.ethvm.db.tables;


import com.ethvm.db.Indexes;
import com.ethvm.db.Keys;
import com.ethvm.db.Public;
import com.ethvm.db.tables.records.ContractHolderCountRecord;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.12"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ContractHolderCount extends TableImpl<ContractHolderCountRecord> {

    private static final long serialVersionUID = 1695579393;

    /**
     * The reference instance of <code>public.contract_holder_count</code>
     */
    public static final ContractHolderCount CONTRACT_HOLDER_COUNT = new ContractHolderCount();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContractHolderCountRecord> getRecordType() {
        return ContractHolderCountRecord.class;
    }

    /**
     * The column <code>public.contract_holder_count.contract_address</code>.
     */
    public final TableField<ContractHolderCountRecord, String> CONTRACT_ADDRESS = createField("contract_address", org.jooq.impl.SQLDataType.CHAR(42).nullable(false), this, "");

    /**
     * The column <code>public.contract_holder_count.block_number</code>.
     */
    public final TableField<ContractHolderCountRecord, BigDecimal> BLOCK_NUMBER = createField("block_number", org.jooq.impl.SQLDataType.NUMERIC.nullable(false), this, "");

    /**
     * The column <code>public.contract_holder_count.token_type</code>.
     */
    public final TableField<ContractHolderCountRecord, String> TOKEN_TYPE = createField("token_type", org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>public.contract_holder_count.count</code>.
     */
    public final TableField<ContractHolderCountRecord, Long> COUNT = createField("count", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>public.contract_holder_count</code> table reference
     */
    public ContractHolderCount() {
        this(DSL.name("contract_holder_count"), null);
    }

    /**
     * Create an aliased <code>public.contract_holder_count</code> table reference
     */
    public ContractHolderCount(String alias) {
        this(DSL.name(alias), CONTRACT_HOLDER_COUNT);
    }

    /**
     * Create an aliased <code>public.contract_holder_count</code> table reference
     */
    public ContractHolderCount(Name alias) {
        this(alias, CONTRACT_HOLDER_COUNT);
    }

    private ContractHolderCount(Name alias, Table<ContractHolderCountRecord> aliased) {
        this(alias, aliased, null);
    }

    private ContractHolderCount(Name alias, Table<ContractHolderCountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> ContractHolderCount(Table<O> child, ForeignKey<O, ContractHolderCountRecord> key) {
        super(child, key, CONTRACT_HOLDER_COUNT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONTRACT_HOLDER_COUNT_PKEY, Indexes.IDX_CONTRACT_HOLDER_COUNT_BY_TOKEN_TYPE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContractHolderCountRecord> getPrimaryKey() {
        return Keys.CONTRACT_HOLDER_COUNT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContractHolderCountRecord>> getKeys() {
        return Arrays.<UniqueKey<ContractHolderCountRecord>>asList(Keys.CONTRACT_HOLDER_COUNT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContractHolderCount as(String alias) {
        return new ContractHolderCount(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContractHolderCount as(Name alias) {
        return new ContractHolderCount(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ContractHolderCount rename(String name) {
        return new ContractHolderCount(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ContractHolderCount rename(Name name) {
        return new ContractHolderCount(name, null);
    }
}
