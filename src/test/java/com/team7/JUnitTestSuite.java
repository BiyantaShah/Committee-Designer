package com.team7;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
   SearchTest.class,ImplementQueryBuilderTest.class,ImplementLoginTest.class,ImplementParseDatabaseTest.class
})

public class JUnitTestSuite {

}
