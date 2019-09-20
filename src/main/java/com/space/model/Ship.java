package com.space.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ship")
public class Ship implements Serializable {

    public final static transient String MIN_PROD_DATE = "2800-01-01";
    public final static transient String MAX_PROD_DATE = "3020-01-01";
    public final static transient Double MIN_SPEED = 0.01;
    public final static transient Double MAX_SPEED = 0.99;
    public final static transient Integer MIN_CREW_SIZE = 1;
    public final static transient Integer MAX_CREW_SIZE = 9999;
    public final static transient Integer MIN_YEAR = 2800;
    public final static transient Integer MAX_YEAR = 3019;
    /* rating = 80*v*k/(y0 - y1 + 1)
        where:
        k = 1 if isUsed = false else k = 0.5
        y0 = 3019
        min rating reached if v = MAX_SPEED and k = 1 and y0 = y1
        max rating reached if v = MIN_SPEED and k = 0.5 and y1 = MIN_YEAR
     */

    public final static transient Double MIN_RATING = calculateRating(MIN_SPEED, true, MIN_YEAR);
    public final static transient Double MAX_RATING = calculateRating(MAX_SPEED, false, MAX_YEAR);

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "planet", length = 50)
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType;

    @Column(name = "prodDate")
    @Temporal(TemporalType.DATE)
    private Date prodDate;

    @Column(name = "isUsed")
    private Boolean isUsed;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "crewSize")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    public Ship(){
    }

    public void updateRating(){
        rating = calculateRating(speed, isUsed, getProdYear(prodDate));
    }

    private static double calculateRating(double speed, boolean isUsed, int y1){
        double k = isUsed ? 0.5 : 1.0;
        double rating =  80.0*speed*k/(Ship.MAX_YEAR - y1 + 1);
        return new BigDecimal(rating).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private static int getProdYear(Date date){
        String pattern = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return Integer.parseInt(simpleDateFormat.format(date));
    }


    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id) &&
                Objects.equals(name, ship.name) &&
                Objects.equals(planet, ship.planet) &&
                shipType == ship.shipType &&
                Objects.equals(prodDate, ship.prodDate) &&
                Objects.equals(isUsed, ship.isUsed) &&
                Objects.equals(speed, ship.speed) &&
                Objects.equals(crewSize, ship.crewSize) &&
                Objects.equals(rating, ship.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, planet, shipType, prodDate, isUsed, speed, crewSize, rating);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", prodDate=" + prodDate +
                ", isUsed=" + isUsed +
                ", speed=" + speed +
                ", crewSize=" + crewSize +
                ", rating=" + rating +
                '}';
    }

    public boolean isEmpty() {
        return id == null && name == null && planet == null && shipType == null
                && prodDate == null && isUsed == null && speed == null
                && crewSize == null && rating == null;
    }
}
