package com.agiles231.okta.user;

public class HashedPassword {

    private final String algorithm;
    private final String value;
    private final String salt;
    private final Integer workFactor;
    private final String saltOrder;
    
    public HashedPassword(String algorithm, String value, String salt, Integer workFactor, String saltOrder) {
        super();
        this.algorithm = algorithm;
        this.value = value;
        this.salt = salt;
        this.workFactor = workFactor;
        this.saltOrder = saltOrder;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getValue() {
        return value;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getWorkFactor() {
        return workFactor;
    }

    public String getSaltOrder() {
        return saltOrder;
    }
    
}
