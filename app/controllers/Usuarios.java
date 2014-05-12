package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.admin.*;
//para verificar accesos
@With(Autorizar.class)
public class Usuarios extends Controller {
	//mostrar lista de usuarios
    public static Result listarUsuarios() {
        return ok(usuarios.render(Usuario.find.all()));
    }
	//mostra formulario agregar nuevo grupo de usuarios
	public static Result nuevoGrupo(){
		 Form<GrupoUsuario> formulario = Form.form(GrupoUsuario.class);
		return ok(nuevogrupo.render(formulario));
	}
	//guardar grupo de suuarios
	public static Result save(){
		Form<GrupoUsuario> formulario = Form.form(GrupoUsuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
			flash("error","Error con el ingreso de datos");
            return badRequest(nuevogrupo.render(formulario));
        }
		else {
			formulario.get().save();
			flash("success", "Grupo Agregado Correctamente");
			return ok(nuevogrupo.render(formulario));  
	}

	}
}
