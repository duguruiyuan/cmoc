package com.xuequ.cmoc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuequ.cmoc.model.ProductOrder;
import com.xuequ.cmoc.page.Page;
import com.xuequ.cmoc.view.CourseSignOrderView;

public interface ProductOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductOrder record);

    int insertSelective(ProductOrder record);

    ProductOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductOrder record);

    int updateByPrimaryKey(ProductOrder record);
    
    ProductOrder selectByOrderNo(String orderNo);
    
    ProductOrder selectByParam(@Param("openid")String openid, @Param("orderNo")String orderNo);
    
    List<CourseSignOrderView> selectCourseSignOrderByPage(Page<CourseSignOrderView> page);
}