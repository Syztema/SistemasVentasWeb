package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Empleado validar(Empleado item) {
        Empleado em = new Empleado();
        String sql = "SELECT * FROM empleado WHERE User=? AND Contrasena=?";

        try {
            System.out.println("Item: " + item.toString());
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, item.getUser());
            ps.setString(2, item.getContrasena());
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Entro en while");
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setContrasena(rs.getString("Contrasena"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Datos Empleado: " + em.toString());
        return em;
    }

    //OPERACIONES CRUD
    public List listar() {
        String sql = "select * from empleado";
        List<Empleado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                em.setContrasena(rs.getString(7));
                lista.add(em);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    public int agregar(Empleado em) {
        String sql = "INSERT INTO empleado(Dni, Nombres, Telefono, Estado, User, Contrasena) VALUE (?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setString(6, em.getContrasena());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return r;
    }

    public Empleado listarId(int id) {
        Empleado emp = new Empleado();
        String sql = "SELECT * FROM empleado WHERE IdEmpleado=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
                emp.setContrasena(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return emp;
    }

    public int actualizar(Empleado em) {
        String sql = "UPDATE empleado SET Dni=?, Nombres=?, Telefono=?, Estado=?, User=?, Contrasena=? WHERE IdEmpleado=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setString(6, em.getContrasena());
            ps.setInt(7, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return r;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE IdEmpleado=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
