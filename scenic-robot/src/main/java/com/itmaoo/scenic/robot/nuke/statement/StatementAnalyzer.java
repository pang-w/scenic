package com.itmaoo.scenic.robot.nuke.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.itmaoo.scenic.robot.entity.po.Chengyu;
import com.itmaoo.scenic.robot.entity.po.LinkWindow;
import com.itmaoo.scenic.robot.entity.yuan.ChenyuKeySearchYuan;
import com.itmaoo.scenic.robot.repository.ChengyuRepository;
import com.mysql.fabric.xmlrpc.base.Array;

/**
 * 我去吃饭
 * 
 * @author mario
 *
 */
@Repository
@Qualifier("statementAnalyzer")
public class StatementAnalyzer {
	@Autowired
	private ChengyuRepository chengyuRepository;

	public StatementStructure analysis(String words, String punctuation) {
		DefaultSs ss = new DefaultSs();

		// 获取强词 不可拆分词组 成语
		ArrayList<String> wordList = new ArrayList<String>();
		Map<Integer,ChenyuKeySearchYuan> indexMap = new HashMap<>();
		for(int count=0; count<words.length();count++){
			List<Chengyu> list = chengyuRepository.findByChengyuLike(words.substring(count, count+1));
			ChenyuKeySearchYuan cksy = new ChenyuKeySearchYuan();
			cksy.setKey(words.substring(count, count+1));
			cksy.setChengyuList(list);
			indexMap.put(count, cksy);
		}
		Map<Chengyu,ArrayList<LinkWindow>> chengyuLinks = new HashMap<>();
		for(Entry<Integer, ChenyuKeySearchYuan> entry:indexMap.entrySet()){
			if(entry.getValue().getChengyuList()!=null){
				for(Chengyu chengyu:entry.getValue().getChengyuList()){
					if(chengyuLinks.get(chengyu)==null){
						ArrayList<LinkWindow> links = new ArrayList<>();
						links.add(new LinkWindow(entry.getKey(),entry.getValue().getKey()));
						chengyuLinks.put(chengyu, links);
					}else{
						ArrayList<LinkWindow> links = chengyuLinks.get(chengyu);
						boolean linked = false;
						for(LinkWindow link:links){
							if(link.doLink(entry.getKey(),entry.getValue().getKey())){
								linked = true;
							}
						}
						if(!linked){
							links.add(new LinkWindow(entry.getKey(), entry.getValue().getKey()));
						}
					}
					
				}
			}
		}
		Map<Chengyu,ArrayList<LinkWindow>> chengyuMap = new HashMap<>();
		for(Entry<Chengyu, ArrayList<LinkWindow>> chengYu:chengyuLinks.entrySet()){
			for(LinkWindow linkWindow:chengYu.getValue()){
				if(chengYu.getKey().getChengyu().equals(linkWindow.getChengyu())){
					if(chengyuMap.get(chengYu.getKey())==null){
						chengyuMap.put(chengYu.getKey(), new ArrayList<>());
					}
					chengyuMap.get(chengYu.getKey()).add(linkWindow);
				}
			}
		}
		
		// 超高频率常用语
		
		// 获取句子拆分关键词 连词 介词
		
		// 句子拆分
		// 1. 组词 每个字词组查询 找到所有命中的词组

		// 2 验证所有正确的词组组合

		// 3 获取准确率最高的词组

		return null;
	}
}
