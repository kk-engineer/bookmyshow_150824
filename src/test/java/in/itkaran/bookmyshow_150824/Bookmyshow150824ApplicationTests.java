package in.itkaran.bookmyshow_150824;

import in.itkaran.bookmyshow_150824.controllers.BookingController;
import in.itkaran.bookmyshow_150824.controllers.UserController;
import in.itkaran.bookmyshow_150824.dtos.SignUpRequestDto;
import in.itkaran.bookmyshow_150824.dtos.SignUpResponseDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieRequestDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieResponseDto;
import in.itkaran.bookmyshow_150824.models.*;
import in.itkaran.bookmyshow_150824.repositories.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Bookmyshow150824ApplicationTests {

    @Autowired
    UserController userController;
    @Autowired
    BookingController bookingController;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testUserSignUp() {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setName("KK");
        signUpRequestDto.setEmail("kk@x.com");
        signUpRequestDto.setPassword("123456");
        SignUpResponseDto responseDto = userController.signUp(signUpRequestDto);
        System.out.println(responseDto.getResponseStatus());
        //System.out.println(responseDto.getUser().getName());
        //System.out.println(responseDto.getUser().getEmail());
        //assert responseDto.getUser().getName().equals("Karan");
        //assert responseDto.getUser().getEmail().equals("kk@x.com");
    }

    @Test
    @Order(2)
    void testBookMovie() {
        BookMovieRequestDto bookingMovieRequestDto = new BookMovieRequestDto();
        bookingMovieRequestDto.setUserId(1L);
        bookingMovieRequestDto.setShowId(1L);
        bookingMovieRequestDto.setShowSeatIds(List.of(11L, 12L));
        BookMovieResponseDto bookMovieResponseDto = bookingController.bookMovie(bookingMovieRequestDto);
        System.out.println(bookMovieResponseDto.getResponseStatus());
        System.out.println(bookMovieResponseDto.getBooking());
    }
}