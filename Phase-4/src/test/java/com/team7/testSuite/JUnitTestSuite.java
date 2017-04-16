package com.team7.testSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.team7.parseTest.ImplementAuthorAffTest;
import com.team7.parseTest.ImplementCommitteesTest;
import com.team7.parseTest.ImplementHomePageTest;
import com.team7.parseTest.ImplementParseDatabaseTest;
import com.team7.parseTest.ImplementSchemaDBTest;
import com.team7.parseTest.ImplementUniAuthorTest;
import com.team7.parseTest.ImplementUniCountryTest;
import com.team7.queryTest.ImplementQueryBuilderTest;
import com.team7.queryTest.ImplementSearchTest;
import com.team7.queryTest.SearchParameterTest;
import com.team7.uiTest.ImplementDisplayUITest;
import com.team7.uiTest.ImplementFavUITest;
import com.team7.uiTest.ImplementLoginTest;
import com.team7.uiTest.ImplementLoginUITest;
import com.team7.uiTest.ImplementPubDetailsUITest;
import com.team7.uiTest.ImplementRegisterTest;
import com.team7.uiTest.ImplementRegisterUITest;
import com.team7.uiTest.ImplementSearchUITest;
import com.team7.uiTest.ImplementSimilarAuthorsUI;

//JUnit Suite Test
@RunWith(Suite.class)

@Suite.SuiteClasses({ 
   ImplementRegisterTest.class,ImplementSearchTest.class,
   ImplementQueryBuilderTest.class,ImplementLoginTest.class,
   ImplementParseDatabaseTest.class,ImplementSchemaDBTest.class,
   ImplementCommitteesTest.class, SearchParameterTest.class, 
   ImplementLoginUITest.class, ImplementRegisterUITest.class,
   ImplementSearchUITest.class, ImplementDisplayUITest.class, 
   ImplementPubDetailsUITest.class, ImplementSimilarAuthorsUI.class,
   ImplementFavUITest.class,ImplementUniAuthorTest.class, 
   ImplementUniCountryTest.class,
   ImplementHomePageTest.class, ImplementAuthorAffTest.class
})

public class JUnitTestSuite {

}
