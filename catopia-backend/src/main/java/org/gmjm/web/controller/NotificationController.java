package org.gmjm.web.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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

	@RequestMapping(method = RequestMethod.GET)
	public List<String> usersWithMessages() {

		return new ArrayList<>();

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId","countOnly"})
	public String notificationCountForUser(@RequestParam String userId, @RequestParam Boolean countOnly) {

		return "";

	}

	@RequestMapping(method = RequestMethod.GET, params = {"userId"})
	public List<String> notificationsForUser(@RequestParam String userId) {

		return new ArrayList<>();

	}

	@RequestMapping(method = RequestMethod.POST)
	public void addNotificationForUser(@RequestParam String userId, @RequestParam String text) {

	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void removeNotification(@RequestParam String userId, @RequestParam int index) {

	}

}
