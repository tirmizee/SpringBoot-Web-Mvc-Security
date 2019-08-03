package com.tirmizee.backend.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Controller
@RequestMapping("/export")
public class ExportController {

	@Autowired 
	private DataSource dataSource;
	
	@Autowired 
	private ApplicationContext appContext;
	
	@GetMapping(path = "/user.pdf")
	public ModelAndView user(ModelAndView modelAndView) throws SQLException {
		Map<String, Object> model = new HashMap<>();
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
	    view.setUrl("classpath:reports/report.jrxml");
	    view.setApplicationContext(appContext);
	    modelAndView.addAllObjects(model);
	    modelAndView.setView(view);
		return modelAndView;
	}
	
	@GetMapping(path = "/test.pdf")
	public ModelAndView test(ModelAndView modelAndView) throws SQLException {
		Map<String, Object> model = new HashMap<>();
//		model.put(JasperParameter.REPORT_CONNECTION, connection);
		model.put("stDate", "01/06/2562");
		model.put("edDate", "30/06/2562");
		model.put("citizenId", null);
		model.put("fullname", null);
		model.put("age", null);
		model.put("searchBankCode",null);
		model.put("searchBranchCode",null);
		model.put("userAdd", null);
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
	    view.setUrl("classpath:reports/Nsf_013_1.jrxml");
	    view.setApplicationContext(appContext);
	    modelAndView.addAllObjects(model);
	    modelAndView.setView(view);
		return modelAndView;
	}
	
	@GetMapping(path = "/province{provinceCode}.pdf")
	public ModelAndView province(@PathVariable String provinceCode) throws SQLException {
		
		Map<String, Object> model = new HashMap<>();
		model.put("provinceCode", provinceCode);
//		model.put(JasperParameter.REPORT_CONNECTION, connection);
		
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setJdbcDataSource(dataSource);
	    view.setUrl("classpath:reports/report_group.jrxml");
	    view.setApplicationContext(appContext);
	    return new ModelAndView(view, model);
	}
	
}
