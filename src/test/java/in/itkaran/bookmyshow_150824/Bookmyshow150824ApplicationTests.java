package in.itkaran.bookmyshow_150824;

import in.itkaran.bookmyshow_150824.controllers.BookingController;
import in.itkaran.bookmyshow_150824.controllers.UserController;
import in.itkaran.bookmyshow_150824.dtos.SignUpRequestDto;
import in.itkaran.bookmyshow_150824.dtos.SignUpResponseDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieRequestDto;
import in.itkaran.bookmyshow_150824.dtos.BookMovieResponseDto;
import in.itkaran.bookmyshow_150824.models.*;
import in.itkaran.bookmyshow_150824.repositories.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class Bookmyshow150824ApplicationTests {

    @Autowired
    UserController userController;

    @Autowired
    BookingController bookingController;

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
    void testBookMovie() {
        BookMovieRequestDto bookingMovieRequestDto = new BookMovieRequestDto();
        bookingMovieRequestDto.setUserId(2L);
        bookingMovieRequestDto.setShowId(2L);
        bookingMovieRequestDto.setShowSeatIds(List.of(41L, 42L));
        BookMovieResponseDto bookMovieResponseDto = bookingController.bookMovie(bookingMovieRequestDto);
        System.out.println(bookMovieResponseDto.getResponseStatus());
        System.out.println(bookMovieResponseDto.getBooking());
    }

    @Test
    void testAddCity() {
        City city = new City();
        city.setName("Pune");
        cityRepository.save(city);

        City city2 = new City();
        city2.setName("Bangalore");
        cityRepository.save(city2);
    }

    @Test
    void testAddScreen() {
        Screen screen = new Screen();
        screen.setName("Screen 1");
        screenRepository.save(screen);

        Screen screen2 = new Screen();
        screen2.setName("Screen 2");
        screenRepository.save(screen2);
    }

    @Test
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
    void testAddMovie() {
        Movie movie = new Movie();
        movie.setName("The Shawshank Redemption");
        movieRepository.save(movie);

        Movie movie2 = new Movie();
        movie2.setName("The Matrix");
        movieRepository.save(movie2);
    }

    @Test
    void testAddShow() {
        Show show = new Show();
        show.setMovie(movieRepository.findById(1L).get());
        show.setFeatures(List.of(Feature.THREE_D, Feature.DOBLY_ATMOS));
        String startTimeStr = "2021-08-24 15:00:00";
        String endTimeStr = "2021-08-24 17:30:00";
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
        show.setScreen(screenRepository.findById(1L).get());
        showRepository.save(show);

        Show show2 = new Show();
        show2.setMovie(movieRepository.findById(2L).get());
        show2.setFeatures(List.of(Feature.TWO_D));
        String startTimeStr2 = "2021-08-24 10:10:00";
        String endTimeStr2 = "2021-08-24 12:30:00";
        Date startTime2 = null;
        Date endTime2 = null;
        try {
            startTime2 = formatter.parse(startTimeStr2);
            System.out.println("Converted Date: " + startTime2);
            endTime2 = formatter.parse(endTimeStr2);
            System.out.println("Converted Date: " + endTime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        show2.setStartTime(startTime2);
        show2.setEndTime(endTime2);
        show2.setScreen(screenRepository.findById(2L).get());
        showRepository.save(show2);
    }

    @Test
    void testAddSeatType() {
        SeatType seatType = new SeatType();
        seatType.setName("Gold");
        seatTypeRepository.save(seatType);

        SeatType seatType2 = new SeatType();
        seatType2.setName("Silver");
        seatTypeRepository.save(seatType2);
    }

    @Test
    void testAddActor() {
        Actor actor = new Actor();
        actor.setName("Morgan Freeman");
        actorRepository.save(actor);

        Actor actor2 = new Actor();
        actor2.setName("Keanu Reeves");
        actorRepository.save(actor2);
    }

    @Test
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
    void testAddShowSeat() {
        List<Seat> seats = seatRepository.findAll();
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(showRepository.findById(1L).get());
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }
        // show 2
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(showRepository.findById(2L).get());
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeatRepository.save(showSeat);
        }
    }

    @Test
    void testAddShowSeatType() {
        ShowSeatType showSeatType = new ShowSeatType();
        showSeatType.setShow(showRepository.findById(1L).get());
        showSeatType.setSeatType(seatTypeRepository.findById(1L).get());
        showSeatType.setPrice(500);
        showSeatTypeRepository.save(showSeatType);

        ShowSeatType showSeatType2 = new ShowSeatType();
        showSeatType2.setShow(showRepository.findById(1L).get());
        showSeatType2.setSeatType(seatTypeRepository.findById(2L).get());
        showSeatType2.setPrice(300);
        showSeatTypeRepository.save(showSeatType2);
    }

}