# PowerMockitoExample

## Goal

The goal of PowerMock is to partially mock an object so that we can override private methods in methods we want to test, which contain private methods.

## Details

This is a very short example project, based on [Intro to PowerMock by Baeldung](http://www.baeldung.com/intro-to-powermock).

Here, we try the example ourselves in order to verify that it is indeed possible to mock a private method!

While it is impossible to extend or override a private method, there is a workaround using the reflection API.  PowerMock uses the reflection API in order to accomplish this.

## Class/Package structure

Many of you coming from the background of Google Plugin for Eclipse are used to seeing a different project structure than what just about every other build tool uses, including Gradle, Maven, and Cloud Tools for Eclipse plugin. The structure is as follows:

**- src/main/java:**  This contains production code.  You will find a class called [CollaboratorForPartialMocking](https://github.com/FullLearning/PowerMockitoExample/blob/master/src/main/java/com/full/mockito/CollaboratorForPartialMocking.java), which contains some simple methods that return strings.  Two of the methods return a concatenation of a call to private methods.

**- src/test/java:**  This contains unit test code. It is kept separate from production for the following reasons:

1. We do not deploy unit test code to Google App Engine. It's for developers and other engineers to run locally only.
2. It keeps the code more organized, since unit test code is kept in mirrored packages in the test folder.  

![Example of Project Structure with src and test folders](https://github.com/FullLearning/PowerMockitoExample/blob/master/docs/ProjectStructure-src-test.png "Example of Project Structure with src and test folders")

For example, the package for [AppTest](https://github.com/FullLearning/PowerMockitoExample/blob/master/src/test/java/com/full/mockito/AppTest.java) is the same as the package for the production code it's testing.  See AppTest to view the unit tests as well as how the mock was created and tested.  

In this code, you'll see that the mock is only partially mocked. Only the private method is mocked so that, when we unit test the public method, it doesn't make calls to resources we don't want to be called. 

![Example of how to mock a private method using the static method "when"](https://github.com/FullLearning/PowerMockitoExample/blob/master/docs/OverridePrivateUsingWhen.png "Example of how to mock a private method using the static method \"when\"")


Instead, we test the _logic_ not resources the app depends on, like databases, web services, API calls, or File I/O reads. A general rule is that unit tests _never_ make calls to these resources. Instead, they rely on mocks.

