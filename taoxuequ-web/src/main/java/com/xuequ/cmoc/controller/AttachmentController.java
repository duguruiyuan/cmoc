package com.xuequ.cmoc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xuequ.cmoc.common.Constants;
import com.xuequ.cmoc.common.RspResult;
import com.xuequ.cmoc.common.enums.ImgTypeEnum;
import com.xuequ.cmoc.common.enums.StatusEnum;
import com.xuequ.cmoc.model.ActivityFamily;
import com.xuequ.cmoc.model.ActivityInfo;
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.service.IActivityService;
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.ZTRuntimeException;
import com.xuequ.cmoc.view.ActivityResourceTypeView;
import com.xuequ.cmoc.vo.AttachmentUploadVO;

@RequestMapping("attachment")
@Controller
public class AttachmentController {
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;
	@Autowired
	private IActivityService activityService;
	
	private String activityroot = PropertiesUtil.getProperty("attachment.activity");

	@RequestMapping("activity/upload/img")
	@ResponseBody Object uploadImg(AttachmentUploadVO vo, 
			@RequestParam(value="files") MultipartFile buildInfo) {
		if(ImgTypeEnum.MARINE.getCode().equals(vo.getType())) {
			ActivityMarines marines = activityMarinesService.selectByPrimaryKey(vo.getResourceId());
			imgUpload(marines.getActivityId(), marines.getId(), vo.getResourceId(), 2, buildInfo);
		}else if(ImgTypeEnum.MEMBER.getCode().equals(vo.getType())) {
			ActivityFamily family = activityFamilyService.selectByPrimaryKey(vo.getResourceId());
			imgUpload(family.getActivityId(), family.getMarineId(), vo.getResourceId(), 3, buildInfo);
		}else if(ImgTypeEnum.MARINE_AND_MEMBER.getCode().equals(vo.getType())) {
			marineMemberUpladImg(vo, buildInfo);
		}else if(ImgTypeEnum.ACTIVITY.getCode().equals(vo.getType())) {
			ActivityInfo info = activityService.selectByPrimaryKey(vo.getResourceId());
			imgUpload(info.getId(), null, vo.getResourceId(), 1, buildInfo);
		}
		return new RspResult(StatusEnum.SUCCESS);
	}
	
	private void marineMemberUpladImg(AttachmentUploadVO vo, MultipartFile buildInfo) {
		String fileName = FileUtils.getFileNameExceptExt(buildInfo.getOriginalFilename());
		ActivityResourceTypeView view = null;
		if(fileName.indexOf("-") != -1) {
			String[] strs = fileName.split("-");
			view = activityService.selectForUpload1(vo.getResourceId(), strs[0], strs[1]);
		}else{
			view = activityService.selectForUpload(vo.getResourceId(), fileName);
		}
		if(view != null) {
			imgUpload(view.getActivityId(), view.getMarineId(), view.getId(), view.getType(), buildInfo);
		}
	}
	
	/**
	 * 图片上传
	 * @auther 胡启萌
	 * @Date 2016年11月23日
	 * @param activityId 活动id
	 * @param marineId 战队id
	 * @param resourceId 要更新图片资源Id
	 * @param type 图片类型 1活动 2战队 3队员
	 * @param buildInfo
	 */
	private void imgUpload(Integer activityId, Integer marineId, Integer resourceId, 
			int type, MultipartFile buildInfo) {
		String path = ImgTypeEnum.ACTIVITY.getCode() + File.separator + activityId;
		if(type == 1) {
			String imgUrl = writeFile(path, buildInfo);
			activityService.updateActivityImg(imgUrl, resourceId);
		}else if(type == 2) {
			path += File.separator + marineId;
			String imgUrl = writeFile(path, buildInfo);
			activityMarinesService.updateMarineImg(imgUrl, resourceId);
		}else if(type == 3) {
			path += marineId;
			String imgUrl = writeFile(path, buildInfo);
			activityFamilyService.updateFamilyImg(imgUrl, resourceId);
		}
	}
	
	public String writeFile(String path, MultipartFile buildInfo) {
		
		String relativeAttachmentPath = activityroot + File.separator + path;
        createDir(relativeAttachmentPath);
        //数据库保存的文件路径
        String realFileName = UUID.randomUUID().toString();
        realFileName += Constants.DOT + FileUtils.getExtName(buildInfo.getOriginalFilename());

        // 文件保存磁盘的完整路径
        String attachmentURL = relativeAttachmentPath + File.separator + realFileName;
        try {
            OutputStream os = new FileOutputStream(attachmentURL);
            IOUtils.copy(buildInfo.getInputStream(), os);
        }
        catch (Exception e) {
            throw new ZTRuntimeException(String.format("创建文件路径[%s]出错", relativeAttachmentPath), e);
        }
        String dataMemoryURL = path + File.separator + realFileName;
        dataMemoryURL.replaceAll("\\\\", "/");
        return dataMemoryURL;
	}
	
	private static void createDir(String uploadPath) {
        File file = new File(uploadPath);
        // 如果该文件目录不存在，则创建一个新的目录
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
