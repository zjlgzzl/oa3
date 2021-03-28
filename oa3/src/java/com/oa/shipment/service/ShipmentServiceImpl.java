package com.oa.shipment.service;

import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.entity.TShipment;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.shipment.dao.ShipmentDAO;	

public class ShipmentServiceImpl implements ShipmentService{

	private ShipmentDAO shipmentDAO;
	
	public List<TShipment> getList(TShipment var1, int var2, int var3) {
		return shipmentDAO.getList(var1, var2, var3);
	}

	public PageBean getPages(TShipment var1, int var2, int var3) {
		return shipmentDAO.getPages(var1, var2, var3);
	}

	public void saveEntity(TShipment var1) throws Exception {
		if(var1.getId() == null) {
			var1.setCreateDate(CommonUtil.getDatetime());
			var1.setCreateUserid(UserInfo.getUserId());
		}
		var1.setLastDate(CommonUtil.getDatetime());
		var1.setLastUserid(UserInfo.getUserId());
		shipmentDAO.saveEntity(var1);
	}

	public TShipment getEntityById(Integer var1) {
		return shipmentDAO.getEntityById(var1);
	}

	public void deleteEntity(Integer var1) throws Exception {
		shipmentDAO.deleteEntity(var1);
	}

	public int checkDup(String var1, Integer var2) {
		return shipmentDAO.checkDup(var1,var2);
	}

	public ShipmentDAO getShipmentDAO() {
		return shipmentDAO;
	}

	public void setShipmentDAO(ShipmentDAO shipmentDAO) {
		this.shipmentDAO = shipmentDAO;
	}

}
