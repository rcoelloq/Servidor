package conexion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VerificarIngreso
{
    public String validarLogin(String usu, String cla)
    {
        String resp = "";
        int idUser  = 0;
        String login="";
        String labelConexion = "CON";
        usu=usu.substring(2, usu.length());
        System.out.println("usuario: " + usu );
        System.out.println("clave: " + cla );
        
        String SSQL = "SELECT * FROM usuario WHERE login='" + usu + 
                "' AND clave='" + cla + "' "
                +     "AND estado = 'A'; ";
        Conexion cn = null;
        try
        {
            cn = new Conexion();
            cn.getConnection();
            PreparedStatement ps = cn.getConnection().prepareStatement(SSQL);
            ResultSet rs = ps.executeQuery(SSQL);
            if(rs.next())
            {
                idUser = rs.getInt("idusuario");
                login  = rs.getString("login");
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
            idUser = 0;
        }
        finally
        {
            cn.desconectar();
        }
        
        //CON|10@ilobato
        resp = labelConexion +"|"+ idUser + "@" +login;
        
        return resp;
    }
}