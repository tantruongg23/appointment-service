package vn.tayjava.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorCreationReq {

    @NotBlank
    private String fullName;

    /** Id của chuyên khoa mà bác sĩ thuộc về */
    @NotNull
    private Long specialtyId;
}
