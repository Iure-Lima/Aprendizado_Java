package com.exemplo.tdd.model;

import com.exemplo.tdd.repository.BookingRepository;
import com.exemplo.tdd.service.BookingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
class BookingServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration{
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @BeforeEach
    void setUp(){
      bookingService = new BookingService();
      bookingRepository = Mockito.mock(BookingRepository.class);

        LocalDate checkIn = LocalDate.parse("2020-11-10");
        LocalDate checkOut = LocalDate.parse("2020-11-20");
        BookModel bookModel= new BookModel("1", "Iure", checkIn, checkOut,2);


        Mockito.when(bookingRepository.findByReserveName(bookModel.getReserveName())).thenReturn(Optional.of(bookModel));
    }

    @DisplayName("Validando o numero de dias")
    @Test
    public void bookingTestServiceDaysCalculador(){
        String name = "Iure";
        int days = bookingService.daysCalculatorWithDatabase(name);
        System.out.println(days);
        Assertions.assertEquals(days, 10);
    }
}