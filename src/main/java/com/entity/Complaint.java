package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Complaints")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "complaint_id")
	private int complaintId;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "complaint_description", nullable = false)
	private String description;

	private String priority;

	private String status;

	@ManyToOne
	@JoinColumn(name = "resident_id")
	private Resident resident;

	public Complaint() {
		super();
	}

	public Complaint(int complaintId, String category, String description, String priority, String status) {
		super();
		this.complaintId = complaintId;
		this.category = category;
		this.description = description;
		this.priority = priority;
		this.status = status;
	}

	public Complaint(String category, String description, String priority, String status, Resident resident) {
		super();
		this.category = category;
		this.description = description;
		this.priority = priority;
		this.status = status;
		this.resident = resident;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

}
