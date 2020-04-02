package org.szh.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.szh.bean.ShapeInfo;
import org.szh.service.ShapeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "ShapeController" })
@RestController
@RequestMapping(value = "test")
public class ShapeController {

	@Resource
	private ShapeService shapeService;

	@ApiOperation(value = "获取某条信息", notes = "获取某条信息")
	@RequestMapping(value = "/{markId}", method = RequestMethod.GET)
	public Object getInfoByMark(@PathVariable("markId") String markId) {
		ShapeInfo base = shapeService.getInfo(markId);
		return base;
	}

	@ApiOperation(value = "保存信息", notes = "保存信息")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@RequestBody ShapeInfo base) {
		return shapeService.save(base);
	}
	
	@ApiOperation(value = "信息列表", notes = "信息列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Object listInfo() {
		return shapeService.getList();
	}
	
	@ApiOperation(value = "更新", notes = "更新")
	@RequestMapping(value = "/update", method = RequestMethod.PATCH)
	public Object updateInfo(@RequestParam String markId) {
		return shapeService.updateInfo(markId);
	}
}
