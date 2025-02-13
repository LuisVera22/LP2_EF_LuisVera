package com.evaluacion.LP2_EF_LuisVera.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productoId;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "url_imagen")
	private String urlImagen;

	@ManyToOne
	@JoinColumn(name = "fk_categoria", nullable = false)
	private CategoriaEntity categoriaEntity;
}