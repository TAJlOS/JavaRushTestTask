package com.space.service.impl;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipValidator;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ShipServiceHelper {

    public static String getShipName(String name){
        return name == null ? "%" : "%"+name+"%";
    }

    public static String getShipPlanet(String planet){
        return planet == null ? "%" : "%"+planet+"%";
    }

    public static String getShipType(ShipType shipType){
        return shipType == null ? "%" : shipType.name();
    }

    public static String getShipAfter(Long after){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return after == null ? Ship.MIN_PROD_DATE : simpleDateFormat.format(new Date(after));
    }

    public static String getShipBefore(Long before){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return before == null ? Ship.MAX_PROD_DATE : simpleDateFormat.format(new Date(before));
    }

    public static Double getShipMinSpeed(Double minSpeed){
        return minSpeed == null ? Ship.MIN_SPEED : minSpeed;
    }

    public static Double getShipMaxSpeed(Double maxSpeed){
        return maxSpeed == null ? Ship.MAX_SPEED : maxSpeed;
    }

    public static Integer getShipMinCrewSize(Integer minCrewSize){
        return minCrewSize == null ? Ship.MIN_CREW_SIZE : minCrewSize;
    }

    public static Integer getShipMaxCrewSize(Integer maxCrewSize){
        return maxCrewSize == null ? Ship.MAX_CREW_SIZE : maxCrewSize;
    }

    public static Double getShipMinRating(Double minRating){
        return minRating == null ? Ship.MIN_RATING : minRating;
    }

    public static Double getShipMaxRating(Double maxRating){
        return maxRating == null ? Ship.MAX_RATING : maxRating;
    }

    public static String getShipOrder(ShipOrder order){
        return order == null ? ShipOrder.ID.getFieldName() : order.getFieldName();
    }

    public static Ship change(Ship prevShip, Ship curShip) throws IllegalArgumentException {
        if(curShip.getName() != null){
            if(ShipValidator.checkName(curShip.getName())){
                prevShip.setName(curShip.getName());
            }else{
                throw new IllegalArgumentException("invalid ship.name");
            }
        }
        if(curShip.getPlanet() != null){
            if(ShipValidator.checkPlanet(curShip.getPlanet())){
                prevShip.setPlanet(curShip.getPlanet());
            }else{
                throw new IllegalArgumentException("invalid ship.planet");
            }
        }
        if(curShip.getShipType() != null){
            prevShip.setShipType(curShip.getShipType());
        }
        if(curShip.getCrewSize() != null){
            if(ShipValidator.checkCrewSize(curShip.getCrewSize())){
                prevShip.setCrewSize(curShip.getCrewSize());
            }else{
                throw new IllegalArgumentException("invalid ship.crewSize");
            }
        }
        if(curShip.getProdDate() != null){
            if(ShipValidator.checkProdDate(curShip.getProdDate())){
                prevShip.setProdDate(curShip.getProdDate());
            }else{
                throw new IllegalArgumentException("invalid ship.prodDate");
            }
        }
        if(curShip.getSpeed() != null){
            if(ShipValidator.checkSpeed(curShip.getSpeed())){
                prevShip.setSpeed(curShip.getSpeed());
            }else{
                throw new IllegalArgumentException("invalid ship.speed");
            }
        }
        if(curShip.getUsed() != null) prevShip.setUsed(curShip.getUsed());
        prevShip.updateRating();
        return prevShip;
    }

    public static String getUsed(Boolean isUsed) {
        return isUsed == null ? "%" : isUsed.toString();
    }
}
