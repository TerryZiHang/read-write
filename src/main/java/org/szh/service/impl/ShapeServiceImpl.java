package org.szh.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.szh.annotation.ReadDataSource;
import org.szh.annotation.WriteDataSource;
import org.szh.bean.ShapeInfo;
import org.szh.mapper.ShapeInfoMapper;
import org.szh.service.ShapeService;

/**
 * @author Terry Zi 
 *
 */
@Service("shapeService")
public class ShapeServiceImpl implements ShapeService{
	
	@Resource
	private ShapeInfoMapper shapeInfoMapper;

	@WriteDataSource
	@Override
	public int save(ShapeInfo shape) {
		shape.setMarkId(UUID.randomUUID().toString());
		return shapeInfoMapper.insertSelective(shape);
	}
	
	@ReadDataSource
	@Override
	public ShapeInfo getInfo(String markId) {
		return shapeInfoMapper.selectByPrimaryKey(markId);
	}

	@ReadDataSource
	@Override
	public List<ShapeInfo> getList() {
		return shapeInfoMapper.selectList();
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int updateInfo(String markId) {
		ShapeInfo base  = shapeInfoMapper.selectByPrimaryKey(markId);
		base.setTheme("包子脸");
		return shapeInfoMapper.updateByPrimaryKey(base);
	}

}
