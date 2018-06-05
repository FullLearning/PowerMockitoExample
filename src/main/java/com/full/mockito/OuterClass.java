package com.full.mockito;

public class OuterClass {
	
	ClassReferencedFromOuterClass classReferencedFromOuterClass = new ClassReferencedFromOuterClass();

	public String run(String parameter1) {
		String data = classReferencedFromOuterClass.getData(parameter1);
		return data;
	}
}
