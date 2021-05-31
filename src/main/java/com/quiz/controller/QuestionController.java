package com.quiz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quiz.model.Account;
import com.quiz.model.Question;
import com.quiz.model.Subject;
import com.quiz.service.QuestionService;
import com.quiz.service.SubjectService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private SubjectService subjectService;

	@RequestMapping("question")
	public String listAll(Model model) {
		return pagination(1, null, model);
	}
	
	@RequestMapping("question/page/{pageNo}")
	public String pagination(@PathVariable(name = "pageNo") int pageNo, @Param("keyword") String keyword, Model model) {
		int pageSize = 5;
		Page<Question> page = questionService.pagination(pageNo, pageSize, keyword);
		List<Question> listQuestion = page.getContent();
		List<Subject> listSubject = subjectService.listAll();
		model.addAttribute("questionNew", new Question());
		model.addAttribute("listSubject", listSubject);
		model.addAttribute("listQuestion", listQuestion);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/quanlycauhoi";
	}

	@RequestMapping(value = "question/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("questionNew") Question question, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		Question temp = null;
		if (question.getQuestionCode() == null) {
			question.setQuestionCode("Q" + (questionService.getMaxId() + 1));
			question.setCreateBy(account.getAccountId());
			question.setCreateDate(new Date());
		} else {
			temp = questionService.get(question.getQuestionId());
			question.setCreateBy(temp.getCreateBy());
			question.setCreateDate(temp.getCreateDate());
			question.setUpdateBy(account.getAccountId());
			question.setUpdateDate(new Date());
		}
		if (question.getAnswer_correct().equals("1")) {
			question.setAnswer_correct(question.getAnswer_1());
		} else if (question.getAnswer_correct().equals("2")) {
			question.setAnswer_correct(question.getAnswer_2());
		} else if (question.getAnswer_correct().equals("3")) {
			question.setAnswer_correct(question.getAnswer_3());
		} else if (question.getAnswer_correct().equals("4")) {
			question.setAnswer_correct(question.getAnswer_4());
		}
		questionService.save(question);
		return "redirect:/question";
	}

	@RequestMapping("question/delete/{id}")
	public String delete(@PathVariable(name = "id") Integer id) {
		questionService.delete(id);
		return "redirect:/question";
	}

	@RequestMapping("question/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Integer id) {
		Question question = questionService.get(id);
		List<Subject> listSubject = subjectService.listAll();
		ModelAndView mav = new ModelAndView("html/suaquanlycauhoi");
		mav.addObject("listSubject", listSubject);
		mav.addObject("questionNew", question);
		return mav;
	}
}
