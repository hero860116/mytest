package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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