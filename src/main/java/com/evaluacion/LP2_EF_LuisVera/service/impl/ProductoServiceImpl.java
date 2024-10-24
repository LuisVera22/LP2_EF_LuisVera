package com.evaluacion.LP2_EF_LuisVera.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.ProductoEntity;
import com.evaluacion.LP2_EF_LuisVera.repository.ProductoRepository;
import com.evaluacion.LP2_EF_LuisVera.service.ProductoService;
import com.evaluacion.LP2_EF_LuisVera.utils.Utilitarians;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	
	@Override
	public List<ProductoEntity> buscarTodosProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	@Override
	public ProductoEntity buscarProductoPorId(Integer id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id).get();
	}

	@Override
	public void crearProducto(ProductoEntity productoEntity, MultipartFile imagen) {
		// TODO Auto-generated method stub
		String nombreImagen = Utilitarians.saveImages(imagen);
		productoEntity.setUrlImagen(nombreImagen);

		productoRepository.save(productoEntity);
	}

	@Override
	public void actualizarProducto(Integer id,ProductoEntity productoEntity) {
		// TODO Auto-generated method stub
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado==null) {
			throw new RuntimeException("Producto no encontrado");
		}
		try {
			productoEncontrado.setCategoriaEntity(productoEntity.getCategoriaEntity());
			productoEncontrado.setNombre(productoEntity.getNombre());
			productoEncontrado.setPrecio(productoEntity.getPrecio());
			productoEncontrado.setStock(productoEntity.getStock());
			productoRepository.save(productoEncontrado);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Error al actualizar");
		}
	}

	@Override
	public void eliminarProducto(Integer id) {
		// TODO Auto-generated method stub
		ProductoEntity productoEncontrado = buscarProductoPorId(id);
		if (productoEncontrado==null) {
			throw new RuntimeException("Producto no encontrado");
		}
		productoRepository.delete(productoEncontrado);
	}

}
