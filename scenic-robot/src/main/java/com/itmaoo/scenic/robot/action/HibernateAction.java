package com.itmaoo.scenic.robot.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.itmaoo.scenic.robot.entity.po.Chengyu;
import com.itmaoo.scenic.robot.entity.po.Ciyu;
import com.itmaoo.scenic.robot.repository.ChengyuRepository;
import com.itmaoo.scenic.robot.repository.CiyuRepository;
import com.itmaoo.scenic.support.ExcelData;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by wan on 2017/1/17.
 */
@Controller
@RequestMapping("/hibernate")
@EnableAutoConfiguration
public class HibernateAction {

    @Autowired
    private ChengyuRepository chengyuRepository;
    
    @Autowired
    private CiyuRepository ciyuRepository;

    @RequestMapping("getUserById")
    @ResponseBody
    public Chengyu getUserById(@RequestParam("id")Integer id) {
    	Chengyu u = chengyuRepository.findOne(id);
    	System.out.println("userRepository: " + chengyuRepository);
    	System.out.println("id: " + id);
        return u;
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public void saveUser() {
    	Chengyu cy = new Chengyu();
        cy.setChengyu("一线太难"); 
    	//chengyuRepository.save(cy);
    }
    @RequestMapping("saveCiyu")
    @ResponseBody
    public void saveCiyu() {
    	File file = new File("/mao/cnword.xlsx");
    	try {
			List<String[]> list = ExcelData.getExcelData(file);
			for( String[] ss:list){
				Ciyu cy = new Ciyu();
				cy.setCiyu(ss[1]);
				cy.setMingci(new Boolean(ss[2]));
				cy.setDongci(new Boolean(ss[3]));
				cy.setXingci(new Boolean(ss[4]));
				cy.setFuci(new Boolean(ss[5]));
				cy.setLiangci(new Boolean(ss[6]));
				cy.setNishengci(new Boolean(ss[7]));
				cy.setJiegouzhuci(new Boolean(ss[8]));
				cy.setZhuci(new Boolean(ss[9]));
				cy.setBinglielianci(new Boolean(ss[10]));
				cy.setLianci(new Boolean(ss[11]));
				cy.setJieci(new Boolean(ss[12]));
				cy.setDaici(new Boolean(ss[13]));
				cy.setYiwenci(new Boolean(ss[14]));
				cy.setShuci(new Boolean(ss[15]));
				cy.setChengyu(new Boolean(ss[16]));
		    	ciyuRepository.save(cy);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    

}
