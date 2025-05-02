package vn.tayjava.controller.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorResponse {
    private Long id;
    private String fullName;
    private Boolean available;
    private Long specialtyId;
}
