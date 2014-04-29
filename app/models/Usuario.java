package models;
import play.db.ebean.*;
import javax.persistence.*;
//manejo de listas
import java.util.*;
//validacion de campos
import javax.validation.*;
import play.data.validation.Constraints.*;
//codificacion pass
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Usuario extends Model{
	//Campos
	@Id
	public long id;
	@Required
	public String nombres;
	@Required
	public String apellidos;
	@Min(value=1)
	public int edad;
	@Required
	@Email 
	public String correo;
	@Required
	public String username;
	@Required
	public String password;

	// -- Queries
    public static Model.Finder<String,Usuario> find = new Model.Finder<String,Usuario>(String.class, Usuario.class);
	
	//crear nuevo usuario
	public static void create(Usuario usuario) {
		//codificar password
		usuario.password = BCrypt.hashpw(usuario.password, BCrypt.gensalt());
		usuario.save();
	}
	
	// verificar datos de login
	public static Usuario autenticarUsuario(String username, String password) {
       Usuario user = Usuario.find.where().eq("username", username).findUnique();
		if (user != null && BCrypt.checkpw(password, user.password)) {
			return user;
		} 
		else {
			return null;
		}
    }
	
	//verificar si ya existe un username en la base de datos
	public static Usuario verificarUsername(String username) {
        return find.where()
            .eq("username", username).findUnique();
    }
	
	//verificar si ya existe un correo en la base de datos
	public static Usuario verificarCorreo(String correo) {
        return find.where()
            .eq("correo", correo).findUnique();
    }
}