package com.itmaoo.scenic.robot.action;

import java.util.List;

import com.itmaoo.scenic.robot.nuke.Nuke;
import com.itmaoo.scenic.robot.nuke.statement.StatementStructure;
import com.itmaoo.scenic.robot.service.ChatContext;
import com.itmaoo.scenic.robot.service.ChatContextProvider;
import com.itmaoo.scenic.robot.service.NukeBuilder;

public class RobotAction {
	public ChatContextProvider chatContextProvider;
	public NukeBuilder nukeBuilder;
	
	public String chat(String request,String contextid){
		//获取聊天上下文
		ChatContext chatContext = chatContextProvider.getChatContext(contextid);
		//解析语句，语言输入处理
		StatementStructure requstStatement = nukeBuilder.buildRequestStatement(chatContext,request);
		
		//构建请求基本单元，语言输入处理
		Nuke requstNuke = nukeBuilder.buildRequestNuke(chatContext,requstStatement);
		
		//语言输出导向
		Nuke attendNuke = nukeBuilder.buildAttendNuke(chatContext,requstNuke);
		
		//知识库筛选， 语言输出处理
		List<Nuke> nukes = nukeBuilder.matchNukes(attendNuke);
		
		//组织语言，语言输出处理
		StatementStructure resStatement = nukeBuilder.buildResponseNuke(nukes,attendNuke,requstNuke);
		
		//验证 回复内容相关度达到某个数值
		
		return resStatement.toString();
	}
}
