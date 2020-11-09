package com.canalplus.technicaltest.model.enumeration;

public enum AdresseStatus {

	ACTIVE("Active"),
	INACTIVE("Inactive");
	
	public final String status;
	 
    private AdresseStatus(String status) {
        this.status = status;
    }
}
