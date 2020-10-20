/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainer
 *  com.oa.busin.dao.ContainerDAO
 */
package com.oa.busin.dao;

import com.common.entity.TContainer;
import java.io.Serializable;
import java.util.List;

public interface ContainerDAO {
    public List<TContainer> getList(int var1);

    public void saveContainer(TContainer var1) throws Exception;

    public void deleteContainer(Serializable var1) throws Exception;

    public void deleteContainerByBusinid(int var1) throws Exception;

    public Object[] getContainerInfo(int var1);
}

