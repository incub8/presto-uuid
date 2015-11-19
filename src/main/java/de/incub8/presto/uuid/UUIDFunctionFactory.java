package de.incub8.presto.uuid;

import java.util.List;

import com.facebook.presto.metadata.FunctionFactory;
import com.facebook.presto.metadata.FunctionListBuilder;
import com.facebook.presto.metadata.ParametricFunction;
import com.facebook.presto.spi.type.TypeManager;

public class UUIDFunctionFactory implements FunctionFactory
{
    private final TypeManager typeManager;

    public UUIDFunctionFactory(TypeManager typeManager)
    {
        this.typeManager = typeManager;
    }

    @Override
    public List<ParametricFunction> listFunctions()
    {
        return new FunctionListBuilder(typeManager).scalar(UUIDFunctions.class).getFunctions();
    }
}
