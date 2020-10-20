/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.UserDAO
 *  com.common.entity.TBusinLog
 *  com.common.entity.TCash
 *  com.common.util.CommonUtil
 *  com.oa.busin.dao.BusinDAO
 *  com.oa.busin.dao.BusinLogDAO
 *  com.oa.busin.dao.CashDAO
 *  com.oa.busin.service.CashService
 *  com.oa.busin.service.CashServiceImpl
 */
package com.oa.busin.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TBusinLog;
import com.common.entity.TCash;
import com.common.util.CommonUtil;
import com.oa.busin.dao.BusinDAO;
import com.oa.busin.dao.BusinLogDAO;
import com.oa.busin.dao.CashDAO;
import com.oa.busin.service.CashService;
import java.io.Serializable;
import java.util.List;

public class CashServiceImpl
implements CashService {
    private CashDAO cashDAO;
    private UserDAO userDAO;
    private BusinLogDAO businLogDAO;
    private BusinDAO businDAO;

    public List<TCash> getList(int businId, short type) {
        return this.cashDAO.getList(businId, type);
    }

    public void updateCashDetailState(List<TCash> cash, short state) throws Exception {
        for (int i = 0; i < cash.size(); ++i) {
            this.cashDAO.updateCashDetailState((Serializable)cash.get(i).getCId(), state);
        }
        if (state == 3) {
            TBusinLog businLog = new TBusinLog();
            businLog.setCDate(CommonUtil.getDatetime());
            businLog.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            businLog.setTBusin(this.businDAO.getEntityById((Serializable)cash.get(0).getTBusin().getCId()));
            businLog.setCNote("财务确认回款");
            this.businLogDAO.saveEntity(businLog);
        }
    }

    public void setCashDAO(CashDAO cashDAO) {
        this.cashDAO = cashDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setBusinLogDAO(BusinLogDAO businLogDAO) {
        this.businLogDAO = businLogDAO;
    }

    public void setBusinDAO(BusinDAO businDAO) {
        this.businDAO = businDAO;
    }
}

