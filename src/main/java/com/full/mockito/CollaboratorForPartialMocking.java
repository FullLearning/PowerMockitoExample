package com.full.mockito;

public class CollaboratorForPartialMocking {
    public static String staticMethod() {
        return "Hello Baeldung!";
    }
 
    public final String finalMethod() {
        return "Hello Baeldung!";
    }
 
    private String privateMethod() {
        return "Hello Baeldung!";
    }
 
    public String privateMethodCaller() {
        return privateMethod() + " Welcome to the Java world.";
    }
    
    public String thisIsNotMocked() {
    		return "hello world!" + someOtherPrivateMethodWeWillNotMock();
    }
    
    private String someOtherPrivateMethodWeWillNotMock() {
    		return "This is the original String value";
    }
}