package in.itkaran.bookmyshow_150824.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;

    public String toString() {
        return "User Name: " + name + "\n" +
                "Email: " + email;
    }
}
