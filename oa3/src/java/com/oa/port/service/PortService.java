package com.oa.port.service;

import java.util.List;

import com.common.entity.TPort;
import com.common.util.PageBean;	

public interface PortService {

	public List<TPort> getList(TPort var1, int var2, int var3);
	
	public PageBean getPages(TPort var1, int var2, int var3);
	
    public void saveEntity(TPort var1) throws Exception;

    public TPort getEntityById(Integer var1);

    public void deleteEntity(Integer var1) throws Exception;

	public int checkDup(String var1, Integer var2);
}
