package com.evaluacion.LP2_EF_LuisVera.model.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserEntity {

    @Id
	@Column(name = "email", nullable = false, length = 60)
	private String email;
	
	@Column(name = "name", nullable = false)
	private String name;

    @Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "password", nullable = false)
	private String password;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate date;
	
	@Column(name = "url_imagen")
	private String urlImagen;
}