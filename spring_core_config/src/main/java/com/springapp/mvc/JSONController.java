package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 在测试新功能的时候，要额外注意版本的变更，因为不同的版本实现的方式不一样
 * 在看别人测试用例的时候一定要牢牢注意被人的版本环境
 */
@Controller
@RequestMapping("/t")
public class JSONController {

	@RequestMapping( method = RequestMethod.GET)
	public @ResponseBody
    Map<String, Object> getShopInJSON() {


        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("name", "lwl") ;
		return maps;

	}

}