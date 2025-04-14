package vn.tayjava.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "specialties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Specialty extends AbstractEntity {

    @Column(name = "name", unique = true)
    private String name;

    private String code;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY)
    private List<Doctor> doctors;

}
