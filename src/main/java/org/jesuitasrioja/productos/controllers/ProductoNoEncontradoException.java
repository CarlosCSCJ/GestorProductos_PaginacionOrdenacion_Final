package org.jesuitasrioja.productos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductoNoEncontradoException extends RuntimeException {

	public ProductoNoEncontradoException(String identificadorProducto) {
		super("product with "+identificadorProducto +" could not be retrieved");
	}
	
}
