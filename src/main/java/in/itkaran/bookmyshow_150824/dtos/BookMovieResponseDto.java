package in.itkaran.bookmyshow_150824.dtos;

import in.itkaran.bookmyshow_150824.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}