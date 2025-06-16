package com.akihabara.market.dao;
import com.akihabara.market.model.*;
import java.sql.*;
import java.util.*;

public class ProductoDAO {
	//conexion con la base de datos
	 private  DatabaseConnection db;
	 
	 public ProductoDAO(){
		 db = new DatabaseConnection();
	 }
	 
	 //metodos publicos productoDAO
	 public void agregarProducto(ProductoOtaku producto) {
		 String sql = "INSERT INTO producto (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
		 try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
	            stmt.setString(1, producto.getNombre());
	            stmt.setString(2, producto.getCategoria());
	            stmt.setDouble(3, producto.getPrecio());
	            stmt.setInt(4, producto.getStock());

	            stmt.executeUpdate();
	            System.out.println("Producto agregado correctamente.");
	        } catch (SQLException e) {
	            System.out.println("Error al agregar producto: " + e.getMessage());
	        }
	 }
	 public ProductoOtaku obtenerProductoPorId(int id) {
		 String sql = "Select * FROM producto WHERE id=?";
		 try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return new ProductoOtaku(
	                            rs.getString("nombre"),
	                            rs.getString("categoria"),
	                            rs.getDouble("precio"),
	                            rs.getInt("stock")
	                    );
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obtener producto por ID: " + e.getMessage());
	        }
	        return null;
	 }
	 public List<ProductoOtaku> obtenerTodosLosProductos() {
		 List<ProductoOtaku> lista = new ArrayList<>();
	        String sql = "SELECT * FROM producto";
	        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                ProductoOtaku producto = new ProductoOtaku(
	                        rs.getString("nombre"),
	                        rs.getString("categoria"),
	                        rs.getDouble("precio"),
	                        rs.getInt("stock")
	                );
	                lista.add(producto);
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al obtener todos los productos: " + e.getMessage());
	        }
	        return lista; 
	 }
	 public boolean actualizarProducto(ProductoOtaku producto) {
		    String sql = "UPDATE producto SET nombre = ?, categoria = ?, precio = ?, stock = ? WHERE id = ?";
		    try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
		        stmt.setString(1, producto.getNombre());
		        stmt.setString(2, producto.getCategoria());
		        stmt.setDouble(3, producto.getPrecio());
		        stmt.setInt(4, producto.getStock());
		        stmt.setInt(5, producto.getId()); // El ID va aquí, como quinto parámetro

		        int filas = stmt.executeUpdate();
		        return filas > 0;
		    } catch (SQLException e) {
		        System.out.println("Error al actualizar producto: " + e.getMessage());
		        return false;
		    }
		}

	 public boolean eliminarProducto(int id) {
		 String sql = "DELETE FROM producto WHERE id = ?";
		 try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            int filas = stmt.executeUpdate();
	            return filas > 0;
	        } catch (SQLException e) {
	            System.out.println("Error al eliminar producto: " + e.getMessage());
	            return false;
	        }
	 }
	 public List<ProductoOtaku> buscarProductosPorNombre(String nombre) {
		 List<ProductoOtaku> lista = new ArrayList<>();
	        String sql = "SELECT * FROM producto WHERE nombre LIKE ?";
	        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
	            stmt.setString(1, "%" + nombre + "%");
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    ProductoOtaku producto = new ProductoOtaku(
	                            rs.getString("nombre"),
	                            rs.getString("categoria"),
	                            rs.getDouble("precio"),
	                            rs.getInt("stock")
	                    );
	                    lista.add(producto);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al buscar producto por nombre: " + e.getMessage());
	        }
	        return lista;
	 }
	 public List<ProductoOtaku> buscarProductoPorCategoria(String categoria) {
		 List<ProductoOtaku> lista = new ArrayList<>();
	        String sql = "SELECT * FROM producto WHERE categoria = ?";
	        try (PreparedStatement stmt = db.getConexion().prepareStatement(sql)) {
	            stmt.setString(1, categoria);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    ProductoOtaku producto = new ProductoOtaku(
	                            rs.getString("nombre"),
	                            rs.getString("categoria"),
	                            rs.getDouble("precio"),
	                            rs.getInt("stock")
	                    );
	                    lista.add(producto);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al buscar producto por categoría: " + e.getMessage());
	        }
	        return lista;
	 }
	 
}
