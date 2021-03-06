package models;
import java.util.*;
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
	public Long id;
	@Required (message="Debe ingresar sus Nombres")
	public String nombres;
	@Required (message="Debe ingresar sus Apellidos")
	public String apellidos;
	@Min(value=1)
	public int edad;
	@Required (message="Debe ingresar un correo electronico valido")
	@Email 
	public String correo;
	@Required (message="Debe ingresar un usuario")
	public String username;
	@Required (message="Debe ingresar una contraseña")
	public String password;
	//grupo por defecto cliente
	//@ManyToOne
	//public GrupoUsuario grupousuario;
	@Column(columnDefinition = "integer default 2")
	public int grupo=2;
	
	// -- Queries
    public static Model.Finder<Long,Usuario> find = new Model.Finder<Long,Usuario>(Long.class, Usuario.class);
	
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