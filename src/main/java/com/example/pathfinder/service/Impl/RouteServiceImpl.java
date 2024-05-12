package com.example.pathfinder.service.Impl;

import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.model.binding.AddRouteBindingModel;
import com.example.pathfinder.model.binding.RoutesModel;
import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.CategoryNames;
import com.example.pathfinder.model.view.RouteCategoryViewModel;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.util.UserCurrent;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    private final UserRepository userRepository;
    
    private final UserCurrent userCurrent;

    private ModelMapper modelMapper;

    private final CategoryService categoryService;

    private CategoryRepository categoryRepository;

    public RouteServiceImpl(RouteRepository routeRepository, UserRepository userRepository, UserCurrent userCurrent, ModelMapper modelMapper, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.userCurrent = userCurrent;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void addRoute(AddRouteBindingModel addRouteBindingModel) {
        Route route = modelMapper.map(addRouteBindingModel,Route.class);

        User user = userRepository.findByUsername(userCurrent.getUsername());


//        route.getCategories().clear();

//        Set<Category> categories = categoryRepository.findByName(addRouteBindingModel.getCategories());
//
//                route.addCategories(categories);

                route.setCategories(addRouteBindingModel
                        .getCategories()
                        .stream()
                        .map(c->categoryService.findByName(c))
                        .collect(Collectors.toSet()));

                route.setAuthor(user);




        routeRepository.save(route);

    }

    @Override
    public List<RouteViewModel> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(m->modelMapper.map(m,RouteViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public RouteDetailsViewModel getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow((null));

        RouteDetailsViewModel routeDetailsViewModel = modelMapper.map(route, RouteDetailsViewModel.class);
        routeDetailsViewModel.setAuthorName(route.getAuthor().getFullName());

        return routeDetailsViewModel;
    }

    @Override
    public List<RoutesModel> findAllRoutes() {
        return routeRepository
                .findAll()
                .stream()
                .map(route -> {
                    RoutesModel routesModel = modelMapper.map(route,RoutesModel.class);

                    if (route.getPictures().isEmpty()){
                        routesModel.setUrl("/images/pic4.jpg");
                    }else {
                        routesModel.setUrl(
                                route.getPictures().stream().findFirst().get().getUrl());
                    }


                    return routesModel;


                }).collect(Collectors.toList());


    }

    @Override
    public List<RouteCategoryViewModel> getAllByCategory(CategoryNames categoryName) {
        List<Route> routes = routeRepository.findAllByCategories_Name(categoryName);
        List<RouteCategoryViewModel> routeList =
                routes.stream()
                .map(route -> modelMapper.map(route, RouteCategoryViewModel.class))
                .collect(Collectors.toList());

        return routeList;
    }
}
