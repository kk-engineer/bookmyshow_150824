package in.itkaran.bookmyshow_150824;

import in.itkaran.bookmyshow_150824.controllers.BookingController;
import in.itkaran.bookmyshow_150824.controllers.UserController;
import in.itkaran.bookmyshow_150824.dtos.BookMovieRequestDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieResponseDto;
import in.itkaran.bookmyshow_150824.dtos.SignUpRequestDto;
import in.itkaran.bookmyshow_150824.dtos.SignUpResponseDto;
import in.itkaran.bookmyshow_150824.models.*;
import in.itkaran.bookmyshow_150824.repositories.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateTestData {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    SeatTypeRepository seatTypeRepository;
    @Autowired
    ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    ActorRepository actorRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testAddUser() {
        User user = new User();
        user.setName("AK");
        user.setEmail("ak@x.com");
        user.setPassword(bCryptPasswordEncoder.encode("Scaler"));
        userRepository.save(user);
    }

    @Test
    @Order(1)
    void testAddCity() {
        City city = new City();
        city.setName("Pune");
        cityRepository.save(city);

        City city2 = new City();
        city2.setName("Bangalore");
        cityRepository.save(city2);
    }

    @Test
    @Order(2)
    void testAddScreen() {
        Screen screen = new Screen();
        screen.setName("Screen 1");
        screenRepository.save(screen);

        Screen screen2 = new Screen();
        screen2.setName("Screen 2");
        screenRepository.save(screen2);
    }

    @Test
    @Order(3)
    void testAddTheatre() {
        Theatre theatre = new Theatre();
        theatre.setName("Inox Amanora Mall");
        theatre.setCity(cityRepository.findById(1L).get());
        theatreRepository.save(theatre);

        Theatre theatre2 = new Theatre();
        theatre2.setName("Cinepolis Seasons Mall");
        theatre2.setCity(cityRepository.findById(1L).get());
        theatreRepository.save(theatre2);
    }

    @Test
    @Order(4)
    void testAddMovie() {
        Movie movie = new Movie();
        movie.setName("The Shawshank Redemption");
        movieRepository.save(movie);

        Movie movie2 = new Movie();
        movie2.setName("The Matrix");
        movieRepository.save(movie2);
    }

    @Test
    @Order(5)
    void testAddShow() {
        List<String> showStartTimes = List.of("2021-08-24 09:00:00", "2021-08-24 12:00:00", "2021-08-24 15:00:00",
                "2021-08-24 18:00:00", "2021-08-24 21:00:00");
        List<String> showEndTimes = List.of("2021-08-24 11:30:00", "2021-08-24 14:30:00", "2021-08-24 17:30:00",
                "2021-08-24 20:30:00", "2021-08-24 23:30:00");
        List<Feature> showFeatures = List.of(Feature.TWO_D, Feature.DOBLY_ATMOS);
        for (Movie movie : movieRepository.findAll()) {
            for (int i = 0; i < showStartTimes.size(); i++) {
                Show show = new Show();
                show.setMovie(movie);
                show.setFeatures(showFeatures);
                String startTimeStr = showStartTimes.get(i);
                String endTimeStr = showEndTimes.get(i);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startTime = null;
                Date endTime = null;
                try {
                    startTime = formatter.parse(startTimeStr);
                    System.out.println("Converted Date: " + startTime);
                    endTime = formatter.parse(endTimeStr);
                    System.out.println("Converted Date: " + endTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                show.setStartTime(startTime);
                show.setEndTime(endTime);
                if(i%2 == 0) {
                    show.setScreen(screenRepository.findById(1L).get());
                } else {
                    show.setScreen(screenRepository.findById(2L).get());
                }
                showRepository.save(show);
            }
        }
    }

    @Test
    @Order(6)
    void testAddSeatType() {
        SeatType seatType = new SeatType();
        seatType.setName("Gold");
        seatTypeRepository.save(seatType);

        SeatType seatType2 = new SeatType();
        seatType2.setName("Silver");
        seatTypeRepository.save(seatType2);
    }

    @Test
    @Order(7)
    void testAddActor() {
        Actor actor = new Actor();
        actor.setName("Morgan Freeman");
        actorRepository.save(actor);

        Actor actor2 = new Actor();
        actor2.setName("Keanu Reeves");
        actorRepository.save(actor2);
    }

    @Test
    @Order(8)
    void testAddSeat() {
        int row = 2;
        int col = 10;
        for (int i = 0; i < row; i++) {
            for (int j = 1; j <= col; j++) {
                Seat seat = new Seat();
                char rowChar = (char) (i + 'A');
                seat.setNumber("" + rowChar + j);
                seat.setRowVal(i);
                seat.setColVal(j);
                seat.setSeatType(seatTypeRepository.findById(1L).get());
                seatRepository.save(seat);
            }
        }
    }

    @Test
    @Order(9)
    void testAddShowSeat() {
        for (Show show: showRepository.findAll()) {
            for (Seat seat: seatRepository.findAll()) {
                ShowSeat showSeat = new ShowSeat();
                showSeat.setSeat(seat);
                showSeat.setShow(show);
                showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
                showSeatRepository.save(showSeat);
            }
        }
    }

    @Test
    @Order(10)
    void testAddShowSeatType() {
        for (Show show: showRepository.findAll()) {
            for (SeatType seatType: seatTypeRepository.findAll()) {
                ShowSeatType showSeatType = new ShowSeatType();
                showSeatType.setShow(show);
                showSeatType.setSeatType(seatType);
                switch (seatType.getName()) {
                    case "Gold":
                        showSeatType.setPrice(500);
                        break;
                    case "Silver":
                        showSeatType.setPrice(300);
                        break;
                }
                showSeatTypeRepository.save(showSeatType);
            }
        }
    }
}
