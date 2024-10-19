package com.any.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rule 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ruleExpression;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRuleExpression() {
		return ruleExpression;
	}
	public void setRuleExpression(String ruleExpression) {
		this.ruleExpression = ruleExpression;
	}
	

}
