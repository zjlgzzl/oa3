/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.GenericException
 */
package com.common.util;

import java.util.HashMap;

public class GenericException
extends Exception {
    private String key;
    private String value;
    private String[] keys;
    private String[] values;
    private HashMap<String, String> map;

    public GenericException() {
    }

    public GenericException(String info) {
        super(info);
    }

    public GenericException(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public GenericException(String[] keys, String[] values) {
        this.keys = keys;
        this.values = values;
    }

    public GenericException(HashMap<String, String> map) {
        this.map = map;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getKeys() {
        return this.keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public String[] getValues() {
        return this.values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public HashMap<String, String> getMap() {
        return this.map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}

