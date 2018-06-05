package com.full.mockito;

import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.mockito.PowerMockito.mock;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.  Must include the package names of the classes being tested
 * in the "fullyQualifiedNames" annotation PrepareForTest.  In this case, the class
 * called CollaboratorForPrivateMethod is in the package "com.full.mockito.*"
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.full.mockito.*")
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    
    /*
     * This demonstrates how to test a method that contains private methods that need
     * to be mocked.  The benefit is that no refactoring is needed, or at least very 
     * minimal refactoring, in order to make the code testable.
     */
    public void testPrivateMethod() throws Exception
    {
    	//if(true) return;
    	
    		System.out.println("\n\ntestPrivateMethod:: Running tests....");
    	
    		// We must instantiate the original, untouched object
	    	CollaboratorForPartialMocking collaborator = new CollaboratorForPartialMocking();
	    	
	    	/*
	    	 * Now we create another CollaboratorForPartialMocking object, decorated
	    	 * with the "spy" method. This is referred to as the "mock object"
	    	 */
	    	CollaboratorForPartialMocking mock = spy(collaborator);
	    	
	    	/* 
	    	 * Here we replace the privateMethod in the mock object with a different 
	    	 * String return value.
	    	 */
	    	when(mock, "privateMethod", "hello").thenReturn("I am a private mock method.");
	    	when(mock, "privateMethod", "hello2").thenReturn("I am the second call to a private mock method.");
	   
	    	/* 
	    	 * Here we call the PUBLIC method that depends on the private method. The 
	    	 * original logic is in the public method, except the private method it
	    	 * is calling is replaced with the return value "I am a private mock method."
	    	 */ 
	    	String input = "hello";
	    	String returnValue = mock.privateMethodCaller(input);
	    	String returnValue2 = mock.privateMethodCaller("hello2");
	    	
	    	
	    	/*
	    	 * Here we call the public method that contains the mocked private method,
	    	 * but we call it with the original object and the one that was decorated
	    	 * with the spy. When calling the original object, we get the original 
	    	 * behavior. When calling the mock, we get the result from the mocked
	    	 * private method.
	    	 */
	    	System.out.println("Calling \"privateMethodCaller\" which contains the mocked private method. Note differences:\n");
	    	System.out.println("- Original object... collaborator.privateMethodCaller() -> " + collaborator.privateMethodCaller(input));
	    	System.out.println("- Mocked object..... mock.privateMethodCaller() -> " + mock.privateMethodCaller(input) + "\n");
	    	System.out.println("- Mocked object..... mock.privateMethodCaller() -> " + mock.privateMethodCaller("dsfdsafdsafsa") + "\n");
	    	
	    	
	    	/*
	    	 * Here we call another public method which contains a private method, but
	    	 * in this case, we didn't mock the method, so in both calls, the original
	    	 * code runs, and the original results return. The purpose of this code is 
	    	 * to show that the mock object is a copy of the original object, except
	    	 * that the mock object's private methods can be mocked.
	    	 */
	    	System.out.println("Calling \"thisIsNotMocked\" which contains only original code. Note similarities:\n");
	    	System.out.println("- Original object... collaborator.thisIsNotMocked() -> " + collaborator.thisIsNotMocked());
	    	System.out.println("- Mocked object..... mock.thisIsNotMocked() -> " + mock.thisIsNotMocked());
	    	
	    	
	    	// Here we validate that expected equals actual. 
	    	assertEquals("I am a private mock method. Welcome to the Java world.", returnValue);
	    	assertEquals("I am the second call to a private mock method. Welcome to the Java world.", returnValue2);
	    	
	    	
	    	
    }
    
    
    /*
     * Here we test the result of calling an object, which calls a method of another object instantiated within that
     * method. In order to mock the method of the class created inside, we must first spy and mock an instance of the 
     * "ClassReferencedFromOuterClass".  
     * 
     * Next, we must use "whenNew" to tell the system to use the innerMock whenever an instance of 
     * ClassReferencedFromOuterClass is created.
     * 
     * Finally, we invoke any methods on the wrapper class
     */
    public void testMockObjectCalledWithinAnotherClass() throws Exception {
    	
    		System.out.println("\n\ntestMockObjectCalledWithinAnotherClass:: Running tests....");
    	
    		// create a mock, using spy.
    	    ClassReferencedFromOuterClass classReferencedFromOuterClass = new ClassReferencedFromOuterClass();
    	    ClassReferencedFromOuterClass innerMock = mock(ClassReferencedFromOuterClass.class);
    	    
    	    // mock the "getData" method so it returns a dummy value when we pass parameter "one".
    	    when(innerMock, "getData", org.mockito.Matchers.anyString()).thenReturn("JAMESWASHERE");
    	    
    	    // Now, when "new ClassReferencedFromOuterClass()" is called within any other class, we return the mock
    	    whenNew(ClassReferencedFromOuterClass.class).withNoArguments().thenReturn(innerMock);
    	    
    	    // Test the code by calling "run" method on the OuterClass.
    	    OuterClass outerClass = new OuterClass();  
    	    String result = outerClass.run("one");
    	    
    	    // Output should be "JAMESWASHERE", which is the dummy value.
    	    System.out.println("testMockObjectCalledWithinAnotherClass:: " + result);
    	    
    	    assertEquals("JAMESWASHERE", result);
    	    
    	 // With mocking, functionality not mocked is null.
    	    String otherData = innerMock.someOtherMethod();
    	    System.out.println("Mock:: otherData = " + otherData);
    }
    
    
    /*
     * Here we test the result of calling an object, which calls a method of another object instantiated within that
     * method. In order to mock the method of the class created inside, we must first spy and mock an instance of the 
     * "ClassReferencedFromOuterClass".  
     * 
     * Next, we must use "whenNew" to tell the system to use the innerMock whenever an instance of 
     * ClassReferencedFromOuterClass is created.
     * 
     * Finally, we invoke any methods on the wrapper class
     */
    public void testSpyObjectCalledWithinAnotherClass() throws Exception {
    	
    		System.out.println("\n\ntestSpyObjectCalledWithinAnotherClass:: Running tests....");
    	
    		// create a mock, using spy.
    	    ClassReferencedFromOuterClass classReferencedFromOuterClass = new ClassReferencedFromOuterClass();
    	    ClassReferencedFromOuterClass innerSpy = spy(classReferencedFromOuterClass);
    	    
    	    // mock the "getData" method so it returns a dummy value when we pass parameter "one".
    	    when(innerSpy, "getData", org.mockito.Matchers.anyString()).thenReturn("JAMESWASHERE");
    	    
    	    // Now, when "new ClassReferencedFromOuterClass()" is called within any other class, we return the mock
    	    whenNew(ClassReferencedFromOuterClass.class).withNoArguments().thenReturn(innerSpy);
    	    
    	    // Test the code by calling "run" method on the OuterClass.
    	    OuterClass outerClass = new OuterClass();  
    	    String result = outerClass.run("one");
    	    
    	    // Output should be "JAMESWASHERE", which is the dummy value.
    	    System.out.println("testMockObjectCalledWithinAnotherClass:: " + result);
    	    
    	    assertEquals("JAMESWASHERE", result);
    	    
    	    // With spying, functionality not mocked still exists
    	    String otherData = innerSpy.someOtherMethod();
    	    System.out.println("Spy:: otherData = " + otherData);
    }
    
    
    public void testStaticMethod() {
    		// TODO:
    	    //spy(CollaboratorForPartialMocking.class);
	    //when(CollaboratorForPartialMocking.staticMethod()).thenReturn("I am a static mock method.");
    }
    
    
}
