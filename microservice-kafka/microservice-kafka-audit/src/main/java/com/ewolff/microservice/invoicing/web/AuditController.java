package com.ewolff.microservice.audit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ewolff.microservice.audit.AuditRepository;

@Controller
public class AuditController {

	private AuditRepository auditRepository;

	@Autowired
	public AuditController(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView Item(@PathVariable("id") long id) {
		return new ModelAndView("audit", "audit", auditRepository.findOne(id));
	}

	@RequestMapping("/")
	public ModelAndView ItemList() {
		return new ModelAndView("auditlist", "audits", auditRepository.findAll());
	}

}
