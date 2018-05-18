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
 * Unit test for simple App.
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

    /**
     * Rigourous Test :-)
     * @throws Exception 
     */
    public void testApp() throws Exception
    {
    	
//    		CollaboratorWithFinalMethods mock = mock(CollaboratorWithFinalMethods.class);
//    		try {
//				whenNew(CollaboratorWithFinalMethods.class).withNoArguments().thenReturn(mock);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		
//    		CollaboratorWithFinalMethods collaborator = new CollaboratorWithFinalMethods();
//    		//verifyNew(CollaboratorWithFinalMethods.class).withNoArguments();
//    		
//    		collaborator.helloMethod();
//    		
//    	    
        assertTrue( true );
    }
    
    public void testApp2() throws Exception
    {
    	
	    	CollaboratorForPartialMocking collaborator = new CollaboratorForPartialMocking();
	    	CollaboratorForPartialMocking mock = spy(collaborator);
	    
	    	//spy(CollaboratorForPartialMocking.class);
	    //when(CollaboratorForPartialMocking.staticMethod()).thenReturn("I am a static mock method.");
	    	System.out.println("Running test 2...");
	    	when(mock, "privateMethod").thenReturn("I am a private mock method.");
	    //	PowerMock.expectPrivate(mock, "privateMethod").andReturn("I am a private mock method.");
	    	String returnValue = mock.privateMethodCaller();
	    	
	    	assertEquals("I am a private mock method. Welcome to the Java world.", returnValue);
	    	
	    	
    }
    
    
}
