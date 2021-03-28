package com.oa.shippingLine.service;

import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.entity.TShippingLine;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.shippingLine.dao.ShippingLineDAO;	

public class ShippingLineServiceImpl implements ShippingLineService{

	private ShippingLineDAO shippingLineDAO;
	
	public List<TShippingLine> getList(TShippingLine var1, int var2, int var3) {
		return shippingLineDAO.getList(var1, var2, var3);
	}

	public PageBean getPages(TShippingLine var1, int var2, int var3) {
		return shippingLineDAO.getPages(var1, var2, var3);
	}

	public void saveEntity(TShippingLine var1) throws Exception {
		if(var1.getId() == null) {
			var1.setCreateDate(CommonUtil.getDatetime());
			var1.setCreateUserid(UserInfo.getUserId());
		}
		var1.setLastDate(CommonUtil.getDatetime());
		var1.setLastUserid(UserInfo.getUserId());
		shippingLineDAO.saveEntity(var1);
	}

	public TShippingLine getEntityById(Integer var1) {
		return shippingLineDAO.getEntityById(var1);
	}

	public void deleteEntity(Integer var1) throws Exception {
		shippingLineDAO.deleteEntity(var1);
	}

	public int checkDup(String var1, Integer var2) {
		return shippingLineDAO.checkDup(var1,var2);
	}

	public ShippingLineDAO getShippingLineDAO() {
		return shippingLineDAO;
	}

	public void setShippingLineDAO(ShippingLineDAO shippingLineDAO) {
		this.shippingLineDAO = shippingLineDAO;
	}

}
