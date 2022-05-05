package org.example.departure.rest;

import org.example.departure.Departure;
import org.example.postoffice.PostOffice;
import org.example.postoffice.rest.PostOfficePayload;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/departure")
public final class DepartureRestController {
    private List<Departure> list = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Departure>> getDeparturesList() {
        return ResponseEntity.ok(list);
    }

    @GetMapping("{departureId}")
    public ResponseEntity<Departure> getDeparture(@PathVariable UUID id) {
        try {
            List<Departure> toReturn = list.stream().filter(x -> x.getId().equals(id)).toList();
            return ResponseEntity.ok(toReturn.get(0));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            "application/vnd.example.todo_payload+json"})
    public ResponseEntity<Departure> createDeparture(@RequestBody DeparturePayload payload) {
        var departure = new Departure(payload.getDepartureDate(), payload.getType());
        list.add(departure);
        return ResponseEntity.ok(departure);
    }

    @PutMapping(path = "{departureId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            "application/vnd.example.todo_payload+json"
    })
    public ResponseEntity<Void> modifyDeparture(@PathVariable UUID departureId, @RequestBody DeparturePayload payload) {
        var departure = new Departure(payload.getDepartureDate(), payload.getType());
        Stream<Departure> stream = list.stream();
        List<Departure> toUpdate = stream.filter(x -> x.getId().equals(departureId)).toList();
        stream.close();
        if (toUpdate.size() > 0) {
            list.remove(toUpdate.get(0));
            list.add(departure);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{departureId}")
    public ResponseEntity<Void> deleteDeparture(@PathVariable UUID departureId) {
        for (Departure departure : list) {
            if (departure.getId().equals(departureId)) {
                list.remove(departure);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();

    }


}
