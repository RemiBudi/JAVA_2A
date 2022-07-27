package rdvmanager;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses( { RdvTest.class, RdvListTest.class, RdvManagerTest.class } )
public class RdvManagerTestSuite {

}
