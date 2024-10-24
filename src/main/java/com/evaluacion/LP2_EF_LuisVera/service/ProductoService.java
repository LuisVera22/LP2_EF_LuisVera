package com.evaluacion.LP2_EF_LuisVera.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.ProductoEntity;


public interface ProductoService {
	List<ProductoEntity>buscarTodosProductos();
	ProductoEntity buscarProductoPorId(Integer id);
	void crearProducto(ProductoEntity productoEntity, MultipartFile imagen);
	void actualizarProducto(Integer id,ProductoEntity productoEntity);
	void eliminarProducto(Integer id);
}
