/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.MySQLLocalDialect
 *  org.hibernate.Hibernate
 *  org.hibernate.dialect.MySQLDialect
 *  org.hibernate.dialect.function.SQLFunction
 *  org.hibernate.dialect.function.SQLFunctionTemplate
 *  org.hibernate.type.Type
 */
package com.common.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.Type;

public class MySQLLocalDialect
extends MySQLDialect {
    public MySQLLocalDialect() {
        this.registerFunction("convert", (SQLFunction)new SQLFunctionTemplate((Type)Hibernate.STRING, "convert(?1 using ?2)"));
    }
}

