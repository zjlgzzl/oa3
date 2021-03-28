package com.oa.shipment.service;

import java.util.List;

import com.common.entity.TShipment;
import com.common.util.PageBean;	

public interface ShipmentService {

	public List<TShipment> getList(TShipment var1, int var2, int var3);
	
	public PageBean getPages(TShipment var1, int var2, int var3);
	
    public void saveEntity(TShipment var1) throws Exception;

    public TShipment getEntityById(Integer var1);

    public void deleteEntity(Integer var1) throws Exception;

	public int checkDup(String var1, Integer var2);
}
