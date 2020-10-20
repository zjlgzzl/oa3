package com.oa.agent.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.entity.TAgent;
import com.common.util.PageBean;
import com.oa.agent.service.AgentService;
import com.opensymphony.xwork2.ActionSupport;

public class AgentAction extends ActionSupport {

	private AgentService agentService;
	private TAgent query;
	private TAgent entity;
	private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    public String getList() {
        List list = this.agentService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.agentService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }
    
    public String addAgent() {
        return "success";
    }

    public String saveAgent() {
        int count = this.agentService.checkDup(this.entity.getName(), this.entity.getId());
        if (count > 0) {
            this.request.setAttribute("errInfo", "名称重复");
            return "input";
        }
        try {
            this.agentService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editAgent() {
        this.entity = this.agentService.getEntityById(Integer.valueOf(this.id));
        return "success";
    }
    
    public String viewAgent() {
        this.entity = this.agentService.getEntityById(Integer.valueOf(this.id));
        request.setAttribute("flag", "view");
        return "success";
    }

    public String deleteAgent() {
        try {
            this.agentService.deleteEntity(Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

	public TAgent getQuery() {
		return query;
	}

	public void setQuery(TAgent query) {
		this.query = query;
	}

	public TAgent getEntity() {
		return entity;
	}

	public void setEntity(TAgent entity) {
		this.entity = entity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
    
    
}
