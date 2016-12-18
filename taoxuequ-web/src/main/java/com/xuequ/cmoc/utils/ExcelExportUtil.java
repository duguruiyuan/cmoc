package com.xuequ.cmoc.utils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
/**
 * 报表导出帮助类
 * @author 000000649
 *
 */

public class ExcelExportUtil  extends ExcelExportView{
	
	private static final long serialVersionUID = 1L;
	
	public ExcelExportUtil(String fileName, String sheetPrefixName) {
		super(fileName, sheetPrefixName);
	}

	
	@Override
	protected void buildExcelDocument(Map<String, Object> paramMap, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 初始化工作区
		
		// 单元格样式
		HSSFCellStyle titleCellStyle = workbook.createCellStyle();
		HSSFCellStyle contentCellStyle = workbook.createCellStyle();
		
		
		titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		titleCellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);// 垂直居中
		titleCellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 前置色
		titleCellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);// 背景色
		titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 填充方式
		
		contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		contentCellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		
		// 标题字体样式
		HSSFFont titleFont = workbook.createFont();
		titleFont = initTitleFont(titleFont);
		titleCellStyle.setFont(titleFont);

		// 字体样式
		HSSFFont contentFont = workbook.createFont();
		contentFont = initContentFont(contentFont);
		contentCellStyle.setFont(contentFont);

		// 注入数据
		@SuppressWarnings("unchecked")
		ExportSetInfoUtil setInfo=(ExportSetInfoUtil)request.getAttribute("exportDatas");
		// 初始化工作区
//		initSheet(sheet);
//		initHeader(sheet, titleCellStyle,setInfo);
		

		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List> entry : set)
		{
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(workbook,setInfo.getObjsMap().size(), sheetNames);
		int sheetNum = 0;
		for (Entry<String, List> entry : set)
		{
			// Sheet
			List objs = entry.getValue();
			// 标题行
			//createTableTitleRow(setInfo, sheets, sheetNum,titleCellStyle);
			// 日期行
			//createTableDateRow(setInfo, sheets, sheetNum,titleCellStyle);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum,titleCellStyle);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			Integer[] columnFormatNames=setInfo.getColumnFormatNames()!=null?setInfo.getColumnFormatNames().get(sheetNum):null;
			int rowNum = 1;
			for (Object obj : objs)
			{
				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length,contentCellStyle,columnFormatNames);
				int cellNum = 0;					
				if(fieldNames != null)
				{
					for (int num = 0; num < fieldNames.length; num++)
					{
						Object value = ReflectionUtils.invokeGetterMethod(obj, fieldNames[num]);
						if(columnFormatNames!=null && columnFormatNames[num]!=null && columnFormatNames[num]==Cell.CELL_TYPE_NUMERIC){
							cells[cellNum].setCellValue(value == null ? 0.0 :new  Double(value.toString()));
						}else{
							cells[cellNum].setCellValue(value == null ? "" : value.toString());
						}
						cellNum++;
					}
				}
				rowNum++;
			}
			adjustColumnSize(sheets, sheetNum, fieldNames);	// 自动调整列宽
			sheetNum++;
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	
	/**
	 * @Description: 创建所有的Sheet
	 */
	private static HSSFSheet[] getSheets(HSSFWorkbook wb,int num, String[] names)
	{
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++)
		{
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}
	
	/**
	 * @Description: 创建内容行的每一列(附加一列序号)
	 */
	private static HSSFCell[] getCells(HSSFRow contentRow, int num,HSSFCellStyle contentCellStyle,Integer[] columnFormatNames)
	{
		HSSFCell[] cells = new HSSFCell[num + 1];

		for (int i = 0,len = cells.length; i < len; i++)
		{
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentCellStyle);
			if(columnFormatNames!=null &&columnFormatNames[i]!=null && columnFormatNames[i]==Cell.CELL_TYPE_NUMERIC){
				cells[i].setCellType(Cell.CELL_TYPE_NUMERIC);
			}
			
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		//cells[0].setCellValue(contentRow.getRowNum() - 2);

		return cells;
	}
	
	/**
	 * @Description: 创建标题行(需合并单元格)
	 */
	private static void createTableTitleRow(ExportSetInfoUtil setInfo,
			HSSFSheet[] sheets, int sheetNum, HSSFCellStyle style)
	{
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		HSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short) 800);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(style);
		titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
	}
	
	
	/**
	 * @Description: 创建日期行(需合并单元格)
	 */
	private static void createTableDateRow(ExportSetInfoUtil setInfo,
			HSSFSheet[] sheets, int sheetNum, HSSFCellStyle style)
	{
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, 
				setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(dateRange);
		HSSFRow dateRow = sheets[sheetNum].createRow(1);
		dateRow.setHeight((short) 350);
		HSSFCell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(style);
		dateCell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	/**
	 * @Description: 自动调整列宽
	 */
	@SuppressWarnings("unchecked")
	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum,
			String[] fieldNames)
	{
		for(int i = 0; i < fieldNames.length + 1; i++)
		{
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}
	

	/**
	 * @Description: 创建表头行(需合并单元格)
	 */
	private static void creatTableHeadRow(ExportSetInfoUtil setInfo,
			HSSFSheet[] sheets, int sheetNum, HSSFCellStyle style)
	{
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(0);
		headRow.setHeight((short) 350);
		// 序号列
//		HSSFCell snCell = headRow.createCell(0);
//		snCell.setCellStyle(style);
//		snCell.setCellValue("序号");
		// 列头名称
		for(int num = 0, len = setInfo.getHeadNames().get(sheetNum).length; num < len; num++)
		{
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(style);
			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num]);
		}
	}
	
}
