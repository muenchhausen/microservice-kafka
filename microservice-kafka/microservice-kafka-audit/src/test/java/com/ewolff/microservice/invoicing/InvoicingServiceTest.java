package com.ewolff.microservice.audit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuditTestApp.class, webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class auditServiceTest {

	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private AuditService auditService;

	@Test
	public void ensureIdempotencySecondCallIgnored() {
		long countBefore = auditRepository.count();
		Audit audit = new Audit(42,
				new Customer(23, "Eberhard", "Wolff", "eberhard.wolff@innoq.com"),
				new Date(0L), new Address("Krischstr. 100", "40789", "Monheim am Rhein"), new ArrayList<AuditLine>());
		auditService.generateAudit(audit);
		assertThat(auditRepository.count(), is(countBefore + 1));
		assertThat(auditRepository.findOne(42L).getUpdated().getTime(), equalTo(0L));
		audit = new Audit(42,
				new Customer(23, "Eberhard", "Wolff", "eberhard.wolff@innoq.com"),
				new Date(), new Address("Krischstr. 100", "40789", "Monheim am Rhein"), new ArrayList<AuditLine>());
		auditService.generateAudit(audit);
		assertThat(auditRepository.count(), is(countBefore + 1));
		assertThat(auditRepository.findOne(42L).getUpdated().getTime(), equalTo(0L));
	}

}
