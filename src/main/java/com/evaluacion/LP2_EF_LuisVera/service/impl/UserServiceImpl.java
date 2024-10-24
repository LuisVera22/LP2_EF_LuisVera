package com.evaluacion.LP2_EF_LuisVera.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.UserEntity;
import com.evaluacion.LP2_EF_LuisVera.repository.UserRepository;
import com.evaluacion.LP2_EF_LuisVera.service.UserService;
import com.evaluacion.LP2_EF_LuisVera.utils.Utilitarians;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void userRegister(UserEntity newUser, MultipartFile photo) {
        // TODO Auto-generated method stub
        String namePhoto = Utilitarians.saveImages(photo);
        newUser.setUrlImagen(namePhoto);

        String passwordHash = Utilitarians.extractHash(newUser.getPassword());
        newUser.setPassword(passwordHash);

        userRepository.save(newUser);
    }

    @Override
    public boolean validateUser(UserEntity userEntity) {
        // TODO Auto-generated method stub
        UserEntity userFound = userRepository.findByEmail(userEntity.getEmail());

        if(userFound==null){
            return false;
        }

        if(!Utilitarians.checkPassword(userEntity.getPassword(),userFound.getPassword())){
            return false;
        }

        return true;
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        // TODO Auto-generated method stub
        return userRepository.findByEmail(email);
    }
    
}
