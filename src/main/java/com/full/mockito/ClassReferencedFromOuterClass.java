package com.full.mockito;

public class ClassReferencedFromOuterClass {

	public String getData(String parameter1) {
		return "Here is the REAL data";
	}
	
	public String someOtherMethod() {
		return "Yes, other methods send real data";
	}
}
