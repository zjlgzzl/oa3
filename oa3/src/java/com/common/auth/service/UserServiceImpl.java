/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.dao.UserDAO
 *  com.common.auth.service.UserService
 *  com.common.auth.service.UserServiceImpl
 *  com.common.entity.TUser
 */
package com.common.auth.service;

import com.common.auth.dao.UserDAO;
import com.common.auth.service.UserService;
import com.common.entity.TUser;
import java.io.Serializable;

public class UserServiceImpl
implements UserService {
    private UserDAO userDAO;

    public TUser getEntityById(Serializable id) {
        return this.userDAO.getEntityById(id);
    }

    public TUser findByUsername(String userName) {
        return this.userDAO.findByUsername(userName);
    }

    public TUser findByEmpid(Serializable id) {
        return this.userDAO.findByEmpid(id);
    }

    public void saveEntity(TUser user) throws Exception {
        this.userDAO.saveEntity(user);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.userDAO.deleteEntity(id);
    }

    public void updateState(Serializable id, short state) throws Exception {
        this.userDAO.updateState(id, state);
    }

    public int checkDup(Serializable id, String userName) {
        return this.userDAO.checkDup(id, userName);
    }

    public int checkRef(Serializable id) {
        return this.userDAO.checkRef(id);
    }

    public void updateUserPwd(Serializable id, String password) throws Exception {
        TUser user = this.userDAO.getEntityById(id);
        user.setCPassword(password);
        this.userDAO.saveEntity(user);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}

