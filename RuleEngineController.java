package com.any.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.any.entity.Rule;
import com.any.entity.User;
import com.any.service.RuleEngineService;

@RestController
@RequestMapping("/api")
public class RuleEngineController 
{
	@Autowired
	private RuleEngineService ruleEngineService;
	
	
	@PostMapping("/users")
	public String addUser(@ModelAttribute User user)
	{
		ruleEngineService.addUser(user);
		return "User Added Successfully!";
	}
	
	@PostMapping("/rules")
	public String addRule(@ModelAttribute Rule rule)
	{
		ruleEngineService.addRule(rule);
		return "Rule Added Successfully!";
	}
	
	@GetMapping("/check-eligibility")
	public String checkEligibility(@RequestParam Long userId)
	{
		boolean eligible = ruleEngineService.checkEligibility(userId);
		
		return eligible ? "User is eligible" : "User is not eligible";
	}
}
