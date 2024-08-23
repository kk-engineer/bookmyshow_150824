package in.itkaran.bookmyshow_150824.services;

import in.itkaran.bookmyshow_150824.exceptions.ShowNotFoundException;
import in.itkaran.bookmyshow_150824.exceptions.UserNotFoundException;
import in.itkaran.bookmyshow_150824.models.*;
import in.itkaran.bookmyshow_150824.repositories.BookingRepository;
import in.itkaran.bookmyshow_150824.repositories.ShowRepository;
import in.itkaran.bookmyshow_150824.repositories.ShowSeatRepository;
import in.itkaran.bookmyshow_150824.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          PriceCalculatorService priceCalculatorService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculatorService = priceCalculatorService;
        this.bookingRepository = bookingRepository;
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds)
            throws UserNotFoundException, ShowNotFoundException {
        /*
        1. Get the user with the userId.
        2. Get the show with the showId.
        3. Get the list of show seats with the showSeatIds.
        4. Check if all the seats are available or not.
            a. If yes, proceed with the booking.
            b. If not, throw an exception.
        ----------TAKE A LOCK---------
        5. Change the seat status to BLOCKED.
        ----------RELEASE THE LOCK----------
        6. Create the booking and move to the payment page.
         */

        // 1. Get the user with the userId.
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " User with id" + userId + " doesn't exist");
            throw new UserNotFoundException("User with id" +
                    userId + " doesn't exist");
        }
        User user = optionalUser.get();

        // 2. Get the show with the showId.
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show with id: "
                    + showId + " doesn't exist");
        }
        Show show = optionalShow.get();

        // 3. Get the list of show seats with the showSeatIds.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // 4. Check if all the seats are available or not.
        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new RuntimeException("ShowSeat with id" +
                        showSeat.getId() + " is NOT available");
            }
        }

        // 5. Set showSeat status to BLOCKED
        for (ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeatRepository.save(showSeat);
        }

        //6. Create the booking and Move to the payment page.
        Booking booking = new Booking();
        booking.setBookingReference(
                RandomStringGenerationService.generateRandomAlphanumericString());
        booking.setUser(user);
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculatorService.calculatePrice(showSeats, show));

        //Save booking in the DB.
        bookingRepository.save(booking);
        return booking;
    }
}
