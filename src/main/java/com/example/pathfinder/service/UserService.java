package com.example.pathfinder.service;

import com.example.pathfinder.model.binding.LoginBindingModel;
import com.example.pathfinder.model.binding.RegisterBindingModel;
import com.example.pathfinder.model.entity.User;


public interface UserService {
    void register(RegisterBindingModel registerBindingModel);

    boolean login(LoginBindingModel loginBindingModel) ;

    void logout();


    User findById(Long id);
}
