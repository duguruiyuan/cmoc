package com.xuequ.cmoc.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;

/**
 * cell帮助类，获取cell类型
 * @author 000000649
 *
 */
public class CellUtil {
    
    public static String getCellValue(Cell cell) {  
            String ret;  
            switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_BLANK:  
                ret = "";  
                break;  
            case Cell.CELL_TYPE_BOOLEAN:  
                ret = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case Cell.CELL_TYPE_ERROR:  
                ret = null;  
                break;  
            case Cell.CELL_TYPE_FORMULA:  
                Workbook wb = cell.getSheet().getWorkbook();  
                CreationHelper crateHelper = wb.getCreationHelper();  
                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
                ret = getCellValue(evaluator.evaluateInCell(cell));  
                break;  
            case Cell.CELL_TYPE_NUMERIC:  
                    ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
                break;  
            case Cell.CELL_TYPE_STRING:  
                ret = cell.getRichStringCellValue().getString();  
                break;  
            default:  
                ret = null;  
            }  
              
            return ret; //有必要自行trim  
        }  
}
