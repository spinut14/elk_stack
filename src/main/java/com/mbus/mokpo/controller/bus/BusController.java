package com.mbus.mokpo.controller.bus;

import java.util.concurrent.atomic.AtomicLong;

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
@RequestMapping(value="/bus")
public class BusController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final static Logger logger = LoggerFactory.getLogger(BusController.class);


	@RequestMapping(value = "/nolist")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		
		if(logger.isInfoEnabled()) {
			logger.info("Test Home Controller");
		}
		IGetCityCodeList iCity = new GetCityCodeList();
		iCity.getCityCodeList();
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name), "2018");
	}
	
}
