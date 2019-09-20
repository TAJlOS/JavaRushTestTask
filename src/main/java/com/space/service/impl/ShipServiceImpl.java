package com.space.service.impl;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.ShipRepository;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public List<Ship> getShipsList(String name, String planet, ShipType shipType, Long after, Long before,
                                   Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                   Integer maxCrewSize, Double minRating, Double maxRating, ShipOrder order,
                                   Integer pageNumber, Integer pageSize) {

        Pageable pageable  = PageRequest.of(pageNumber, pageSize, Sort.by(ShipServiceHelper.getShipOrder(order)));

        return shipRepository.findAllShips(ShipServiceHelper.getShipName(name), ShipServiceHelper.getShipPlanet(planet),
                ShipServiceHelper.getShipType(shipType), ShipServiceHelper.getShipAfter(after), ShipServiceHelper.getShipBefore(before),
                isUsed == null ? true : isUsed, isUsed == null ? false : isUsed, ShipServiceHelper.getShipMinSpeed(minSpeed), ShipServiceHelper.getShipMaxSpeed(maxSpeed),
                ShipServiceHelper.getShipMinCrewSize(minCrewSize), ShipServiceHelper.getShipMaxCrewSize(maxCrewSize),
                ShipServiceHelper.getShipMinRating(minRating), ShipServiceHelper.getShipMaxRating(maxRating),
                pageable);
    }


    @Override
    public Integer getShipsCount(String name, String planet, ShipType shipType, Long after, Long before,
                                 Boolean isUsed, Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                 Integer maxCrewSize, Double minRating, Double maxRating) {

        return shipRepository.getShipsCount(ShipServiceHelper.getShipName(name), ShipServiceHelper.getShipPlanet(planet),
                ShipServiceHelper.getShipType(shipType), ShipServiceHelper.getShipAfter(after), ShipServiceHelper.getShipBefore(before),
                isUsed == null ? true : isUsed, isUsed == null ? false : isUsed, ShipServiceHelper.getShipMinSpeed(minSpeed), ShipServiceHelper.getShipMaxSpeed(maxSpeed),
                ShipServiceHelper.getShipMinCrewSize(minCrewSize), ShipServiceHelper.getShipMaxCrewSize(maxCrewSize),
                ShipServiceHelper.getShipMinRating(minRating), ShipServiceHelper.getShipMaxRating(maxRating));
    }

    @Override
    public Ship saveShip(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public Optional<Ship> findShipById(long i) {
        return shipRepository.findById(i);
    }

    @Override
    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }
}
