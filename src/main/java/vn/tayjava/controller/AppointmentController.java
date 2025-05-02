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
import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.controller.request.AppointmentCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.model.Appointment;
import vn.tayjava.service.AppointmentService;

@RestController
@Slf4j(topic = "APPOINTMENT-CONTROLLER")
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /*
     * ────────────────────────────────────────────────────────────────────────
     * 1. ĐẶT LỊCH KHÁM
     * ────────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Đặt lịch khám", description = "Tạo mới một lịch hẹn cho bệnh nhân. Hệ thống tự kiểm tra "
            + "(doctorId, date, period) đã tồn tại chưa, đồng thời tự tạo "
            + "bệnh nhân mới nếu chưa có.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Nội dung đặt lịch", content = @Content(schema = @Schema(implementation = AppointmentCreationReq.class), examples = {
                    /* Ca sáng */
                    @ExampleObject(name = "Ca sáng", value = """
                            {
                              "doctorId": 2,
                              "appointmentDate": "2025-04-15",
                              "period": "MORNING",
                              "startHour": "07:00:00",
                              "endHour": "11:00:00",
                              "examinationType": "REGULAR",
                              "symptoms": "Ho, sốt nhẹ",
                              "patient": {
                                "fullName": "Nguyễn Văn A",
                                "dob": "1995-02-14",
                                "gender": 1,
                                "phone": "0901234567",
                                "province": "Hà Nội",
                                "district": "Hoàn Kiếm",
                                "ward": "Hàng Bông",
                                "street": "12 Hàng Bông",
                                "nationality": "Việt Nam"
                              }
                            }"""),
                    /* Ca trưa */
                    @ExampleObject(name = "Ca trưa", value = """
                            {
                              "doctorId": 2,
                              "appointmentDate": "2025-04-15",
                              "period": "LUNCH",
                              "startHour": "12:00:00",
                              "endHour": "13:00:00",
                              "examinationType": "REGULAR",
                              "symptoms": "Đau đầu nhẹ",
                              "patient": {
                                "fullName": "Trần Thị B",
                                "dob": "1988-07-09",
                                "gender": 0,
                                "phone": "0912345678",
                                "province": "Hồ Chí Minh",
                                "district": "Quận 1",
                                "ward": "Bến Nghé",
                                "street": "25 Lê Lợi",
                                "nationality": "Việt Nam"
                              }
                            }"""),
                    /* Ca chiều */
                    @ExampleObject(name = "Ca chiều", value = """
                            {
                              "doctorId": 2,
                              "appointmentDate": "2025-04-15",
                              "period": "AFTERNOON",
                              "startHour": "13:00:00",
                              "endHour": "17:00:00",
                              "examinationType": "VIP",
                              "symptoms": "Khám sức khoẻ tổng quát",
                              "patient": {
                                "fullName": "Lê Văn C",
                                "dob": "1975-10-21",
                                "gender": 1,
                                "phone": "0987654321",
                                "province": "Đà Nẵng",
                                "district": "Hải Châu",
                                "ward": "Thạch Thang",
                                "street": "88 Trần Phú",
                                "nationality": "Việt Nam"
                              }
                            }""")
            })))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Đặt lịch thành công")
    })
    @PostMapping
    public ResponseData<?> createAppointment(@Valid @RequestBody AppointmentCreationReq request) {
        log.info("Request create appointment: {}", request);
        Appointment appointment = appointmentService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Đặt lịch khám thành công", appointment);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 2. CẬP NHẬT TRẠNG THÁI
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Cập nhật trạng thái lịch khám", description = "Thay đổi trạng thái một lịch hẹn (PENDING, CONFIRMED, COMPLETED, CANCELLED).")
    @PatchMapping("/{id}")
    public ResponseData<?> updateAppointmentStatus(
            @PathVariable Long id,
            @Parameter(description = "Trạng thái mới", example = "CONFIRMED") @RequestParam AppointmentStatus status) {

        log.info("Request update appointment status: {} -> {}", id, status);
        Appointment updated = appointmentService.updateAppointmentStatus(id, status);
        return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật trạng thái thành công", updated);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 3. LẤY LỊCH THEO BỆNH NHÂN
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Danh sách lịch hẹn của một bệnh nhân", description = "Trả về các lịch hẹn của bệnh nhân theo ID, sắp xếp giảm dần theo ngày.")
    @GetMapping("/patient/{patientId}")
    public ResponseData<?> getAppointmentsByPatient(
            @PathVariable Long patientId,
            @Parameter(description = "Trang", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10") @RequestParam(defaultValue = "10") int size) {

        log.info("Request get appointments by patient id: {}", patientId);
        PageResponse<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId, page, size);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Lấy tất cả lịch hẹn của bệnh nhân thành công", appointments);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 4. LẤY TOÀN BỘ LỊCH
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Danh sách toàn bộ lịch hẹn", description = "Phân trang danh sách lịch, sắp xếp giảm dần theo ngày hẹn.")
    @GetMapping
    public ResponseData<?> getAllAppointments(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        log.info("Request get all appointments page={}, size={}", page, size);
        PageResponse<Appointment> appointments = appointmentService.getAllAppointments(page, size);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy tất cả lịch hẹn thành công", appointments);
    }
}
