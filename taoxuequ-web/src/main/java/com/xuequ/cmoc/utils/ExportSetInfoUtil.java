package com.xuequ.cmoc.utils;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 封装Excel导出的设置信息
 * @author 000000649
 *
 */
public class ExportSetInfoUtil {

	@SuppressWarnings("unchecked")
	private LinkedHashMap<String, List> objsMap;
	
	private String[] titles;
	
	private List<String[]> headNames;
	
	private List<String[]> fieldNames;
	
	private List<Integer[]> columnFormatNames;
	

	
	
	


	public ExportSetInfoUtil(LinkedHashMap<String, List> objsMap,
			String[] titles, List<String[]> headNames,
			List<String[]> fieldNames, List<Integer[]> columnFormatNames) {
		super();
		this.objsMap = objsMap;
		this.titles = titles;
		this.headNames = headNames;
		this.fieldNames = fieldNames;
		this.columnFormatNames = columnFormatNames;
	}

	public ExportSetInfoUtil(LinkedHashMap<String, List> objsMap,
			String[] titles, List<String[]> headNames,
			List<String[]> fieldNames) {
		super();
		this.objsMap = objsMap;
		this.titles = titles;
		this.headNames = headNames;
		this.fieldNames = fieldNames;
	}
	
	public List<Integer[]> getColumnFormatNames() {
		return columnFormatNames;
	}

	public void setColumnFormatNames(List<Integer[]> columnFormatNames) {
		this.columnFormatNames = columnFormatNames;
	}

	public ExportSetInfoUtil() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public LinkedHashMap<String, List> getObjsMap()
	{
		return objsMap;
	}

	/**
	 * @param objMap 导出数据
	 * 
	 * 泛型
	 * String : 代表sheet名称
	 * List : 代表单个sheet里的所有行数据
	 */
	@SuppressWarnings("unchecked")
	public void setObjsMap(LinkedHashMap<String, List> objsMap)
	{
		this.objsMap = objsMap;
	}

	public List<String[]> getFieldNames()
	{
		return fieldNames;
	}

	/**
	 * @param clazz 对应每个sheet里的每行数据的对象的属性名称
	 */
	public void setFieldNames(List<String[]> fieldNames)
	{
		this.fieldNames = fieldNames;
	}

	public String[] getTitles()
	{
		return titles;
	}

	/**
	 * @param titles 对应每个sheet里的标题，即顶部大字
	 */
	public void setTitles(String[] titles)
	{
		this.titles = titles;
	}

	public List<String[]> getHeadNames()
	{
		return headNames;
	}

	/**
	 * @param headNames 对应每个页签的表头的每一列的名称
	 */
	public void setHeadNames(List<String[]> headNames)
	{
		this.headNames = headNames;
	}

}
