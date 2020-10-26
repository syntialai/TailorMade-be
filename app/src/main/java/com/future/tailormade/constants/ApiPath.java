package com.future.tailormade.constants;

public class ApiPath {

    public static final String API_PATH = "/api";

    /**
     * Users API Path
     */
    public static final String USERS = API_PATH + "/users";
    public static final String USERS_SIGN_IN = USERS + "/_sign-in";
    public static final String USERS_SIGN_OUT = USERS + "/_sign-out";
    public static final String USERS_SIGN_UP = USERS + "/_sign-up";
    public static final String USERS_REFRESH_TOKEN = USERS + "/_refresh-token";
}