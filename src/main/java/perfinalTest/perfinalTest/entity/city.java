package perfinalTest.perfinalTest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "citys")
@Data
public class city implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tipus")
    private String tipus;

    @Column(name = "km")
    private Integer km;

    // a szélességi és hosszúsági fok forrása : https://i-weather.com/
    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longlatitude")
    private String longlatitude;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<travel> itineraries;

    public city() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public city(Long id, String name, String tipus, Integer km, String latitude, String longlatitude) {
        this.id = id;
        this.name = name;
        this.tipus = tipus;
        this.km = km;
        this.latitude = latitude;
        this.longlatitude = longlatitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLonglatitude() {
        return longlatitude;
    }

    public void setLonglatitude(String longlatitude) {
        this.longlatitude = longlatitude;
    }

    @Override
    public String toString() {
        return "city{" +
                "name='" + name + '\'' +
                ", tipus='" + tipus + '\'' +
                ", km=" + km +
                ", latitude='" + latitude + '\'' +
                ", longlatitude='" + longlatitude + '\'' +
                '}';
    }
}
