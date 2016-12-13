package com.xuequ.cmoc.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.SysDictData;
import com.xuequ.cmoc.model.SysDictType;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.service.ISysDictService;
import com.xuequ.cmoc.vo.DictQueryVO;

@RequestMapping("content/dict")
@Controller
public class SysDictController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysDictController.class);
	
	@Autowired
	private ISysDictService sysDictService;

	@RequestMapping(value={"","/"})
	public String dictManage() {
		return "content/dictManage";
	}
	
	@RequestMapping("json/dictType/id")
	@ResponseBody Object dictTypeById(Integer id) {
		return sysDictService.selectDictTypeById(id);
	}
	
	@RequestMapping("json/dictData/id")
	@ResponseBody Object dictDataById(Integer id) {
		return sysDictService.selectDictDataById(id);
	}
	
	@RequestMapping("json/dictCode/exists")
	@ResponseBody int distCodeExists(String dictCode) {
		return sysDictService.selectCountByDictCode(dictCode);
	}
	
	@RequestMapping("json/dictType/all")
	@ResponseBody Object dictTypeAll() {
		return sysDictService.selectDictTypeAll();
	}
	
	@RequestMapping("json/dictData")
	@ResponseBody Object dictData(DictQueryVO vo) {
		Grid grid = new Grid();
		List<SysDictData> list = sysDictService.selectListByDictTypeId(vo.getDictTypeId());
		grid.setTotal(list.size());
		grid.setRows(list);
		return grid;
	}
	
	@RequestMapping("json/dictType/addUpdate")
	@ResponseBody Object dictTypeAddUpdate(SysDictType dictType) {
		try {
			SysUser user = (SysUser) session.getAttribute(Constants.APP_USER);
			if(dictType.getId() != null) {
				dictType.setUpdater(user.getUserName());
				dictType.setUpdateUserId(user.getIdUser());
				dictType.setUpdateTime(new Date());
				sysDictService.updateByPrimaryKeySelective(dictType);
			}else {
				dictType.setCreater(user.getUserName());
				dictType.setCreateUserId(user.getIdUser());
				dictType.setCreateTime(new Date());
				sysDictService.insertSelective(dictType);
			}
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--dictTypeActive, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/dictDate/addUpdate")
	@ResponseBody Object dictDataAddUpdate(SysDictData dictData) {
		try {
			SysUser user = (SysUser) session.getAttribute(Constants.APP_USER);
			if(dictData.getId() != null) {
				dictData.setUpdater(user.getUserName());
				dictData.setUpdateUserId(user.getIdUser());
				dictData.setUpdateTime(new Date());
				sysDictService.updateByPrimaryKeySelective(dictData);
			}else {
				dictData.setCreater(user.getUserName());
				dictData.setCreateUserId(user.getIdUser());
				dictData.setCreateTime(new Date());
				sysDictService.insertSelective(dictData);
			}
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--dictTypeActive, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
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
