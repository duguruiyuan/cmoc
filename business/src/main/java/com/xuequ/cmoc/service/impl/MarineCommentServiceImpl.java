package com.xuequ.cmoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuequ.cmoc.dao.MarineCommentMapper;
import com.xuequ.cmoc.dao.MarineStudentImpressionMapper;
import com.xuequ.cmoc.model.MarineComment;
import com.xuequ.cmoc.model.MarineStudentImpression;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.service.IMarineCommentService;
import com.xuequ.cmoc.utils.StringUtil;

@Service("marineCommentService")
public class MarineCommentServiceImpl implements IMarineCommentService {

	@Autowired
	private MarineCommentMapper marineCommentMapper;
	@Autowired
	private MarineStudentImpressionMapper marineStudentImpressionMapper;
	
	@Override
	public List<MarineComment> selectListByPage(Page<MarineComment> page) {
		return marineCommentMapper.selectListByPage(page);
	}

	@Override
	public List<MarineStudentImpression> selectStudentImpByMarineId(Integer marineId) {
		List<MarineStudentImpression> list = marineStudentImpressionMapper.selectByMarineId(marineId);
		if(StringUtil.isNullOrEmpty(list)) {
			int count = marineStudentImpressionMapper.insertByMarineId(marineId);
			if(count > 0) {
				list = marineStudentImpressionMapper.selectByMarineId(marineId);
			}
		}
		return list;
	}

	@Override
	public int addStuImpVotes(Integer id) {
		return marineStudentImpressionMapper.addStuImpVotes(id);
	}

	@Override
	public int insertMarineComment(MarineComment record) {
		return marineCommentMapper.insertSelective(record);
	}

}
