package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion
{
    Connection cn;
    //private static final String driver = "com.mysql.jdbc.Driver";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    //private static final String url = "jdbc:mysql://localhost/chatsocket";
    private static final String url = "jdbc:mysql://localhost:3308/chatsocket?serverTimezone=UTC";
    
    public Conexion()
    {
        cn = null;
        try
        {
            Class.forName(driver);
            cn = DriverManager.getConnection(url,user,password);
            if(cn != null)
            {
                System.out.println("Conexión exitosa base de datos");
            }
            else
            {
                System.out.println("Ocurrio un error al tratar de conectarse a la DB");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Retorna la conexión
    public Connection getConnection()
    {
        return cn;
    }
    
    // Desconecta de la base de datos
    public void desconectar()
    {
        cn = null;
        if(cn == null)
        {
            System.out.println("Conexión terminada base de datos ");
        }
    }
}