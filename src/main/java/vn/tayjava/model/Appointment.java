package vn.tayjava.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.common.enumerate.ExaminationType;
import vn.tayjava.common.enumerate.PeriodEnum;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends AbstractEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    // @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "examination_type", nullable = false)
    private ExaminationType examinationType;

    @Temporal(TemporalType.DATE)
    @Column(name = "appointment_date", nullable = false)
    private Date appointmentDate;

    @Enumerated(EnumType.STRING)
    // @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "period", nullable = false)
    private PeriodEnum period;

    private LocalTime startHour;

    private LocalTime endHour;

    // Optional denormalized data
    @Column(name = "specialty_id")
    private Long specialtyId;

    @Column(name = "specialty_name")
    private String specialtyName;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "patient_name")
    private String patientName;

    @Enumerated(EnumType.STRING)
    // @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status = AppointmentStatus.PENDING;

    @Column(name = "symptoms", columnDefinition = "TEXT")
    private String symptoms;
}
