package com.full.mockito;

public class ClassReferencedFromOuterClass {

	public String getData(String parameter1) {
		System.out.println("Does this REAL getData method ever get called");
		return "Here is the REAL data";
	}
	
	public String someOtherMethod() {
		return "Yes, other methods send real data";
	}
}
