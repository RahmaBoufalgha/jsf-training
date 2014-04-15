package edu.esprit.web.mbeans;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import edu.app.business.CatalogServiceLocal;
import edu.app.persistence.Category;

@ManagedBean
@ApplicationScoped
public class HelperBean {


	@EJB
	private CatalogServiceLocal catalog;

	public HelperBean() {
	}

	public Category findCategoryByName(String name) {
		return catalog.findCategoryByName(name);
	}

}
