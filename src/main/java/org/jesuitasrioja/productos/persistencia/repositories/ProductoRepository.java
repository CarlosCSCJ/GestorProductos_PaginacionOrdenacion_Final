package org.jesuitasrioja.productos.persistencia.repositories;

import org.jesuitasrioja.productos.modelo.producto.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String> {
	
	@Query(value = "select u from Producto p where p.name = :firstname"	, nativeQuery = true)
	public Producto findByName(@Param("firstname") String firstname);
	
	public Page<Producto> findByNombre(String nombre, Pageable pageable);
	
	public Page<Producto> findByPrecioBetween(Double min, Double max, Pageable pageable);

}
