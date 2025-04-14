package vn.tayjava.service;

import vn.tayjava.controller.request.PatientCreationReq;

public interface PatientService {
    long save(PatientCreationReq request);
}
