/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.entity.TCustomer
 *  com.common.util.CommonUtil
 *  com.common.util.PageBean
 *  com.oa.base.action.CusAction
 *  com.oa.base.service.CusService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.base.action;

import com.common.auth.action.UserInfo;
import com.common.entity.TCustomer;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.base.service.CusService;
import com.oa.base.service.SalerService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class CusAction
extends ActionSupport {
    private CusService cusService;
    private SalerService salerService;
    private TCustomer query;
    private TCustomer cus;
    private int cusId;
    private Short blackFlag;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public String getList() {
    	try {
    		this.request.setAttribute("salerList", (Object)this.salerService.getValidList());
    		List list = this.cusService.getList(this.query, this.pageNow, this.pageSize);
    		this.request.setAttribute("cusList", (Object)list);
    		PageBean pagebean = this.cusService.getPages(this.query, this.pageNow, this.pageSize);
    		this.request.setAttribute("pageBean", (Object)pagebean);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "success";
    }

    public String addCus() {
    	this.request.setAttribute("salerList", (Object)this.salerService.getValidList());
        return "success";
    }

    public String saveCus() {
        if (this.cus.getCName() == null || "".equals(this.cus.getCName().trim())) {
            this.request.setAttribute("errInfo", (Object)"客户名称不能为空");
            return "input";
        }
        int count = this.cusService.checkName((Serializable)this.cus.getCId(), this.cus.getCName());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"客户名称重复");
            return "input";
        }
        try {
            this.cusService.saveEntity(this.cus);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editCus() {
    	this.request.setAttribute("salerList", (Object)this.salerService.getValidList());
        this.cus = this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId));
        return "success";
    }

    public String deleteCus() {
        int count = this.cusService.checkRef((Serializable)Integer.valueOf(this.cusId));
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"客户已经被使用，不允许删除");
            return "input";
        }
        try {
            this.cusService.deleteEntity((Serializable)Integer.valueOf(this.cusId));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String invalidCus() {
        try {
            this.cusService.updateEntityState((Serializable)Integer.valueOf(this.cusId), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String setBlack() {
        TCustomer cc = this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId));
        cc.setCBlack(this.blackFlag);
        if (this.blackFlag == 1) {
            cc.setCLastUserid(Integer.valueOf(UserInfo.getUserId()));
            cc.setCLastDate(CommonUtil.getDatetime());
        } else {
            cc.setCLastUserid(null);
            cc.setCLastDate(null);
        }
        try {
            this.cusService.saveEntity(cc);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.request.setAttribute("errInfo", (Object)"更新失败");
            return "input";
        }
        return "success";
    }

    public TCustomer getQuery() {
        return this.query;
    }

    public void setQuery(TCustomer query) {
        this.query = query;
    }

    public TCustomer getCus() {
        return this.cus;
    }

    public void setCus(TCustomer cus) {
        this.cus = cus;
    }

    public int getCusId() {
        return this.cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getPageNow() {
        return this.pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCusService(CusService cusService) {
        this.cusService = cusService;
    }

    public Short getBlackFlag() {
        return this.blackFlag;
    }

    public void setBlackFlag(Short blackFlag) {
        this.blackFlag = blackFlag;
    }

	public void setSalerService(SalerService salerService) {
		this.salerService = salerService;
	}
    
}

