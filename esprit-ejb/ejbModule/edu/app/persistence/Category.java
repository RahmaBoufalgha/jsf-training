package edu.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name="t_category")

public class Category implements Serializable {
	
	private static final long serialVersionUID = 1077338873585734472L;
	
	private int id;
	private String name;
	
	private List<Product> products;
	
	
	public Category() {
	}
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Id    
	public int getId() {
		return this.id;
	}
	@Column(unique=true)
	public String getName() {
		return this.name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy = "category")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product){
		this.getProducts().add(product);
		product.setCategory(this);
	}
	@PreRemove
	public void preRemove(){
		for(Product p:products)
			p.setCategory(null);
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
   
}
