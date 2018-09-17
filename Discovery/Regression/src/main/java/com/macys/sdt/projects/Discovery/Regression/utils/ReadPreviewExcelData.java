package com.macys.sdt.projects.Discovery.Regression.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.macys.sdt.projects.Discovery.Regression.steps.website.PreviewHome;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Admin on 01/09/17.
 */
public class ReadPreviewExcelData {
    private static final Logger logger = LoggerFactory.getLogger(ReadPreviewExcelData.class);

    /*
   Method Name: getPreviewExcelData
   Description: Read Data from a excel provided in under Data/website/mcom. takes Excel File Name as input.
   Return type: HashMap<String, HashMap<String, String>>
   * */
    public static HashMap<String, HashMap<String, String>> getPreviewExcelData(String excelFileName) throws IOException {
        // String excelFilePath = "E:\\Desktop files\\EXCEL\\PreviewTestData.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(getPreivewDatafilePath("repo excel", excelFileName + ".xlsx")));
        HashMap<String, HashMap<String, String>> ExcelData = new HashMap<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            HashMap<String, String> rowData = new HashMap<>();
            if (nextRow.getRowNum() == 0) continue;
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (getCellValueAsString(cell).equalsIgnoreCase("Field Type") || getCellValueAsString(cell).equalsIgnoreCase("Info") || getCellValueAsString(cell).equalsIgnoreCase("Links") || getCellValueAsString(cell).equalsIgnoreCase("Details")) {
                    continue;
                }
                //  System.out.print(cell.getColumnIndex());
                rowData.put(getKeyToColumnIndex(cell), getCellValueAsString(cell));
                //  System.out.print(getCellValueAsString(cell));
                // System.out.print(" - ");
            }
            ExcelData.put(getCellValueAsString(nextRow.getCell(0)) + "_" + nextRow.getRowNum(), rowData);

        }
        //workbook.close();
        inputStream.close();

        logger.info(ExcelData.toString());
        return (ExcelData);
    }

    /*
   Method Name: getCellValueAsString
   Description: Returns excel cell value in String
   Return type: String
   * */
    private static String getCellValueAsString(Cell cell) {
        String strCellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    strCellValue = cell.toString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "dd/MM/yyyy");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    } else {
                        Double value = cell.getNumericCellValue();
                        Long longValue = value.longValue();
                        strCellValue = new String(longValue.toString());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    strCellValue = new String(new Boolean(
                            cell.getBooleanCellValue()).toString());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    strCellValue = "";
                    break;
            }
        }
        return strCellValue;
    }

    /*
      Method Name: getKeyToColumnIndex
      Description: Return the top header to be inserted as a key to Hashmap created by method getPreviewExcelData
      Return type: String
    */
    private static String getKeyToColumnIndex(Cell cell) {
        switch (cell.getColumnIndex()) {
            case 0:
                return "Field Type";
            case 1:
                return "Info";
            case 2:
                return "Links";
            case 3:
                return "Details";
            default:
                return null;
        }
    }

    /*
      Method Name: getPreivewDatafilePath
      Description: Returns the file path for Excels kept under Data/website/mcom
      Return type: String
     */
    public static String getPreivewDatafilePath(String type, String filename) throws IOException {
        GitTestDataUtility gt = GitTestDataUtility.getInstance();
        String path = System.getProperty("user.dir") + File.separator + "Discovery" + File.separator + "Regression" + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "data" + File.separator + "website" + File.separator + "mcom" + File.separator;
        switch (type) {
            case "excel":
                return path + "excel" + File.separator + filename;

            case "image":
                return path + "image" + File.separator + filename;
            case "repo excel":
                //Get test data from excel and place in temp folder
                return gt.repoPath + File.separator + filename;
            case "repo image":
                //Get test data from excel and place in temp folder
                return gt.repoPath + File.separator + "image" + File.separator + filename;
            default:

                logger.info("type not defined");
                return null;
        }
    }

    /*
     Method Name: getFileNameWithDate
     Description: Returns the file name according to the current date
     Return type: String
    */
    public static String getFileNameWithDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String Todaydate = df.format(date);
        // logger.info("CurrentDate::" + Todaydate);
        //Appending the name
        //return ("PTD_" + Todaydate);
        return ("PTD");

    }
}