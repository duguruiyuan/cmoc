package com.xuechengjf.aim.controller.blacklist;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.xuechengjf.aim.controller.system.BaseController;
import com.xuechengjf.aim.enums.Constants;
import com.xuechengjf.aim.model.blacklist.BusiBlackListInfo;
import com.xuechengjf.aim.model.blacklist.TBusiBlackList;
import com.xuechengjf.aim.model.system.TdictData;
import com.xuechengjf.aim.model.system.Tuser;
import com.xuechengjf.aim.service.backlist.BusiBlackListService;
import com.xuechengjf.aim.service.system.DictionaryServiceI;
import com.xuechengjf.aim.util.Constant;
import com.xuechengjf.aim.util.DateUtil;
import com.xuechengjf.aim.util.excel.CellUtil;
import com.xuechengjf.aim.util.excel.ExcelExportUtil;
import com.xuechengjf.aim.util.excel.ExportSetInfoUtil;
import com.xuechengjf.aim.view.system.Grid;
import com.xuechengjf.aim.view.system.Json;
import com.xuechengjf.aim.view.system.Page;
import com.xuechengjf.aim.vo.BlackListQueryVO;

@RequestMapping("blacklist")
@Controller
public class BlackListController extends BaseController {
	
	private Logger logger = Logger.getLogger(BlackListController.class);
	
	@Autowired
	private BusiBlackListService busiBlackListService;
	@Autowired
	private DictionaryServiceI dictionaryServiceI;
	
	
	/**
	 * 黑名单列表页
	 * @author 000001436
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return "antiFraud/blacklistManagement/search/index";
	}
	/**
	 * 待提审名单
	 * @author 000001436
	 * @return
	 */
	@RequestMapping("arraigning")
	public String arraigning() {
		return "";
	}
	
	/**
	 * 待审核名单
	 * @author 000001436
	 * @return
	 */
	@RequestMapping("auditing")
	public String auditing() {
		return "antiFraud/blacklistAudit/search/index";
	}
	
	@RequestMapping("add")
	public String addBlackList() {
		return "antiFraud/blacklistManagement/add/index";
	}
	
    @RequestMapping("dict/list")
    public @ResponseBody void selectVehicleTypeAndBrand(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String sourceKey = "BLACK_LIST_SOURCE";
        List<TdictData> sourceDict = dictionaryServiceI.selectByDictType(sourceKey);
        String reasonKey = "BLACK_LIST_REASON"; 
        List<TdictData> reasonDict = dictionaryServiceI.selectByDictType(reasonKey);
        String statusKey = "BLACK_LIST_STATUS"; 
        List<TdictData> statusDict = dictionaryServiceI.selectByDictType(statusKey);
        String custTypeKey = "CUST_TYPE";
        List<TdictData> custTypeDict = dictionaryServiceI.selectByDictType(custTypeKey);
        String idTypeKey = "ID_TYPE";
        List<TdictData> idTypeDict = dictionaryServiceI.selectByDictType(idTypeKey);
        map.put("sourceDict", sourceDict);//信息来源
        map.put("reasonDict", reasonDict);//黑名单原因
        map.put("statusDict", statusDict);//黑名单状态
        map.put("custTypeDict", custTypeDict);//客户类型
        map.put("idTypeDict", idTypeDict);//证件类型
        writeJson(map);
    }
	
	/**
	 * 黑名单列表查询
	 * @author 000001436
	 */
	@RequestMapping("list/query")
	public @ResponseBody void listQuery(BlackListQueryVO vo) {
		try {
			Tuser user = (Tuser)request.getSession().getAttribute(Constant.SESSION_USER);
			Page<TBusiBlackList> page = new Page<TBusiBlackList>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custName", vo.getCustName());
			paramMap.put("custId", vo.getCustId());
			paramMap.put("idNo", vo.getIdNo());
			paramMap.put("mobile", vo.getMobile());
			paramMap.put("sourceType", vo.getSourceType());
			paramMap.put("blackListNo", vo.getBlackListNo());
			paramMap.put("status", vo.getStatus());
			paramMap.put("menuId", vo.getMenuId());
			paramMap.put("userId", user.getId());
			paramMap.put("jobNumber", user.getJobNumber());
			paramMap.put("belongId", user.getBelongId());
			page.setParams(paramMap);
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			Grid grid = new Grid();
			List<TBusiBlackList> list = busiBlackListService.selectListByPage(page);
			grid.setTotal(page.getTotalRecord());
			grid.setRows(list);
			writeJsonAll(grid);
		}catch (Exception e){
			logger.error("--listQuery error",e);
		}
	}
	/**
	 * 黑名单详情
	 * @author 000001436
	 * @param blackListNo
	 * @param model
	 * @return
	 */
	@RequestMapping("detail/{blackListNo}")
	public String blackListDetail(@PathVariable String blackListNo, Model model) {
		BusiBlackListInfo info = busiBlackListService.selectByBlackListNo(blackListNo);
		if(info != null) {
			model.addAttribute("info", info);
			if(Constants.CustType.CUST_TYPE_1.getCode().equals(info.getCustType())) {
				return "antiFraud/blacklistManagement/check/personal";
			}
			return "antiFraud/blacklistManagement/check/company";
		}
		return null;
	}

	/**
	 * 新增黑名单
	 * @author 000001436
	 */
	@RequestMapping("/json/add")
	public @ResponseBody void addList(TBusiBlackList reqVo) {
		Json json = new Json();
		try {
			Tuser tuser = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
			reqVo.setAddType(Constants.BlackListAddType.BLACK_LIST_ADD_TYPE_1.getCode());
			reqVo.setAddTypeValue(Constants.BlackListAddType.BLACK_LIST_ADD_TYPE_1.getDesc());
			reqVo.setBusiType(Constants.BlackListBusiType.BLACK_LIST_BUSI_TYPE_01.getCode());
			reqVo.setBusiTypeValue(Constants.BlackListBusiType.BLACK_LIST_BUSI_TYPE_01.getDesc());
			int result = busiBlackListService.addBlackList(reqVo, tuser);
			if(result == 1) {
				json.setMsg("黑名单录入成功");
				json.setSuccess(true);
			} else if(result == 2) {
				json.setMsg("该客户已经是黑名单用户！");
			} else if(result == 3) {
				json.setMsg("该客户已录入黑名单，正在审核流程中！");
			} else {
				json.setMsg("黑名单录入失败");
			}
			
		}catch (Exception e){
			logger.error("--addList error",e);
			json.setSuccess(false);
			json.setMsg("系统异常");
		}
		writeJson(json);
	}
	/**
	 * 提审黑名单
	 * @author 000001436
	 * @param ids
	 */
	@RequestMapping("/json/arraign")
	public @ResponseBody void arraignBlackList(@RequestParam("ids[]") List<Long> ids) {
		Json json = new Json();
		try {
			Tuser tuser = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
			int count = busiBlackListService.updateBlackList(ids, Constants.BlackListStatus.BLACK_LIST_STATUS_02.getCode(), 
					Constants.BlackListStatus.BLACK_LIST_STATUS_03.getCode(), tuser);
			if(count == 1) {
				json.setMsg("黑名单提审成功");
				json.setSuccess(true);
			}else {
				json.setMsg("数据已过期，请刷新页面");
				json.setSuccess(false);
			}
		}catch (Exception e){
			logger.error("--addList error",e);
			json.setSuccess(false);
			json.setMsg("系统异常");
		}
		writeJson(json);
	}
	/**
	 * 审核黑名单
	 * @author 000001436
	 * @param ids
	 */
	@RequestMapping("/json/audit")
	public @ResponseBody void auditBlackList(TBusiBlackList req) {
		Json json = new Json();
		try {
			Tuser tuser = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
			int count = busiBlackListService.updateAuditBlackList(req, tuser);
			if(count == 1) {
				json.setMsg("黑名单审核成功");
				json.setSuccess(true);
			}else {
				json.setMsg("数据已过期，请刷新页面");
				json.setSuccess(false);
			}
		}catch (Exception e){
			logger.error("--addList error",e);
			json.setSuccess(false);
			json.setMsg("系统异常");
		}
		writeJson(json);
	}
	/**
	 * 取消黑名单
	 * @author 000001436
	 * @param ids
	 */
	@RequestMapping("/json/cancel")
	public @ResponseBody void cancelBlackList(@RequestParam("ids[]") List<Long> ids, 
			@RequestParam("remark") String remark) {
		Json json = new Json();
		try {
			Tuser tuser = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
			int count = busiBlackListService.updateBlackList(ids, Constants.BlackListStatus.BLACK_LIST_STATUS_01.getCode(), 
					Constants.BlackListStatus.BLACK_LIST_STATUS_03.getCode(), tuser);
			if(count == 1) {
				json.setMsg("黑名单取消成功");
				json.setSuccess(true);
			}else {
				json.setMsg("数据已过期，请刷新页面再试");
				json.setSuccess(false);
			}
		}catch (Exception e){
			logger.error("--cancelBlackList error",e);
			json.setSuccess(false);
			json.setMsg("系统异常");
		}
		writeJson(json);
	}
	
	/**
	 * 删除黑名单
	 * @author 000001436
	 * @param ids
	 */
	@RequestMapping("/json/del")
	public @ResponseBody void delBlackList(@RequestParam("ids[]") List<Long> ids) {
		Json json = new Json();
		try {
			int count = busiBlackListService.deleteBlackList(ids);
			if(count == 0) {
				json.setMsg("数据已过期，请刷新页面再试");
				json.setSuccess(false);
			}else {
				json.setMsg("黑名单删除成功");
				json.setSuccess(true);
			}
		}catch (Exception e){
			logger.error("--addList error",e);
			json.setSuccess(false);
			json.setMsg("系统异常");
		}
		writeJson(json);
	}
	/**
	 * 导出黑名单
	 * @author 000001436
	 * @param vo
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/json/export")
	public ModelAndView getBlackListExport(BlackListQueryVO vo) {
		ModelAndView modelAndView=null;
		try {
			Tuser user = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
			Page<TBusiBlackList> page = new Page<TBusiBlackList>();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("custName", vo.getCustName());
			paramMap.put("custId", vo.getCustId());
			paramMap.put("idNo", vo.getIdNo());
			paramMap.put("mobile", vo.getMobile());
			paramMap.put("sourceType", vo.getSourceType());
			paramMap.put("blackListNo", vo.getBlackListNo());
			paramMap.put("status", vo.getStatus());
			paramMap.put("menuId", vo.getMenuId());
			paramMap.put("userId", user.getId());
			paramMap.put("jobNumber", user.getJobNumber());
			paramMap.put("belongId", user.getBelongId());
			//查询出所有的记录
			page.setPageSize(Integer.MAX_VALUE-1); 
			page.setParams(paramMap);
			List<TBusiBlackList> list = busiBlackListService.selectListByPage(page);
			//数据源
			LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();
			String title = Constant.BLOACK_LIST_EXPORT + DateUtil.shortDateString(new Date(), 
					DateUtil.SHORT_DATE_PATTERN);
			//一个子sheet
			map.put(title, list);
			
			//标题  和  标题变量
		    List<String[]> headNames = new ArrayList<String[]>();
		    headNames.add(new String[] {"客户姓名", "证件类型", "证件编号", "手机号", "黑名单编号", "客户类型", 
		    		"贷款类型", "黑名单来源", "原因描述", "备注", "单位名称", "单位电话", 
		    		"单位地址", "配偶姓名", "配偶单位名称", "配偶电话", "第一联系人姓名", 
		    		"第一人联系人关系", "第一联系人电话", "第二联系人姓名", "第二人联系人关系", "第二联系人电话", 
		    		"第三联系人姓名", "第三人联系人关系", "第三联系人电话", "第四联系人姓名", "第四人联系人关系", "第四联系人电话",
		    		"第五联系人姓名", "第五人联系人关系", "第五联系人电话","创建人姓名", "创建时间"});
		    List<String[]> fieldNames = new ArrayList<String[]>();
		    fieldNames.add(new String[] {"custName", "idTypeValue", "idNo", "mobile", "blackListNo", "custTypeValue",
		    		"loanType", "sourceTypeValue", "reasonValue", "remark", "companyName", "companyTelephone",
		    		"companyAddr", "spouseName", "spouseCompanyName", "spouseMobile", "firstContactName",
		    		"firstContactRelations", "firstContactMobile", "secondContactName", "secondContactRelations",
		    		"secondContactMobile", "thirdContactName", "thirdContactRelations", "thirdContactMobile",
		    		"fourContactName", "fourContactRelations", "fourContactMobile", "fiveContactName",
		    		"fiveContactRelations", "fiveContactMobile", "createrName", "createTime"});
		    String [] titles=new String[] {title};
		    //列格式
		    List<Integer[]> columnFormat=new ArrayList<Integer[]>();
		    Integer [] format=new Integer[34];
		    format[32] = 8;
		    columnFormat.add(format);
		    //excel生成帮助类
		    ExportSetInfoUtil setInfo = new ExportSetInfoUtil(map,titles,headNames,fieldNames,columnFormat);
		    request.setAttribute("exportDatas", setInfo);
		    //生成
			modelAndView = new ModelAndView(new ExcelExportUtil(title+".xls",""));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return modelAndView;
	}
	
	/**
	 * 导入黑名单
	 * @author 000001436
	 * @param buildInfo
	 */
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/json/import")
	public @ResponseBody void blackListImport(@RequestParam(value="files",required=false) 
									MultipartFile buildInfo) {
		Json j = new Json();
    	try{
    		j = saveBuildInfo(buildInfo, request);
    	}catch(Exception e){
    		 logger.error("批量导入黑名单信息报错：",e);
    		 j.setMsg("导入失败,请检查模板或数据!");
 			 j.setSuccess(false);
    	}
    	writeJson(j);
	}
	
	public Json saveBuildInfo(MultipartFile buildInfo ,HttpServletRequest request) throws Exception{
    	Tuser tuser = (Tuser) request.getSession().getAttribute(Constant.SESSION_USER);
		Json json=new Json();
    	List<TBusiBlackList> list = new ArrayList<TBusiBlackList>();
    	// 创建一个FileInputStream 文件输入流
        InputStream inputStream = buildInfo.getInputStream();
    	// 创建对Excel工作簿文件的引用
        Workbook wookbook = null;
        String filename = buildInfo.getOriginalFilename();
        String fileType = filename.substring(filename.lastIndexOf(".") + 1,
        		filename.length());
        if (fileType.equals("xls")) {
        	try{
        		wookbook = new HSSFWorkbook(inputStream);
        	}catch(Exception e){
        		wookbook = null;
           	 	wookbook = new XSSFWorkbook(inputStream);
        	}
        }else{
       	 	wookbook = new XSSFWorkbook(inputStream);
        }
        // 在Excel文档中，第一张工作表的缺省索引是0
        Sheet sheet = wookbook.getSheetAt(0);
        int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();// 获取总列数
        int rowNum=sheet.getPhysicalNumberOfRows();//获得总行数
        if(rowNum <= 1) {
        	json.setMsg("文件内容为空!");
            json.setSuccess(false);
            return json;
        }
        // 遍历行 从第三行开始遍历
        for (int i = 1; i < rowNum; i++) {
        	// 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
            	TBusiBlackList blackList = new TBusiBlackList();
            	blackList.setIdType(Constants.IdCard.ID_CARD_TYPE_01.getCode());
            	blackList.setIdTypeValue(Constants.IdCard.ID_CARD_TYPE_01.getDesc());
            	blackList.setCustType(Constants.CustType.CUST_TYPE_1.getCode());
            	blackList.setCustTypeValue(Constants.CustType.CUST_TYPE_1.getDesc());
            	blackList.setSourceType(Constants.SourceType.Source_Type_03.getCode());
            	blackList.setSourceTypeValue(Constants.SourceType.Source_Type_03.getDesc());
            	blackList.setAddType(Constants.BlackListAddType.BLACK_LIST_ADD_TYPE_2.getCode());
            	blackList.setAddTypeValue(Constants.BlackListAddType.BLACK_LIST_ADD_TYPE_2.getDesc());
            	blackList.setBusiType(Constants.BlackListBusiType.BLACK_LIST_BUSI_TYPE_01.getCode());
            	blackList.setBusiTypeValue(Constants.BlackListBusiType.BLACK_LIST_BUSI_TYPE_01.getDesc());
            	for(int j = 0; j < coloumNum; j ++) {
            		Cell cell = row.getCell((short)j);
            		if(cell != null) {
            			String val = CellUtil.getCellValue(cell);
            			if(j == 0) blackList.setLoanTypeValue(val);
            			else if(j == 1) blackList.setCustName(val);
            			else if(j == 2) blackList.setIdNo(val);
            			else if(j == 3) blackList.setClassify(val);
            			else if(j == 4) blackList.setReasonValue(val);
            			else if(j == 5) blackList.setMobile(val);
            			else if(j == 6) blackList.setCompanyName(val);
            			else if(j == 7) blackList.setCompanyTelephone(val);
            			else if(j == 8) blackList.setCompanyAddr(val);
            			else if(j == 9) blackList.setSpouseName(val);
            			else if(j == 10) blackList.setSpouseCompanyName(val);
            			else if(j == 11) blackList.setSpouseMobile(val);
            			else if(j == 12) blackList.setFirstContactName(val);
            			else if(j == 13) blackList.setFirstContactRelations(val);
            			else if(j == 14) blackList.setFirstContactMobile(val);
            			else if(j == 15) blackList.setSecondContactName(val);
            			else if(j == 16) blackList.setSecondContactRelations(val);
            			else if(j == 17) blackList.setSecondContactMobile(val);
            			else if(j == 18) blackList.setThirdContactName(val);
            			else if(j == 19) blackList.setThirdContactRelations(val);
            			else if(j == 20) blackList.setThirdContactMobile(val);
            			else if(j == 21) blackList.setFourContactName(val);
            			else if(j == 22) blackList.setFourContactRelations(val);
            			else if(j == 23) blackList.setFourContactMobile(val);
            			else if(j == 24) blackList.setFiveContactName(val);
            			else if(j == 25) blackList.setFiveContactRelations(val);
            			else if(j == 26) blackList.setFiveContactMobile(val);
            			else if(j == 27) blackList.setRemark(val);
            		}
            	}
            	list.add(blackList);
            }
        }
        //保存数据
        if(list != null && list.size() > 0){
        	busiBlackListService.addBlackListBatch(list, tuser);
        	json.setMsg("文件导入成功!");
            json.setSuccess(true);
        }
    	return json;
    }
	
}
