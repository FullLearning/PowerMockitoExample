package com.full.mockito;

import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

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
    
    
    public void testStaticMethod() {
    		// TODO:
    	    //spy(CollaboratorForPartialMocking.class);
	    //when(CollaboratorForPartialMocking.staticMethod()).thenReturn("I am a static mock method.");
    }
    
    
}
