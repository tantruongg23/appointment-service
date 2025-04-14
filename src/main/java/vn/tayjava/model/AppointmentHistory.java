package vn.tayjava.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.tayjava.common.enumerate.AppointmentStatus;

@Entity
@Table(name = "appointment_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "old_status")
    private AppointmentStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "new_status", nullable = false)
    private AppointmentStatus newStatus;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    @Column(name = "note")
    private String note;

    @PrePersist
    public void prePersist() {
        this.changedAt = LocalDateTime.now();
    }
}
