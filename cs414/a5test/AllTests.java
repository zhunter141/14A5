package cs414.a5test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTests.class,SquareTests.class,DeedTests.class,PlayerTests.class,
	TokenTests.class,BankTests.class,AccountTests.class,TokenTests.class,UtilityTest.class,
	RailRoadTest.class,DiceTest.class })
public class AllTests {

}
