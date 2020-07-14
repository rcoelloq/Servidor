/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author Sebastian
 */
public class test {
    public static void main(String arg[]){
        Conexion con = new Conexion();
        if (con.getConnection()!= null) 
            System.out.print("Conectado");
        else        
            System.out.print("No Conectado");
    }
            
    
}
