package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.DTOs.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/parking-spot")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "open-api")
public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    @Operation(summary = "Salva o Parking Spot", method = "POST")
    public ResponseEntity<Object> saveParkingSpot(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO){
        return parkingSpotService.saveParkingSpot(parkingSpotDTO);
    }

    @GetMapping
    @Operation(summary = "Busca totos os Parking Spot", method = "GET")
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpot(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return parkingSpotService.getAllParkingSpot(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um Parking Spot pelo id", method = "GET")
    public ResponseEntity<Object> getByID(@PathVariable(value = "id") UUID id){
        return parkingSpotService.getByID(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um Parking Spot", method = "DELETE")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id){
        return parkingSpotService.deleteParkingSpot(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um Parking Spot", method = "PUT")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id, @RequestBody @Valid ParkingSpotDTO parkingSpotDTO){
        return parkingSpotService.updateParkingSpot(id, parkingSpotDTO);
    }

}
