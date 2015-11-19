package de.incub8.presto.uuid;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javax.inject.Inject;

import com.facebook.presto.metadata.FunctionFactory;
import com.facebook.presto.spi.Plugin;
import com.facebook.presto.spi.type.TypeManager;
import com.google.common.collect.ImmutableList;

public class UUIDPlugin implements Plugin
{
    private TypeManager typeManager;

    @Inject
    public void setTypeManager(TypeManager typeManager)
    {
        this.typeManager = requireNonNull(typeManager, "typeManager is null");
    }

    @Override
    public <T> List<T> getServices(Class<T> type)
    {
        if (type == FunctionFactory.class)
        {
            return ImmutableList.of(type.cast(new UUIDFunctionFactory(typeManager)));
        }
        return ImmutableList.of();
    }
}
