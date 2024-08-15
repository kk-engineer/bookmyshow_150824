package in.itkaran.bookmyshow_150824.controllers;

import in.itkaran.bookmyshow_150824.dtos.ResponseStatus;
import in.itkaran.bookmyshow_150824.dtos.SignUpRequestDto;
import in.itkaran.bookmyshow_150824.dtos.SignUpResponseDto;
import in.itkaran.bookmyshow_150824.exceptions.UserAlreadyExistsException;
import in.itkaran.bookmyshow_150824.models.User;
import in.itkaran.bookmyshow_150824.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try {
            User user = userService.signUp(requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword());

            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserAlreadyExistsException e) {
            System.out.println(e.getMessage());
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDto;
    }
}
