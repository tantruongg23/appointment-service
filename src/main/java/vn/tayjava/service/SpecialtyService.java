package vn.tayjava.service;

import java.util.List;

import vn.tayjava.model.Specialty;

public interface SpecialtyService {
    Specialty create(Specialty request);

    List<Specialty> findAll();

    Specialty findOne(long id);

    Specialty update(long id, Specialty request);

    void delete(long id);
}
