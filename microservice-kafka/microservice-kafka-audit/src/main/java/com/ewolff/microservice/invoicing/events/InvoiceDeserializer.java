package com.ewolff.microservice.audit.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ewolff.microservice.audit.Invoice;

public class InvoiceDeserializer extends JsonDeserializer<Invoice> {

	public InvoiceDeserializer() {
		super(Invoice.class);
	}

}
