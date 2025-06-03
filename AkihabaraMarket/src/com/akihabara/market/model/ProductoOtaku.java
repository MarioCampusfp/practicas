package com.akihabara.market.model;

public class ProductoOtaku {
	//atributos de la tabla productos de la base de datos
	String nombre;
	String categoria;
	double precio;
	int stock;
	
	//contructo vacio
	public ProductoOtaku() {
		
	}
	//contructo con todos los parametros
	public ProductoOtaku(String nombre, String categoria,double precio,int stock) {
		this.nombre=nombre;
		this.categoria=categoria;
		this.precio=precio;
		this.stock=stock;
	}
	
	//get y set del atributo de la tabla
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria=categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio=precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock=stock;
	}
	
	//metodo toString()
	@Override
	public String toString() {
		return "Nombre del producto: "+ nombre +
				", categoria del producto: "+ categoria+
				", precio del producto: "+ precio+
				", stock del producto: "+ stock;
	}
}
