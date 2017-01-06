package org.gmjm.web.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.gmjm.model.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController()
public class BannerController
{

	@Resource(name="appRedisTemplate")
	ValueOperations<String,String> valueOperations;




	@RequestMapping(path="/banner",method=GET, headers="Accept=text/plain")
	public String getBanner(HttpServletResponse respone) throws IOException
	{
		 String banner = valueOperations.get("banner");

		 Long remainingTime = valueOperations.getOperations().getExpire("banner");

		 return banner;
	}

	@RequestMapping(path="/banner",method=PUT)
	public void putBanner(@RequestBody  String banner) throws IOException
	{
		valueOperations.set("banner",banner,60, TimeUnit.SECONDS);
	}

}
