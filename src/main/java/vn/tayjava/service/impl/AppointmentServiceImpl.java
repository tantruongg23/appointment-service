package vn.tayjava.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.controller.request.AppointmentCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.exception.InvalidDataException;
import vn.tayjava.model.Appointment;
import vn.tayjava.repository.AppointmentRepository;
import vn.tayjava.repository.DoctorScheduleRepository;
import vn.tayjava.service.AppointmentService;
import vn.tayjava.service.DoctorService;
import vn.tayjava.service.PatientService;

@Service
@Slf4j(topic = "APPOINTMENT-SERVICE")
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorScheduleRepository doctorScheduleRepository;

    private final PatientService patientService;

    private final DoctorService doctorService;

    @Override
    public Appointment create(AppointmentCreationReq request) {

        // check doctor schedule
        if (request == null) {
            throw new InvalidDataException("Dữ liệu không hợp lệ");
        }

        // check doctor & time
        if (!this.doctorScheduleRepository
                .existsByDoctorIdAndDateAndPeriod(
                        request.getDoctorId(),
                        request.getAppointmentDate(),
                        request.getPeriod())) {

            throw new InvalidDataException("Vui lòng chọn lịch hẹn khác");
        }

        // check patient
        if (request.getPatient() == null) {
            throw new InvalidDataException("Dữ liệu bệnh nhân không hợp lệ");
        }

        // call patient service
        long patientId = this.patientService.save(request.getPatient());

        // call doctor service
        long doctorId = this.doctorService.findById(request.getDoctorId());

        Appointment appointment = new Appointment();
        appointment.setExaminationType(request.getExaminationType());
        appointment.setDoctorId(doctorId);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setPeriod(request.getPeriod());
        appointment.setStartHour(request.getStartHour());
        appointment.setEndHour(request.getEndHour());
        appointment.setSymptoms(request.getSymptoms());
        appointment.setPatientId(patientId);
        appointment.setStatus(AppointmentStatus.PENDING);

        appointment = this.appointmentRepository.save(appointment);

        log.info("Appointment has saved successfully: {}", appointment);
        return appointment;
    }

    @Override
    @Transactional
    public Appointment updateAppointmentStatus(long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch hẹn"));

        appointment.setStatus(status);
        return appointmentRepository.save(appointment);
    }

    @Override
    public PageResponse<Appointment> getAppointmentsByPatientId(Long patientId, int pageNo, int pageSize) {
        int newPageNo = pageNo == 0 ? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(newPageNo, pageSize, Sort.by("appointmentDate").descending());
        //
        Page<Appointment> pageAppointments = this.appointmentRepository.findByPatientId(patientId, pageable);
        PageResponse<Appointment> result = PageResponse.<Appointment>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pageAppointments.getTotalPages())
                .totalElements(pageAppointments.getTotalElements())
                .items(pageAppointments.getContent())
                .build();

        return result;
    }

    @Override
    public PageResponse<Appointment> getAllAppointments(int pageNo, int pageSize) {
        int newPageNo = pageNo == 0 ? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(newPageNo, pageSize, Sort.by("appointmentDate").descending());

        Page<Appointment> pageAppointments = this.appointmentRepository.findAll(pageable);
        PageResponse<Appointment> result = PageResponse.<Appointment>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pageAppointments.getTotalPages())
                .totalElements(pageAppointments.getTotalElements())
                .items(pageAppointments.getContent())
                .build();

        return result;
    }

}
