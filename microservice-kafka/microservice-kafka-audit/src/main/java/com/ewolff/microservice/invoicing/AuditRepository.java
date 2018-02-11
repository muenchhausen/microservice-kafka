package com.ewolff.microservice.audit;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuditRepository extends PagingAndSortingRepository<Audit, Long> {

	@Query("SELECT max(i.updated) FROM Audit i")
	Date lastUpdate();

}
