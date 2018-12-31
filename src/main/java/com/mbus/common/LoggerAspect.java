package com.mbus.common;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mbus.mokpo.constant.ComConstants;

@Aspect
public class LoggerAspect {


//	@Before("execution(* com.mbus.*Controller.*(..)) || execution(* com.mbus.*Service.*(..)) || execution(* com.mbus.*Dao.*(..))")
	@Before("execution(* com.mbus.mokpo.rescontroller.city.*.*(..)) or execution(* com.mbus.mokpo.controller.city.*.*(..))")
	public void startLog(JoinPoint jp) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = req.getServletPath();
		String finPath = ComConstants.BLANK;
		if(ComConstants.ROOT_PATH.equals(path)) {
			MDC.put("trxPath", path);
		}else {
			int idx = path.indexOf(ComConstants.ROOT_PATH, ComConstants.ONE_INT);
			if(-1 == idx) {
				finPath = path.substring(1, path.length());
			}else {
				finPath = path.substring(1, idx);
			}
			
			MDC.put("trxPath", finPath);
		}
		System.out.println("req path : " + req.getServletPath());

	}
	
	
//	@After("execution(* com.mbus.*Controller.*(..)) || execution(* com.mbus.*Service.*(..)) || execution(* com.mbus.*Dao.*(..))")
	@After("execution(* com.mbus.mokpo.rescontroller.city.*.*(..))")
	public void endLog(JoinPoint jp) {
		MDC.remove("trxPath");
	}
}
