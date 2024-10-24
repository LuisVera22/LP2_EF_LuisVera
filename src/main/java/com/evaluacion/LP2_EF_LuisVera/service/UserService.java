package com.evaluacion.LP2_EF_LuisVera.service;

import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.UserEntity;

public interface UserService {
    void userRegister(UserEntity newUser, MultipartFile photo);
    boolean validateUser(UserEntity userEntity);
    UserEntity findUserByEmail(String email);
}
