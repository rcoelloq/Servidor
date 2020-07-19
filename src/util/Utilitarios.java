
package util;

import bo.Grupo;
import bo.Mensaje;
import java.util.ArrayList;

/**
 *
 * @author yuno
 */
public class Utilitarios {
    
    public static String getCurrentTime()
    {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }
            
    public static Mensaje getObjectMessage(String value)
    {
      String[] arrayPartsMsg = null ;
      Mensaje msg = null;
      if(!value.equals(""))   
      {
          msg = new Mensaje();
          arrayPartsMsg = value.split(Constantes.tokenIdMsg);
          msg.setEmisor(arrayPartsMsg[0]);  
          msg.setReceptor(arrayPartsMsg[1]);
          msg.setMensaje(arrayPartsMsg[2]);
          msg.setFechaEnvio(getCurrentTime());
      }
      return msg;
    } 
    
    
    public static String[] parsingString(String value)
    {
      String[] arrayParts = null ;
      if(!value.equals(""))   
      {
          arrayParts = value.split("\\|");
      }
      
      return arrayParts;
    } 
    
    
    public static String[] parsingStringNodes(String value)
    {
      String[] arrayParts = null ;
      if(!value.equals(""))   
      {
          arrayParts = value.split("\\@");
      }
      
      return arrayParts;
    } 
    
    public static String armarTramaRespGrp(ArrayList<Grupo> listaGrupo)
    {
        String trama = "";
        if(listaGrupo != null)
        {   
            
            for (Grupo temp : listaGrupo) 
            {
                trama += temp.getIdGrupo() + "@" +temp.getDescripcionGrupo() + "|";
            }
        }
        return trama;
    }
}
