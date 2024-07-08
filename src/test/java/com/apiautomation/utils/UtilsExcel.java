package com.apiautomation.utils;

//Open that FileInputStream
//Understand the WorkbookSheet
//Row
//Cell
//Close the Stream

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilsExcel {
    //it will be used by DataProviders of the TestNG
    //object[][]
    public static String FILE_NAME = "src/test/resource/TD.xlsx";
    static Workbook book;
    static Sheet sheet;


    public static Object[][] getTestData(String sheetName){
        FileInputStream file = null;
        try {
            file = new FileInputStream(FILE_NAME);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0; i<sheet.getLastRowNum(); i++){
            for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++){
                data[i][k] = sheet.getRow(i+1).getCell(k).toString();
            }
        }

        return data;
    }

    @DataProvider
    public Object[][] getData(){
        //in future i can write logic to select which sheet i want to open.
        //Ask user which sheet to open
        //data.properties --> Sheet1 or Sheet2
        //Sheet1 --> U/P - QA
        //Sheet2 --> U/P -Prod
        return getTestData("Sheet1");
    }



}
