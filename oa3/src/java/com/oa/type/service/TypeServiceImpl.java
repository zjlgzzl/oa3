package com.oa.type.service;

import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.entity.TType;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.type.dao.TypeDAO;	

public class TypeServiceImpl implements TypeService{

	private TypeDAO typeDAO;
	
	public List<TType> getList(TType var1, int var2, int var3) {
		return typeDAO.getList(var1, var2, var3);
	}

	public PageBean getPages(TType var1, int var2, int var3) {
		return typeDAO.getPages(var1, var2, var3);
	}

	public void saveEntity(TType var1) throws Exception {
		if(var1.getId() == null) {
			var1.setCreateDate(CommonUtil.getDatetime());
			var1.setCreateUserid(UserInfo.getUserId());
		}
		var1.setLastDate(CommonUtil.getDatetime());
		var1.setLastUserid(UserInfo.getUserId());
		typeDAO.saveEntity(var1);
	}

	public TType getEntityById(Integer var1) {
		return typeDAO.getEntityById(var1);
	}

	public void deleteEntity(Integer var1) throws Exception {
		typeDAO.deleteEntity(var1);
	}

	public int checkDup(String var1, Integer var2) {
		return typeDAO.checkDup(var1,var2);
	}

	public TypeDAO getTypeDAO() {
		return typeDAO;
	}

	public void setTypeDAO(TypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

}
