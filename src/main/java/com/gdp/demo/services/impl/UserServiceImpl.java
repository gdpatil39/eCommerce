package com.gdp.demo.services.impl;

import com.gdp.demo.dtos.Userdtos;
import com.gdp.demo.entities.User;
import com.gdp.demo.repositories.UserRepository;
import com.gdp.demo.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper mapper;
    

    @Override
    public Userdtos createUser(Userdtos userdtos) {
        
    	//generate unique id in string format 
    	String userId=UUID.randomUUID().toString();
    	userdtos.setUserId(userId);
    	
    	User user=dtoToEntity(userdtos);
         User saveUser = userRepository.save(user);

        Userdtos newDto=entityToDto(saveUser);




        return newDto;
    }



    @Override
    public Userdtos updateUser(Userdtos userdtos, String userId) {
        
    	User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found with given id "));
    	
    	user.setAbout(userdtos.getAbout());
    	user.setName(userdtos.getName());
    	user.setGender(userdtos.getGender());
    	user.setPassword(userdtos.getPassword());
    	user.setImageName(userdtos.getImageName());
    	
    	User updateUser = userRepository.save(user);
    	Userdtos updateDto = entityToDto(updateUser);
    	
    	
    	
    	return updateDto;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<Userdtos> getAllUser() {
        return null;
    }

    @Override
    public Userdtos getUserById(String userId) {
    	User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found with given id "));
        return entityToDto(user);
    }

    @Override
    public Userdtos getUserByEmail(String email) {
       
    	User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found with given email id and password"));
    	
    	return entityToDto(user);
    }

    @Override
    public List<Userdtos> searchUser(String keyword) {
        
    	List<User> nameContaining = userRepository.findByNameContaining(keyword);
    	
    	List<Userdtos> dtoNameContaining = nameContaining.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
    	
    	return dtoNameContaining;
    }

    private Userdtos entityToDto(User user) {
		
    	
    	/*
		 * return Userdtos.builder() .userId(user.getUserId()) .name(user.getName())
		 * .email(user.getEmail()) .password(user.getPassword())
		 * .gender(user.getGender()) .about(user.getAbout())
		 * .imageName(user.getImageName()) .build();
		 */
    	
    	
    	
    	return mapper.map(user, Userdtos.class);
         
    }

    private User dtoToEntity(Userdtos userDto) {


//    	  return User.builder()
//    	            .userId(userDto.getUserId())
//    	            .name(userDto.getName())
//    	            .email(userDto.getEmail())
//    	            .password(userDto.getPassword())
//    	            .gender(userDto.getGender())
//    	            .about(userDto.getAbout())
//    	            .imageName(userDto.getImageName())
//    	            .build();
        return mapper.map(userDto, User.class);
    }
}
