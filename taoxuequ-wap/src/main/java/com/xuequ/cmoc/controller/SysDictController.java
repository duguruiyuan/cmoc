package com.xuequ.cmoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.model.SysDictData;
import com.xuequ.cmoc.service.ISysDictService;

@RequestMapping("dict")
@Controller
public class SysDictController extends BaseController {
	
	@Autowired
	private ISysDictService sysDictService;

	@RequestMapping("json/dictData/dictCode")
	@ResponseBody Object dictDataDictCode(String dictCode) {
		return sysDictService.selectListByDictCode(dictCode);
	}
	
	@RequestMapping("json/dictData/compent")
	@ResponseBody Object dictDataCompent(String dictCode) {
		List<SysDictData> list = sysDictService.selectListByDictCode(dictCode);
		StringBuffer sb = new StringBuffer();
		for(SysDictData dictData : list) {
			sb.append("<option value=\"" + dictData.getDictDataKey() + "\">" + dictData.getDictDataValue() + "</option>");
		}
		return sb.toString();
	}
	
	@RequestMapping("json/init/dictData")
	@ResponseBody Object initDictData(String dictCode){
		return sysDictService.selectResource();
	}
	
}
