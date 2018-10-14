package com.pm.user;

public final class UserQuery {
    public final static String ALL = "SELECT u FROM UserEntity u";
    public final static String ALL_ACTIVE = "SELECT u FROM UserEntity u WHERE u.status = ?1";
}
