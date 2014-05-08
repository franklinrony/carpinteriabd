package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
//import views.html.*;
import views.html.admin.*;
public class Admin extends Controller {
	
    public static Result index() {
        return ok(index.render());
    }
	

}
