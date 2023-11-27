package io.swagger.converter;

import java.util.Collection;
import java.util.List;


public abstract class Converter<SOURCE, TARGET>
{
	public abstract void populate(SOURCE source, TARGET target);

	protected abstract TARGET createTarget();

	public TARGET convert(SOURCE source)
	{
		if (source == null)
		{
			return null;
		}

		TARGET target = createTarget();
		populate(source, target);

		return target;
	}

	public List<TARGET> convertAll(Collection<SOURCE> sources)
	{
		return sources.stream()
			  .map(this::convert)
			  .toList();
	}
}
