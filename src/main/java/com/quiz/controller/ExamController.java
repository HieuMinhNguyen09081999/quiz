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

import com.quiz.dto.ExamDto;
import com.quiz.model.Account;
import com.quiz.model.Exam;
import com.quiz.model.ExamQuestion;
import com.quiz.model.Question;
import com.quiz.model.Subject;
import com.quiz.service.ExamQuestionService;
import com.quiz.service.ExamService;
import com.quiz.service.QuestionService;
import com.quiz.service.SubjectService;

@Controller
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ExamQuestionService examQuestionService;

	@RequestMapping("/exam")
	public String listAll(Model model) {
		return pagination(1, null, model);
	}

	@RequestMapping("/exam/page/{pageNo}")
	public String pagination(@PathVariable(name = "pageNo") int pageNo,@Param("keyword") String keyword, Model model) {
		int pageSize = 5;
		Page<Exam> page = examService.pagination(pageNo, pageSize, keyword);
		List<Exam> listExam = page.getContent();
		List<Subject> listSubject = subjectService.listAll();
		model.addAttribute("keyword", keyword);
		model.addAttribute("exam", new ExamDto());
		model.addAttribute("listExam", listExam);
		model.addAttribute("listSubject", listSubject);
		model.addAttribute("listQuestion", questionService.listAll());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/quanlydethi";
	}

	@RequestMapping(value = "exam/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("exam") ExamDto examDto, HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		Exam exam = examDto.getExamId() == null ? new Exam() : examService.get(examDto.getExamId());
		int totalQuestion = 0;
		int totalQuestionHard = 0;
		int totalQuestionNormal = 0;
		int totalQuestionEasy = 0;
		if (examDto.getExamCode() == null) {
			exam.setExamCode("EX" + (examService.findMaxExamId() + 1));
		} else {
			exam.setExamCode(examDto.getExamCode());
			exam.setUpdateDate(new Date());
			exam.setUpdateBy(account.getAccountId());
			List<ExamQuestion> listExamQuestions = examQuestionService.getExamQuestionByExamCode(examDto.getExamCode());
			examQuestionService.deleteAll(listExamQuestions);
		}
		exam.setSubjectId(examDto.getSubjectId());
		exam.setDuration(examDto.getDuration());
		exam.setStartTime(examDto.getStartTime());			
		exam.setCreateBy(account.getAccountId());
		exam.setCreateDate(new Date());
		exam = examService.save(exam);
		if(examDto.getListQuestion() != null) {
			for (String obj : examDto.getListQuestion()) {
				ExamQuestion examQuestion = new ExamQuestion();
				Question question = questionService.get(obj);
				examQuestion.setExamCode(exam.getExamCode());
				examQuestion.setQuestionCode(question.getQuestionCode());
				examQuestionService.save(examQuestion);
				totalQuestion++;
				if (question.getLevel() == 0) {
					totalQuestionEasy++;
				}
				if (question.getLevel() == 1) {
					totalQuestionNormal++;
				}
				if (question.getLevel() == 2) {
					totalQuestionHard++;
				}
			}
		}
		exam.setTotalAmountQuestion(totalQuestion);
		exam.setAmountQuestionEasy(totalQuestionEasy);
		exam.setAmountQuestionNormal(totalQuestionNormal);
		exam.setAmountQuestionHard(totalQuestionHard);
		exam = examService.save(exam);
		return "redirect:/exam";
	}

	@RequestMapping("exam/delete/{id}")
	public String delele(@PathVariable(name = "id") Integer id) {
		examService.delete(id);
		return "redirect:/exam";
	}

	@RequestMapping("exam/edit/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Integer id) {
		Exam exam = examService.get(id);
		List<String> listQuestion = examQuestionService.get(exam.getExamCode());
		System.out.println(listQuestion);
		ExamDto examDto = new ExamDto();
		examDto.setExamId(exam.getExamId());
		examDto.setExamCode(exam.getExamCode());
		examDto.setSubjectId(exam.getSubjectId());
		examDto.setDuration(exam.getDuration());
		examDto.setListQuestion(listQuestion);
		List<Subject> listSubject = subjectService.listAll();
		ModelAndView mav = new ModelAndView("html/suaquanlydethi");
		mav.addObject("exam", examDto);
		mav.addObject("listQuestion", questionService.listAll());
		mav.addObject("listQuestionSelected", listQuestion);
		mav.addObject("listSubject", listSubject);
		return mav;
	}
}
