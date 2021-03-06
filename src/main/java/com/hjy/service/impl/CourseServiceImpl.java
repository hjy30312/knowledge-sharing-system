package com.hjy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.hjy.common.ResponseCode;
import com.hjy.common.ServerResponse;
import com.hjy.dao.CourseMapper;
import com.hjy.entity.Course;
import com.hjy.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author hjy
 * @create 2018/06/24
 **/
@Service("CourseService")
public class CourseServiceImpl implements CourseService {


	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseMapper courseMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ServerResponse insertCourse(Course course) {
		int resultCount = courseMapper.insert(course);
		if(resultCount == 0){
			return ServerResponse.createByErrorMessage("注册失败");
		}
		return ServerResponse.createBySuccessMessage("注册成功");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ServerResponse deleteCourseById(String courseIds) {

		List<String> courseIdList = Splitter.on(",").splitToList(courseIds);

		if(CollectionUtils.isEmpty(courseIdList)){
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}

		courseMapper.deleteByCourseIds(courseIdList);
		return ServerResponse.createBySuccessMessage("删除成功");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ServerResponse updateCourse(Course course) {
		logger.info(course.getCourseId());
		logger.info(course.getCourseName());

		int i = courseMapper.updateByPrimaryKeySelective(course);
		if (i > 0) {
			return ServerResponse.createBySuccessMessage("更新成功");
		} else {
			return ServerResponse.createByErrorMessage("更新失败");
		}
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ServerResponse<PageInfo> getCourseList(int pageNum, int pageSize,String majorName) {
		//使用分页插件,核心代码就这一行
		PageHelper.startPage(pageNum,pageSize);
		// 获取
		List<Course> courseList;
		if(StringUtils.isNoneBlank(majorName)) {
			courseList = courseMapper.getListByMajorName(majorName);
		} else {
			courseList = courseMapper.getList();
		}
		PageInfo<Course> pageInfo = new PageInfo<>(courseList);
		return ServerResponse.createBySuccess(pageInfo);
	}
}
