package com.example.APICustomQueries02.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    private FlightRepository repository;

    private String generateRandomString() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    private Status generateRandomStatus() {
        Status[] statuses = Status.values();
        int randomIndex = new Random().nextInt(statuses.length);
        return statuses[randomIndex];
    }

    public void generateRandomFlights(int num) {
        List<Flight> flightList = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= num; i++) {
            Flight randomFlight = new Flight();
            randomFlight.setDescription("Flight " + i);
            randomFlight.setFromAirport(generateRandomString());
            randomFlight.setToAirport(generateRandomString());
            randomFlight.setStatus(generateRandomStatus());
            flightList.add(randomFlight);
        }
        repository.saveAll(flightList);
    }

    public List<Flight> findAllFlights() {
        return repository.findAll();
    }
    public Page<Flight> findAllFlightsSorted() {
        Pageable pageSortAsc = PageRequest.of(0, 5, Sort.by("fromAirport").ascending());
        return repository.findAll(pageSortAsc);
    }

    public List<Flight> findOnTimeFlights() {
        List<Flight> allFlights = findAllFlights();
        List<Flight> onTimeFlights = new ArrayList<>();

        for (Flight f : allFlights) {
            if (f.getStatus() == Status.ONTIME) {
                onTimeFlights.add(f);
            }
        }
        return onTimeFlights;
    }
    public List<Flight> findByStatus(String status1, String status2){
        return repository.findByStatus(status1,status2);
    }
}
