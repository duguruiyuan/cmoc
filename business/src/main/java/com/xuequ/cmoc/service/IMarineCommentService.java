package com.xuequ.cmoc.service;

import java.util.List;

import com.xuequ.cmoc.model.MarineComment;
import com.xuequ.cmoc.model.MarineStudentImpression;
import com.xuequ.cmoc.page.Page;

public interface IMarineCommentService {

	List<MarineComment> selectListByPage(Page<MarineComment> page);
	
	List<MarineStudentImpression> selectStudentImpByMarineId(Integer marineId);
	
	int addStuImpVotes(Integer id);
	
	int insertMarineComment(MarineComment record);
}
