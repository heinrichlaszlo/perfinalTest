package perfinalTest.perfinalTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "persons")
@Data
public class person implements Serializable {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<travel> itineraries;

    public person(String name) {
        this.name = name;
    }

    public person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
