package com.akihabara.market.view;

import com.akihabara.market.dao.*;
import com.akihabara.market.model.*;

import java.util.Scanner;

public class InterfazConsola {
	//Menu principal
	public void mostrarmenu(){
		System.out.println("===Menu===");
		System.out.println("1. Mostrar Productos");
		System.out.println("2. Añadir Producto");
		System.out.println("3. Eliminar Producto");
		System.out.println("4. Modificar Producto");
		System.out.println("5. Buscar por Nombre");
		System.out.println("6. Buscar por Id");
		System.out.println("7. Buscar por Categoria");
		System.out.println("8. Salir del Programa");
	}
	//Ejecucion de la opcion del menu 
	public void selecionmenu() {
		ProductoDAO producto = new ProductoDAO();
		Scanner scanner = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("Escribe la opcion del menu que desees: ");
			opcion =Integer.parseInt(scanner.nextLine());
			
			switch(opcion) {
			//opcion para mostrar todos los productos
			case 1:
				System.out.println("=== Lista de Productos ===");
			    for (ProductoOtaku p : producto.obtenerTodosLosProductos()) {
			        System.out.println(p);
			    }
			    break;
			//opcion para la creacion de un producto
			case 2:
				System.out.println("Escribe el tipo de producto (ej. figura, camiseta): ");
			    String tipo = scanner.nextLine();
			    System.out.println("Escribe la franquicia (ej. Naruto, One Piece): ");
			    String franquicia = scanner.nextLine();

			    LlmService llm = new LlmService();
			    String nombreSugerido = llm.sugerirNombreProducto(tipo, franquicia);
			    System.out.println("Nombre sugerido por IA: " + nombreSugerido);

			    System.out.println("Escribe la categoria del producto: ");
			    String categoria = scanner.nextLine();
			    System.out.println("Escribe el precio del producto: ");
			    double precio = Double.parseDouble(scanner.nextLine());
			    System.out.println("Escribe el stock del producto: ");
			    int stock = Integer.parseInt(scanner.nextLine());

			    ProductoOtaku nuevoProducto = new ProductoOtaku(nombreSugerido, categoria, precio, stock);
			    producto.agregarProducto(nuevoProducto);
			    break;
			//opcion para eliminar un producto
			case 3:
				int id;
				System.out.println("Escribe el id del Producto que vayas a eliminar: ");
				id = Integer.parseInt(scanner.nextLine());
				producto.eliminarProducto(id);
				break;
			//opcion para actualizar un producto
			case 4:
				String actualizado_nombre;
				System.out.println("escribe el nombre del producto: ");
				actualizado_nombre = scanner.nextLine();
				String actualizado_categoria;
				System.out.println("escribe la categoria del producto: ");
				actualizado_categoria = scanner.nextLine();
				double actualizado_precio;
				System.out.println("escribe el precio del producto: ");
				actualizado_precio = Double.parseDouble(scanner.nextLine());
				int actualizado_stock;
				System.out.println("escribe el stock del producto: ");
				actualizado_stock = Integer.parseInt(scanner.nextLine());
				ProductoOtaku producto2 = new ProductoOtaku(actualizado_nombre, actualizado_categoria, actualizado_precio, actualizado_stock);
				producto.actualizarProducto(producto2);
				break;
			//opcion para buscar un producto por nombre
			case 5:
				String buscar_nombre;
			    System.out.println("escribe el nombre del producto que quieres buscar: ");
			    buscar_nombre = scanner.nextLine();
			    var resultados = producto.buscarProductosPorNombre(buscar_nombre);

			    if (resultados.isEmpty()) {
			        System.out.println("No se encontraron productos con ese nombre.");
			    } else {
			        System.out.println("=== Resultados de la búsqueda ===");
			        for (ProductoOtaku p : resultados) {
			            System.out.println(p);
			        }
			    }
			    break;
			//opcion para buscar un producto por su id
			case 6:
				int buscar_id;
			    System.out.println("Escribe la ID del producto que quieres buscar: ");
			    buscar_id = Integer.parseInt(scanner.nextLine());

			    ProductoOtaku encontrado = producto.obtenerProductoPorId(buscar_id);
			    if (encontrado != null) {
			        System.out.println("=== Producto encontrado ===");
			        System.out.println(encontrado);
			    } else {
			        System.out.println("No se encontró ningún producto con esa ID.");
			    }
				break;
			//opcion para buscar multiples productos por su categoria
			case 7:
				String buscar_categoria;
			    System.out.println("Escribe la categoría del producto que quieres buscar: ");
			    buscar_categoria = scanner.nextLine();

			    var listaPorCategoria = producto.buscarProductoPorCategoria(buscar_categoria);
			    if (listaPorCategoria.isEmpty()) {
			        System.out.println("No se encontraron productos en esa categoría.");
			    } else {
			        System.out.println("=== Productos en la categoría '" + buscar_categoria + "' ===");
			        for (ProductoOtaku p : listaPorCategoria) {
			            System.out.println(p);
			        }
			    }
				break;
			//opcion para salir de la aplicacion
			case 8:
				System.out.println("Saliendo de la aplicación...");
				break;
			//opcion por defecto
			case 9:
			default:
				 System.out.println("Opción inválida. Intente de nuevo.");
			}
		}while (opcion != 0);
		scanner.close();
	}
	
}
