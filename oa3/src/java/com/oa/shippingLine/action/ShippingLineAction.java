package com.oa.shippingLine.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.entity.TShippingLine;
import com.common.util.PageBean;
import com.oa.shippingLine.service.ShippingLineService;
import com.opensymphony.xwork2.ActionSupport;

public class ShippingLineAction extends ActionSupport {

	private ShippingLineService shippingLineService;
	private TShippingLine query;
	private TShippingLine entity;
	private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    public String getList() {
        List list = this.shippingLineService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.shippingLineService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }
    
    public String addShippingLine() {
        return "success";
    }

    public String saveShippingLine() {
        int count = this.shippingLineService.checkDup(this.entity.getName(), this.entity.getId());
        if (count > 0) {
            this.request.setAttribute("errInfo", "名称重复");
            return "input";
        }
        try {
            this.shippingLineService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editShippingLine() {
        this.entity = this.shippingLineService.getEntityById(Integer.valueOf(this.id));
        return "success";
    }
    
    public String viewShippingLine() {
        this.entity = this.shippingLineService.getEntityById(Integer.valueOf(this.id));
        request.setAttribute("flag", "view");
        return "success";
    }

    public String deleteShippingLine() {
        try {
            this.shippingLineService.deleteEntity(Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

	public ShippingLineService getShippingLineService() {
		return shippingLineService;
	}

	public void setShippingLineService(ShippingLineService shippingLineService) {
		this.shippingLineService = shippingLineService;
	}

	public TShippingLine getQuery() {
		return query;
	}

	public void setQuery(TShippingLine query) {
		this.query = query;
	}

	public TShippingLine getEntity() {
		return entity;
	}

	public void setEntity(TShippingLine entity) {
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
