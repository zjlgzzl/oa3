package com.oa.agent.dao;

import java.util.List;

import com.common.entity.TAgent;
import com.common.util.PageBean;

public interface AgentDAO {
	public List<TAgent> getList(TAgent var1, int var2, int var3);

    public PageBean getPages(TAgent var1, int var2, int var3);

    public void saveEntity(TAgent var1) throws Exception;

    public TAgent getEntityById(Integer var1);

    public void deleteEntity(Integer var1) throws Exception;

	public int checkDup(String var1, Integer var2);
}
