package org.jesuitasrioja.productos.persistencia.services;

import java.util.Optional;

import org.jesuitasrioja.productos.modelo.producto.Producto;
import org.jesuitasrioja.productos.persistencia.repositories.ProductoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseService<Producto, String, ProductoRepository> {

	//modo manual que se utiliza cuando los métodos del repository son suficientes.
	public void cambiarNombre(String id, String nombre) {
		Optional<Producto> pOptional = this.repositorio.findById(id);
		if(pOptional.isPresent()) {
			Producto p = pOptional.get();
			p.setNombre(nombre);
			this.repositorio.save(p);
		}
	}
	
	public Producto encontrarPorNombre(String nombre) {
		return this.repositorio.findByName(nombre);
	}
	
	
	public Page<Producto> rangoPrecio(Double min, Double max, Pageable p){
		return this.repositorio.findByPrecioBetween(min, max, p);
	}
}
