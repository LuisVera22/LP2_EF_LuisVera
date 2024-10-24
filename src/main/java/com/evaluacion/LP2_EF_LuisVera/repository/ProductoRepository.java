package com.evaluacion.LP2_EF_LuisVera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.LP2_EF_LuisVera.model.entity.ProductoEntity;

@Repository
public interface ProductoRepository 
extends JpaRepository<ProductoEntity, Integer>{

}
