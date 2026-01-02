package tests;

import base.BaseTest;
import org.testng.annotations.BeforeClass;
import utils.LoginHelper;

public class AuthenticatedBaseTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void login() {
        LoginHelper.loginWithValidUser();
    }
}
