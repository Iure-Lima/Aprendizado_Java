package com.api.parkingcontrol.services;

import com.api.parkingcontrol.DTOs.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository repository;

    public ParkingSpotService(ParkingSpotRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<Object> saveParkingSpot(ParkingSpotDTO parkingSpotDTO){
        if(existsByLicensePlateCar(parkingSpotDTO.licensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }

        if(existsByParkingSpotNumber(parkingSpotDTO.licensePlateCar())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if(existsByApartmentAndBlock(parkingSpotDTO.apartament(), parkingSpotDTO.block())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered for this apartment/block!");
        }

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setRegistracionDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(parkingSpotModel));
    }

    private boolean existsByApartmentAndBlock(String apartament, String block) {
        return repository.existsByApartamentAndBlock(apartament,block);
    }

    private boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return repository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    private boolean existsByLicensePlateCar(String licensePlateCar) {
        return repository.existsByLicensePlateCar(licensePlateCar);
    }


    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpot(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(pageable));
    }

    public ResponseEntity<Object> getByID(UUID id) {
        Optional<ParkingSpotModel> parkingSpotModel = repository.findById(id);

        if (parkingSpotModel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel.get());
    }

    @Transactional
    public ResponseEntity<Object> deleteParkingSpot(UUID id) {
        Optional<ParkingSpotModel> parkingSpotModel = repository.findById(id);

        if (parkingSpotModel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");

        repository.delete(parkingSpotModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot deleted successfully");
    }

    public ResponseEntity<Object> updateParkingSpot(UUID id, ParkingSpotDTO parkingSpotDTO) {
        Optional<ParkingSpotModel> parkingSpotModel = repository.findById(id);

        if (parkingSpotModel.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");

        ParkingSpotModel parkingSpotModel1 = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO,parkingSpotModel1);
        parkingSpotModel1.setId(parkingSpotModel.get().getId());
        parkingSpotModel1.setRegistracionDate(parkingSpotModel.get().getRegistracionDate());
        parkingSpotModel1.setUpdate_at(new Date());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(parkingSpotModel1));

    }
}
