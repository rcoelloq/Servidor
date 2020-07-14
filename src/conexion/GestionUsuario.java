/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author isitk
 */
public class GestionUsuario {
     Conexion cc = new Conexion();
     Connection conexion = cc.getConnection();
    
      public ArrayList<String> AllPerfil(){
        ArrayList<String> lista= new ArrayList<String>();
        Statement st;
        ResultSet datos= null;
       
        String sql = "";
        sql = "SELECT * FROM grupo ";
         
        try {
            st=conexion.createStatement();
            datos=st.executeQuery(sql);
            while(datos.next()){
                lista.add(datos.getString("idgrupo"));
            }
             
        } catch (Exception e) {
        }
       
       
       return lista;
       
    }
}
