package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StuDao;
import com.example.demo.dto.Stu;

@Controller
@RequestMapping("jpa")
public class StuController {
	@Autowired private StuDao dao;
	
	/**列表查询
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String findlist(Model model){
		List<Stu> list=dao.findAll();
		model.addAttribute("list", list);
		return "list";
		
	}
	
	/**跳转页面
	 * @return
	 */
	@RequestMapping("toadd")
	public String toadd(){
		return "add";
		
	}
	
	/**添加
	 * @param stu
	 * @return
	 */
	@RequestMapping("add")
	public String add(Stu stu){
		dao.save(stu);
		
		return "redirect:/jpa/list";
		
	}
	/**删除
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Long id){
		dao.deleteById(id);
		
		return "redirect:/jpa/list";
		
	}
	/**去修改
	 * @param id
	 * @return
	 */
	@RequestMapping("toupdate")
	public String toupdate(Long id,Model model){
		Stu stu=dao.findAllById(id);
		model.addAttribute("stu", stu);
		return "update";
		
	}
	@RequestMapping("update")
	public String update(Stu stu){
		dao.saveAndFlush(stu);
		
		return "redirect:/jpa/list";
		
	}
	
	/**登录
	 * @param stu
	 * @return
	 */
	@RequestMapping("login")
	public String login(Stu stu){
		dao.saveAndFlush(stu);
		
		return "redirect:/jpa/list";
		
	}
}
