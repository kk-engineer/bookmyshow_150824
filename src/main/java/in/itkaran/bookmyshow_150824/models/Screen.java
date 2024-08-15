package in.itkaran.bookmyshow_150824.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {
    private String name;
    @OneToMany
    private List<Seat> seats;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection  // This annotation is used to store a collection of basic data types
    private List<Feature> features;
}
