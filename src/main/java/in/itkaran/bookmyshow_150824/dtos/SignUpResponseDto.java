package in.itkaran.bookmyshow_150824.dtos;

import in.itkaran.bookmyshow_150824.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private User user;
    private ResponseStatus responseStatus;
}