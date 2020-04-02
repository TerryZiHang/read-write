package org.szh.service;

import java.util.List;

import org.szh.bean.ShapeInfo;

public interface ShapeService {
	
	int save(ShapeInfo shape);
	
	ShapeInfo getInfo(String markId);

	List<ShapeInfo> getList();

	int updateInfo(String markId);

}
