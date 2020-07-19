/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author yuno
 */
public class Test {
    public static void main(String arg[])
    {
        try{
            String texto = "COM|9";
            String[] arrayValues = null;
            /*arrayValues = Utilitarios.parsingString(texto);
            System.out.println(arrayValues[0]);*/
            
            String arreglo[] = Utilitarios.parsingStringNodes("10@ilobato");
            System.out.println(arreglo[0]);
            System.out.println(arreglo[1]);
            
        }catch(Exception ex){
                ex.printStackTrace();
        }

    }
}
