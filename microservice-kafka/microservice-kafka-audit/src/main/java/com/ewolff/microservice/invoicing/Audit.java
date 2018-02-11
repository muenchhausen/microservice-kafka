package com.ewolff.microservice.audit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Audit {

	@Id
	private long id;

	@Embedded
	private Customer customer;

	private Date updated;

	@Embedded
	private Address billingAddress = new Address();

	@OneToMany(cascade = CascadeType.ALL)
	private List<AuditLine> auditLine;

	public Audit() {
		super();
		auditLine = new ArrayList<AuditLine>();
	}

	public Audit(long id, Customer customer, Date updated, Address billingAddress, List<AuditLine> auditLine) {
		super();
		this.id = id;
		this.customer = customer;
		this.updated = updated;
		this.billingAddress = billingAddress;
		this.auditLine = auditLine;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date created) {
		this.updated = created;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<AuditLine> getAuditLine() {
		return auditLine;
	}

	public void setAuditLine(List<AuditLine> auditLine) {
		this.auditLine = auditLine;
	}

	@Transient
	public void setOrderLine(List<AuditLine> orderLine) {
		this.auditLine = orderLine;
	}

	public void addLine(int count, Item item) {
		this.auditLine.add(new AuditLine(count, item));
	}

	public int getNumberOfLines() {
		return auditLine.size();
	}

	public double totalAmount() {
		return auditLine.stream().map((ol) -> ol.getCount() * ol.getItem().getPrice()).reduce(0.0,
				(d1, d2) -> d1 + d2);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
