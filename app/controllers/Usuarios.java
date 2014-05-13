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
	//ver listado de grupos
	public static Result listadoGrupos(){
		return ok(grupos.render(GrupoUsuario.find.all()));
	}
	// GET, editar el registro
    public static Result editar(Long id) {
        Form<GrupoUsuario> formulario = Form.form(GrupoUsuario.class).fill(GrupoUsuario.find.byId(id));
        return ok(editargrupo.render(id, formulario));
    }
	//guardar registro editador
	public static Result actualizar(Long id){
		Form<GrupoUsuario> formulario = Form.form(GrupoUsuario.class).bindFromRequest();
        if(formulario.hasErrors()) {
			flash("error","Error con el ingreso de datos");
            return badRequest(editargrupo.render(id, formulario));
        }
        formulario.get().update(id);
        flash("success", "Grupo actualizado con exito!");
        return ok(grupos.render(GrupoUsuario.find.all()));
	}
	//borrar grupo 
	public static Result borrarGrupo(Long id){
		GrupoUsuario.find.ref(id).delete();
        flash("success", "Grupo ha sido eliminado");
        return ok(grupos.render(GrupoUsuario.find.all()));
	}
}
