package com.ewolff.microservice.audit.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.ewolff.microservice.audit.Audit;
import com.ewolff.microservice.audit.AuditService;

@Component
public class OrderKafkaListener {

	private final Logger log = LoggerFactory.getLogger(OrderKafkaListener.class);

	private AuditService auditService;

	public OrderKafkaListener(AuditService auditService) {
		super();
		this.auditService = auditService;
	}

	@KafkaListener(topics = "order")
	public void order(Audit audit, Acknowledgment acknowledgment) {
		log.info("Revceived audit " + audit.getId());
		auditService.generateAudit(audit);
		acknowledgment.acknowledge();
	}

}
