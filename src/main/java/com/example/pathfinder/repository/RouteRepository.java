package com.example.pathfinder.repository;


import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {

    List<Route> findAllByCategories_Name(CategoryNames categoryNames);
}
