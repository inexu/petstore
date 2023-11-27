package io.swagger.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "Category")
@Table(name = "category")
public class CategoryModel
{
	private Long id;
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
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
		final CategoryModel that = (CategoryModel) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name);
	}
}
