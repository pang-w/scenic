package com.itmaoo.oa.support;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class PropertiesUtils implements EmbeddedValueResolverAware {
	private StringValueResolver resolver;

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.resolver = resolver;
	}

	public String getPropertiesValue(String name) {
		return resolver.resolveStringValue(name);
	}
}
