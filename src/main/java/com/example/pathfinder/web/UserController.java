package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.LoginBindingModel;
import com.example.pathfinder.model.binding.RegisterBindingModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
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
    public String login(Model model){
        if (!model.containsAttribute("loginBindingModel")){
            model.addAttribute("loginBindingModel",new LoginBindingModel());
        }

        return "login";

    }

    @PostMapping("/login")
    public String confirmLogin(LoginBindingModel loginBindingModel){


        boolean isLogin = userService.login(loginBindingModel);

        if (isLogin){
           return  "redirect:/";
       }

        return  "login";

//       return isLogin ? "redirect:/" : "login";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id,Model model){



        model.addAttribute("user",
                modelMapper.map(userService.findById(id),UserViewModel.class));

        return "profile";
    }






}
