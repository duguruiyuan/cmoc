package com.xuequ.cmoc.utils;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelExportView extends AbstractExcelView implements Serializable{

	/**
	 * 
	 */
	protected static final long serialVersionUID = 6755908174385604832L;
	
	protected String fileName; // 导出excel文件名称
	protected String sheetPrefixName;// 工作表前缀
	
	public ExcelExportView(String fileName, String sheetPrefixName) {
		super();
		this.fileName = fileName;
		this.sheetPrefixName = sheetPrefixName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetPrefixName() {
		return sheetPrefixName;
	}

	public void setSheetPrefixName(String sheetPrefixName) {
		this.sheetPrefixName = sheetPrefixName;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> paramMap,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 初始化准备
		// 业务数据注入
	}
	
	// 初始化sheet
	protected void initSheet(HSSFSheet sheet) {
			// example
			// sheet.setColumnWidth(0, 20 * 256);
			// sheet.setColumnWidth(1, 40 * 256);
			// sheet.setColumnWidth(2, 20 * 256);
			// sheet.setColumnWidth(3, 20 * 256);
			// sheet.setColumnWidth(4, 20 * 256);
			// sheet.setColumnWidth(5, 20 * 256);
			// sheet.setColumnWidth(6, 20 * 256);
	}

	// 初始化sheet头部样式
	protected void initHeader(HSSFSheet sheet, HSSFCellStyle style) {
			// example
			// HSSFRow row = sheet.createRow((short) 0);
			// row.setHeightInPoints((short) 13.25);
			// createCell(row, 0, "所属模块", style);
			// createCell(row, 1, "课程名称", style);
			// createCell(row, 2, "课程等级", style);
			// createCell(row, 3, "标准课时", style);
			// createCell(row, 4, "培训对象", style);
			// createCell(row, 5, "创建时间", style);
			// createCell(row, 6, "创建者", style);
	}
	
	// 创建单元格
	protected void createCell(HSSFRow row, int column, Object value, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(column);
		cell.setCellValue(String.valueOf(value));
		cell.setCellStyle(style);
		
	}

	// 标题字体样式设置
	public HSSFFont initTitleFont(HSSFFont font) {
		font.setFontName("Arial");// 字体名称
		font.setFontHeightInPoints((short) 10);// 字号
		font.setColor(HSSFColor.BLACK.index);// 颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		return font;
	}

	// 内容字体设置
	public HSSFFont initContentFont(HSSFFont font) {
		font.setFontName("Arial");// 字体名称
		font.setFontHeightInPoints((short) 10);// 字号
		font.setColor(HSSFColor.BLACK.index);// 颜色
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);// 不加粗
		return font;
	}
	
}
