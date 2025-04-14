package vn.tayjava.controller.request;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import vn.tayjava.common.enumerate.ExaminationType;
import vn.tayjava.common.enumerate.PeriodEnum;

@Getter
@ToString
public class AppointmentCreationReq implements Serializable {

    @NotNull(message = "Chọn chuyên khoa")
    private long specialtyId;

    private String specialtyName;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Chọn dịch vụ khám")
    private ExaminationType examinationType;

    @NotNull(message = "Chọn bác sĩ")
    private long doctorId;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Chọn ngày muốn khám")
    private Date appointmentDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Chọn khung giờ muốn khám")
    private PeriodEnum period;

    private LocalTime startHour;
    private LocalTime endHour;

    @NotBlank(message = "Nhập vấn đề sức khỏe cần khám")
    private String symptoms;

    @NotNull(message = "Điền thông tin bệnh nhân")
    private PatientCreationReq patient;

}
