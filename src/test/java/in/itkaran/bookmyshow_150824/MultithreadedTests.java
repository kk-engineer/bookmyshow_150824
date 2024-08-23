package in.itkaran.bookmyshow_150824;

import in.itkaran.bookmyshow_150824.controllers.BookingController;
import in.itkaran.bookmyshow_150824.dtos.BookMovieRequestDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieResponseDto;
import lombok.Getter;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MultithreadedTests {
    // class to store the result of a thread
    @Getter
    static class ThreadResult {
        private final String threadName;
        private final BookMovieResponseDto response;

        public ThreadResult(String threadName, BookMovieResponseDto response) {
            this.threadName = threadName;
            this.response = response;
        }

    }

    @Autowired
    BookingController bookingController;

    @Test
    void contextLoads() {
    }

    @Test
    void testParallelSeatBooking() throws ExecutionException, InterruptedException {
        Long showId = 1L, userId = 1L;
        List<Long> bookingSeats = List.of(5L, 6L);
        int numberOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<ThreadResult>> futureBookings = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            Callable<ThreadResult> task = () -> {
                BookMovieResponseDto response =  bookingController.bookMovie(
                        new BookMovieRequestDto(
                        userId, showId, bookingSeats));
                String threadName = Thread.currentThread().getName();
                return new ThreadResult(threadName, response);
            };
            futureBookings.add(executorService.submit(task));
        }

        System.out.println("CLIENT - Booking details:");
        for (Future<ThreadResult> futureBooking : futureBookings) {
            ThreadResult result = futureBooking.get();
            System.out.println("Thread: " + result.getThreadName());
            System.out.println("Booking: " + result.getResponse().getBooking());
            System.out.println("Blocking Status: " + result.getResponse().getResponseStatus());
            System.out.println("#######################");
        }
    }
}
