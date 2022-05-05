package org.example.postoffice.rest;

import org.example.departure.Departure;
import org.example.departure.rest.DeparturePayload;
import org.example.postoffice.PostOffice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/postoffice")
public final class PostOfficeRestController {

    private List<PostOffice> list = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<PostOffice>> getDeparturesList() {
        return ResponseEntity.ok(list);
    }

    @GetMapping("{postOfficeId}")
    public ResponseEntity<PostOffice> getPostOffice(@PathVariable UUID postOfficeId) {
        try {
            List<PostOffice> toReturn = list.stream().filter(x -> x.getId().equals(postOfficeId)).toList();
            return ResponseEntity.ok(toReturn.get(0));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            "application/vnd.example.todo_payload+json"})
    public ResponseEntity<PostOffice> createOffice(@RequestBody PostOffice payload) {
        var postOffice = new PostOffice(payload.getName(), payload.getCityName());
        list.add(postOffice);
        return ResponseEntity.ok(postOffice);
    }

    @PutMapping(path = "{postOfficeId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            "application/vnd.example.todo_payload+json"
    })
    public ResponseEntity<Void> modifyOffice(@PathVariable UUID postOfficeId, @RequestBody PostOfficePayload payload) {
        var postOffice = new PostOffice(payload.getName(), payload.getCityName());
        Stream<PostOffice> stream = list.stream();
        List<PostOffice> toUpdate = stream.filter(x -> x.getId().equals(postOfficeId)).toList();
        stream.close();
        if (toUpdate.size() > 0) {
            list.remove(toUpdate.get(0));
            list.add(postOffice);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{postOfficeId}")
    public ResponseEntity<Void> deleteOfice(@PathVariable UUID postOfficeId) {
        for (PostOffice postOffice : list) {
            if (postOffice.getId().equals(postOfficeId)) {
                list.remove(postOffice);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();

    }


}