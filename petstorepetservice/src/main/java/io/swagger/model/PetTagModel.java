package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "PetTag")
@Table(name = "pet_tag")
public class PetTagModel implements Serializable
{
	private Long petId;
	private Long tagId;

	@Id
	@Column(name = "pet_id")
	public Long getPetId()
	{
		return petId;
	}

	public void setPetId(final Long petId)
	{
		this.petId = petId;
	}

	@Id
	@Column(name = "tag_id")
	public Long getTagId()
	{
		return tagId;
	}

	public void setTagId(final Long tagId)
	{
		this.tagId = tagId;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final PetTagModel that = (PetTagModel) o;
		return Objects.equals(petId, that.petId) && Objects.equals(tagId, that.tagId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(petId, tagId);
	}
}
