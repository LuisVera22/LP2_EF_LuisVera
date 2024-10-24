package com.evaluacion.LP2_EF_LuisVera.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evaluacion.LP2_EF_LuisVera.model.entity.CategoriaEntity;
import com.evaluacion.LP2_EF_LuisVera.repository.CategoriaRepository;
import com.evaluacion.LP2_EF_LuisVera.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaEntity> obtenerTodasCategorias() {
        // TODO Auto-generated method stub
        return categoriaRepository.findAll();
    }
    
}
