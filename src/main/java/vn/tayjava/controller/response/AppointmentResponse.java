package vn.tayjava.controller.response;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.common.enumerate.GenderEnum;

@Setter
@Getter
@Builder
public class AppointmentResponse {
    private long id;

    private String specialtyName;
    private long doctorId;
    private String doctorName;
    private String doctorTitle;

    private String symptoms;
    private AppointmentStatus status;

    @Getter
    @Setter
    @Builder
    public static class PatientDTO {
        private long id;

        private String fullName;
        private String phone;
        @Temporal(TemporalType.DATE)
        private Date dob;

        private GenderEnum gender;

        private String nationality;

        private long customerId;

        private String province;

        private String district;

        private String ward;

        private String street;
    }
}
