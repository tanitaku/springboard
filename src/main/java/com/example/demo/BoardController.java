package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

@Controller
public class BoardController {
	
	@Autowired
	BoardRepository repos;
	
	@Autowired
	DivisionRepository d_repos;
	
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
		// 分類テーブルの読み込み
		List<Division> list = d_repos.findAll();
		mav.addObject("lists", list);
		return mav;
	}
	
	/* 編集画面への遷移 */
	@GetMapping("/edit")
	ModelAndView edit(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		SpringBoard data = repos.findById(id);
		mav.addObject("formModel", data);
		mav.setViewName("boards/new");
		// 分類テーブルの読み込み
		List<Division> list = d_repos.findAll();
		mav.addObject("lists", list);
		return mav;
	}
	
	/* 更新処理  */
	@PostMapping("/create")
	@Transactional(readOnly=false)
	public ModelAndView save(
			@ModelAttribute("formModel") @Validated SpringBoard board, BindingResult result) {
		
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("boards/new");
			return mav;
		}
		
		board.setCreateDate(new Date());
		repos.saveAndFlush(board);
		return new ModelAndView("redirect:/");
	}
	
	/* 詳細画面への遷移 */
	@GetMapping("/show")
	ModelAndView show(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		SpringBoard data = repos.findById(id);
		mav.addObject("data", data);
		// 分類テーブルの読み込み
		Division div = d_repos.findById(data.getDivision());
		mav.addObject("div", div);
		mav.setViewName("boards/show");
		return mav;
	}
	
	@PostConstruct
	public void init() {
		SpringBoard board1 = new SpringBoard();
		board1.setCreateUser("島根　花子");
		board1.setContent("あいうえお");
		board1.setTitle("あいうえお");
		Date date = new Date();
		board1.setCreateDate(date);
		board1.setDivision(1);
		repos.saveAndFlush(board1);
		
		// 分類テーブル初期データの登録
		Division div1 = new Division();
		div1.setId(1);
		div1.setName("通達/連絡");
		d_repos.saveAndFlush(div1);
		
		div1 = new Division();
		div1.setId(2);
		div1.setName("会議開催について");
		d_repos.saveAndFlush(div1);
		
		div1 = new Division();
		div1.setId(3);
		div1.setName("スケジュール");
		d_repos.saveAndFlush(div1);
		
		div1 = new Division();
		div1.setId(4);
		div1.setName("イベント");
		d_repos.saveAndFlush(div1);
		
		div1 = new Division();
		div1.setId(5);
		div1.setName("その他");
		d_repos.saveAndFlush(div1);
	}
	
	

}
