package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;


@Entity 
public class GrupoUsuario extends Model {

    @Id
    public Long id;

    @Constraints.Required(message="Debe ingresar el nombre del grupo")
    public String descripcion;

	// Generic query helper for entity with id Long
	public static Model.Finder<Long,GrupoUsuario> find = new Model.Finder<Long,GrupoUsuario>(Long.class, GrupoUsuario.class);

	// Para usar en select list
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(GrupoUsuario c: GrupoUsuario.find.orderBy("descripcion").findList()) {
            options.put(c.id.toString(), c.descripcion);
        }
        return options;
    }


}
