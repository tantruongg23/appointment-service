package vn.tayjava.model;

import java.time.LocalTime;
import java.util.Date;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.tayjava.common.enumerate.PeriodEnum;

@Entity
@Table(name = "doctor_schedule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSchedule extends AbstractEntity {

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "date")
    private Date date;

    @Enumerated(EnumType.STRING)
    // @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private PeriodEnum period;

    private LocalTime startHour;
    private LocalTime endHour;

    @Column(name = "available", nullable = false)
    private boolean available = true;
}
