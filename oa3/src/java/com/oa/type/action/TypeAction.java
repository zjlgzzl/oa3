package com.oa.type.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.entity.TType;
import com.common.util.PageBean;
import com.oa.type.service.TypeService;
import com.opensymphony.xwork2.ActionSupport;

public class TypeAction extends ActionSupport {

	private TypeService typeService;
	private TType query;
	private TType entity;
	private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    public String getList() {
        List list = this.typeService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.typeService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }
    
    public String addType() {
        return "success";
    }

    public String saveType() {
        int count = this.typeService.checkDup(this.entity.getName(), this.entity.getId());
        if (count > 0) {
            this.request.setAttribute("errInfo", "名称重复");
            return "input";
        }
        try {
            this.typeService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editType() {
        this.entity = this.typeService.getEntityById(Integer.valueOf(this.id));
        return "success";
    }
    
    public String viewType() {
        this.entity = this.typeService.getEntityById(Integer.valueOf(this.id));
        request.setAttribute("flag", "view");
        return "success";
    }

    public String deleteType() {
        try {
            this.typeService.deleteEntity(Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public TType getQuery() {
		return query;
	}

	public void setQuery(TType query) {
		this.query = query;
	}

	public TType getEntity() {
		return entity;
	}

	public void setEntity(TType entity) {
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
