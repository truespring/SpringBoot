package com.apress.spring.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.spring.domain.Journal;
import com.apress.spring.repository.JournalRepository;

@Controller
public class JournalController {

	@Autowired
	JournalRepository repo;
	
	// 해당 메서드를 추가하여 JSON 데이터로 응답하도록 에너테이션을 붙임
	// 데이터 변환은 스프링 부트가 아닌 스프링 MVC 모듈이 수행
	// @ResponseBody를 붙이는 순간 스프링 MVC는 응답 메시지를 JSON 데이터로 변환하는 역할을 할 수 있음
	@RequestMapping(value="/journal", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody List<Journal> getJournal() {
		return repo.findAll();
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("journal", repo.findAll());
		return "index";
	}
}
