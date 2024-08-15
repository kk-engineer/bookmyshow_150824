package in.itkaran.bookmyshow_150824.controllers;

import in.itkaran.bookmyshow_150824.dtos.BookMovieRequestDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieResponseDto;
import in.itkaran.bookmyshow_150824.dtos.ResponseStatus;
import in.itkaran.bookmyshow_150824.models.Booking;
import in.itkaran.bookmyshow_150824.services.BookingService;
import org.springframework.stereotype.Controller;


@Controller
public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto) {
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try {
            Booking booking = bookingService.bookMovie(
                    requestDto.getUserId(),
                    requestDto.getShowId(),
                    requestDto.getShowSeatIds()
            );

            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
