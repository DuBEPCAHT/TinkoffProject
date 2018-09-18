package com.dyarvoy.TinkoffProject.repository;

import com.dyarvoy.TinkoffProject.model.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * CRUD repository for Result entity
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@Repository
public interface ResultRepository extends CrudRepository<Result, String> {

}
