package com.space.service;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface ShipService {

    List<Ship> getShipsList(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                            Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                            Double maxRating, ShipOrder order, Integer pageNumber, Integer pageSize);

    Integer getShipsCount(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                          Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize,
                          Double minRating, Double maxRating);

    Ship saveShip(Ship ship);

    Optional<Ship> findShipById(long i);

    void deleteShip(Long id);
}
