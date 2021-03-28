/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainer
 *  com.oa.busin.dao.ContainerDAO
 *  com.oa.busin.service.ContainerService
 *  com.oa.busin.service.ContainerServiceImpl
 */
package com.oa.busin.service;

import com.common.entity.TContainer;
import com.oa.busin.dao.ContainerDAO;
import com.oa.busin.service.ContainerService;
import java.util.List;

public class ContainerServiceImpl
implements ContainerService {
    private ContainerDAO containerDAO;

    public List<TContainer> getList(int businId) {
        return this.containerDAO.getList(businId);
    }

    public void setContainerDAO(ContainerDAO containerDAO) {
        this.containerDAO = containerDAO;
    }
}

