package io.swagger.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity(name = "ProductTag")
@Table(name = "product_tag")
public class ProductTagModel implements Serializable
{
	private Long productId;
	private Long tagId;

	@Id
	@Column(name = "product_id")
	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(final Long productId)
	{
		this.productId = productId;
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
		final ProductTagModel that = (ProductTagModel) o;
		return Objects.equals(productId, that.productId) && Objects.equals(tagId, that.tagId);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(productId, tagId);
	}
}
