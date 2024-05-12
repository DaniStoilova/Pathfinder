package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.RegisterBindingModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users/")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }



    @ModelAttribute
    public RegisterBindingModel registerBindingModel(){
        return new RegisterBindingModel();
    }

//    @ModelAttribute
//    public LoginBindingModel loginBindingModel(){
//        return new LoginBindingModel();
//    }



    @GetMapping("/register")
    public String register(){

        return "register";

    }

    @PostMapping("/register")
    public String registerConfirm(RegisterBindingModel registerBindingModel){
        userService.register(registerBindingModel);
        return "redirect:login";
    }



    @GetMapping("/login")
    public String login(){

        return "login";

    }

    @PostMapping("/login-error")
    public String confirmLogin(@ModelAttribute("username") String username,Model model){

        model.addAttribute("username",username);
        model.addAttribute("bad_credentials",true);



        return  "login";

    }

    @GetMapping("/profile")
    public String profile(Model model,@AuthenticationPrincipal User user){

//        model.addAttribute("user",
//                modelMapper.map(userService.findById(id),UserViewModel.class));

        UserViewModel userViewModel = this.userService.getLoggedUser(user.getUsername());

        model.addAttribute("user",userViewModel);

        return "profile";

    }

}
