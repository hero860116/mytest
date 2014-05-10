package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/")
public class HelloController {
    public static AtomicLong count = new AtomicLong(0);

    static {
        new Thread(new PrintTpsRunable()).start();
    }


	@RequestMapping(method = RequestMethod.GET)
    public @ResponseBody long printWelcome() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("message", "test");
        count.incrementAndGet();

		return count.get();
	}
}