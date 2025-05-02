package vn.tayjava.service;

import vn.tayjava.controller.request.DoctorCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.model.Doctor;

public interface DoctorService {
    long findById(long id);

    PageResponse<Doctor> getAllDoctors(int pageNo, int pageSize);

    Doctor create(DoctorCreationReq request);

    Doctor changeDoctorStatus(Long id, boolean available);

    Doctor getDoctorById(Long id);

    Doctor updateDoctor(long id, DoctorCreationReq request);

    void deleteDoctor(long id);
}
