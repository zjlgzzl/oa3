/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TService
 *  com.oa.busin.dao.BusinServiceDAO
 *  com.oa.busin.service.BusinServiceService
 *  com.oa.busin.service.BusinServiceServiceImpl
 */
package com.oa.busin.service;

import com.common.entity.TService;
import com.oa.busin.dao.BusinServiceDAO;
import com.oa.busin.service.BusinServiceService;
import java.util.List;

public class BusinServiceServiceImpl
implements BusinServiceService {
    private BusinServiceDAO businServiceDAO;

    public List<TService> getList(int businId) {
        return this.businServiceDAO.getList(businId);
    }

    public void setBusinServiceDAO(BusinServiceDAO businServiceDAO) {
        this.businServiceDAO = businServiceDAO;
    }
}

