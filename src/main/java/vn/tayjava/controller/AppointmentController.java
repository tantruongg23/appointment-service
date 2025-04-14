package vn.tayjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.controller.request.AppointmentCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.controller.response.ResponseError;
import vn.tayjava.model.Appointment;
import vn.tayjava.service.AppointmentService;

@RestController
@Slf4j(topic = "APPOINTMENT-CONTROLLER")
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping()
    public ResponseData<?> createAppointment(@RequestBody AppointmentCreationReq request) {
        log.info("Request create appointment: {}", request);
        // not return error message because Security
        try {
            Appointment appointment = appointmentService.create(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Đặt lịch khám thành công", appointment);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Đặt lịch khám thất bại");
        }
    }

    @PatchMapping("/{id}")
    public ResponseData<?> updateAppointmentStatus(@PathVariable Long id,
            @RequestParam AppointmentStatus status) {
        log.info("Request update appointment status: {}", id, status);
        try {
            Appointment updated = appointmentService.updateAppointmentStatus(id, status);
            return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật trạng thái thành công", updated);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Cập nhật trạng thái thất bại");
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseData<?> getAppointmentsByPatient(@PathVariable Long patientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Request get appointments by patient id: {}", patientId);

        try {
            PageResponse<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId, page,
                    size);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy tất cả lịch hẹn của bệnh nhân thành công",
                    appointments);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy tất cả lịch hẹn của bệnh nhân thất bại");
        }
    }

    @GetMapping
    public ResponseData<?> getAllAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Request get all appointments by page: {}", page, size);

        try {
            PageResponse<Appointment> appointments = appointmentService.getAllAppointments(page,
                    size);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy tất cả lịch hẹn thành công", appointments);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Lấy tất cả lịch hẹn thất bại");
        }
    }

}
