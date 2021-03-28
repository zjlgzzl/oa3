/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TCostItem
 *  com.common.entity.TCostUser
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.dao.CostItemDAO
 *  com.oa.base.service.CostItemService
 *  com.oa.base.service.CostItemServiceImpl
 */
package com.oa.base.service;

import com.common.auth.action.UserInfo;
import com.common.entity.TCostItem;
import com.common.entity.TCostUser;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.dao.CostItemDAO;
import com.oa.base.service.CostItemService;
import java.io.Serializable;
import java.util.List;

public class CostItemServiceImpl
implements CostItemService {
    private CostItemDAO costItemDAO;

    public List<TCostItem> getList(TCostItem query, int pageNow, int pageSize) {
        return this.costItemDAO.getList(query, pageNow, pageSize);
    }

    public PageBean getPages(TCostItem query, int pageNow, int pageSize) {
        return this.costItemDAO.getPages(query, pageNow, pageSize);
    }

    public void saveEntity(TCostItem entity, Integer[] userIds) throws Exception {
        if (entity.getCId() == null) {
            entity.setCState(Short.valueOf((short)1));
            entity.setCCreateUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCCreateDate(CommonUtil.getDatetime());
        } else {
            entity.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            entity.setCLastDate(CommonUtil.getDatetime());
            if (entity.getCType() == 1) {
                this.costItemDAO.deleteUser(entity.getCId().intValue());
            }
        }
        this.costItemDAO.saveEntity(entity);
        if (userIds != null && userIds.length > 0) {
            for (int i = 0; i < userIds.length; ++i) {
                TCostUser uu = new TCostUser();
                uu.setCCostid(entity.getCId());
                uu.setCUserid(userIds[i]);
                uu.setCType(Integer.valueOf(Integer.parseInt(entity.getCType().toString())));
                this.costItemDAO.saveUser(uu);
                uu = null;
            }
        }
    }

    public TCostItem getEntityById(Serializable id) {
        return this.costItemDAO.getEntityById(id);
    }

    public void deleteEntity(Serializable id) throws Exception {
        this.costItemDAO.deleteUser(Integer.parseInt(id.toString()));
        this.costItemDAO.deleteEntity(id);
    }

    public int checkName(int typeId, Serializable id, String name) {
        return this.costItemDAO.checkName(typeId, id, name);
    }

    public void updateEntityState(Serializable id, short state) throws Exception {
        this.costItemDAO.updateEntityState(id, state);
    }

    public List<TCostItem> getValidList() {
        return this.costItemDAO.getValidList();
    }

    public int checkRef(Serializable id) {
        return this.costItemDAO.checkRef(id);
    }

    public int checkRef2(Serializable id) {
        return this.costItemDAO.checkRef2(id);
    }

    public List<Integer> getUserList(int costid) {
        return this.costItemDAO.getUserList(costid);
    }

    public void deleteUser(int costid) throws Exception {
        this.costItemDAO.deleteUser(costid);
    }

    public void saveUser(TCostUser entity) throws Exception {
        this.costItemDAO.saveUser(entity);
    }

    public void setCostItemDAO(CostItemDAO costItemDAO) {
        this.costItemDAO = costItemDAO;
    }
}

