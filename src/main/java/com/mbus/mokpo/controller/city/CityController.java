package com.mbus.mokpo.controller.city;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mbus.mokpo.service.city.IGetCityCodeList;
import com.mbus.mokpo.service.city.impl.GetCityCodeList;
import com.mbus.mokpo.vo.city.Greeting;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/city")
public class CityController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final static Logger logger = LoggerFactory.getLogger(CityController.class);


	@RequestMapping(value="/cdlist")
	public Greeting cdlist(@RequestParam(value="name", defaultValue="World") String name, HttpServletRequest req) {
		
		if(logger.isInfoEnabled()) {
			logger.info("[" + req.getContextPath() + req.getServletPath() + "] Controller");
		}
		IGetCityCodeList iCity = new GetCityCodeList();
		iCity.getCityCodeList();
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name), "2018");
	}
	
}
