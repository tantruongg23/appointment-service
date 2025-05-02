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
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.model.Specialty;
import vn.tayjava.service.SpecialtyService;

@RestController
@RequestMapping("/api/v1/specialties")
@RequiredArgsConstructor
@Slf4j(topic = "SPECIALTY-CONTROLLER")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 1. CREATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Tạo chuyên khoa", description = "Thêm mới chuyên khoa vào hệ thống.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Thông tin chuyên khoa", content = @Content(schema = @Schema(implementation = Specialty.class), examples = @ExampleObject(name = "Specialty sample", value = """
            {
              "name": "CK Nội Soi",
              "code": "NS"
            }"""))))
    @ApiResponse(responseCode = "201", description = "Tạo chuyên khoa thành công")
    @PostMapping
    public ResponseData<?> createSpecialty(@Valid @RequestBody Specialty request) {
        log.info("Request create specialty");
        Specialty created = specialtyService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Tạo chuyên khoa thành công",
                created);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 2. UPDATE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Cập nhật chuyên khoa", description = "Cập nhật thông tin chuyên khoa theo ID.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Nội dung cập nhật", content = @Content(schema = @Schema(implementation = Specialty.class), examples = @ExampleObject(name = "Specialty update sample", value = """
            {
              "name": "CK Nội Soi",
              "code": "NS"
            }"""))))
    @ApiResponse(responseCode = "200", description = "Cập nhật chuyên khoa thành công")
    @PutMapping("/{id}")
    public ResponseData<?> updateSpecialty(@PathVariable Long id,
            @Valid @RequestBody Specialty request) {
        log.info("Request update specialty {}", id);
        Specialty updated = specialtyService.update(id, request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Cập nhật chuyên khoa thành công",
                updated);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 3. GET ALL (PAGING)
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Danh sách chuyên khoa (paging)", description = "Trả về danh sách chuyên khoa, có phân trang.")
    @ApiResponse(responseCode = "200", description = "Lấy tất cả chuyên khoa thành công")
    @GetMapping
    public ResponseData<?> getAllSpecialties(
            @Parameter(description = "Trang", example = "1") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Kích thước trang", example = "10") @RequestParam(defaultValue = "10") int size) {

        log.info("Request get all specialties: page={}, size={}", page, size);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Lấy tất cả chuyên khoa thành công",
                specialtyService.findAll(page, size));
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 4. GET BY ID
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Chi tiết chuyên khoa", description = "Lấy chuyên khoa theo ID.")
    @ApiResponse(responseCode = "200", description = "Lấy chuyên khoa thành công")
    @GetMapping("/{id}")
    public ResponseData<?> getSpecialty(@PathVariable Long id) {
        log.info("Request get a specialty {}", id);
        Specialty specialty = specialtyService.findOne(id);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Lấy chuyên khoa thành công",
                specialty);
    }

    /*
     * ───────────────────────────────────────────────────────────────────────*\
     * 5. DELETE
     * \*───────────────────────────────────────────────────────────────────────
     */
    @Operation(summary = "Xoá chuyên khoa", description = "Xoá chuyên khoa khỏi hệ thống theo ID.")
    @ApiResponse(responseCode = "204", description = "Xóa chuyên khoa thành công")
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteSpecialty(@PathVariable Long id) {
        log.info("Request delete specialty {}", id);
        specialtyService.delete(id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(),
                "Xóa chuyên khoa thành công",
                null);
    }
}
