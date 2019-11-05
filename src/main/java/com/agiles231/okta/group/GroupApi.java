package com.agiles231.okta.group;

import java.util.Collection;
import java.util.Map;

import com.agiles231.okta.group.exception.GroupOperationFailedException;
import com.agiles231.okta.user.User;

public interface GroupApi {

  public Group addGroup(Map<String, Object> profile) throws GroupOperationFailedException;

  public Group getGroup(String id) throws GroupOperationFailedException;
  
  public Collection<Group> getGroups(Integer limit, String after) throws GroupOperationFailedException;

  public Collection<Group> simpleSearchGroups(String q, Integer limit) throws GroupOperationFailedException;

  public Collection<Group> simpleSearchGroups(String q, Integer limit, String after) throws GroupOperationFailedException;

  public Collection<Group> filterGroups(String filter, Integer limit) throws GroupOperationFailedException;

  public Collection<Group> filterGroups(String filter, Integer limit, String after) throws GroupOperationFailedException;

  public Collection<Group> getAllGroups(Integer limit) throws GroupOperationFailedException;
  
  public Group updateGroup(String id, Map<String, Object> profile) throws GroupOperationFailedException;

  public Group removeGroup(String id) throws GroupOperationFailedException;
  
  public Collection<User> getGroupMembers(String id) throws GroupOperationFailedException;
  
  public void addUserToGroup(String id, String userId) throws GroupOperationFailedException;

  public void removeUserFromGroup(String id, String userId) throws GroupOperationFailedException;
  
  public void createGroupRule(GroupRuleCondition condition) throws GroupOperationFailedException;
  
  public void updateGroupRule(String ruleId, GroupRuleCondition condition) throws GroupOperationFailedException;
  
  public Collection<GroupRuleCondition> getGroupRules(Integer limit, String after) throws GroupOperationFailedException;

  public GroupRuleCondition getGroupRule(String ruleId) throws GroupOperationFailedException;

  public void deleteGroupRule(String ruleId) throws GroupOperationFailedException;

  public void activateGroupRule(String ruleId) throws GroupOperationFailedException;

  public void deactivateGroupRule(String ruleId) throws GroupOperationFailedException;
}
