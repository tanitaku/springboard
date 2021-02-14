package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;

@Controller
public class BoardController {
	
	@Autowired
	BoardRepository repos;
	
	/* 一覧画面への遷移 */
	@GetMapping
	public ModelAndView list() {
	ModelAndView mav = new ModelAndView();
	List<SpringBoard> list = repos.findAll();
	mav.setViewName("boards/list");
	mav.addObject("data", list);
	return mav;
	}
	
	/* 新規画面への遷移 */
	@GetMapping("/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		SpringBoard data = new SpringBoard();
		mav.addObject("formModel", data);
		mav.setViewName("boards/new");
		return mav;
	}
	
	/* 編集画面への遷移 */
	@GetMapping("/edit")
	ModelAndView edit(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		SpringBoard data = repos.findById(id);
		mav.addObject("formModel", data);
		mav.setViewName("boards/new");
		return mav;
	}
	
	/* 更新処理  */
	@PostMapping()
	@Transactional(readOnly=false)
	public ModelAndView save(
			@ModelAttribute("formModel") SpringBoard board) {
		repos.saveAndFlush(board);
		return new ModelAndView("redirect:users/list");
	}
	
	/* 詳細画面への遷移 */
	@GetMapping("/show")
	ModelAndView show(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		SpringBoard data = repos.findById(id);
		mav.addObject("formModel", data);
		mav.setViewName("boards/show");
		return mav;
	}
	

}
