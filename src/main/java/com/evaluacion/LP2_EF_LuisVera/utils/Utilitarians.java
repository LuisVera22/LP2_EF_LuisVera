package com.evaluacion.LP2_EF_LuisVera.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

public class Utilitarians {

    public static String saveImages(MultipartFile image){
        try {
            Path pathDire = Paths.get("src/main/resources/static/img/images");
			if(!Files.exists(pathDire)) {
				Files.createDirectories(pathDire);
			}
			
			byte[] bytes = image.getBytes();
			Path path = Paths.get("src/main/resources/static/img/images/" + 
			image.getOriginalFilename());
			
			Files.write(path, bytes);
			return image.getOriginalFilename();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
			return null;
        }
    }

    public static String extractHash(String passwordTextoPlano) {
		return BCrypt.hashpw(passwordTextoPlano, BCrypt.gensalt());
	}
	
	public static boolean checkPassword(String passwordInput, String hashPassword) {
		return BCrypt.checkpw(passwordInput, hashPassword);
	}
}
