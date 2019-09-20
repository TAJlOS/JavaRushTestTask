package com.space.repository;

import com.space.model.Ship;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
    @Query(value = "SELECT * FROM ship WHERE LOWER(ship.name) LIKE LOWER(:name) AND LOWER(ship.planet) LIKE LOWER(:planet) AND ship.shipType LIKE :shipType "+
            "AND ship.prodDate > :after AND ship.prodDate < :before AND (ship.isUsed = :isUsed1 OR ship.isUsed = :isUsed2) "+
            "AND ship.speed >= :minSpeed AND ship.speed <= :maxSpeed "+
            "AND ship.crewSize >= :minCrewSize AND ship.crewSize <= :maxCrewSize "+
            "AND ship.rating >= :minRating AND ship.rating <= :maxRating ", nativeQuery = true)
    List<Ship> findAllShips(@Param(value = "name") String name,
                            @Param(value = "planet") String planet,
                            @Param(value = "shipType") String shipType,
                            @Param(value = "after") String after,
                            @Param(value = "before") String before,
                            @Param(value = "isUsed1") Boolean isUsed1,
                            @Param(value = "isUsed2") Boolean isUsed2,
                            @Param(value = "minSpeed") Double minSpeed,
                            @Param(value = "maxSpeed") Double maxSpeed,
                            @Param(value = "minCrewSize") Integer minCrewSize,
                            @Param(value = "maxCrewSize") Integer maxCrewSize,
                            @Param(value = "minRating") Double minRating,
                            @Param(value = "maxRating") Double maxRating,
                            Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM ship WHERE LOWER(ship.name) LIKE LOWER(:name) AND LOWER(ship.planet) LIKE LOWER(:planet) AND ship.shipType LIKE :shipType "+
            "AND ship.prodDate > :after AND ship.prodDate < :before AND (ship.isUsed = :isUsed1 OR ship.isUsed = :isUsed2) "+
            "AND ship.speed >= :minSpeed AND ship.speed <= :maxSpeed "+
            "AND ship.crewSize >= :minCrewSize AND ship.crewSize <= :maxCrewSize "+
            "AND ship.rating >= :minRating AND ship.rating <= :maxRating ", nativeQuery = true)
    Integer getShipsCount(@Param(value = "name") String name,
                            @Param(value = "planet") String planet,
                            @Param(value = "shipType") String shipType,
                            @Param(value = "after") String after,
                            @Param(value = "before") String before,
                            @Param(value = "isUsed1") Boolean isUsed1,
                            @Param(value = "isUsed2") Boolean isUsed2,
                            @Param(value = "minSpeed") Double minSpeed,
                            @Param(value = "maxSpeed") Double maxSpeed,
                            @Param(value = "minCrewSize") Integer minCrewSize,
                            @Param(value = "maxCrewSize") Integer maxCrewSize,
                            @Param(value = "minRating") Double minRating,
                            @Param(value = "maxRating") Double maxRating);




}
