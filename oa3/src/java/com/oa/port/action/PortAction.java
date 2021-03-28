package com.oa.port.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.entity.TPort;
import com.common.util.PageBean;
import com.oa.port.service.PortService;
import com.opensymphony.xwork2.ActionSupport;

public class PortAction extends ActionSupport {

	private PortService portService;
	private TPort query;
	private TPort entity;
	private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    public String getList() {
        List list = this.portService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.portService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }
    
    public String addPort() {
        return "success";
    }

    public String savePort() {
        int count = this.portService.checkDup(this.entity.getName(), this.entity.getId());
        if (count > 0) {
            this.request.setAttribute("errInfo", "名称重复");
            return "input";
        }
        try {
            this.portService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editPort() {
        this.entity = this.portService.getEntityById(Integer.valueOf(this.id));
        return "success";
    }
    
    public String viewPort() {
        this.entity = this.portService.getEntityById(Integer.valueOf(this.id));
        request.setAttribute("flag", "view");
        return "success";
    }

    public String deletePort() {
        try {
            this.portService.deleteEntity(Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

	public PortService getPortService() {
		return portService;
	}

	public void setPortService(PortService portService) {
		this.portService = portService;
	}

	public TPort getQuery() {
		return query;
	}

	public void setQuery(TPort query) {
		this.query = query;
	}

	public TPort getEntity() {
		return entity;
	}

	public void setEntity(TPort entity) {
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
