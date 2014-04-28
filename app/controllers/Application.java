package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import models.*;
import views.html.*;
import views.html.registro.*;
public class Application extends Controller {
	//crear formulario para enviar como parametro
	static Form<Usuario> usuarioForm = Form.form(Usuario.class);
	
    public static Result index() {
        return ok(index.render("Hola mundo"));
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
			filledForm.reject("repassword", "Password don't match");
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
		return redirect(routes.Application.index());  
	}
}
}
