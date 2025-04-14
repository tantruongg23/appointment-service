package vn.tayjava.service;

import vn.tayjava.common.enumerate.AppointmentStatus;
import vn.tayjava.controller.request.AppointmentCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.model.Appointment;

public interface AppointmentService {

    Appointment create(AppointmentCreationReq request);

    Appointment updateAppointmentStatus(long id, AppointmentStatus status);

    PageResponse<Appointment> getAppointmentsByPatientId(Long patientId, int pageNo, int pageSize);

    PageResponse<Appointment> getAllAppointments(int pageNo, int pageSize);
}
