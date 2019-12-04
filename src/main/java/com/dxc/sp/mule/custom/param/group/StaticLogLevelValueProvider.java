package com.dxc.sp.mule.custom.param.group;

import java.util.Set;

import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;
import org.mule.runtime.api.value.Value;

public class StaticLogLevelValueProvider implements ValueProvider {
	
	@Override
    public Set<Value> resolve() {
        return ValueBuilder.getValuesFor("TRACE", "DEBUG", "INFO","WARN","ERROR","FATAL");
    }

}
