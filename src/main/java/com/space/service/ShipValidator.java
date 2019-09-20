package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class ShipValidator implements Validator{


    @Override
    public boolean supports(Class<?> aClass) {
        return Ship.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Ship ship = (Ship) obj;
        if(ship.getUsed() == null) ship.setUsed(false);
        if(!(checkName(ship.getName()) && checkPlanet(ship.getPlanet()) &&
                checkShipType(ship.getShipType()) && checkProdDate(ship.getProdDate()) &&
                checkSpeed(ship.getSpeed()) && checkCrewSize(ship.getCrewSize()))){
            errors.reject("errors");
        }
    }

    public static boolean checkName(String name){
        if(name == null || name.equals("")) return false;
        return name.length() <= 50;
    }

    public static boolean checkPlanet(String planet){
        if(planet == null || planet.equals("")) return false;
        return planet.length() <= 50;
    }

    public static boolean checkShipType(ShipType shipType){
        return shipType != null;
    }

    public static boolean checkSpeed(Double speed){
        return speed != null && speed >= Ship.MIN_SPEED && speed <= Ship.MAX_SPEED;
    }

    public static boolean checkCrewSize(Integer crewSize){
        return crewSize != null && crewSize >= Ship.MIN_CREW_SIZE && crewSize <= Ship.MAX_CREW_SIZE;
    }

    public static boolean checkProdDate(Date prodDate){
        return prodDate != null && getProdYear(prodDate) >= Ship.MIN_YEAR && getProdYear(prodDate) <= Ship.MAX_YEAR;
    }

    public static int getProdYear(Date date){
        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return Integer.parseInt(simpleDateFormat.format(date));
    }
}