package com.pm.project;

public final class ProjectQuery {
    public final static String ALL = "SELECT p FROM ProjectEntity p";
    public final static String ALL_ACTIVE = "SELECT p FROM ProjectEntity p WHERE p.status = ?1";
}
