package com.project.inventory.exceptionhandling;


import java.time.LocalDate;

public class ErrorResponse {

	private LocalDate date;
	private String mesage;
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMesage() {
		return mesage;
	}
	public void setMesage(String mesage) {
		this.mesage = mesage;
	}
	
	
}
