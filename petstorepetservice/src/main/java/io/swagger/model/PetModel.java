package io.swagger.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity(name = "Pet")
@Table(name = "pet")
public class PetModel
{
	private Long id;
	private CategoryModel category;
	private String name;
	@JsonProperty("photoURL")
	private String photoUrl;
	private List<TagModel> tags;
	private String status;

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

	@ManyToOne
	@JoinColumn(name = "category_id")
	public CategoryModel getCategory()
	{
		return category;
	}

	public void setCategory(CategoryModel category)
	{
		this.category = category;
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

	@Column(name = "photoURL", nullable = false)
	public String getPhotoUrl()
	{
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}

	@ManyToMany
	@JoinTable(name = "pet_tag",
		  joinColumns = { @JoinColumn(name = "pet_id") },
		  inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	public List<TagModel> getTags()
	{
		return tags;
	}

	public void setTags(List<TagModel> tags)
	{
		this.tags = tags;
	}

	@Column(name = "status")
	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final PetModel that = (PetModel) o;
		return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(photoUrl,
			  that.photoUrl) && Objects.equals(status, that.status);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, photoUrl, status);
	}
}
