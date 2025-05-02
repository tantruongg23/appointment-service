package vn.tayjava.service;

import vn.tayjava.controller.response.PageResponse;
import vn.tayjava.model.Specialty;

public interface SpecialtyService {
    Specialty create(Specialty request);

    PageResponse<Specialty> findAll(int pageNo, int pageSize);

    Specialty findOne(long id);

    Specialty update(long id, Specialty request);

    void delete(long id);
}
