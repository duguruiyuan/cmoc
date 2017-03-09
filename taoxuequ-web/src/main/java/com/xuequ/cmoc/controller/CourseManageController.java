package com.xuequ.cmoc.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xuequ.cmoc.common.Configuration;
import com.xuequ.cmoc.common.Const;
import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.OrderStatusEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ChildSignInfo;
import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.model.Grid;
import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.model.SysUser;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.reqVo.CourseSignOrderVO;
import com.xuequ.cmoc.service.IChildSignInfoService;
import com.xuequ.cmoc.service.ICourseService;
import com.xuequ.cmoc.service.IProductOrderService;
import com.xuequ.cmoc.thread.WechatMsgCallback;
import com.xuequ.cmoc.utils.BeanUtils;
import com.xuequ.cmoc.utils.CellUtil;
import com.xuequ.cmoc.utils.ExcelExportUtil;
import com.xuequ.cmoc.utils.ExportSetInfoUtil;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.ValidatorUtil;
import com.xuequ.cmoc.view.ChildSignView;
import com.xuequ.cmoc.view.CourseBuyerView;
import com.xuequ.cmoc.view.CourseSignOrderView;
import com.xuequ.cmoc.vo.BuyerQueryVO;
import com.xuequ.cmoc.vo.ChildSignInfoVO;
import com.xuequ.cmoc.vo.ChildSignReportVO;
import com.xuequ.cmoc.vo.CourseQueryVO;
import com.xuequ.cmoc.vo.CourseSubmitVO;

@RequestMapping("course")
@Controller
public class CourseManageController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(CourseManageController.class);

	@Autowired
	private ICourseService courseService;
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IChildSignInfoService childSignInfoService;
	
	@RequestMapping("manage")
	public String manage() {
		return "course/manage";
	}
	
	@RequestMapping("sign/record")
	public String buyRecord(){
		return "course/signRecord";
	}
	
	@RequestMapping("sign/order")
	public String signOrder() {
		return "course/signOrder";
	}
	
	@RequestMapping("json/signOrder/list")
	@ResponseBody Object signOrderList(CourseSignOrderVO vo) {
		try {
			Page<CourseSignOrderView> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseSignOrderView> list = productOrderService.selectCourseSignOrderByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--signOrderList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/order/confirmPay")
	@ResponseBody Object orderConfirmPay(CourseSignOrderVO vo) {
		try {
			ProductOrder order = productOrderService.selectById(vo.getOrderId());
			if(order != null) {
				order.setOrderStatus("000");
				order.setUpdateTime(new Date());
				order.setPaySubmitTime(new Date());
				productOrderService.updateById(order);
				CourseSignOrderView view = productOrderService.selectCourseSignOrderByOrderId(vo.getOrderId());
				if(view != null) {
					new WechatMsgCallback(PropertiesUtil.getProperty(Configuration.getInstance().getEnv() + "_paySucessMsg"), 
							view).execute();
				}
			}
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--orderConfirmPay, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/sign/list")
	@ResponseBody Object courseSignList(BuyerQueryVO vo) {
		try {
			Page<ChildSignView> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<ChildSignView> list = courseService.selectCourseSignByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--courseSignList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/list/import")
	ModelAndView jsonListImport(BuyerQueryVO vo) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Page<ChildSignView> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(Integer.MAX_VALUE-1);
			page.setParams(vo);
			List<ChildSignView> signList = courseService.selectCourseSignByPage(page);
			List<ChildSignReportVO> list = BeanUtils.copyAs(signList, ChildSignReportVO.class);
			//数据源
			LinkedHashMap<String, List> map = new LinkedHashMap<String, List>();
			String title="课程网上预约报名表";
			map.put(title, list);
			
			//标题  和  标题变量
		    List<String[]> headNames = new ArrayList<String[]>();
		    headNames.add(new String[] { "课程名称","紧急联系人", "联系电话", "小孩姓名", "小孩身份证号","小孩性别","小孩年龄","有无疾病",
		    		"疾病描述","报名时间"});
		    List<String[]> fieldNames = new ArrayList<String[]>();
		    fieldNames.add(new String[] { "courseName","emerName", "emerMobile", "childName", "childIdcard", "childSex","childAge","isDisease",
		    		"diseaseDesc","signTime"});
		    
		    //excel生成帮助类
		    List<Integer[]> columnFormat=new ArrayList<Integer[]>();
		    Integer [] format=new Integer[22];
		    columnFormat.add(format);
		    
		    String [] titles=new String[] {title};
		    //excel生成帮助类
		    ExportSetInfoUtil setInfo = new ExportSetInfoUtil(map,titles,headNames,fieldNames,columnFormat);
		    request.setAttribute("exportDatas", setInfo);
		    
		    //生成
			modelAndView=new ModelAndView(new ExcelExportUtil(title+".xls",""));
		} catch (Exception e) {
			logger.error("--jsonListQuery, error={}", e);
		}
		return modelAndView;
	}
	
	@RequestMapping("json/buyRecord/list")
	@ResponseBody Object buyRecordList(BuyerQueryVO vo) {
		try {
			Page<CourseBuyerView> page = new Page<>();
			vo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseBuyerView> list = courseService.selectBuyRecordByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--buyRecordList, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/list/query")
	@ResponseBody Object jsonListQuery(CourseQueryVO vo) {
		try {
			Page<CourseInfo> page = new Page<>();
			page.setPageNo(vo.getPage());
			page.setPageSize(vo.getRows());
			page.setParams(vo);
			List<CourseInfo> list = courseService.selectListByPage(page);
			Grid grid = new Grid();
			grid.setRows(list);
			grid.setTotal(page.getTotalRecord());
			return grid;
		} catch (Exception e) {
			logger.error("--jsonListQuery, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	
	@RequestMapping("json/addUpdate")
	@ResponseBody Object addUpdate(CourseSubmitVO vo) {
		try {
			SysUser user = (SysUser) session.getAttribute(Constants.APP_USER);
			courseService.addAndUpdateCourse(vo, user);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--addUpdate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/edit")
	public String edit(Model model) {
		String courseId = request.getParameter("id");
		model.addAttribute("courseId", courseId);
		return "course/edit";
	}
	
	@RequestMapping("json/detail/query")
	@ResponseBody Object courseDetail(Integer courseId) {
		return courseService.selectByPrimaryKey(courseId);
	}
	
	@RequestMapping("json/edit/shelves")
	@ResponseBody Object editShelves(Integer shelves, Integer id) {
		try {
			courseService.updateShelves(shelves, id);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--editShelves, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("json/course/compent")
	@ResponseBody Object courseCompent() {
		List<CourseInfo> list = courseService.selectShelvesList();
		StringBuffer sb = new StringBuffer();
		for(CourseInfo info : list) {
			sb.append("<option value=\"" + info.getId() + "\">" + info.getCourseName() + "</option>");
		}
		return sb.toString();
	}
	
	@RequestMapping("json/sign/update")
	@ResponseBody Object signUpdate(ChildSignInfo vo) {
		try {
			courseService.updateChildSignInfo(vo);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--signUpdate, error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	@RequestMapping("sign/print/{id}")
	public String signPrint(Model model, @PathVariable Integer id) {
		model.addAttribute("signInfo", courseService.selectChildSignById(id));
		return "course/signPrint";
	}
	
	/**
	 * 学生用户导入
	 * @auther 胡启萌
	 * @Date 2016年11月14日
	 * @return
	 */
	@RequestMapping("namelist/import")
	@ResponseBody Object namelistImport(@RequestParam(value="orderId") Integer orderId, 
			@RequestParam(value="files",required=false) MultipartFile buildInfo) {
		try {
			return saveBuildInfo(buildInfo, orderId);
		} catch (Exception e) {
			logger.error("--namelistImport, error={}", e);
		}
		return new RspResult(StatusEnum.TEMPLATE_ERROR);
	}
	
	public RspResult saveBuildInfo(MultipartFile buildInfo, Integer orderId) throws Exception{
    	SysUser sysUser = (SysUser) session.getAttribute(Constants.APP_USER);
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
        	return new RspResult(StatusEnum.TEMPLATE_DATA_NULL);
        }
        List<ChildSignInfoVO> list = new ArrayList<>();
        // 遍历行 从第三行开始遍历
        for (int i = 1; i < rowNum; i++) {
        	// 读取左上端单元格
            Row row = sheet.getRow(i);
            // 行不为空
            if (row != null) {
            	ChildSignInfoVO signInfo = new ChildSignInfoVO();
            	signInfo.setRows(i + 1);
            	for(int j = 0; j < coloumNum; j ++) {
            		Cell cell = row.getCell((short)j);
            		if(cell != null) {
            			String val = CellUtil.getCellValue(cell);
            			if(j == 0) signInfo.setEmerName(val.trim());
            			else if(j == 1) signInfo.setEmerMobile(val.trim());
            			else if(j == 2) signInfo.setChildName(val.trim());
            			else if(j == 3) signInfo.setChildSex(val.trim());
            			else if(j == 4) signInfo.setChildIdcard(val.trim());
            			else if(j == 5) signInfo.setIsDisease(val.trim());
            			else if(j == 6) signInfo.setDiseaseDesc(val.trim());
            		}
            	}
            	list.add(signInfo);
            }
        }
        List<String> errorList = new ArrayList<>();
        for(ChildSignInfoVO signInfo : list) {
        	String validator = ValidatorUtil.validatorParams(signInfo);
            if(null != validator) {
            	errorList.add("第" + signInfo.getRows() + "行" + validator);
            }
        }
        if(errorList.size() > 0) return new RspResult(StatusEnum.FAIL, errorList);
        List<ChildSignInfo> childList = BeanUtils.copyAs(list, ChildSignInfo.class);
        childSignInfoService.insertCourseSignNamelist(childList, orderId);
        return new RspResult(StatusEnum.SUCCESS);
    }
	
	/**
	 * 根据编号查询名单信息
	 * @param childId
	 * @return
	 */
	@RequestMapping("namelist/query/{childId}")
	@ResponseBody Object namelistQueryById(@PathVariable Integer childId) {
		try {
			ChildSignInfo childSignInfo = childSignInfoService.selectById(childId);
			return new RspResult(StatusEnum.SUCCESS, childSignInfo);
		} catch (Exception e) {
			logger.error("--namelistQueryById error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 删除名单
	 * @param vo
	 * @return
	 */
	@RequestMapping("namelist/del")
	@ResponseBody Object namelistDel(ChildSignInfo vo) {
		try {
			SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.APP_USER);
			vo.setIsDelete("Y");
			vo.setUpdateUserId(sysUser.getIdUser());
			vo.setUpdater(sysUser.getUserName());
			vo.setUpdateTime(new Date());
			childSignInfoService.updateByPrimaryKey(vo);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--namelistDel error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
	
	/**
	 * 名单信息变更
	 * @param vo
	 * @return
	 */
	@RequestMapping("namelist/update")
	@ResponseBody Object namelistUpdate(ChildSignInfo vo) {
		try {
			SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.APP_USER);
			vo.setUpdateUserId(sysUser.getIdUser());
			vo.setUpdater(sysUser.getUserName());
			vo.setUpdateTime(new Date());
			childSignInfoService.updateByPrimaryKey(vo);
			return new RspResult(StatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error("--namelistUpdate error={}", e);
		}
		return new RspResult(StatusEnum.FAIL);
	}
}
