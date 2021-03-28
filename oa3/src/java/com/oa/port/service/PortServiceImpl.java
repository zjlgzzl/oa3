package com.oa.port.service;

import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.entity.TPort;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.port.dao.PortDAO;	

public class PortServiceImpl implements PortService{

	private PortDAO portDAO;
	
	public List<TPort> getList(TPort var1, int var2, int var3) {
		return portDAO.getList(var1, var2, var3);
	}

	public PageBean getPages(TPort var1, int var2, int var3) {
		return portDAO.getPages(var1, var2, var3);
	}

	public void saveEntity(TPort var1) throws Exception {
		if(var1.getId() == null) {
			var1.setCreateDate(CommonUtil.getDatetime());
			var1.setCreateUserid(UserInfo.getUserId());
		}
		var1.setLastDate(CommonUtil.getDatetime());
		var1.setLastUserid(UserInfo.getUserId());
		portDAO.saveEntity(var1);
	}

	public TPort getEntityById(Integer var1) {
		return portDAO.getEntityById(var1);
	}

	public void deleteEntity(Integer var1) throws Exception {
		portDAO.deleteEntity(var1);
	}

	public int checkDup(String var1, Integer var2) {
		return portDAO.checkDup(var1,var2);
	}

	public PortDAO getPortDAO() {
		return portDAO;
	}

	public void setPortDAO(PortDAO portDAO) {
		this.portDAO = portDAO;
	}

}
