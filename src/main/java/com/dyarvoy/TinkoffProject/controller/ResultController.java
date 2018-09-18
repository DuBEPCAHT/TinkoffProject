package com.dyarvoy.TinkoffProject.controller;

import com.dyarvoy.TinkoffProject.model.Result;
import com.dyarvoy.TinkoffProject.repository.ResultRepository;
import com.dyarvoy.TinkoffProject.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/*
 * REST controller with end-point for start search number in file
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@RestController
public class ResultController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/findNumber", method = RequestMethod.GET)
    public ResponseEntity findNumber(@RequestParam(value = "number") String number) throws IOException {
        if(number.matches("^\\d+$")) {
            Result result = fileService.findNumber(Integer.parseInt(number));
            if(result.getFileNames() != null){
                result.setCode("00.Result.OK");
                result.setNumber(Integer.parseInt(number));
                resultRepository.save(result);
                logger.info("\n----------------------------------\nЧисло " + number + " было найденно в файлах. " + new Date());
                return new ResponseEntity(HttpStatus.OK);
            } else {
                result.setCode("01.Result.NotFound");
                result.setNumber(Integer.parseInt(number));
                result.setError("Число не найдено ни в одном файле");
                resultRepository.save(result);
                logger.info("\n----------------------------------\nЧисло " + number + " не найденно в файлах. " + new Date());
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            Result result = new Result();
            result.setCode("02.Result.Error");
            result.setError("Указано не корректное значение для поиска");
            resultRepository.save(result);
            logger.info("\n----------------------------------\n" + number + " не является корректным значением для поиска. " + new Date());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
