package com.itmaoo.scenic.robot.nuke.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Table;

import com.itmaoo.scenic.robot.entity.po.Ciyu;
import com.itmaoo.scenic.robot.entity.yuan.CiyuKeySearchYuan;
import com.itmaoo.scenic.robot.repository.CiyuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.itmaoo.scenic.robot.entity.po.Chengyu;
import com.itmaoo.scenic.robot.entity.po.LinkWindow;
import com.itmaoo.scenic.robot.entity.yuan.ChenyuKeySearchYuan;
import com.itmaoo.scenic.robot.repository.ChengyuRepository;
import com.mysql.fabric.xmlrpc.base.Array;
import org.springframework.util.CollectionUtils;

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
	@Autowired
	private CiyuRepository ciyuRepository;

	public Map<Integer,Ciyu> analysis(String words, String punctuation) {
		DefaultSs ss = new DefaultSs();


		// 获取强词 不可拆分词组 成语
		Map<Chengyu,ArrayList<LinkWindow>> chengyuMap = getChengyuMap(words);
		
		// 超高频率常用语

		// 获取句子拆分关键词 连词 介词
		
		// 句子拆分
		// 1. 组词 每个字词组查询 找到所有命中的词组
		Map<Ciyu,ArrayList<LinkWindow>> ciyuMap = getCiyuMap(words);
		// 2 验证所有正确的词组组合


		Map<Integer,ArrayList<Ciyu>>  indexCiyu = new HashMap<>();
		for (Entry<Ciyu,ArrayList<LinkWindow>> ciyuEntry:ciyuMap.entrySet()){
			for (LinkWindow lw:ciyuEntry.getValue()) {
				if (indexCiyu.get(lw.getStart())==null) {
					ArrayList<Ciyu> ciyus = new ArrayList<>();
					ciyus.add(ciyuEntry.getKey());
					indexCiyu.put(lw.getStart(),ciyus);
				}else{
					indexCiyu.get(lw.getStart()).add(ciyuEntry.getKey());
				}

			}
		}
		List<Map<Integer,Ciyu>> allAsemble = new ArrayList();

		getAllAsemble(words,allAsemble,words.length(),indexCiyu,0,null);

		// 3 获取准确率最高的词组
		//去除分开与组合相同

		for(Map<Integer,Ciyu> aa:allAsemble){
			 for (int count = 0; count<words.length();count++){
			 	if(aa.get(count)!=null){
			 		System.out.print(aa.get(count).getCiyu()+"|");
				}
			 }
			System.out.println("");
		}
		Map<Integer,Ciyu> shotSize = null;
		for(Map<Integer,Ciyu> aa:allAsemble){

			if(shotSize==null){
				shotSize = aa;
			}else if(aa.size()<shotSize.size()) {
				shotSize = aa;
			}
		}
		System.out.println("<<<ß");
		for (int count = 0; count<words.length();count++){
			if(shotSize.get(count)!=null){
				System.out.print(shotSize.get(count).getCiyu()+"|");
			}
		}
		return shotSize;
	}

	private void getAllAsemble(String words,List<Map<Integer,Ciyu>> allAsemble,Integer size,Map<Integer,ArrayList<Ciyu>>  indexCiyu, Integer index,Map<Integer,Ciyu> st){
		if(st==null){
			st = new HashMap<>();
		}
		Ciyu ciyu = new Ciyu();
		ciyu.setCiyu(words.substring(index,index+1));
		st.put(index,ciyu);
		if(indexCiyu.get(index)!=null){
			for(Ciyu cy:indexCiyu.get(index)){
				Map<Integer,Ciyu> st2 = new HashMap<>();
				st2.putAll(st);
				st2.put(index,cy);

				if(index+cy.getCiyu().length()<size){
					getAllAsemble(words,allAsemble, size, indexCiyu, index + cy.getCiyu().length(),st2);
				}else{
					allAsemble.add(st2);
				}
			}
		}
		if(index+1>=size){
			allAsemble.add(st);
		}else{
			getAllAsemble(words,allAsemble, size, indexCiyu, index + 1,st);
		}

	}

	private Map<Chengyu,ArrayList<LinkWindow>> getChengyuMap(String words) {
		Map<Chengyu,ArrayList<LinkWindow>> chengyuMap = new HashMap<>();

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
		return chengyuMap;
	}
	private Map<Ciyu,ArrayList<LinkWindow>> getCiyuMap(String words) {
		Map<Ciyu,ArrayList<LinkWindow>> chengyuMap = new HashMap<>();

		ArrayList<String> wordList = new ArrayList<String>();
		Map<Integer,CiyuKeySearchYuan> indexMap = new HashMap<>();
		for(int count=0; count<words.length();count++){
			List<Ciyu> list = ciyuRepository.findByCiyuLike(words.substring(count, count+1));
			CiyuKeySearchYuan cksy = new CiyuKeySearchYuan();
			cksy.setKey(words.substring(count, count+1));
			cksy.setChengyuList(list);
			indexMap.put(count, cksy);
		}
		Map<Ciyu,ArrayList<LinkWindow>> chengyuLinks = new HashMap<>();
		for(Entry<Integer, CiyuKeySearchYuan> entry:indexMap.entrySet()){
			if(entry.getValue().getChengyuList()!=null){
				for(Ciyu chengyu:entry.getValue().getChengyuList()){
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
		for(Entry<Ciyu, ArrayList<LinkWindow>> chengYu:chengyuLinks.entrySet()){
			for(LinkWindow linkWindow:chengYu.getValue()){
				if(chengYu.getKey().getCiyu().equals(linkWindow.getChengyu())){
					if(chengyuMap.get(chengYu.getKey())==null){
						chengyuMap.put(chengYu.getKey(), new ArrayList<>());
					}
					chengyuMap.get(chengYu.getKey()).add(linkWindow);
				}
			}
		}
		return chengyuMap;
	}
}
