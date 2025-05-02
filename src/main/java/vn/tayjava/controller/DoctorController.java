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
import vn.tayjava.controller.request.DoctorCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.model.Doctor;
import vn.tayjava.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
@Slf4j(topic = "DOCTOR-CONTROLLER")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 1. CREATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Tạo mới bác sĩ", description = "Thêm bác sĩ vào hệ thống. `specialtyId` phải trỏ tới chuyên khoa đã có.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Thông tin bác sĩ cần tạo", content = @Content(schema = @Schema(implementation = DoctorCreationReq.class), examples = @ExampleObject(name = "Doctor sample", value = """
            {
              "fullName": "Bs. Phạm Hữu Tâm",
              "specialtyId": 3
            }"""))))
    @ApiResponse(responseCode = "201", description = "Tạo bác sĩ thành công")
    @PostMapping
    public ResponseData<?> createDoctor(@Valid @RequestBody DoctorCreationReq request) {
        log.info("Tạo bác sĩ: {}", request);
        Doctor doctor = doctorService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Tạo bác sĩ thành công", doctor);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 2. UPDATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Cập nhật bác sĩ", description = "Cập nhật thông tin bác sĩ theo ID.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Nội dung cập nhật", content = @Content(schema = @Schema(implementation = DoctorCreationReq.class), examples = @ExampleObject(name = "Doctor update sample", value = """
            {
              "fullName": "Bs. Phạm Hữu Tâm (Chỉnh sửa)",
              "specialtyId": 4
            }"""))))
    @ApiResponse(responseCode = "200", description = "Cập nhật bác sĩ thành công")
    @PutMapping("/{id}")
    public ResponseData<?> updateDoctor(@PathVariable Long id,
            @Valid @RequestBody DoctorCreationReq request) {
        Doctor updated = doctorService.updateDoctor(id, request);
        return new ResponseData<>(HttpStatus.OK.value(), "Cập nhật bác sĩ thành công", updated);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 3. THAY ĐỔI TRẠNG THÁI
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Thay đổi trạng thái làm việc", description = "Bật/tắt trạng thái `available` của bác sĩ.")
    @ApiResponse(responseCode = "200", description = "Thay đổi trạng thái thành công")
    @PatchMapping("/{id}/status")
    public ResponseData<?> changeStatus(@PathVariable Long id,
            @Parameter(description = "true = mở lịch, false = tạm ngừng", example = "false") @RequestParam boolean available) {
        Doctor doctor = doctorService.changeDoctorStatus(id, available);
        String msg = available ? "Mở lịch làm việc" : "Tạm ngừng lịch làm việc";
        return new ResponseData<>(HttpStatus.OK.value(), msg + " thành công", doctor);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 4. GET ALL (PAGING)
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Danh sách bác sĩ (paging)", description = "Phân trang danh sách bác sĩ, sắp xếp theo tên.")
    @ApiResponse(responseCode = "200", description = "Lấy danh sách bác sĩ thành công")
    @GetMapping
    public ResponseData<?> getAllDoctors(
            @Parameter(description = "Trang", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10") @RequestParam(defaultValue = "10") int size) {

        log.info("Lấy danh sách bác sĩ: page={}, size={}", page, size);
        PageResponse<Doctor> data = doctorService.getAllDoctors(page, size);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy danh sách bác sĩ thành công", data);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 5. GET BY ID
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Chi tiết bác sĩ", description = "Lấy thông tin bác sĩ theo ID.")
    @ApiResponse(responseCode = "200", description = "Lấy bác sĩ thành công")
    @GetMapping("/{id}")
    public ResponseData<?> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Lấy bác sĩ thành công", doctor);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 6. DELETE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Xoá bác sĩ", description = "Xoá bác sĩ khỏi hệ thống theo ID.")
    @ApiResponse(responseCode = "200", description = "Xoá bác sĩ thành công")
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Xoá bác sĩ thành công", null);
    }
}
