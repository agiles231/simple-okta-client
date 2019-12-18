package com.agiles231.okta.user;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.agiles231.okta.user.exception.UserNotFoundException;
import com.agiles231.okta.user.exception.UserOperationFailedException;

public interface UserApi {

    public User createUser(Map<String, Object> profile, Boolean activate, Boolean provider, UserCredentials credentials,
            List<String> groupIds, String nextLogin) throws UserOperationFailedException;

    public User getClientSelf() throws UserNotFoundException;

    public User getUser(String loginOrId) throws UserNotFoundException;

    public Collection<User> getUsers(Integer limit) throws Exception;

    public Collection<User> getUsers(Integer limit, String after) throws Exception;

    public Collection<User> getAllUsers(Integer limit) throws Exception;

    public Collection<User> filterUsers(String filter, Integer limit) throws Exception;

    public Collection<User> searchUsers(String search, Integer limit) throws Exception;

    public Collection<User> simpleSearchUsers(String q, Integer limit) throws Exception;

    public Collection<User> filterUsers(String filter, Integer limit, String after) throws Exception;

    public Collection<User> searchUsers(String search, Integer limit, String after) throws Exception;

    public Collection<User> simpleSearchUsers(String q, Integer limit, String after) throws Exception;

    public User updateUser(String id, UserCredentials credentials, Map<String, Object> profile)
            throws UserNotFoundException, UserOperationFailedException;

    public User partialUpdateUser(String id, UserCredentials credentials, Map<String, Object> profile)
            throws UserNotFoundException, UserOperationFailedException;

    public Collection<UserGroup> getUserGroups(String userId) throws UserNotFoundException;

    public void deleteUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException;

    public void activateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException;

    public void reactivateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException;

    public void deactivateUser(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException;

    public void suspendUser(String id) throws UserNotFoundException, IllegalStateException;

    public void unsuspendUser(String id) throws UserNotFoundException, IllegalStateException;

    public void unlockUser(String id) throws UserNotFoundException, IllegalStateException;

    public void resetPassword(String id) throws UserNotFoundException, IllegalStateException;

    public URI resetPasswordWithoutEmail(String id) throws UserNotFoundException, IllegalStateException;

    public User expirePassword(String id) throws UserNotFoundException, IllegalStateException;

    public String expirePasswordWithTempPassword(String id) throws UserNotFoundException, IllegalStateException;

    public void resetFactors(String id) throws UserNotFoundException, IllegalStateException;

    public void forgotPassword(String id, Boolean sendEmail) throws UserNotFoundException, IllegalStateException;

    public UserCredentials changePassword(String id, String strict, CredentialsPassword oldPassword,
            CredentialsPassword newPassword) throws UserNotFoundException, IllegalStateException;

    public UserCredentials changeRecoveryQuestion(String id, CredentialsPassword password,
            RecoveryQuestion recoveryQuestion) throws UserNotFoundException, IllegalStateException;

}
