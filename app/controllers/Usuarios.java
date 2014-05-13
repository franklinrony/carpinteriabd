package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.admin.*;
//para verificar accesos
@With(Autorizar.class)
public class Usuarios extends Controller {
	//crear formulario para enviar como parametro
	static Form<Usuario> usuarioForm = Form.form(Usuario.class);
	
	//mostrar lista de usuarios
    public static Result listarUsuarios() {
        return ok(usuarios.render(Usuario.find.all()));
    }
	//mostrar Formulario crear usuario desde el panel de admin
	public static Result crearUsuario(){
		return ok(nuevousuario.render(usuarioForm));
	}
	
	//Registrar usuario en la bd
	public static Result registrarUsuario(){
	//asignando los campos llenos a los Campos del form
	Form<Usuario> filledForm = usuarioForm.bindFromRequest();
	// Check repeated password
	if(!filledForm.field("password").valueOr("").isEmpty()) {
		if(!filledForm.field("password").valueOr("").equals(filledForm.field("repassword").value())) {
			filledForm.reject("repassword", "los passwords no coinciden");
		}
	}
	//Verificar si el usuario no tiene una cuenta creada previamente

	if(Usuario.verificarUsername(filledForm.field("username").value())!=null){
		filledForm.reject("username", "Usuario ya registrado");
	}
	if(Usuario.verificarCorreo(filledForm.field("correo").value())!=null){
		filledForm.reject("correo", "Correo electronico ya registrado");
	}
	//que muestre el formulario con errores
	if(filledForm.hasErrors()) {
		return badRequest(nuevousuario.render(filledForm));
	} 
	//guardar usuario en la bd
	else {
		Usuario.create(filledForm.get());
		flash("success", "Usuario creado correctamente");
		return redirect(routes.Usuarios.crearUsuario());  
	}
	}
	//borrar usuarios
	public static Result borrarUsuario(Long id){
		Usuario.find.ref(id).delete();
        flash("success", "Usuario ha sido eliminado");
        return ok(usuarios.render(Usuario.find.all()));
	}
	//mostra formulario agregar nuevo grupo de usuarios
	public static Result nuevoGrupo(){
		 Form<GrupoUsuario> formulario = Form.form(GrupoUsuario.class);
		return ok(nuevogrupo.render(formulario));
	}
	//guardar grupo de usuarios
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
