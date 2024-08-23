package in.itkaran.bookmyshow_150824.services;

import in.itkaran.bookmyshow_150824.models.Show;
import in.itkaran.bookmyshow_150824.models.ShowSeat;
import in.itkaran.bookmyshow_150824.models.ShowSeatType;
import in.itkaran.bookmyshow_150824.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show) {
        // find all show seat types for the show and calculate the total price
        List<ShowSeatType> showSeatTypes =
                showSeatTypeRepository.findAllByShow(show);

        int amount = 0;
        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                // check if the booked show seat is of which seat type and get the price
                if (showSeat.getSeat().getSeatType().getName().equals(showSeatType.getSeatType().getName())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}