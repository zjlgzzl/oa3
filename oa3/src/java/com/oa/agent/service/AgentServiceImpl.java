package com.oa.agent.service;

import java.util.List;

import com.common.auth.action.UserInfo;
import com.common.entity.TAgent;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.agent.dao.AgentDAO;	

public class AgentServiceImpl implements AgentService{

	private AgentDAO agentDAO;
	
	public List<TAgent> getList(TAgent var1, int var2, int var3) {
		return agentDAO.getList(var1, var2, var3);
	}

	public PageBean getPages(TAgent var1, int var2, int var3) {
		return agentDAO.getPages(var1, var2, var3);
	}

	public void saveEntity(TAgent var1) throws Exception {
		if(var1.getId() == null) {
			var1.setCreateDate(CommonUtil.getDatetime());
			var1.setCreateUserid(UserInfo.getUserId());
		}
		var1.setLastDate(CommonUtil.getDatetime());
		var1.setLastUserid(UserInfo.getUserId());
		agentDAO.saveEntity(var1);
	}

	public TAgent getEntityById(Integer var1) {
		return agentDAO.getEntityById(var1);
	}

	public void deleteEntity(Integer var1) throws Exception {
		agentDAO.deleteEntity(var1);
	}

	public int checkDup(String var1, Integer var2) {
		return agentDAO.checkDup(var1,var2);
	}

	public AgentDAO getAgentDAO() {
		return agentDAO;
	}

	public void setAgentDAO(AgentDAO agentDAO) {
		this.agentDAO = agentDAO;
	}

}
