package controllers;
//borrar si ya noes util para seguridad areas admin
import play.mvc.Security;
import play.mvc.Result;
import play.mvc.Http.Context;
import models.*;

public class Secured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get("username");
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.denegado());
    }
    
    }