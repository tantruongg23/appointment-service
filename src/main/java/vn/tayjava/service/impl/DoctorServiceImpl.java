package vn.tayjava.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.tayjava.exception.IdInvalidException;
import vn.tayjava.model.Doctor;
import vn.tayjava.repository.DoctorRepository;
import vn.tayjava.service.DoctorService;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public long findById(long id) {
        Doctor doctor = this.doctorRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("Bác sĩ không tồn tại"));
        return doctor.getId();
    }

}
