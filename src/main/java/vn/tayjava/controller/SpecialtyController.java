package vn.tayjava.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.controller.response.ResponseData;
import vn.tayjava.controller.response.ResponseError;
import vn.tayjava.model.Specialty;
import vn.tayjava.service.SpecialtyService;

@RestController
@RequestMapping("/api/v1/specialties")
@RequiredArgsConstructor
@Slf4j(topic = "SPECIALTY-CONTROLLER")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @PostMapping
    public ResponseData<?> createSpecialty(@Valid @RequestBody Specialty request) {
        log.info("Request create specialty");
        try {
            return new ResponseData<Specialty>(HttpStatus.CREATED.value(), "Tạo chuyên khoa thành công",
                    this.specialtyService.create(request));
        } catch (Exception e) {
            log.error("Create specialty failed {} {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseData<?> createSpecialty(@PathVariable Long id, @Valid @RequestBody Specialty request) {
        log.info("Request update specialty");
        try {
            return new ResponseData<Specialty>(HttpStatus.CREATED.value(), "Cập nhập chuyên khoa thành công",
                    this.specialtyService.update(id, request));
        } catch (Exception e) {
            log.error("Update specialty failed {} {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping
    public ResponseData<?> getAllSpecialties() {
        log.info("Request get all specialties");
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy tất cả chuyên khoa thành công",
                    this.specialtyService.findAll());
        } catch (Exception e) {
            log.error("Get all specialties failed", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getSpecialty(@PathVariable Long id) {
        log.info("Request get a specialty");
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Lấy chuyên khoa thành công",
                    this.specialtyService.findOne(id));
        } catch (Exception e) {
            log.error("Get a specialty failed", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData deleteSpecialty(@PathVariable Long id) {
        log.info("Request delete specialty");
        try {
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Xóa chuyên khoa thành công");
        } catch (Exception e) {
            log.error("Delete specialty failed", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Delete specialty failed");
        }
    }
}
