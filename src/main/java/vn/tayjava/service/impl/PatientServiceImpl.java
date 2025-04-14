package vn.tayjava.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.tayjava.controller.request.PatientCreationReq;
import vn.tayjava.model.Patient;
import vn.tayjava.repository.PatientRepository;
import vn.tayjava.service.PatientService;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    private Patient findByPhone(String phone) {
        return this.patientRepository.findByPhone(phone);
    }

    @Override
    public long save(PatientCreationReq request) {
        Patient existedPatient = this.findByPhone(request.getPhone());
        if (existedPatient == null) {
            existedPatient = new Patient();
        }

        existedPatient.setPhone(request.getPhone());
        existedPatient.setFullName(request.getFullName());
        existedPatient.setDob(request.getDob());
        // check if id exists
        existedPatient.setCustomerId(request.getCustomerId());
        existedPatient.setGender(request.getGender());
        existedPatient.setNationality(request.getNationality());
        existedPatient.setProvince(request.getProvince());
        existedPatient.setDistrict(request.getDistrict());
        existedPatient.setWard(request.getWard());
        existedPatient.setStreet(request.getStreet());
        existedPatient = this.patientRepository.save(existedPatient);

        return existedPatient.getId();
    }
}
