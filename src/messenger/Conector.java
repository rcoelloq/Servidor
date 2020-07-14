
package messenger;
import conexion.VerificarIngreso;
import java.net.*;
import java.io.*;

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
            this.salida.writeUTF(msg+"\n");
        }catch (IOException e){};
    }
    
    public void run()
    {
     String text="test";
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
            
             System.out.println(text);
             VServidor.jTextArea1.setText(VServidor.jTextArea1.getText()+"\n"+text);
             
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
            s.close(); //Cierre de conexion
        }catch(IOException e){};
        try{
            ss.close();//Cierre conexion servidor
        }catch(IOException e){};
    }
}
