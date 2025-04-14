package vn.tayjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.controller.request.DoctorScheduleCreationReq;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.controller.response.ResponseError;
import vn.tayjava.model.DoctorSchedule;
import vn.tayjava.service.DoctorScheduleService;

@RestController
@RequestMapping("/doctor-schedules")
@Slf4j(topic = "DOCTOR-SCHEDULE-CONTROLLER")
@RequiredArgsConstructor
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    @PostMapping
    public ResponseData<?> createDoctorSchedule(@Valid @RequestBody DoctorScheduleCreationReq request) {
        log.info("Request create a doctor schedule: {}", request);
        try {
            DoctorSchedule doctorSchedule = this.doctorScheduleService.create(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo lịch khám thành công", doctorSchedule);
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Tạo lịch khám thất bại");
        }
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateSchedule(@PathVariable Long id,
            @Valid @RequestBody DoctorScheduleCreationReq request) {
        try {
            DoctorSchedule updated = doctorScheduleService.updateSchedule(id, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật lịch thành công", updated);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping
    public ResponseData<?> getAllSchedules(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("Get all doctor schedules {} {}", page, size);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách lịch khám thành công",
                    doctorScheduleService.getAllSchedules(page, size));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

    }

    @GetMapping("/doctor/{id}")
    public ResponseData<?> getAllSchedulesByDoctorId(
            @PathVariable long id,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        log.info("Get all doctor schedules by doctor id {} {} {}", id, page, size);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách lịch khám thành công",
                    doctorScheduleService.getAllSchedulesByDoctorId(id, page, size));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseData<?> getScheduleById(@PathVariable Long id) {
        try {
            DoctorSchedule schedule = doctorScheduleService.getScheduleById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy lịch thành công", schedule);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteSchedule(@PathVariable Long id) {
        doctorScheduleService.deleteSchedule(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Xóa lịch khám thành công", null);
    }

}
