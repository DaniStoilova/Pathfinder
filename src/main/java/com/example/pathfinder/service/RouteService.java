package com.example.pathfinder.service;

import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteViewModel;
import com.example.pathfinder.model.binding.AddRouteBindingModel;
import com.example.pathfinder.model.binding.RoutesModel;
import com.example.pathfinder.model.enums.CategoryNames;
import com.example.pathfinder.model.view.RouteCategoryViewModel;

import java.util.List;


public interface RouteService {

    void addRoute(AddRouteBindingModel addRouteBindingModel);

    List<RouteViewModel> getAll();

    RouteDetailsViewModel getDetails(Long id);

    List<RoutesModel> findAllRoutes();

    List<RouteCategoryViewModel> getAllByCategory(CategoryNames categoryName);
}
