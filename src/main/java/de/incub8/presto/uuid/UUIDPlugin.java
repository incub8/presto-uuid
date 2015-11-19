package de.incub8.presto.uuid;

import java.util.List;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableList;

public class UUIDPlugin implements Plugin
{
    @Override
    public <T> List<T> getServices(Class<T> type)
    {
        return ImmutableList.of();
    }
}
