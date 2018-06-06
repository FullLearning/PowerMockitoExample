package com.full.mockito;

public class StaticClass {

	public static String getStaticData(String input)  {
		System.out.println("The method we're trying to stub RAN! " + input);
		return "This is the REAL static response.";
	}
}
