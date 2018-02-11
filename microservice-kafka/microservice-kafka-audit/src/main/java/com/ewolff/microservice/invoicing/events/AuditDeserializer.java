package com.ewolff.microservice.audit.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ewolff.microservice.audit.Audit;

public class AuditDeserializer extends JsonDeserializer<Audit> {

	public AuditDeserializer() {
		super(Audit.class);
	}

}
