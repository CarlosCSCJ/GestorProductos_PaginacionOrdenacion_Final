package org.jesuitasrioja.productos.controllers;

import java.util.Optional;

import org.jesuitasrioja.productos.modelo.producto.Producto;
import org.jesuitasrioja.productos.persistencia.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService ps;
	

	@GetMapping("/productos")
	public ResponseEntity<?> allProducts(@PageableDefault(size=10, page=0) Pageable pageable) {
		Page<Producto> pagina = ps.findAll(pageable);	
		
		return ResponseEntity.status(HttpStatus.OK).body(pagina);
	}
	
	
	@GetMapping("/productoPorPrecio")
	public ResponseEntity<?> allProducts(@RequestParam(name = "from") Double from, 
										 @RequestParam(name = "to") Double to, 
										 Pageable pageable) {
		
		System.out.println(from);
		System.out.println(to);
		
		Page<Producto> paginaProducto = ps.rangoPrecio(from, to, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(paginaProducto);
	}

	@GetMapping("/producto/{id}")
	public ResponseEntity<?> getProducto(@PathVariable String id) {
		Optional<Producto> productoOptional = ps.findById(id);
		if(productoOptional.isPresent()) {
			Producto producto = productoOptional.get();
			return ResponseEntity.status(HttpStatus.OK).body(producto);
		}else {
			throw new ProductoNoEncontradoException(id);
		}
	}

	@GetMapping("/producto")
	public Producto getProducto2(@RequestParam String id) {
		return ps.findById(id).get();
	}

	@PostMapping("/producto")
	public String postProducto(@RequestBody Producto nuevoProducto) {
		return ps.save(nuevoProducto).toString();
	}

	@PutMapping("/producto")
	public String putProducto(@RequestBody Producto editadoProducto) {
		return ps.edit(editadoProducto).toString();
	}

	@DeleteMapping("/producto/{id}")
	public String deleteProducto(@PathVariable String id) {
		ps.deleteById(id);
		return "OK";
	}

}
