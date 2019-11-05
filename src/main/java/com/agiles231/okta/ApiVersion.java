package com.agiles231.okta;

public class ApiVersion {

  private static final String v1 = "v1";
  public static ApiVersion V1 = new ApiVersion("v1");
  
  private final String version;

  private ApiVersion(String version) {
    if (!version.equals(v1)) {
      throw new IllegalArgumentException("Valid version(s): " + v1 + "\nProvided version: " + version);
    } else {
      this.version = version;
    }
  }

  public String getVersion() {
    return version;
  }

  @Override
  public String toString() {
    return version;
  }
  
  
  
}
