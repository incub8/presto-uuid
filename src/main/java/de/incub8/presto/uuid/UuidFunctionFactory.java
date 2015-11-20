package de.incub8.presto.uuid;

import java.util.List;

import com.facebook.presto.metadata.FunctionFactory;
import com.facebook.presto.metadata.FunctionListBuilder;
import com.facebook.presto.metadata.SqlFunction;
import com.facebook.presto.spi.type.TypeManager;

public class UuidFunctionFactory implements FunctionFactory
{
    private final TypeManager typeManager;

    public UuidFunctionFactory(TypeManager typeManager)
    {
        this.typeManager = typeManager;
    }

    @Override
    public List<SqlFunction> listFunctions()
    {
        return new FunctionListBuilder(typeManager).scalar(UuidFunctions.class).getFunctions();
    }
}
