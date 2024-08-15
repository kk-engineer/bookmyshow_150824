package in.itkaran.bookmyshow_150824.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class City extends BaseModel {
    private String name;
//    private List<Theatre> theatres;
}
