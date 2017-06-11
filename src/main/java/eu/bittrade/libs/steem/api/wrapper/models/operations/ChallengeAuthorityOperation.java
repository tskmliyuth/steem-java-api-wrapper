package eu.bittrade.libs.steem.api.wrapper.models.operations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.bittrade.libs.steem.api.wrapper.enums.OperationType;
import eu.bittrade.libs.steem.api.wrapper.enums.PrivateKeyType;
import eu.bittrade.libs.steem.api.wrapper.exceptions.SteemInvalidTransactionException;
import eu.bittrade.libs.steem.api.wrapper.models.AccountName;
import eu.bittrade.libs.steem.api.wrapper.util.SteemJUtils;

/**
 * This class represents the Steem "challenge_authority_operation" object.
 * 
 * @author <a href="http://steemit.com/@dez1337">dez1337</a>
 */
public class ChallengeAuthorityOperation extends Operation {
    private AccountName challenger;
    private AccountName challenged;
    @JsonProperty("require_owner")
    private Boolean requireOwner;

    /**
     * Create a new challenge authority operation.
     */
    public ChallengeAuthorityOperation() {
        super(PrivateKeyType.ACTIVE);
        // Set default values:
        this.setRequireOwner(false);
    }

    /**
     * @return the challenger
     */
    public AccountName getChallenger() {
        return challenger;
    }

    /**
     * @param challenger
     *            the challenger to set
     */
    public void setChallenger(AccountName challenger) {
        this.challenger = challenger;
    }

    /**
     * @return the challenged
     */
    public AccountName getChallenged() {
        return challenged;
    }

    /**
     * @param challenged
     *            the challenged to set
     */
    public void setChallenged(AccountName challenged) {
        this.challenged = challenged;
    }

    /**
     * @return the requireOwner
     */
    public Boolean getRequireOwner() {
        return requireOwner;
    }

    /**
     * @param requireOwner
     *            the requireOwner to set
     */
    public void setRequireOwner(Boolean requireOwner) {
        this.requireOwner = requireOwner;
    }

    @Override
    public byte[] toByteArray() throws SteemInvalidTransactionException {
        try (ByteArrayOutputStream serializedChallengeAuthorityOperation = new ByteArrayOutputStream()) {
            serializedChallengeAuthorityOperation.write(
                    SteemJUtils.transformIntToVarIntByteArray(OperationType.CHALLENGE_AUTHORITY_OPERATION.ordinal()));
            serializedChallengeAuthorityOperation.write(this.getChallenger().toByteArray());
            serializedChallengeAuthorityOperation.write(this.getChallenged().toByteArray());
            serializedChallengeAuthorityOperation
                    .write(SteemJUtils.transformBooleanToByteArray(this.getRequireOwner()));

            return serializedChallengeAuthorityOperation.toByteArray();
        } catch (IOException e) {
            throw new SteemInvalidTransactionException(
                    "A problem occured while transforming the operation into a byte array.", e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
