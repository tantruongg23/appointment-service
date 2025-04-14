package vn.tayjava.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tayjava.common.enumerate.PeriodEnum;
import vn.tayjava.model.DoctorSchedule;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    boolean existsByDoctorIdAndDateAndPeriod(long doctorId, Date date, PeriodEnum period);

    Page<DoctorSchedule> findByDoctorId(long doctorId, Pageable pageable);

}