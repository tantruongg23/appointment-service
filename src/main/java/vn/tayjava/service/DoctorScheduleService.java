package vn.tayjava.service;

import java.util.List;

import vn.tayjava.controller.request.DoctorScheduleCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.model.DoctorSchedule;

public interface DoctorScheduleService {

    PageResponse<DoctorSchedule> getAllSchedules(int pageNo, int pageSize);

    PageResponse<DoctorSchedule> getAllSchedulesByDoctorId(long id, int pageNo, int pageSize);

    DoctorSchedule create(DoctorScheduleCreationReq request);

    DoctorSchedule changeScheduleStatus(Long id, boolean available);

    DoctorSchedule getScheduleById(Long id);

    DoctorSchedule updateSchedule(long id, DoctorScheduleCreationReq request);

    void deleteSchedule(long id);

}
