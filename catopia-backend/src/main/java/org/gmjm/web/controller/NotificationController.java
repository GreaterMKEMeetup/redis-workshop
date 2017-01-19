package org.gmjm.web.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notifications")
public class NotificationController
{

	public static final String NOTIFICATIONS = "notifications:";
	public static final long MAX_NOTIFICATIONS = 10L;

	@Resource(name="appRedisTemplate")
	RedisTemplate<String,String> redisTemplate;

	private BoundListOperations<String,String> getList(String userId) {

		if(StringUtils.isEmpty(userId)) {
			throw new IllegalArgumentException("userId cannot be null, or empty");
		} else if( userId.length() > 30) {
			throw new IllegalArgumentException("userId exceeded max length of 30");
		}

		return redisTemplate.boundListOps(NOTIFICATIONS + userId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<String> usersWithMessages() {

		return redisTemplate.keys(NOTIFICATIONS + "*")
			.stream()
			.map(s -> s.substring(s.indexOf(':') + 1))
			.collect(Collectors.toList());

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId","countOnly"})
	public String notificationCountForUser(@RequestParam String userId, @RequestParam Boolean countOnly) {

		return getList(userId).size().toString();

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId"})
	public List<String> notificationsForUser(@RequestParam String userId) {

		return getList(userId).range(0,-1);

	}

	@RequestMapping(method = RequestMethod.POST)
	public void addNotificationForUser(@RequestParam String userId, @RequestParam String text) {

		BoundListOperations<String,String> boundList = getList(userId);
		boundList.leftPush(text);
		boundList.trim(0,MAX_NOTIFICATIONS -1);

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void removeNotification(@RequestParam String userId, @RequestParam int index) {

		try {
			//Lists don't allow removal by index, but this is a nice trick.
			BoundListOperations<String,String> boundList = getList(userId);
			String uuid = UUID.randomUUID().toString();
			boundList.set(index, uuid);
			boundList.remove( 1, uuid);

		} catch (InvalidDataAccessApiUsageException e) {
			throw new IllegalArgumentException("Key does not exist, or index was out of bounds.");
		}

	}


}

