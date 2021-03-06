package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.*;
import views.html.registro.*;
//import play.mvc.Http.*;
public class Application extends Controller {
	//crear formulario para enviar como parametro
	static Form<Usuario> usuarioForm = Form.form(Usuario.class);
	//formulario login
	static Form<Login> loginForm=Form.form(Login.class);
	
    public static Result index() {
        return ok(index.render("Hola mundo"));
    }
	
	//mostrar pagina de acceso denegado cuando
	//el usuario no tiene permisos para ver una pagina
	public static Result denegado(){
		return forbidden(accesodenegado.render());
	}
	
	//Mostrar formulario de registro
	public static Result mostrarFormulario(){
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
		flash("success", "Usuario creado correctamente,ahora puede iniciar sesion");
		return redirect(routes.Application.mostrarLogin());  
	}
	}
	
	//mostrar  pagina login
	public static Result mostrarLogin(){
		return ok(login.render(loginForm));
	}
	//metodo verificar datos de usuaio
	public static Result autenticarUsuario(){
	
		Form<Login> filledLogin = loginForm.bindFromRequest();
        if(filledLogin.hasErrors()) {
            return badRequest(login.render(filledLogin));
        } else {
			//buscar  usuario para saber su grupo
	       Usuario user = Usuario.find.where().eq("username", filledLogin.get().username).findUnique();
			//manejo sesion 
			session("username",filledLogin.get().username.toString());
			session("grupo",Integer.toString(user.grupo));
			flash("success", "Ha iniciado sesion correctamente");
            return redirect(
                routes.Application.index()
            );
        }
	}
	//cerrar sesion
    public static Result cerrarSesion() {
		//borrar sesion
        session().clear();
        flash("success", "Ha cerrado sesion correctamente");
        return redirect(
            routes.Application.index()
        );
    }
}
