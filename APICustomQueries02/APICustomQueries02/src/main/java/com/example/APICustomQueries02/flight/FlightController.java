package com.example.APICustomQueries02.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/provision")
    public void flightsProvision(@RequestParam(defaultValue = "100") int num) {
        flightService.generateRandomFlights(num);
    }
    @GetMapping("/getAll")
    public List<Flight> getAllFlights(){
        return flightService.findAllFlights();
    }

    @GetMapping("/getAllSorted")
    public Page<Flight> getAllFlightsSorted(){
        return flightService.findAllFlightsSorted();
    }
    @GetMapping("/getOnTime")
    public List<Flight> getOnTimeFlights(){
        return flightService.findOnTimeFlights();
    }
    @GetMapping("/getByStatus")
    public List<Flight> findByStatus(@RequestParam String status1,@RequestParam String status2){
        return flightService.findByStatus(status1,status2);
    }
}
