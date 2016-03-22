package junitparams.utils;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by goonawardanan on 21/03/2016.
 */
public class ExcelUtil {
    private static String fileNameWithPath = "C:\\Users\\goonawardanan\\projects\\SpringRestApp\\src\\test\\resources\\users.xlsx";


    public static void readExcel(String fileName, int sheetIndex) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            //Get the workbook instance for XLS file

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readExcels(String fileName, int sheetIndex, String columnName) {
        try {
            FileInputStream file = new FileInputStream(new File(fileName));


            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue());
                            break;
                    }
                }

            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static XSSFWorkbook getWorkbook(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileName));


        return new XSSFWorkbook(file);


    }

    public static XSSFSheet getWorkSheet(XSSFWorkbook workbook, int sheetIndex) {
        //Get first/desired sheet from the workbook
        return workbook.getSheetAt(sheetIndex);


    }


    public static Row getRow(XSSFSheet sheet, int rowIndex) {

        return sheet.getRow(rowIndex);
    }

    public static List<Cell> getColumn(XSSFSheet sheet, String columnName) {
        List<Cell> cells = new ArrayList<Cell>();
        Integer columnNo = getColumnIndexForAGivenColumnName(sheet, columnName);
        if (columnNo != null) {
            for (Row row : sheet) {
                Cell c = row.getCell(columnNo);
                if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
                    // Nothing in the cell in this row, skip it
                } else {
                    cells.add(c);
                }
            }
        } else {
            System.out.println("could not find column " + columnName + " in the given Sheet");
        }

//Remove the column header
        cells.remove(0);
        return cells;

    }

    public static int getColumnIndexForAGivenColumnName(XSSFSheet sheet, String columnName) {
        Row firstRow = getRow(sheet, 0);
        Integer columnNo = null;
        for (Cell cell : firstRow) {
            if (cell.getStringCellValue().equals(columnName)) {
                columnNo = cell.getColumnIndex();
            }
        }
        return columnNo;

    }

    public static ArrayList<String> getColumnArr(XSSFSheet sheet, String columnName) {
        ArrayList<String> strArr = new ArrayList<String>();
        List<Cell> cellArr = getColumn(sheet, columnName);
        if (cellArr != null && !cellArr.isEmpty()) {
            for (Cell cellx : cellArr) {
                strArr.add(String.valueOf(((XSSFCell) cellx).getRawValue()));
            }
        } else {
            System.out.println("The cells are empty!");
        }
        return strArr;
    }


    public static ArrayList<String> getColumnArrStringValues(XSSFSheet sheet, String columnName) {
        ArrayList<String> strArr = new ArrayList<String>();
        List<Cell> cellArr = getColumn(sheet, columnName);
        if (cellArr != null && !cellArr.isEmpty()) {
            for (Cell cellx : cellArr) {
                strArr.add(String.valueOf(((XSSFCell) cellx).getStringCellValue()));
            }
        } else {
            System.out.println("The cells are empty!");
        }
        return strArr;
    }

    public static ArrayList<String> getCellsContentFromAGivenSheetNumberOfAGivenXLSFile(String fileName, int sheetIndex , String columnName) throws IOException {

        return getColumnArr(getWorkSheet(getWorkbook(fileName),sheetIndex),columnName);

    }

    public static ArrayList<String> getCellsStringContentFromAGivenSheetNumberOfAGivenXLSFile(String fileName, int sheetIndex , String columnName) throws IOException {

        return getColumnArrStringValues(getWorkSheet(getWorkbook(fileName),sheetIndex),columnName);

    }
}
