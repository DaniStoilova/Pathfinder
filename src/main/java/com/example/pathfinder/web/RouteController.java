package com.example.pathfinder.web;

import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.binding.AddRouteBindingModel;
import com.example.pathfinder.model.enums.CategoryNames;
import com.example.pathfinder.model.enums.Level;
import com.example.pathfinder.model.view.RouteCategoryViewModel;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/routes")
public class RouteController {

    private ModelMapper modelMapper;
    private RouteService routeService;

    private UserService userService;

    public RouteController(ModelMapper modelMapper, RouteService routeService, UserService userService) {
        this.modelMapper = modelMapper;
        this.routeService = routeService;
        this.userService = userService;
    }


    @ModelAttribute
    public AddRouteBindingModel addRouteBindingModel(){
        return new AddRouteBindingModel();
    }


    @ModelAttribute("levels")
    public Level[] levels() {
        return Level.values();
    }

    @ModelAttribute("categories")
    public CategoryNames[] categories() {
        return CategoryNames.values();
    }

    @GetMapping("/add")
    public String add(){

        return "add-route";
    }
    @GetMapping()
    public String route(Model model){

        model.addAttribute("routes", routeService.findAllRoutes());

        return "routes";
    }

    @PostMapping("/add")
    public String addConfirm(AddRouteBindingModel addRouteBindingModel, @AuthenticationPrincipal User user){

        routeService.addRoute(addRouteBindingModel,user);

        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable("id") Long id,Model model) {
        RouteDetailsViewModel routes = routeService.getDetails(id);


        model.addAttribute("details", routes);

        return "route-details";
    }

    @GetMapping("/{categoryName}")
    public String  getAllByCategory(@PathVariable("categoryName") CategoryNames categoryName,Model model) {


        String view =
                switch (categoryName) {
                    case PEDESTRIAN -> "pedestrian";
                    case MOTORCYCLE -> "motorcycle";
                    case CAR -> "car";
                    case BICYCLE -> "bicycle";
                };

        List<RouteCategoryViewModel> category = routeService.getAllByCategory(categoryName);

        model.addAttribute("category",category);

        return view;
    }

}
