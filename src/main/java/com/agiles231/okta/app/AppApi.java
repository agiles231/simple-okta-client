package com.agiles231.okta.app;

import java.util.Collection;
import java.util.Map;

import com.agiles231.okta.app.exception.AppOperationFailedException;

public interface AppApi {

  public App addApp(Boolean activate, String name, String signOnMode, Map<String, Object> settings) throws AppOperationFailedException;
  
  public App getApp(String id) throws AppOperationFailedException;
  
  public Collection<App> getApps(Integer limit, String expand, String after) throws AppOperationFailedException;

  public Collection<App> simpleSearchApps(String q, Integer limit, String expand) throws AppOperationFailedException;

  public Collection<App> simpleSearchApps(String q, Integer limit, String expand, String after) throws AppOperationFailedException;

  public Collection<App> filterApps(Integer limit, String filter, String expand) throws AppOperationFailedException;

  public Collection<App> filterApps(Integer limit, String filter, String expand, String after) throws AppOperationFailedException;

  public Collection<App> getAllApps(Integer limit, String expand) throws AppOperationFailedException;
  
  public App updateApp(String id, App app) throws AppOperationFailedException;
  
  public void deleteApp(String id) throws AppOperationFailedException;
  
  public void activateApp(String id) throws AppOperationFailedException;
  
  public void deactivateApp(String id) throws AppOperationFailedException;
  
  public AppUser assignUserToApp(String appId, String userId, String scope, AppUserCredentials credentials
      , Map<String, Object> profile) throws AppOperationFailedException;
  
  public AppUser getAppUser(String appId, String userId) throws AppOperationFailedException;
  
  public Collection<AppUser> getAppUsers(String appId, Integer limit) throws AppOperationFailedException;

  public Collection<AppUser> getAppUsers(String appId, Integer limit, String after) throws AppOperationFailedException;

  public Collection<AppUser> getAppUsers(String appId, String q, Integer limit) throws AppOperationFailedException;
                                                     
  public Collection<AppUser> getAppUsers(String appId, String q, Integer limit, String after) throws AppOperationFailedException;

  public Collection<AppUser> getAllAppUsers(String appId, Integer limit) throws AppOperationFailedException;
  
  public AppUser updateAppUser(String appId, String userId, AppUserCredentials credentials, Map<String, Object> profile) throws AppOperationFailedException;

  public AppUser removeAppUser(String appId, String userId) throws AppOperationFailedException;
  
  public AppGroup assignGroupToApp(String appId, String groupId, AppGroup appGroup) throws AppOperationFailedException;
  
  public AppGroup getAppGroup(String appId, String groupId) throws AppOperationFailedException;

  public Collection<AppGroup> getAppGroups(String appId, Integer limit, String after) throws AppOperationFailedException;

  public Collection<AppGroup> getAllAppGroups(String appId, Integer limit) throws AppOperationFailedException;
  
  public void removeGroupFromApp(String appId, String groupId) throws AppOperationFailedException;
}
