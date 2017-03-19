package com.team7;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
   ImplementSearchTest.class,ImplementQueryBuilderTest.class,ImplementLoginTest.class,ImplementParseDatabaseTest.class,ImplementSchemaDBTest.class
})

public class JUnitTestSuite {

}
