package com.full.mockito;

import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.mockito.PowerMockito.mock;

import org.powermock.api.mockito.PowerMockito;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.full.mockito.*")
public class StaticTest 
    extends TestCase
{
	
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StaticTest( String testName )
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
    
    
    public void testStaticMethodMocking() throws Exception {
    	
    		String expectedResult = "This is a static test.";
    		spy(StaticClass.class);
    		
    		//PowerMockito.mockStatic(StaticClass.class);
    		System.out.println("About to **set up mock** test code...");
    		when(StaticClass.class, "getStaticData", "test").thenReturn("This is a static test.");
    		
    		//when(StaticClass.getStaticData("test")).thenReturn("This is a static test.");
    		//PowerMockito.when(StaticClass.getStaticData("test")).thenReturn("This is a static test.");
    		
    		System.out.println("\nAbout to **run** test code...");
    		String actualResult = StaticClass.getStaticData("test");
    		
    		assertEquals(expectedResult, actualResult);
    		
    		System.out.println("\nCheck the default behavior exists for other input...");
    		assertEquals("This is the REAL static response.", StaticClass.getStaticData("something"));
    }
}

