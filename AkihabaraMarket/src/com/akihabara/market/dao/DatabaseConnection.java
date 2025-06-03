package com.akihabara.market.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//variables
	static final String DB_URL="jdbc:mysql://localhost:3306/kihabara_db";
	static final String USUARIO = "userAkihabara";
    static final String CLAVE = "curso";
    
    //propiedad conexion
    private Connection conexion;
    
    //contucto de conexion
    public DatabaseConnection() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		System.out.println("Se ha cargado en memoria el driver de MySQL.");
         
    		conexion = DriverManager.getConnection(DB_URL, USUARIO, CLAVE);
    		System.out.println("Se ha establecido con éxito la conexión a la base de datos.");
    	} catch (ClassNotFoundException e) {
    		System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
    	} catch (SQLException e) {
    		System.out.println("Error al conectar con la base de datos: " + e.getMessage());
    	}
    }
    
    //metodos
    public Connection getConexion() {
    	return conexion;
    }
    public void cerrarConexion() {
    	if (conexion != null) {
    		try {
    			if (!conexion.isClosed()) {
    				conexion.close();
    				System.out.println("Se ha cerrado la conexion con la base de datos");
    			}	
    		}catch(SQLException e) {
    			 System.out.println("Error al cerrar la conexión: " + e.getMessage());
    		}
    	}else {
            System.out.println("La conexión es nula, no se pudo cerrar.");
        }
    }
}
