package edu.esprit.web.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import edu.app.business.AuthenticationServiceLocal;
import edu.app.business.CatalogServiceLocal;
import edu.app.business.CustomerServiceLocal;
import edu.app.persistence.Customer;

@ManagedBean
@RequestScoped
public class RegisterBean{
	
	@EJB
	private AuthenticationServiceLocal authService;
	
	@EJB
	private CustomerServiceLocal customerService; 
	
	@ManagedProperty("#{authBean}")
	private AuthenticationBean authBean;
	
	private Customer customer;
	
	public RegisterBean() {
	}
	
	@PostConstruct
	public void init(){
		customer = new Customer();
	}
	
	public String doSignUp(){
		String navigateTo = null;
		customerService.saveOrUpdate(customer);
		authBean.setUser(customer);
		navigateTo = authBean.doLogin();
		return navigateTo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public void validateLoginUnicity(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String loginToValidate = (String)value;
		if (loginToValidate == null || loginToValidate.trim().isEmpty()) {
			return;
		}
		boolean loginInUse = authService.loginExists(loginToValidate);
		if (loginInUse) {
			throw new ValidatorException(new FacesMessage("login already in use!"));
		}
	}

	public AuthenticationBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(AuthenticationBean authBean) {
		this.authBean = authBean;
	}
	
	

}
