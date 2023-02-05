package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static Object[][] getExcelData(String sheetLabel, String path) throws IOException {

        int rowCount = XLUtils.getRowCount(path, sheetLabel);
        int colCount = XLUtils.getCellCount(path, sheetLabel, 1);

        String exceldata[][] = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                exceldata[i - 1][j] = XLUtils.getCellData(path, sheetLabel, i, j);
            }
        }

        return (exceldata);

    }


    public static int getRowCount(String xlfile, String xlsheet) throws IOException
    {

        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowcount = ws.getLastRowNum();
        wb.close();
        fi.close();

        return rowcount;

    }

    public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException
    {

        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();

        return cellcount;
    }

    public static String getCellData(String xlfile, String xlsheet, int rownum, int column) throws IOException
    {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(column);
        String data;

        try {
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        }
        catch (Exception e)
        {
            data="";
        }

        wb.close();
        fi.close();
        return data;

    }

    public static void setCellData(String xlfile, String xlsheet, int rownum, int column, String data) throws IOException
    {
        fi  = new FileInputStream(xlfile);
        wb  = new XSSFWorkbook(fi);
        ws  = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);
        cell = row.createCell(column);
        cell.setCellValue(data);
        fo = new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }

}
