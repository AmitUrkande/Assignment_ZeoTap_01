package com.any.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.any.Repository.RuleRepository;
import com.any.Repository.UserRepository;
import com.any.entity.Rule;
import com.any.entity.User;

@Service
public class RuleEngineService 
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RuleRepository ruleRepository;
	
	public User addUser(User user)
	{
		return userRepository.save(user);
	}
	
	public Rule addRule(Rule rule)
	{
		return ruleRepository.save(rule);
	}
	
	public boolean checkEligibility(Long userId)
	{
		Optional<User> user = userRepository.findById(userId);
		List<Rule> rules = ruleRepository.findAll();
		
		if(user.isPresent())
		{
			Map<String, Object> userAttributes = Map.of(
					"age", user.get().getAge(),
					"income", user.get().getIncome(),
					"spend", user.get().getSpend(),
					"department", user.get().getDepartment()
					);
			
			for(Rule rule : rules)
			{
				if(!eveluateRule(rule.getRuleExpression(), userAttributes))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean eveluateRule(String ruleExpression, Map<String, Object> attributes)
	{
		// Example implementation of parsing rule expression and evaluating it
		// Use Java's scripting engine or a custom AST parser to handle complex expression
		
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		
		try
		{
			for(Map.Entry<String, Object> entry : attributes.entrySet())
			{
				engine.put(entry.getKey(), entry.getValue());
			}
			return (Boolean) engine.eval(ruleExpression);
		}
		catch(ScriptException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
