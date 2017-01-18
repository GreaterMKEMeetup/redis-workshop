package org.gmjm.web.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController()
public class BannerController
{

	private BoundValueOperations<String,String> valueOperations;

	@Autowired
	public BannerController(RedisTemplate<String,String> redisTemplate)
	{
		this.valueOperations = redisTemplate.boundValueOps("banner");
	}

	@RequestMapping(path="/banner",method=GET, headers="Accept=text/plain")
	public String getBanner(HttpServletResponse respone) throws IOException
	{
		 return valueOperations.get();
	}

	@RequestMapping(path="/banner",method=PUT)
	public void putBanner(@RequestBody  String banner) throws IOException
	{
		valueOperations.set(banner,60, TimeUnit.SECONDS);
	}

}
