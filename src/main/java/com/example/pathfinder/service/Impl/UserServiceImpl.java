package com.example.pathfinder.service.Impl;

import com.example.pathfinder.model.binding.RegisterBindingModel;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.UserCurrent;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private UserCurrent userCurrent;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserCurrent userCurrent) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userCurrent = userCurrent;
    }


    @Override
    public void register(RegisterBindingModel registerBindingModel) {

        User user = modelMapper.map(registerBindingModel, User.class);

        user.setLevel(Level.BEGINNER);

        user.setPassword(passwordEncoder.encode(registerBindingModel.getPassword()));

        userRepository.save(user);


    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user,User.class)).orElse(null);
    }

    @Override
    public UserViewModel getLoggedUser(String username) {

        Optional<User> user = userRepository.findByUsername(username);


        return modelMapper.map(user,UserViewModel.class);
    }

}

