package com.quiz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quiz.dto.ExamDto;
import com.quiz.dto.GenerateExam;
import com.quiz.model.Account;
import com.quiz.model.Exam;
import com.quiz.model.ExamQuestion;
import com.quiz.model.History;
import com.quiz.model.Question;
import com.quiz.model.Test;
import com.quiz.service.ExamQuestionService;
import com.quiz.service.ExamService;
import com.quiz.service.HistoryService;
import com.quiz.service.QuestionService;
import com.quiz.service.SubjectService;
import com.quiz.service.TestServie;

@Controller
public class TestController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ExamService examService;

	@Autowired
	private ExamQuestionService examQuestionService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private TestServie testService;

	private Date startTest;
	private History historyForNotifi = null;

	@RequestMapping("test")
	public String test(Model model) {
		return pagination(1, null, model);
	}

	@RequestMapping("test/page/{pageNo}")
	public String pagination(@PathVariable(name = "pageNo") int pageNo, @Param("subjectId") Integer subjectId,
			Model model) {
		int pageSize = 6;
		Page<Exam> page = examService.paginationTest(pageNo, pageSize, subjectId);
		List<Exam> listExam = page.getContent();
		model.addAttribute("listExam", listExam);
		if (historyForNotifi != null) {
			model.addAttribute("history", historyForNotifi.getResult());
			historyForNotifi = null;
		} else {
			model.addAttribute("history", null);
		}
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("listSubject", subjectService.listAll());
		model.addAttribute("generateExam", new GenerateExam());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/thamgiathi";
	}

	@RequestMapping("testReal")
	public String testReal(Model model) {
		return paginationReal(1, null, model);
	}

	@RequestMapping("testReal/page/{pageNo}")
	public String paginationReal(@PathVariable(name = "pageNo") int pageNo, @Param("subjectId") Integer subjectId,
			Model model) {
		int pageSize = 6;
		Page<Exam> page;
		page = examService.paginationReal(pageNo, pageSize, subjectId);
		List<Exam> listExam = page.getContent();
		model.addAttribute("listExam", listExam);
		if (historyForNotifi != null) {
			model.addAttribute("history", historyForNotifi.getResult());
			historyForNotifi = null;
		} else {
			model.addAttribute("history", null);
		}
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("listSubject", subjectService.listAll());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		return "html/thamgiathithat";
	}

	@RequestMapping("test/{code}")
	public String testing(@PathVariable(name = "code") String code, Model model) {
		startTest = new Date();
		List<String> listQuestionCode = examQuestionService.get(code);
		List<Question> listQuestion = new ArrayList<Question>();
		for (String obj : listQuestionCode) {
			Question question = questionService.get(obj);
			listQuestion.add(question);
		}
		Exam exam = examService.findExamByCode(code);
		ExamDto examDto = new ExamDto();
		examDto.setSubjectId(exam.getSubjectId());
		examDto.setExamCode(exam.getExamCode());
		model.addAttribute("examDto", examDto);
		model.addAttribute("duration", exam.getDuration());
		model.addAttribute("listQuestion", listQuestion);
		model.addAttribute("listSubject", subjectService.listAll());
		return "html/tienhanhthi";
	}

	@RequestMapping("testing/save")
	public String testingSave(HttpServletRequest request, HttpSession session, Model model) {
		int correct = 0;
		String examCode = request.getParameter("examCode");
		History history = new History();
		history.setExamCode(examCode);
		history.setAccountCode(((Account) session.getAttribute("account")).getAccountCode());
		history.setTestDay(startTest);
		history = historyService.save(history);
		List<String> listQuestionCode = examQuestionService.get(examCode);
		for (String questionCode : listQuestionCode) {
			Question question = questionService.get(questionCode);
			Test test = new Test();
			test.setHistoryId(history.getHistoryId());
			test.setQuestionCode(questionCode);
			test.setAnswer(request.getParameter(questionCode));
			testService.save(test);
			if (question.getAnswer_1().equals(question.getAnswer_correct())) {
				String answerCorrect = "1";
				if (answerCorrect.equals(request.getParameter(questionCode))) {
					correct++;
					continue;
				}
			}
			if (question.getAnswer_2().equals(question.getAnswer_correct())) {
				String answerCorrect = "2";
				if (answerCorrect.equals(request.getParameter(questionCode))) {
					correct++;
					continue;
				}
			}
			if (question.getAnswer_3().equals(question.getAnswer_correct())) {
				String answerCorrect = "3";
				if (answerCorrect.equals(request.getParameter(questionCode))) {
					correct++;
					continue;
				}
			}
			if (question.getAnswer_4().equals(question.getAnswer_correct())) {
				String answerCorrect = "4";
				if (answerCorrect.equals(request.getParameter(questionCode))) {
					testService.save(test);
					correct++;
					continue;
				}
			}
		}
		float result = (10 / (float) listQuestionCode.size()) * correct;
		history.setResult(Math.round(result));
		historyForNotifi = historyService.save(history);
		return "redirect:/test";
	}

	@RequestMapping("test/generate")
	public String generateExam(@ModelAttribute("generateExam") GenerateExam generateExam, HttpServletRequest request,
			HttpServletResponse response) {
		Exam exam = new Exam();
		exam.setExamCode("RD" + examService.findMaxExamId());
		exam.setSubjectId(generateExam.getSubjectId());
		exam.setDuration(generateExam.getDuration());
		exam.setAmountQuestionEasy(generateExam.getTotalQuestionEasy());
		exam.setAmountQuestionNormal(generateExam.getTotalQuestionNormal());
		exam.setAmountQuestionHard(generateExam.getTotalQuestionHard());
		exam.setTotalAmountQuestion(generateExam.getTotalQuestionEasy() + generateExam.getTotalQuestionNormal()
				+ generateExam.getTotalQuestionHard());
		exam = examService.save(exam);
		List<Question> listQuestionEasy = questionService.findQuestionEasy(generateExam.getSubjectId());
		List<Question> listQuestionHard = questionService.findQuestionHard(generateExam.getSubjectId());
		List<Question> listQuestionNormal = questionService.findQuestionNormal(generateExam.getSubjectId());
		for (int i = 0; i < generateExam.getTotalQuestionEasy(); i++) {
			Random rd = new Random();
			int numberQuestion = 0;
			do {
				numberQuestion = rd.nextInt(listQuestionEasy.size());
				if (examQuestionService.findExamQuestionByExamCodeAndQuesionCode(exam.getExamCode(),
						listQuestionEasy.get(numberQuestion).getQuestionCode()) != null) {
					continue;
				} else {
					break;
				}
			} while (true);
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setExamCode(exam.getExamCode());
			examQuestion.setQuestionCode(listQuestionEasy.get(numberQuestion).getQuestionCode());
			examQuestionService.save(examQuestion);
		}
		for (int i = 0; i < generateExam.getTotalQuestionNormal(); i++) {
			Random rd = new Random();
			int numberQuestion = 0;
			do {
				numberQuestion = rd.nextInt(listQuestionNormal.size());
				if (examQuestionService.findExamQuestionByExamCodeAndQuesionCode(exam.getExamCode(),
						listQuestionNormal.get(numberQuestion).getQuestionCode()) != null) {
					continue;
				} else {
					break;
				}
			} while (true);
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setExamCode(exam.getExamCode());
			examQuestion.setQuestionCode(listQuestionNormal.get(numberQuestion).getQuestionCode());
			examQuestionService.save(examQuestion);
		}
		for (int i = 0; i < generateExam.getTotalQuestionHard(); i++) {
			Random rd = new Random();
			int numberQuestion = 0;
			do {
				numberQuestion = rd.nextInt(listQuestionHard.size());
				if (examQuestionService.findExamQuestionByExamCodeAndQuesionCode(exam.getExamCode(),
						listQuestionHard.get(numberQuestion).getQuestionCode()) != null) {
					continue;
				} else {
					break;
				}
			} while (true);
			ExamQuestion examQuestion = new ExamQuestion();
			examQuestion.setExamCode(exam.getExamCode());
			examQuestion.setQuestionCode(listQuestionHard.get(numberQuestion).getQuestionCode());
			examQuestionService.save(examQuestion);
		}
		return "redirect:/test/" + exam.getExamCode();
	}
}
