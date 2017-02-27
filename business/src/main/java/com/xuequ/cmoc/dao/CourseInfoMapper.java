package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.CourseInfo;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseGroupOrderView;
import com.xuequ.cmoc.view.CourseListView;
import com.xuequ.cmoc.view.CourseSchduleActivityView;

public interface CourseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseInfo record);

    int insertSelective(CourseInfo record);

    CourseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseInfo record);

    int updateByPrimaryKeyWithBLOBs(CourseInfo record);

    int updateByPrimaryKey(CourseInfo record);
    
    List<CourseInfo> selectListByPage(Page<CourseInfo> page);
    
    int updateShelves(@Param("shelves")Integer shelves, @Param("id")Integer id);
    
    int delCourse(Integer id);
    
    List<CourseInfo> selectShelvesList();
    
    public List<CourseListView> selectShelvesSignByPage(Page<CourseListView> view);
    
    CourseListView selectDetailById(Integer productId);
    
    /**
     * 查询课程得排期活动列表
     * @param courseId
     * @return
     */
    List<CourseSchduleActivityView> selectScheduActivityInfoByCourseId(Integer courseId);
    List<CourseGroupOrderView> selectCourseGroupOrder(@Param("openid")String openid, @Param("orderNo")String orderNo);
}