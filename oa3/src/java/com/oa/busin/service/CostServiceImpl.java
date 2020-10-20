/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.UserDAO
 *  com.common.entity.TBusinLog
 *  com.common.entity.TCost
 *  com.common.entity.VCostGroupSum
 *  com.common.util.CommonUtil
 *  com.oa.busin.dao.BusinDAO
 *  com.oa.busin.dao.BusinLogDAO
 *  com.oa.busin.dao.CostDAO
 *  com.oa.busin.service.CostService
 *  com.oa.busin.service.CostServiceImpl
 */
package com.oa.busin.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.UserDAO;
import com.common.entity.TBusinLog;
import com.common.entity.TCost;
import com.common.entity.VCostGroupSum;
import com.common.util.CommonUtil;
import com.oa.busin.dao.BusinDAO;
import com.oa.busin.dao.BusinLogDAO;
import com.oa.busin.dao.CostDAO;
import com.oa.busin.service.CostService;
import java.io.Serializable;
import java.util.List;

public class CostServiceImpl
implements CostService {
    private CostDAO costDAO;
    private UserDAO userDAO;
    private BusinLogDAO businLogDAO;
    private BusinDAO businDAO;

    public List<TCost> getList(int businId, short groupId) {
        return this.costDAO.getList(businId, groupId);
    }

    public void updateCostDetailState(List<TCost> cost, short state) throws Exception {
        for (int i = 0; i < cost.size(); ++i) {
            this.costDAO.updateCostDetailState((Serializable)cost.get(i).getCId(), state);
        }
        if (state == 5) {
            TBusinLog businLog = new TBusinLog();
            businLog.setCDate(CommonUtil.getDatetime());
            businLog.setTUser(this.userDAO.getEntityById((Serializable)Integer.valueOf(UserInfo.getUserId())));
            businLog.setTBusin(this.businDAO.getEntityById((Serializable)cost.get(0).getTBusin().getCId()));
            businLog.setCNote("财务确认领款");
            this.businLogDAO.saveEntity(businLog);
        }
    }

    public int getFAuditCount(int businId) {
        return this.costDAO.getFAuditCount(businId);
    }

    public TCost getCostById(int id) {
        return this.costDAO.getCostById(id);
    }

    public void saveCost(TCost entity) throws Exception {
        this.costDAO.saveCost(entity);
    }

    public void saveCost(int[] cid, short flag) throws Exception {
        for (int i = 0; i < cid.length; ++i) {
            TCost cc = this.costDAO.getCostById(cid[i]);
            cc.setCNewCostFlag(Short.valueOf(flag));
            this.costDAO.saveCost(cc);
        }
    }

    public void saveCostHidden(int[] cid, short flag) throws Exception {
        for (int i = 0; i < cid.length; ++i) {
            TCost cc = this.costDAO.getCostById(cid[i]);
            cc.setCHidden(Short.valueOf(flag));
            this.costDAO.saveCost(cc);
        }
    }

    public void saveCostBaoxiao(int[] cid, short flag) throws Exception {
        for (int i = 0; i < cid.length; ++i) {
            TCost cc = this.costDAO.getCostById(cid[i]);
            cc.setCBaoxiao(Short.valueOf(flag));
            this.costDAO.saveCost(cc);
        }
    }

    public List<VCostGroupSum> getCostGroupSumByBusinId(int businId) {
        return this.costDAO.getCostGroupSumByBusinId(businId);
    }

    public VCostGroupSum getCostGroupSumByBusinId(int businId, int groupId) {
        return this.costDAO.getCostGroupSumByBusinId(businId, groupId);
    }

    public void setCostDAO(CostDAO costDAO) {
        this.costDAO = costDAO;
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

