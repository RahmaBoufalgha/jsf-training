package edu.esprit.web.mbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
	//
	
	
	public AuthenticationBean() {
	}
	
	//model initialization
	@PostConstruct
	public void initModel(){
		user = new User();
	}
	
	public String doLogin(){
		String navigateTo = null;
		//login application logic
		User found = authenticationServiceLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			user = found;
			if(user instanceof Admin){
				navigateTo = "success";
			}
			
		}else {
			navigateTo = "failure";
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
	
	

}
