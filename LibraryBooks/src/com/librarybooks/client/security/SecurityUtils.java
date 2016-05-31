package com.librarybooks.client.security;

import com.google.gwt.user.client.Cookies;

public class SecurityUtils {
    private static final String KEY = "authUsername";

    public static String getAuthenticatedUsername() {

        return Cookies.getCookie(KEY);
    }

    public static void setAuthenticatedUsername(String username) {
        if (username == null) {
            Cookies.removeCookie(KEY);
        } else {
            Cookies.setCookie(KEY, username);
        }
    }
}
