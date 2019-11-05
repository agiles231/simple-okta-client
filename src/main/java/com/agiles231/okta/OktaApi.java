package com.agiles231.okta;

import com.agiles231.okta.app.AppApi;
import com.agiles231.okta.auth.AuthApi;
import com.agiles231.okta.group.GroupApi;
import com.agiles231.okta.user.UserApi;

public interface OktaApi {

  public UserApi getUserApi();
  
  public AppApi getAppApi();

  public GroupApi getGroupApi();
  
  public AuthApi getAuthApi();
}
