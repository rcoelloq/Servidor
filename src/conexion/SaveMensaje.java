/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import bo.Grupo;
import bo.Mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author isitk
 */
public class SaveMensaje {
     Conexion cc = new Conexion();
     Connection conexion = cc.getConnection();
    
     public boolean saveMessage(Mensaje msj){
        
        boolean banderaOk= false;
       
        // the mysql insert statement
        String query = " insert into mensajes (emisor, receptor, mensaje, fechaEnvio)"
                     + " values (?, ?, ?, ?)";
        
        try {
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conexion.prepareStatement(query);
            preparedStmt.setString(1, msj.getEmisor());
            preparedStmt.setString(2, msj.getReceptor());
            preparedStmt.setString(3, msj.getMensaje());
            preparedStmt.setString(4, msj.getFechaEnvio());

            // execute the preparedstatement
            preparedStmt.execute();
            conexion.close();
            banderaOk = true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al recuperar los grupos del usuario: "+ e);
        } 
       return banderaOk;   
    }
}
