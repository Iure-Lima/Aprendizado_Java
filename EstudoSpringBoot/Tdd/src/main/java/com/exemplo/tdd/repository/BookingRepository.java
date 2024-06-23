package com.exemplo.tdd.repository;

import com.exemplo.tdd.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookModel, String> {

    Optional<BookModel> findByReserveName(String reserveName);
}
