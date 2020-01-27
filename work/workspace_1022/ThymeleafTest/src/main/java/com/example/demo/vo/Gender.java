package com.example.demo.vo;

public enum Gender {

	FEMALE("Female gender"),
	MALE("Male gender");
	
	private String description;
	
	Gender(final String description){
		// -> parameter가 final이면 절대 값이 갱신되지 않는다는 것을 보장함 
		
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
}
