package com.space.controller;


import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;
import com.space.service.ShipValidator;
import com.space.service.impl.ShipServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value = "/rest")
public class ShipController {

    @Autowired
    private ShipService shipService;

    public ShipController() {
    }

    @Autowired
    private ShipValidator shipValidator;


    @RequestMapping(value = "/ships", method = RequestMethod.GET)
    public ResponseEntity<List<Ship>> getShipsList(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String planet,
                                   @RequestParam(required = false) ShipType shipType,
                                   @RequestParam(required = false) Long after,
                                   @RequestParam(required = false) Long before,
                                   @RequestParam(required = false) Boolean isUsed,
                                   @RequestParam(required = false) Double minSpeed,
                                   @RequestParam(required = false) Double maxSpeed,
                                   @RequestParam(required = false) Integer minCrewSize,
                                   @RequestParam(required = false) Integer maxCrewSize,
                                   @RequestParam(required = false) Double minRating,
                                   @RequestParam(required = false) Double maxRating,
                                   @RequestParam(required = false) ShipOrder order,
                                   @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(required = false, defaultValue = "3") Integer pageSize){

        List<Ship> shipsList = shipService.getShipsList(name, planet, shipType, after,
                before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating,
                maxRating, order, pageNumber, pageSize);
        return new ResponseEntity<>(shipsList, HttpStatus.OK);
    }

    @RequestMapping(value = "/ships/count", method = RequestMethod.GET)
    public ResponseEntity<Integer> getShipsCount(@RequestParam(required = false) String name,
                               @RequestParam(required = false) String planet,
                               @RequestParam(required = false) ShipType shipType,
                               @RequestParam(required = false) Long after,
                               @RequestParam(required = false) Long before,
                               @RequestParam(required = false) Boolean isUsed,
                               @RequestParam(required = false) Double minSpeed,
                               @RequestParam(required = false) Double maxSpeed,
                               @RequestParam(required = false) Integer minCrewSize,
                               @RequestParam(required = false) Integer maxCrewSize,
                               @RequestParam(required = false) Double minRating,
                               @RequestParam(required = false) Double maxRating){

        Integer shipsCount = shipService.getShipsCount(name, planet, shipType, after,
                before, isUsed, minSpeed, maxSpeed, minCrewSize, maxCrewSize, minRating, maxRating);
        return new ResponseEntity<>(shipsCount, HttpStatus.OK);
    }


    @RequestMapping(value = "/ships", method = RequestMethod.POST)
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship, BindingResult result){
        shipValidator.validate(ship, result);
        if(result.hasErrors()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            if(ship.getRating() == null) {
                ship.updateRating();
            }
            Ship ship_in_table = shipService.saveShip(ship);
            return new ResponseEntity<>(ship_in_table, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ship> getShip(@PathVariable String id){
        if(id != null && !id.equals("")){
            try{
                long i = Long.parseLong(id);
                if(i <= 0){
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
                Optional<Ship> ship = shipService.findShipById(i);
                if(ship.isPresent()){
                    return new ResponseEntity<>(ship.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }catch (NumberFormatException e){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.POST)
    public ResponseEntity<Ship> updateShip(@PathVariable String id, @RequestBody Ship ship){
        ResponseEntity<Ship> entity = getShip(id);
        if(entity.getStatusCode() != HttpStatus.OK){
            return entity;
        }
        Ship prevShip = entity.getBody();
        if(ship.isEmpty()){
            return new ResponseEntity<>(prevShip, HttpStatus.OK);
        }
        try {
            Ship curShip = ShipServiceHelper.change(prevShip, ship);
            Ship ship_in_table = shipService.saveShip(curShip);
            return new ResponseEntity<>(ship_in_table, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/ships/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Ship> deleteShip(@PathVariable String id){
        ResponseEntity<Ship> entity = getShip(id);
        if(entity.getStatusCode() != HttpStatus.OK){
            return entity;
        }
        shipService.deleteShip(entity.getBody().getId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
