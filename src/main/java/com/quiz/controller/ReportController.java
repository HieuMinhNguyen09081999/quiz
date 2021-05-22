package com.quiz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.quiz.dto.Report;
import com.quiz.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("report")
	public String report(Model model) {
		List<Report> listReportAccout = reportService.getReportAccount();
		List<Report> listReportQuestion = reportService.getReportQuestion();
		List<Report> listReportExam = reportService.getReportExam();
		List<Double> listRatioExam = reportService.getRatioLevelExam();
		Map<Double,Double> reportAccount = new HashMap<Double, Double>();
		Map<Double,Double> reportQuestion = new HashMap<Double, Double>();
		Map<Double,Double> reportExam = new HashMap<Double, Double>();
		for(Report obj : listReportAccout) {
			reportAccount.put(obj.getYear(), obj.getTotal());
		}
		for(Report obj : listReportQuestion) {
			reportQuestion.put(obj.getYear(), obj.getTotal());
		}
		for(Report obj : listReportExam) {
			reportExam.put(obj.getYear(), obj.getTotal());
		}
		model.addAttribute("reportAccount", reportAccount);
		model.addAttribute("reportQuestion", reportQuestion);
		model.addAttribute("reportExam", reportExam);
		model.addAttribute("hard", listRatioExam.get(2));
		model.addAttribute("normal", listRatioExam.get(1));
		model.addAttribute("easy", listRatioExam.get(0));
		return "html/thongke";
	}
}
