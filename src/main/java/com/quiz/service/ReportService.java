package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.dto.Report;
import com.quiz.model.Exam;
import com.quiz.repository.ExamRepository;

@Service
public class ReportService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ExamRepository examRepository;

	public List<Report> getReportAccount() {
		List<Report> list = new ArrayList<Report>();
		String sql = "SELECT YEAR(a.create_date) AS year, count(*) AS total FROM account a "
				+ "GROUP BY YEAR(a.create_date)" + "ORDER BY YEAR(a.create_date)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		for (Object[] obj : result) {
			Report report = new Report();
			report.setYear(Double.parseDouble(obj[0].toString()));
			report.setTotal(Double.parseDouble(obj[1].toString()));
			list.add(report);
		}
		return list;
	}

	public List<Report> getReportQuestion() {
		List<Report> list = new ArrayList<Report>();
		String sql = "SELECT YEAR(a.create_date) AS year, count(*) AS total FROM question a "
				+ "GROUP BY YEAR(a.create_date)" + "ORDER BY YEAR(a.create_date)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		for (Object[] obj : result) {
			Report report = new Report();
			report.setYear(Double.parseDouble(obj[0].toString()));
			report.setTotal(Double.parseDouble(obj[1].toString()));
			list.add(report);
		}
		return list;
	}

	public List<Report> getReportExam() {
		List<Report> list = new ArrayList<Report>();
		String sql = "SELECT YEAR(a.create_date) AS year, count(*) AS total FROM exam a "
				+ "GROUP BY YEAR(a.create_date)" + "ORDER BY YEAR(a.create_date)";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		for (Object[] obj : result) {
			Report report = new Report();
			report.setYear(Double.parseDouble(obj[0].toString()));
			report.setTotal(Double.parseDouble(obj[1].toString()));
			list.add(report);
		}
		return list;
	}

	public List<Double> getRatioLevelExam() {
		List<Double> list = new ArrayList<Double>();
		List<Exam> listExam = examRepository.findAll();
		int totalEasy = 0;
		int totalNormal = 0;
		int totalHard = 0;
		int total = listExam.size();
		for (Exam obj : listExam) {
			if ((obj.getAmountQuestionHard() / obj.getTotalAmountQuestion()) > 0.2) {
				totalHard++;
			}
			if (obj.getAmountQuestionHard() / obj.getTotalAmountQuestion() > 0.05
					|| obj.getAmountQuestionNormal() / obj.getTotalAmountQuestion() > 0.65
					|| obj.getAmountQuestionEasy() / obj.getTotalAmountQuestion() > 0.3) {
				totalNormal++;
			}
			if (obj.getAmountQuestionHard() / obj.getTotalAmountQuestion() > 0
					|| obj.getAmountQuestionNormal() / obj.getTotalAmountQuestion() > 0.5
					|| obj.getAmountQuestionEasy() / obj.getTotalAmountQuestion() > 0.5) {
				totalEasy++;
			}
		}

		list.add((double) (totalEasy / total) * 100);
		list.add((double) (totalNormal / total) * 100);
		list.add((double) (totalHard / total) * 100);
		System.out.println(list);
		return list;
	}

}
