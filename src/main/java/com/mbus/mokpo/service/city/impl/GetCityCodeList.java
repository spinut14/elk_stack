package com.mbus.mokpo.service.city.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.mbus.mokpo.config.MbusConfig;
import com.mbus.mokpo.config.MbusConfigInfo;
import com.mbus.mokpo.controller.city.CityController;
import com.mbus.mokpo.service.city.IGetCityCodeList;

@Service
public class GetCityCodeList implements IGetCityCodeList {

	private final static Logger logger = LoggerFactory.getLogger(GetCityCodeList.class);	
	public MbusConfigInfo config;	
	@Override
	public void getCityCodeList() {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(MbusConfig.class);
		config = ctx.getBean("getConfigInfo", MbusConfigInfo.class);
		byte[] brrSvc = Base64.decodeBase64(config.getSvcKey());
		String svcKey = new String(brrSvc);

		logger.info("[config] : ["+config+"]");
        if(logger.isDebugEnabled()) {
        	logger.debug("[config] : ["+config+"]");
		}	
		
        try {
        	
            String apiURL = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getCtyCodeList";
            apiURL = apiURL + "?serviceKey="+svcKey+"&_type=json";
            logger.info("[api URL] : ["+apiURL+"]");
            if(logger.isDebugEnabled()) {
            	logger.debug("[api URL] : ["+apiURL+"]");
    		}
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            // 200 에러가 아닌 다른 response code 발생가능하며
            // 200 에러인 경우가 있는데 데이터가 없이 HTML 태그만 보이는 경우도 있음
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
        } catch (Exception e) {
            
        }
        ctx.close();
	}
}
