/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import bo.Grupo;
import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class test {
    public static void main(String arg[]){
        
        ArrayList<Grupo> lista= new ArrayList<Grupo>();
        
        GestionUsuario g =  new GestionUsuario();
        
        lista = g.AllPerfilByUser("ilobato");
                
        for (Grupo temp : lista) {
            System.out.print(temp.getLogin());
            System.out.print(temp.getDescripcionGrupo());
            System.out.print(temp.getIdGrupo());
            System.out.println();
        }
    }
    
}
