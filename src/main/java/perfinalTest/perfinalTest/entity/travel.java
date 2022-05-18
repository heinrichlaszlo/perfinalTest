package perfinalTest.perfinalTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Date;


@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "travels")
@Data
public class travel implements Serializable {
    @Id
    @Column(name = "travel_id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long travelID;

    @Column(name = "travelDate")
    private Date travelDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "name")
    private person person;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private city city;

    public Long getTravelID() {
        return travelID;
    }

    public void setTravelID(Long travelID) {
        this.travelID = travelID;
    }
}
