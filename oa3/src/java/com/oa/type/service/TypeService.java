package com.oa.type.service;

import java.util.List;

import com.common.entity.TType;
import com.common.util.PageBean;	

public interface TypeService {

	public List<TType> getList(TType var1, int var2, int var3);
	
	public PageBean getPages(TType var1, int var2, int var3);
	
    public void saveEntity(TType var1) throws Exception;

    public TType getEntityById(Integer var1);

    public void deleteEntity(Integer var1) throws Exception;

	public int checkDup(String var1, Integer var2);
}
