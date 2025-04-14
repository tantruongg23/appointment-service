package vn.tayjava.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.tayjava.controller.request.DoctorScheduleCreationReq;
import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.exception.InvalidDataException;
import vn.tayjava.model.DoctorSchedule;
import vn.tayjava.repository.DoctorScheduleRepository;
import vn.tayjava.service.DoctorScheduleService;
import vn.tayjava.service.DoctorService;

@Service
@RequiredArgsConstructor
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    private final DoctorService doctorService;

    public DoctorSchedule create(DoctorScheduleCreationReq request) {
        long doctorId = this.doctorService.findById(request.getDoctorId());

        // check if exists
        if (this.doctorScheduleRepository.existsByDoctorIdAndDateAndPeriod(
                doctorId,
                request.getDate(),
                request.getPeriod())) {
            throw new InvalidDataException("Đã tồn tại lịch này");
        }
        DoctorSchedule schedule = new DoctorSchedule();
        schedule.setDoctorId(doctorId);
        schedule.setDate(request.getDate());
        schedule.setPeriod(request.getPeriod());
        schedule.setStartHour(request.getStartHour());
        schedule.setEndHour(request.getEndHour());
        schedule.setAvailable(true);

        return this.doctorScheduleRepository.save(schedule);
    }

    public DoctorSchedule changeScheduleStatus(Long id, boolean available) {
        DoctorSchedule schedule = this.doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lịch không tồn tại"));

        schedule.setAvailable(available);
        return this.doctorScheduleRepository.save(schedule);
    }

    @Override
    public DoctorSchedule getScheduleById(Long id) {
        return doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch với ID = " + id));
    }

    @Override
    public DoctorSchedule updateSchedule(long id, DoctorScheduleCreationReq request) {
        DoctorSchedule existing = doctorScheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lịch để cập nhật"));

        long doctorId = this.doctorService.findById(request.getDoctorId());

        if (doctorScheduleRepository.existsByDoctorIdAndDateAndPeriod(
                doctorId, request.getDate(), request.getPeriod()) && !existing.getDoctorId().equals(doctorId)) {
            throw new InvalidDataException("Đã tồn tại lịch với thông tin này");
        }

        existing.setDoctorId(doctorId);
        existing.setDate(request.getDate());
        existing.setPeriod(request.getPeriod());
        existing.setStartHour(request.getStartHour());
        existing.setEndHour(request.getEndHour());

        return doctorScheduleRepository.save(existing);
    }

    @Override
    public void deleteSchedule(long id) {
        this.doctorScheduleRepository.deleteById(id);
    }

    @Override
    public PageResponse<DoctorSchedule> getAllSchedules(int pageNo, int pageSize) {
        int newPageNo = pageNo > 0 ? pageNo - 1 : pageNo;

        Pageable pageable = PageRequest.of(newPageNo, pageSize, (Sort.by(Direction.DESC, "date")));

        Page<DoctorSchedule> pages = this.doctorScheduleRepository.findAll(pageable);
        PageResponse<DoctorSchedule> result = PageResponse.<DoctorSchedule>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getTotalElements())
                .items(pages.getContent())
                .build();
        return result;
    }

    @Override
    public PageResponse<DoctorSchedule> getAllSchedulesByDoctorId(long id, int pageNo, int pageSize) {
        int newPageNo = pageNo > 0 ? pageNo - 1 : pageNo;

        Pageable pageable = PageRequest.of(newPageNo, pageSize, (Sort.by(Direction.DESC, "date")));

        Page<DoctorSchedule> pages = this.doctorScheduleRepository.findByDoctorId(id, pageable);
        PageResponse<DoctorSchedule> result = PageResponse.<DoctorSchedule>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(pages.getTotalPages())
                .totalElements(pages.getTotalElements())
                .items(pages.getContent())
                .build();
        return result;
    }

}
