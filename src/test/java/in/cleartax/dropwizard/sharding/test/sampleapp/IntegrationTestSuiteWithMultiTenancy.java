package in.cleartax.dropwizard.sharding.test.sampleapp;

import in.cleartax.dropwizard.sharding.test.sampleapp.application.TestApplication;
import in.cleartax.dropwizard.sharding.test.sampleapp.application.TestConfig;
import io.dropwizard.setup.Environment;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import javax.ws.rs.client.Client;

/**
 * Created on 05/10/18
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({OrderIntegrationTestWithMultiTenancy.class})
public class IntegrationTestSuiteWithMultiTenancy {
    private static final String TEST_CONFIG_PATH =
            ResourceHelpers.resourceFilePath("test_with_multitenant.yml");
    @ClassRule
    public static final DropwizardAppRule<TestConfig> RULE =
            new DropwizardAppRule<>(TestApplication.class, TEST_CONFIG_PATH);
    public static Client client;

    static {
        RULE.addListener(new DropwizardAppRule.ServiceListener<TestConfig>() {
            @Override
            public void onRun(TestConfig configuration, Environment environment, DropwizardAppRule<TestConfig> rule)
                    throws Exception {
                client = TestHelper.onSuiteRun(rule);
            }
        });
    }
}
