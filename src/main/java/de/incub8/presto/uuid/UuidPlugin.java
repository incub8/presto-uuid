package de.incub8.presto.uuid;

import java.util.Set;

import org.kohsuke.MetaInfServices;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableSet;

@MetaInfServices
public class UuidPlugin implements Plugin {
	@Override
	public Set<Class<?>> getFunctions() {
		return ImmutableSet.<Class<?>>builder().add(UuidFunctions.class).build();
	}
}
