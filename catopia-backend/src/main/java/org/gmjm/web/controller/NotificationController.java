package org.gmjm.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notifications")
public class NotificationController
{
	
	private static String NOTIFICATION_KEYSPACE = "notifications:";

	@Value("${catopia.notifications.maxperuser}")
	int maxNotificationsPerUser;


	@Autowired
	RedissonClient redissonClient;

	RList<String> getList(String userId) {
		if(StringUtils.isEmpty(userId) && userId.length() > 0){
			throw new IllegalArgumentException("userId must have a length > 0");
		}
		return redissonClient.getList( NOTIFICATION_KEYSPACE + userId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<String> usersWithMessages() {

		return redissonClient.getKeys().findKeysByPattern(NOTIFICATION_KEYSPACE + "*")
			.stream()
			.map(fullKey -> fullKey.substring(fullKey.indexOf(':') + 1))
			.collect(Collectors.toList());

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId","countOnly"})
	public String notificationCountForUser(@RequestParam String userId, @RequestParam Boolean countOnly) {

		return Integer.toString(getList(userId).size());

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId"})
	public List<String> notificationsForUser(@RequestParam String userId) {

		return getList(userId).readAll();

	}

	@RequestMapping(method = RequestMethod.POST)
	public void addNotificationForUser(@RequestParam String userId, @RequestParam String text) {
		getList(userId).add(0,text);
		getList(userId).trim(0,maxNotificationsPerUser - 1);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void removeNotification(@RequestParam String userId, @RequestParam int index) {
		getList(userId).remove(index);
	}

}
