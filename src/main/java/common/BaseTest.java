package common;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseTest {

    protected static SoftAssertImpl softAssertImpl = new SoftAssertImpl();
    protected static List<String> allMethodTest = new LinkedList<String>();
    protected static Class<?> clazz;
    protected static boolean isUpdateCaseExistedStatus;
    protected static boolean isUpdateTestResult;

    @Rule
    public final TestName name = new TestName();

    @Before
    public void beforeTest() {
        clazz = this.getClass();
        softAssertImpl = new SoftAssertImpl();
    }

    @After
    public void afterTest() {
        allMethodTest.add(name.getMethodName());
        isUpdateCaseExistedStatus = isUpdateCaseExistedStatus();
        isUpdateTestResult = isIsUpdateTestResult();
    }

    public boolean isUpdateCaseExistedStatus() {
        return false;
    }

    public boolean isIsUpdateTestResult() {
        return false;
    }

}
