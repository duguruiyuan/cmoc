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
import com.xuequ.cmoc.model.ActivityMarines;
import com.xuequ.cmoc.service.IActivityFamilyService;
import com.xuequ.cmoc.service.IActivityMarinesService;
import com.xuequ.cmoc.utils.FileUtils;
import com.xuequ.cmoc.utils.PropertiesUtil;
import com.xuequ.cmoc.utils.ZTRuntimeException;
import com.xuequ.cmoc.vo.AttachmentUploadVO;

@RequestMapping("attachment")
@Controller
public class AttachmentController {
	
	@Autowired
	private IActivityMarinesService activityMarinesService;
	@Autowired
	private IActivityFamilyService activityFamilyService;
	
	private String activityroot = PropertiesUtil.getProperty("attachment.activity");

	@RequestMapping("upload/img")
	@ResponseBody Object uploadImg(AttachmentUploadVO vo, 
			@RequestParam(value="files") MultipartFile buildInfo) {
		String path = ImgTypeEnum.ACTIVITY.getCode() + File.separator;
		if(ImgTypeEnum.MARINE.getCode().equals(vo.getType())) {
			ActivityMarines marines = activityMarinesService.selectByPrimaryKey(vo.getResourceId());
			path += ImgTypeEnum.MARINE.getCode() + File.separator +
					marines.getActivityId();
			String imgUrl = writeFile(path, buildInfo);
			activityMarinesService.updateMarineImg(imgUrl, vo.getResourceId());
		}else if(ImgTypeEnum.MEMBER.getCode().equals(vo.getType())) {
			ActivityFamily family = activityFamilyService.selectByPrimaryKey(vo.getResourceId());
			path += ImgTypeEnum.MARINE.getCode() + File.separator +
					family.getActivityId() + File.separator + family.getMarineId();
			String imgUrl = writeFile(path, buildInfo);
			activityFamilyService.updateFamilyImg(imgUrl, vo.getResourceId());
		}
		return new RspResult(StatusEnum.SUCCESS);
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
