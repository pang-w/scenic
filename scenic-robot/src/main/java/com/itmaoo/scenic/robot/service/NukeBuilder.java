package com.itmaoo.scenic.robot.service;

import java.util.List;
import java.util.Map;

import com.itmaoo.scenic.robot.entity.po.Ciyu;
import com.itmaoo.scenic.robot.nuke.Nuke;
import com.itmaoo.scenic.robot.nuke.statement.StatementAnalyzer;
import com.itmaoo.scenic.robot.nuke.statement.StatementStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NukeBuilder {
	@Autowired
	public StatementAnalyzer analyzer;

	public Map<Integer,Ciyu> buildRequestStatement(ChatContext chatContext, String request) {
		return analyzer.analysis(request,"111");

	}
	
	public Nuke buildRequestNuke(ChatContext chatContext, Map<Integer,Ciyu> requstStatement) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Nuke buildAttendNuke(ChatContext chatContext, Nuke request) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Nuke> matchNukes(Nuke nuke) {
		// TODO Auto-generated method stub
		return null;
	}

	public StatementStructure buildResponseNuke(List<Nuke> nukes, Nuke nuke, Nuke request) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

}
