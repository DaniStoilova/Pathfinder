package com.example.pathfinder.service.Impl;

import com.example.pathfinder.model.binding.LoginBindingModel;
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
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public boolean login(LoginBindingModel loginBindingModel){
        User user = userRepository.findByUsername(loginBindingModel.getUsername());

        if (user == null){
            throw new IllegalArgumentException("Username is not present");
        }

        boolean pass = passwordEncoder.matches(loginBindingModel.getPassword(), user.getPassword());

        if (!pass){
//            throw new IllegalArgumentException("Incorrect password");
        }
        userCurrent.setId(user.getId());
        userCurrent.setUsername(user.getUsername());
        userCurrent.setEmail(user.getEmail());

        return pass;
    }

    @Override
    public void logout() {

        userCurrent.setId(null);
        userCurrent.setUsername(null);
        userCurrent.setEmail(null);

    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user,User.class)).orElse(null);
    }
}

