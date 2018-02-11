package com.ewolff.microservice.audit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

	private final Logger log = LoggerFactory.getLogger(AuditService.class);

	private AuditRepository auditRepository;

	@Autowired
	public AuditService(AuditRepository auditRepository) {
		super();
		this.auditRepository = auditRepository;
	}

	@Transactional
	public void generateAudit(Audit audit) {
		if (auditRepository.exists(audit.getId())) {
			log.info("Audit id {} already exists - ignored", audit.getId());
		} else {
			auditRepository.save(audit);
		}
	}

}
