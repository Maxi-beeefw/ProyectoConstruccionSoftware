package Modelo;
import static Modelo.Conexion.getConnection;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Pc
 */
public class ConsultasLogin {




   public boolean validarUsuario(String usuario, String clave) throws SQLException {
        CallableStatement ps = null;
        Connection con = getConnection();
    boolean valido = false;

    try {

        ps = con.prepareCall("{ call VALIDAR_USUARIO(?,?,?) }");
        ps.setString(1, usuario);
        ps.setString(2, clave);
        ps.registerOutParameter(3, Types.NUMERIC);
        ps.execute();
        int resultado = ps.getInt(3);

        if (resultado == 1) {
            valido = true;
        }
    } finally {
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }

    return valido;
}

}