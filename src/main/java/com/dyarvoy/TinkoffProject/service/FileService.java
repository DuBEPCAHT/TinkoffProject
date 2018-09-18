package com.dyarvoy.TinkoffProject.service;

import com.dyarvoy.TinkoffProject.model.Result;
import com.dyarvoy.TinkoffProject.service.exception.FileCreateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/*
 * Basic logic this program
 * This class use for initializing 20 txt file with size 1GB
 * Find input 'number' within these 20 files
 * And utility methods
 *
 * @date 18.09.2018
 * @author Denis Yarovoy(dyarovoy@bostonsd.ru)
 */

@Component
public class FileService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private FileWriter fileWriter;
    private BufferedWriter writer;
    private Random random = new Random();

    //Creating 20 txt files with size 1GB each
    public void createInstanseFiles(int n) throws FileCreateException {
        String[] filesName = initialFileName();
        for (int i = 0; i < filesName.length; i++) {
            File file = new File("src\\main\\resources\\static\\" + filesName[i]);
            try {
                fileWriter = new FileWriter(file);
                writer = new BufferedWriter(fileWriter);
                int amount = n;
                while (amount > 0) {
                    int randomNumb = random.nextInt(1_000_000);
                    writer.write(randomNumb + ",");
                    amount--;
                }
                writer.close();
                logger.info("\n----------------------------------\n20 файлов успешно созданно " + new Date());
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("\n----------------------------------\nВозникла ошибка при создании файлов " + new Date());
                throw new FileCreateException("Error in moment create " + filesName[i] + ";");
            }
        }
    }

    //Utility method for create names for files
    private String[] initialFileName(){
        String[] filesName = new String[20];
        for(int i = 0; i < filesName.length; i++){
            filesName[i] = "text_" + i + ".txt";
        }
        return filesName;
    }

    //Find number in one file
    private boolean findNumbInOneFile(int number, String fileName) throws IOException {
        int chr;
        int counter = 0;
        char[] charNumber = Integer.toString(number).toCharArray();
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(new File("src\\main\\resources\\static\\" + fileName)));
        while((chr = bin.read())!=-1) {
            if (charNumber[counter] == (char)chr){
                counter++;
                if(counter == charNumber.length){
                    logger.info("\n----------------------------------\nЧисло "+ number + " найденно в файле" + fileName + ". " + new Date());
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        bin.close();
        logger.info("\n----------------------------------\nЧисло "+ number + " не найденно в файле" + fileName + ". " + new Date());
        return false;
    }

    //Get all files names
    private ArrayList<String> getAllFileName(){
        File folder = new File("src\\main\\resources\\static\\");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> paths = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                paths.add(listOfFiles[i].getName());
            }
        }
        return paths;
    }

    //Main method for find input 'number' in all files
    public Result findNumber(int number) throws IOException {
        Result result = new Result();
        ArrayList<String> listFile = getAllFileName();
        for(String path: listFile){
            if(findNumbInOneFile(number, path)){
                if(result.getFileNames() == null){
                    result.setFileNames(path + ", ");
                } else {
                    result.setFileNames(result.getFileNames() + path + ", ");
                }
            }
        }
        return result;
    }

}
