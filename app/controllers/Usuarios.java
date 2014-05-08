package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
//import views.html.*;
import views.html.admin.*;
public class Usuarios extends Controller {
	
    public static Result listarUsuarios() {
        return ok(usuarios.render(Usuario.find.all()));
    }
	

}
