/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import bo.Grupo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isitk
 */
public class GestionUsuario {
     Conexion cc = new Conexion();
     Connection conexion = cc.getConnection();
    
      public ArrayList<Grupo> AllPerfilByUser(String user){
        ArrayList<Grupo> lista= new ArrayList<Grupo>();
        Statement st;
        ResultSet datos= null;
        Grupo grupo;
       
        String sqlGrupos = 
            "select u.login, g.idgrupo, g.nombregrupo "  +
            "from usuario_grupo ug, grupo g, usuario u " +
            "WHERE ug.grupoId = g.idgrupo "      +
            "and ug.usuarioId = u.idusuario " +
            "and  u.login = '" + user + "'";
        
        try {
            st=conexion.createStatement();
            datos=st.executeQuery(sqlGrupos);
            while(datos.next()){
                grupo =  new  Grupo();
                grupo.setLogin(datos.getString("login"));
                grupo.setIdGrupo(datos.getInt("idgrupo"));
                grupo.setDescripcionGrupo(datos.getString("nombregrupo"));
                lista.add(grupo);
            }
        } catch (Exception e) {
            lista = null;
            System.out.println("Ocurrio un error al recuperar los grupos del usuario: "+ e);
        }
       return lista;   
    }
}
