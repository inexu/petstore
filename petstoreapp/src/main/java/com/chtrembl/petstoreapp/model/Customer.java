package com.chtrembl.petstoreapp.model;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@SuppressWarnings("serial")
@Component
public class Customer implements Serializable
{
	@JsonProperty("sessionId")
	private String sessionId = null;
	@JsonProperty("name")
	private String name = null;

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(final String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Customer customer = (Customer) o;
		return Objects.equals(sessionId, customer.sessionId) && Objects.equals(name, customer.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(sessionId, name);
	}
}
