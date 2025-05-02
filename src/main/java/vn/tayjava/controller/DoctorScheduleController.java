package vn.tayjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.controller.request.DoctorScheduleCreationReq;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.model.DoctorSchedule;
import vn.tayjava.service.DoctorScheduleService;

@RestController
@RequestMapping("/api/v1/doctor-schedules")
@Slf4j(topic = "DOCTOR-SCHEDULE-CONTROLLER")
@RequiredArgsConstructor
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 1. CREATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Tạo lịch khám", description = "Tạo mới một lịch khám cho bác sĩ. "
            + "Mỗi (doctorId, date, period) chỉ được phép tồn tại một lịch.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Thông tin lịch khám cần tạo", content = @Content(schema = @Schema(implementation = DoctorScheduleCreationReq.class), examples = {
                    @ExampleObject(name = "Ca sáng", value = """
                            {
                              "doctorId": 2,
                              "date": "2025-04-17",
                              "period": "MORNING",
                              "startHour": "07:00:00",
                              "endHour": "11:00:00"
                            }"""),
                    @ExampleObject(name = "Ca trưa", value = """
                            {
                              "doctorId": 2,
                              "date": "2025-04-17",
                              "period": "LUNCH",
                              "startHour": "12:00:00",
                              "endHour": "13:00:00"
                            }"""),
                    @ExampleObject(name = "Ca chiều", value = """
                            {
                              "doctorId": 2,
                              "date": "2025-04-17",
                              "period": "AFTERNOON",
                              "startHour": "13:00:00",
                              "endHour": "17:00:00"
                            }""")
            })))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tạo lịch khám thành công")
    })
    @PostMapping
    public ResponseData<?> createDoctorSchedule(@Valid @RequestBody DoctorScheduleCreationReq request) {
        log.info("Request create a doctor schedule: {}", request);
        DoctorSchedule doctorSchedule = doctorScheduleService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo lịch khám thành công", doctorSchedule);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 2. UPDATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Cập nhật lịch khám", description = "Cập nhật một lịch khám cho bác sĩ.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cập nhật lịch thành công")
    })
    @PutMapping("/{id}")
    public ResponseData<?> updateSchedule(@PathVariable Long id,
            @Valid @RequestBody DoctorScheduleCreationReq request) {
        DoctorSchedule updated = doctorScheduleService.updateSchedule(id, request);
        return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật lịch thành công", updated);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 3. GET ALL (PAGING)
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Lấy danh sách lịch khám (paging)", description = "Trả về danh sách lịch khám, sắp xếp giảm dần theo ngày.")
    @GetMapping
    public ResponseData<?> getAllSchedules(
            @Parameter(description = "Số trang (bắt đầu từ 1)", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10") @RequestParam(defaultValue = "10") int size) {

        log.info("Get all doctor schedules {} {}", page, size);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách lịch khám thành công",
                doctorScheduleService.getAllSchedules(page, size));
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 4. GET ALL BY DOCTOR
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Lấy lịch khám theo bác sĩ (paging)", description = "Trả về danh sách lịch của một bác sĩ theo ID, sắp xếp giảm dần theo ngày.")
    @GetMapping("/doctor/{id}")
    public ResponseData<?> getAllSchedulesByDoctorId(
            @PathVariable long id,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Get all doctor schedules by doctor id {} {} {}", id, page, size);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách lịch khám thành công",
                doctorScheduleService.getAllSchedulesByDoctorId(id, page, size));
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 5. GET BY ID
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Lấy chi tiết lịch khám", description = "Trả về chi tiết lịch khám theo ID.")
    @GetMapping("/{id}")
    public ResponseData<?> getScheduleById(@PathVariable Long id) {
        DoctorSchedule schedule = doctorScheduleService.getScheduleById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy lịch thành công", schedule);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 6. DELETE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Xoá lịch khám", description = "Xoá lịch khám khỏi hệ thống theo ID.")
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteSchedule(@PathVariable Long id) {
        doctorScheduleService.deleteSchedule(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Xoá lịch khám thành công", null);
    }
}
