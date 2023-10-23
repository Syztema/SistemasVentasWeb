package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public String GenerarSerie() {
        String numeroserie = "";
        String sql = "SELECT max(NumeroSerie) FROM ventas";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                numeroserie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return numeroserie;
    }

}
