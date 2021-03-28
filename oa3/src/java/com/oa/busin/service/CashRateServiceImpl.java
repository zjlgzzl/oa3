/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TCashRate
 *  com.oa.busin.dao.CashRateDAO
 *  com.oa.busin.service.CashRateService
 *  com.oa.busin.service.CashRateServiceImpl
 */
package com.oa.busin.service;

import com.common.entity.TCashRate;
import com.oa.busin.dao.CashRateDAO;
import com.oa.busin.service.CashRateService;
import java.util.List;

public class CashRateServiceImpl
implements CashRateService {
    private CashRateDAO cashRateDAO;

    public List<TCashRate> getList(int businId, short type) {
        return this.cashRateDAO.getList(businId, type);
    }

    public void setCashRateDAO(CashRateDAO cashRateDAO) {
        this.cashRateDAO = cashRateDAO;
    }
}

