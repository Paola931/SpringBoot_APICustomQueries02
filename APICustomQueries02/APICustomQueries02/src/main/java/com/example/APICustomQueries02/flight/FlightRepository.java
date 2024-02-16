package com.example.APICustomQueries02.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "SELECT * FROM Flight", nativeQuery = true)
    List<Flight> findAllFlights();

    @Query(value = "SELECT * FROM Flight WHERE status = :status1 OR status = :status2 ", nativeQuery = true)
    List<Flight> findByStatus(@Param("status1") String status1,@Param("status2") String status2);
}
