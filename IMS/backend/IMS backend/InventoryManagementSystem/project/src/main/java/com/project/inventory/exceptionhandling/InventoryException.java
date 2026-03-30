package com.project.inventory.exceptionhandling;

public class InventoryException extends RuntimeException {
	public InventoryException( String message) {
		super(message);
	}
}