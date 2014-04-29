 package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;
import views.html.*;   
	
public  class Login extends Controller {
        
        public String username;
        public String password;
        
         public String validate() {
             if(Usuario.autenticarUsuario(username, password) == null) {
                 return "Invalid user or password";
             }
             return null;
        }
        
}