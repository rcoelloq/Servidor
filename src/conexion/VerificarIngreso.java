package conexion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VerificarIngreso
{
    public int validarLogin(String usu, String cla)
    {
        int res = 0;
        usu=usu.substring(2, usu.length());
        System.out.println("usuario: " + usu );
        System.out.println("clave: " + cla );
        String SSQL = "SELECT * FROM usuario WHERE login='" + usu + "' AND clave='" + cla + "'";
        Conexion cn = null;
        try
        {
            cn = new Conexion();
            cn.getConnection();
            PreparedStatement ps = cn.getConnection().prepareStatement(SSQL);
            ResultSet rs = ps.executeQuery(SSQL);
            if(rs.next())
            {
                res = 1;
            }
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            cn.desconectar();
        }
        return res;
    }
}