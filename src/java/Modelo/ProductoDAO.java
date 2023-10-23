package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;           
    ResultSet rs;
    int r;
    
    //OPERACIONES CRUD
    public List listar() {
        String sql = "select * from producto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pr = new Producto();
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));                
                pr.setEstado(rs.getString(5));                
                lista.add(pr);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
    
    public int agregar(Producto pr) {
        String sql = "INSERT INTO producto(Nombres, Precio, Stock, Estado) VALUE (?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);            
            ps.setString(1, pr.getNom());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return r;
    }
    
    public Producto listarId(int id){
        Producto pr = new Producto();
        String sql = "SELECT * FROM producto WHERE IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){                
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setStock(rs.getInt(4));                
                pr.setEstado(rs.getString(5));                
            }            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return pr;
    }
        
    public int actualizar(Producto pr) {
        String sql = "UPDATE producto SET Nombres=?, Precio=?, Stock=?, Estado=? WHERE IdProducto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);      
            ps.setString(1, pr.getNom());
            ps.setDouble(2, pr.getPrecio());
            ps.setInt(3, pr.getStock());
            ps.setString(4, pr.getEstado());            
            ps.setInt(5, pr.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return r;
    }
    
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE IdProducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
