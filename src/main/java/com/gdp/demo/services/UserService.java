package com.gdp.demo.services;

import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.Userdtos;
import com.gdp.demo.entities.User;

import java.util.List;

import org.springframework.stereotype.Service;

public interface UserService {
    //create
    Userdtos createUser(Userdtos userdtos);
    //update
    Userdtos updateUser(Userdtos userdtos ,String userId);

    //delete

    void deleteUser(String userId);


    //get all users
    PageableResponse<Userdtos> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single user by id
    Userdtos getUserById(String userId);

    //get single user by email
    Userdtos getUserByEmail(String email);


    //search user
    List<Userdtos> searchUser(String keyword);
    //other user specific functionality

}
