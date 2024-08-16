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
        List<ShowSeatType> showSeatTypes =
                showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        for (ShowSeat showSeat : showSeats) {
            for (ShowSeatType showSeatType : showSeatTypes) {
                System.out.println(showSeat.getSeat().getSeatType());
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getPrice();
                    break;
                }
            }
        }

        return amount;
    }
}