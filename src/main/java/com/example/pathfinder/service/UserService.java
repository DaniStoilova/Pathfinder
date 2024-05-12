package com.example.pathfinder.service;

import com.example.pathfinder.model.binding.RegisterBindingModel;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.view.UserViewModel;

import java.util.Optional;


public interface UserService {
    void register(RegisterBindingModel registerBindingModel);
    User findById(Long id);

    UserViewModel getLoggedUser(String username);
}
