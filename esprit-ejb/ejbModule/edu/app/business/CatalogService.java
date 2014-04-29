package edu.app.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import edu.app.persistence.Category;
import edu.app.persistence.Product;

@Stateless
public class CatalogService implements CatalogServiceRemote, CatalogServiceLocal {
	
	@PersistenceContext
	private EntityManager em;

    public CatalogService() {
    }

	public void createProduct(Product product) {
		em.persist(product);
	}

	public void saveOrUpdateProduct(Product product) {
		em.merge(product);
	}

	public Product findProductById(int id) {
		return em.find(Product.class, id);
	}

	public void removeProduct(Product product) {
		em.remove(em.merge(product));
	}

	public List<Product> findAllProducts() {
		return em.createQuery("select p from Product p").getResultList();
	}

	public List<Product> findProductsByCategory(Category category) {
		return em.createQuery("select p from Product p where p.category=:c").setParameter("c", category).getResultList();
	}

	public void createCategory(Category category) {
		em.persist(category);
	}

	public void saveOrUpdateCategory(Category category) {
		em.merge(category);
	}

	public Category findCategoryById(int id) {
		return em.find(Category.class, id);
	}

	public void removeCategory(Category category) {
		em.remove(em.merge(category));
	}

	public List<Category> findAllCategories() {
		return em.createQuery("select c from Category c").getResultList();
	}

	public Category findCategoryByName(String name) {
		Category found = null;
		Query query = em.createQuery("select c from Category c where c.name=:x");
		query.setParameter("x", name);
		try{
			found = (Category) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no category with name="+name);
		}
		return found;
	}

	public byte[] findPictureByProductId(int productId) {
		byte[] picture = null;
		Query query = em.createQuery("select p.picture from Product p where p.id=:x");
		query.setParameter("x", productId);
		try{
			picture =  (byte[]) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no picture");
		}
		return picture;
		
	}

}
