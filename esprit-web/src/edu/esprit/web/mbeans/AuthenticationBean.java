package edu.esprit.web.mbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.app.business.AuthenticationServiceLocal;
import edu.app.persistence.Admin;
import edu.app.persistence.User;


@ManagedBean( name = "authBean" )
@SessionScoped
public class AuthenticationBean implements Serializable{

	private static final long serialVersionUID = -6916676537171647179L;
	
	@EJB
	private AuthenticationServiceLocal authenticationServiceLocal;
	
	//model
	private User user;
	private boolean loggedIn;
	//
	
	
	public AuthenticationBean() {
	}
	
	//model initialization
	@PostConstruct
	public void initModel(){
		user = new User();
		loggedIn = false;
	}
	
	public String doLogin(){
		String navigateTo = null;
		//login application logic
		User found = authenticationServiceLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			loggedIn = true;
			if(user instanceof Admin){
				navigateTo = "/pages/admin/home?faces-redirect=true";
			}
			
		}else {
			loggedIn = false;
			FacesContext.getCurrentInstance().addMessage("login_form:login_submit", new FacesMessage("Bad credentials!"));
		}
		return navigateTo;
	}
	
	public String doLogout(){
		String navigateTo = null;
		initModel();
		navigateTo = "/welcome?faces-redirect=true";
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	

	
	
	

}
