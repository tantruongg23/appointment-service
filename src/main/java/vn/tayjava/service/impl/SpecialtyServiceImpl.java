package vn.tayjava.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vn.tayjava.exception.IdInvalidException;
import vn.tayjava.exception.InvalidDataException;
import vn.tayjava.model.Specialty;
import vn.tayjava.repository.SpecialtyRepository;
import vn.tayjava.service.SpecialtyService;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "SPECIALTY-SERVICE")
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Specialty create(Specialty request) {
        if (request == null) {
            throw new InvalidDataException("Vui lòng nhập đúng dữ liệu");
        }

        if (this.specialtyRepository.existsByName(request.getName())) {
            throw new IdInvalidException("Tên chuyên khoa đã tồn tại, vui lòng nhập tên khác");
        }

        log.info("Created specialty with name {}", request.getName());

        return this.specialtyRepository.save(request);
    }

    @Override
    public List<Specialty> findAll() {
        return this.specialtyRepository.findAll();
    }

    @Override
    public Specialty findOne(long id) {
        return this.findById(id);
    }

    @Override
    public Specialty update(long id, Specialty request) {
        if (request == null) {
            throw new InvalidDataException("Vui lòng nhập đúng dữ liệu");
        }

        Specialty specialty = this.findById(id);

        if (this.specialtyRepository.existsByName(request.getName())) {
            throw new IdInvalidException("Tên chuyên khoa đã tồn tại, vui lòng nhập tên khác");
        }

        specialty.setName(request.getName());

        log.info("Updated specialty with name {}", request.getName());

        return this.specialtyRepository.save(specialty);
    }

    @Override
    public void delete(long id) {
        this.specialtyRepository.deleteById(id);
    }

    private Specialty findById(long id) {
        return this.specialtyRepository.findById(id).orElseThrow(
                () -> new IdInvalidException("Chuyên khoa không tồn tại"));
    }
}
