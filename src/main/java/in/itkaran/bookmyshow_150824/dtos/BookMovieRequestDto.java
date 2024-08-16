package in.itkaran.bookmyshow_150824.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> showSeatIds;

    public BookMovieRequestDto() {
    }

    public BookMovieRequestDto(Long userId, Long showId, List<Long> showSeatIds) {
        this.userId = userId;
        this.showId = showId;
        this.showSeatIds = showSeatIds;
    }
}
