package vn.tayjava.controller.request;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.tayjava.common.enumerate.PeriodEnum;

@Getter
@Setter // 👈 cần thiết để Spring có thể set giá trị
@ToString
public class DoctorScheduleCreationReq implements Serializable {
    // @NotNull(message = "Vui lòng nhập đúng ID bác sĩ")
    private long doctorId;
    // @NotNull(message = "Vui lòng nhập ngày đúng định dạng")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    // @NotNull(message = "Vui lòng chọn khung giờ khám")
    private PeriodEnum period;

    private LocalTime startHour;

    private LocalTime endHour;
}
