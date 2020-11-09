package com.canalplus.technicaltest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@CucumberOptions(plugin = {"summary", "json:target/cucumber-report.json"},
//features = "src/test/resources/adresse.feature")
@RunWith(SpringRunner.class)
@SpringBootTest
class TechnicaltestApplicationTests{
	
	
	@Test
	public void contextLoads() {
	}
	
//	private TestNGCucumberRunner testNGCucumberRunner;
//
//    @BeforeClass(alwaysRun = true)
//    public void setUpClass() {
//
//        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
//
//    }
//
//
//    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
//    public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) throws Throwable {
//
//        testNGCucumberRunner.runScenario(pickle.getPickle());
//
//    }
//
//
//    @DataProvider
//
//    public Object[][] scenarios() {
//
//        return testNGCucumberRunner.provideScenarios();
//
//    }
//
//
//    @AfterClass(alwaysRun = true)
//    public void tearDownClass() {
//
//        testNGCucumberRunner.finish();
//
//    }


}
