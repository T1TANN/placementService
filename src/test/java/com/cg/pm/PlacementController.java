package com.cg.pm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacementController {
    @Autowired
    private PlacementService service;

    // Retrieve all placements
    @GetMapping("/placements")
    public List<Placement> listAll() {
        return service.listAll();
    }

    // Retrieve placement by ID
    @GetMapping("/placements/{id}")
    public ResponseEntity<Placement> get(@PathVariable Long id) {
        try {
            Placement placement = service.get(id);
            return new ResponseEntity<Placement>(placement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Placement>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new placement
    @PostMapping("/placements")
    public void add(@RequestBody Placement placement) {
        service.save(placement);
    }

    // Update a placement
    @PutMapping("/placements/{id}")
    public ResponseEntity<?> update(@RequestBody Placement placement, @PathVariable Long id) {
        try {
            Placement existPlacement = service.get(id);
            service.save(existPlacement);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a placement
    @DeleteMapping("/placements/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Schedule a placement drive
    @PostMapping("/placements/schedule")
    public void schedule(@RequestBody Placement placement) {
        service.schedule(placement);
    }

    // Cancel a placement drive
    @PostMapping("/placements/cancel")
    public void cancel(@RequestBody Placement placement) {
        service.cancel(placement);
    }
}