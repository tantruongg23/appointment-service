package vn.tayjava.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.tayjava.common.enumerate.GenderEnum;
import vn.tayjava.util.validator.PhoneNumber;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends AbstractEntity {
    @NotBlank(message = "Name must be filled, > 5 character")
    private String fullName;
    @PhoneNumber(message = "Phone is invalid format")
    private String phone;

    @NotNull(message = "Date of birth must not be empty")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @NotNull(message = "Gender must not be empty")
    private GenderEnum gender;
    @NotNull(message = "Nationality must not be empty")
    private String nationality;

    private long customerId;

    @NotBlank(message = "Province must not be empty")
    private String province;

    @NotBlank(message = "Province must not be empty")
    private String district;

    @NotBlank(message = "Ward must not be empty")
    private String ward;

    @NotBlank(message = "Street must not be empty")
    private String street;

}
