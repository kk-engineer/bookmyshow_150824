package in.itkaran.bookmyshow_150824.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    @ManyToMany
    private List<Actor> actors;
}
