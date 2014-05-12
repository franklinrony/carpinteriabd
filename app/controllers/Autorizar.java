package controllers;

import play.*;
import play.mvc.*;
import models.*;
import play.libs.F;
import play.mvc.Http.Context;
public class Autorizar extends Action.Simple {

    public F.Promise<SimpleResult> call(Http.Context ctx) throws Throwable {
	Logger.info("Calling action for " + ctx);
		//verificar si el usuario esta logueado
        if(ctx.session().get("username") == null)
			return F.Promise.<SimpleResult>pure(redirect(routes.Application.denegado()));
		//verificar el grupo de usuario
		String username= ctx.session().get("username");	
		Usuario usuario = Usuario.find.where().eq("username", username).findUnique();
					Logger.info("Calling action for " + usuario.grupo);

		if(usuario.grupo!=1){
						return F.Promise.<SimpleResult>pure(redirect(routes.Application.denegado()));
		}
        return delegate.call(ctx);
    }

}