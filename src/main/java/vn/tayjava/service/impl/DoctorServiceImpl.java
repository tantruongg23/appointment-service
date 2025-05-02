package vn.tayjava.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.tayjava.controller.request.DoctorCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.exception.IdInvalidException;
import vn.tayjava.exception.InvalidDataException;
import vn.tayjava.model.Doctor;
import vn.tayjava.model.Specialty;
import vn.tayjava.repository.DoctorRepository;
import vn.tayjava.repository.SpecialtyRepository;
import vn.tayjava.service.DoctorService;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    // ──────────────────────────────────────────────────────────────────────
    // BASIC LOOK‑UP
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public long findById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Bác sĩ không tồn tại"))
                .getId();
    }

    // ──────────────────────────────────────────────────────────────────────
    // CREATE
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public Doctor create(DoctorCreationReq req) {

        // 1) Validate unique name (optional – comment out if duplicates allowed)
        // if (doctorRepository.existsByFullName(req.getFullName())) {
        // throw new InvalidDataException("Bác sĩ đã tồn tại");
        // }

        // 2) Validate specialty
        Specialty specialty = specialtyRepository.findById(req.getSpecialtyId())
                .orElseThrow(() -> new IdInvalidException("Chuyên khoa không tồn tại"));

        // 3) Persist
        Doctor doctor = new Doctor();
        doctor.setFullName(req.getFullName());
        doctor.setSpecialty(specialty);
        doctor.setAvailable(true);
        return doctorRepository.save(doctor);
    }

    // ──────────────────────────────────────────────────────────────────────
    // UPDATE
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public Doctor updateDoctor(long id, DoctorCreationReq req) {

        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Không tìm thấy bác sĩ"));

        // Optional uniqueness check
        // if (!existing.getFullName().equals(req.getFullName()) &&
        // doctorRepository.existsByFullName(req.getFullName())) {
        // throw new InvalidDataException("Tên bác sĩ đã tồn tại");
        // }

        Specialty specialty = specialtyRepository.findById(req.getSpecialtyId())
                .orElseThrow(() -> new IdInvalidException("Chuyên khoa không tồn tại"));

        existing.setFullName(req.getFullName());
        existing.setSpecialty(specialty);

        return doctorRepository.save(existing);
    }

    // ──────────────────────────────────────────────────────────────────────
    // CHANGE STATUS
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public Doctor changeDoctorStatus(Long id, boolean available) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Không tìm thấy bác sĩ"));
        doctor.setAvailable(available);
        return doctorRepository.save(doctor);
    }

    // ──────────────────────────────────────────────────────────────────────
    // GET BY ID (FULL OBJECT)
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("Không tìm thấy bác sĩ"));
    }

    // ──────────────────────────────────────────────────────────────────────
    // DELETE
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public void deleteDoctor(long id) {
        if (!doctorRepository.existsById(id)) {
            throw new IdInvalidException("Không tìm thấy bác sĩ để xoá");
        }
        doctorRepository.deleteById(id);
    }

    // ──────────────────────────────────────────────────────────────────────
    // PAGINATION
    // ──────────────────────────────────────────────────────────────────────
    @Override
    public PageResponse<Doctor> getAllDoctors(int pageNo, int pageSize) {

        int realPage = pageNo > 0 ? pageNo - 1 : 0;
        Pageable pageable = PageRequest.of(
                realPage,
                pageSize,
                Sort.by(Sort.Direction.ASC, "fullName"));

        Page<Doctor> pages = doctorRepository.findAll(pageable);

        return PageResponse.<Doctor>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getTotalElements())
                .items(pages.getContent())
                .build();
    }
}
