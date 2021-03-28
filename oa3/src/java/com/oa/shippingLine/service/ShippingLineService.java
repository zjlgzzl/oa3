package com.oa.shippingLine.service;

import java.util.List;

import com.common.entity.TShippingLine;
import com.common.util.PageBean;	

public interface ShippingLineService {

	public List<TShippingLine> getList(TShippingLine var1, int var2, int var3);
	
	public PageBean getPages(TShippingLine var1, int var2, int var3);
	
    public void saveEntity(TShippingLine var1) throws Exception;

    public TShippingLine getEntityById(Integer var1);

    public void deleteEntity(Integer var1) throws Exception;

	public int checkDup(String var1, Integer var2);
}
