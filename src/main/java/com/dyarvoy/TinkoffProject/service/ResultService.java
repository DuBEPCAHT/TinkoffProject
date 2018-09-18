package com.dyarvoy.TinkoffProject.service;

import com.dyarvoy.TinkoffProject.model.Result;
import com.dyarvoy.TinkoffProject.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/*
 * Service for Result entity
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@Service
public class ResultService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResultRepository resultRepository;

    //Save Result Entity in DB
    @Transactional
    public void persist(Result result) {
        logger.info("\n----------------------------------\nДобавление сущности Result в базу данных." + new Date());
        resultRepository.save(result);
    }


}
