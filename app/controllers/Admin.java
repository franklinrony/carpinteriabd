package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.admin.*;
import play.mvc.Http.*;
//para verificar accesos
@With(Autorizar.class)
public class Admin extends Controller {
	
    public static Result index() {
            return ok(index.render());
       }
}