
package messenger;
import bo.Grupo;
import bo.Mensaje;
import conexion.GestionUsuario;
import conexion.SaveMensaje;
import conexion.VerificarIngreso;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import util.Constantes;
import util.Utilitarios;
import static util.Utilitarios.armarTramaRespGrp;

public class Conector extends Thread {
    private Socket s;
    private ServerSocket ss;
    private InputStreamReader entradaSocket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto = 8180;
    
    public Conector(String nombre)
    {
        super(nombre);
    }
    public void enviarMSG(String msg)
    {
        try{
            System.out.println("MSG Enviado Servidor: "+msg);
            this.salida.writeUTF(msg+"\n");
        }catch (IOException e){};
    }
    
    public void run()
    {
     String text="test";
     String grupoActual = "";
     try{
         this.ss = new ServerSocket(puerto);//Genero la conexion con servidor
         this.s = ss.accept(); //Generar conexion cuando un cliente se conecta con el servidor
         
         //Creacion de entrada de datos para lectura de mensajes
         this.entradaSocket = new InputStreamReader(s.getInputStream());
         this.entrada = new BufferedReader(entradaSocket);//Leer los datos de entrada
         
         //creacion de salida de datos para el envio de mensajes
         this.salida = new DataOutputStream(s.getOutputStream());
         
        //100pre recibiendo mensajes/leyendo mensajes
         while(true)
         {
             text = this.entrada.readLine();
             
             if(text.contains("/")){
                String[] parts = text.split("/");
                String user = parts[0]; 
                String clave = parts[1]; 
                
                VerificarIngreso vi = new VerificarIngreso();
                System.out.println("validar"+vi.validarLogin(user,clave));
                enviarMSG(vi.validarLogin(user,clave)+"");
             }
             else if (text.trim().equals("D"))
             {
                 desconectar();
             }
                 
             if(text.contains(Constantes.etiquetaGrupo))
             {   
                 ArrayList<Grupo> lista= new ArrayList<Grupo>();
                 GestionUsuario vi = new GestionUsuario();
                 String[] arrayGroup = Utilitarios.parsingString(text.trim());
                 lista = vi.AllPerfilByUser(arrayGroup[1]);
                 enviarMSG(armarTramaRespGrp(lista));
             }
             
             if(text.contains(Constantes.etiquetaMensaje))
             {
                 SaveMensaje save =  new SaveMensaje();
                 // -MSGilobato@canales@aaaa
                 text = text.substring(5,text.length());
                 //ilobato@canales@aaaa
                  Mensaje message = Utilitarios.getObjectMessage(text);
                 //Insertar MSG
                 save.saveMessage(message);
                 boolean tieneGrupo = (!grupoActual.equals("")) ? true: false;
                 //Si es el mismo grupo mantengo la conversacion
                 if (tieneGrupo)
                 {
                     if(grupoActual.equals(message.getReceptor()))
                        VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+
                                                      message.getEmisor()+": "+ message.getMensaje());
                     else
                      {
                          VServidor.jTextArea1.setText("");//Si escoge otro grupo limpio el bloque de mensajes
                          VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+
                                                 message.getEmisor()+": "+ message.getMensaje());//Escribo Mensaje
                      }  
                 }
                 else
                    VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+
                                                 message.getEmisor()+": "+ message.getMensaje());
                 
                 grupoActual = message.getReceptor();//Grupo Actual
             }   
         }
         }catch (IOException e){
            System.out.println("Ocurrio un error Input/Output exception: " + e.getMessage());
         }catch (Exception a){
             System.out.println("Ocurrio un error Exception: " +  a.getMessage());
         }
    }
    public String leerMSG()
    {
        try{
            return this.entrada.readLine();
        }catch(IOException e){
        };
        return null;
    }
    public void desconectar()
    {
        try{
            if (s != null)
            {
                s.close(); //Cierre de conexion
            }      
        }catch(Exception e){
            System.out.println("Ocurrio un error al cerra conexion: "+ e);
        }finally {
            System.out.println("Cierre de conexion OK.");
        }
        try{
            ss.close();//Cierre conexion servidor
        }catch(Exception ex){
            System.out.println("Ocurrio un error al cerra conexion servidor: "+ ex);
        }finally {
            System.out.println("Cierre de conexion servdir OK.");
        }
    }
}
