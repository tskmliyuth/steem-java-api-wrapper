package eu.bittrade.libs.steemj.plugins.apis.account.by.key;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import eu.bittrade.libs.steemj.BaseIT;
import eu.bittrade.libs.steemj.IntegrationTest;
import eu.bittrade.libs.steemj.communication.CommunicationHandler;
import eu.bittrade.libs.steemj.exceptions.SteemCommunicationException;
import eu.bittrade.libs.steemj.exceptions.SteemResponseException;
import eu.bittrade.libs.steemj.plugins.apis.account.by.key.models.GetKeyReferencesArgs;
import eu.bittrade.libs.steemj.protocol.AccountName;
import eu.bittrade.libs.steemj.protocol.PublicKey;

/**
 * This class contains all test connected to the
 * {@link eu.bittrade.libs.steemj.plugins.apis.account.by.key.AccountByKeyApi
 * AccountByKeyApi}.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class AccountByKeyApiIT extends BaseIT {
    private static CommunicationHandler COMMUNICATION_HANDLER;

    /**
     * Setup the test environment.
     * 
     * @throws SteemCommunicationException
     *             If a communication error occurs.
     */
    @BeforeClass
    public static void init() throws SteemCommunicationException {
        setupIntegrationTestEnvironment();

        COMMUNICATION_HANDLER = new CommunicationHandler();
    }

    /**
     * Test the
     * {@link eu.bittrade.libs.steemj.plugins.apis.account.by.key.AccountByKeyApi#getKeyReferences(CommunicationHandler, eu.bittrade.libs.steemj.plugins.apis.account.by.key.models.GetKeyReferencesArgs)}
     * method.
     * 
     * @throws SteemCommunicationException
     *             If a communication error occurs.
     * @throws SteemResponseException
     *             If the response is an error.
     */
    @Category({ IntegrationTest.class })
    @Test
    public void testGetBlock() throws SteemCommunicationException, SteemResponseException {
        final List<AccountName> accountList = AccountByKeyApi
                .getKeyReferences(COMMUNICATION_HANDLER,
                        new GetKeyReferencesArgs(
                                Arrays.asList(new PublicKey("STM79rHgAa75LkJJrhhr4fSM8ccBoLwod3HaAAxzihNRFKZSvTvZ5"))))
                .getAccounts();

        assertThat(accountList.size(), equalTo(1));
        assertThat(accountList.get(0), equalTo(new AccountName("dez1337")));
    }
}
