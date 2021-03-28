package com.oa.shipment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.entity.TShipment;
import com.common.util.PageBean;
import com.oa.shipment.service.ShipmentService;
import com.opensymphony.xwork2.ActionSupport;

public class ShipmentAction extends ActionSupport {

	private ShipmentService shipmentService;
	private TShipment query;
	private TShipment entity;
	private int id;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    
    public String getList() {
        List list = this.shipmentService.getList(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.shipmentService.getPages(this.query, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }
    
    public String addShipment() {
        return "success";
    }

    public String saveShipment() {
        int count = this.shipmentService.checkDup(this.entity.getName(), this.entity.getId());
        if (count > 0) {
            this.request.setAttribute("errInfo", "名称重复");
            return "input";
        }
        try {
            this.shipmentService.saveEntity(this.entity);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String editShipment() {
        this.entity = this.shipmentService.getEntityById(Integer.valueOf(this.id));
        return "success";
    }
    
    public String viewShipment() {
        this.entity = this.shipmentService.getEntityById(Integer.valueOf(this.id));
        request.setAttribute("flag", "view");
        return "success";
    }

    public String deleteShipment() {
        try {
            this.shipmentService.deleteEntity(Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

	public ShipmentService getShipmentService() {
		return shipmentService;
	}

	public void setShipmentService(ShipmentService shipmentService) {
		this.shipmentService = shipmentService;
	}

	public TShipment getQuery() {
		return query;
	}

	public void setQuery(TShipment query) {
		this.query = query;
	}

	public TShipment getEntity() {
		return entity;
	}

	public void setEntity(TShipment entity) {
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
