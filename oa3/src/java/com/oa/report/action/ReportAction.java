package com.oa.report.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.common.entity.BankAccountReport;
import com.common.entity.CostCashoutReport;
import com.common.entity.TBank;
import com.common.entity.TBusin;
import com.common.entity.TCash;
import com.common.entity.TCashRate;
import com.common.entity.TContainer;
import com.common.entity.TCost;
import com.common.entity.TCostItem;
import com.common.entity.TJiezhuan;
import com.common.entity.TProfitCus;
import com.common.entity.VBankmoneyreport;
import com.common.entity.VBusinprofitcusreport;
import com.common.entity.VBusinprofitreport;
import com.common.entity.VBusinreceivereport;
import com.common.entity.VCostReport;
import com.common.entity.VCostReport2;
import com.common.entity.VCostReport2Id;
import com.common.entity.VCostReportId;
import com.common.entity.VEmpprofitreport;
import com.common.util.CommonUtil;
import com.common.util.ExportUtil;
import com.common.util.PageBean;
import com.google.gson.Gson;
import com.oa.agent.service.AgentService;
import com.oa.bank.service.BankService;
import com.oa.base.service.BusinTypeService;
import com.oa.base.service.CostItemService;
import com.oa.base.service.CusService;
import com.oa.base.service.SalerService;
import com.oa.busin.service.BusinService;
import com.oa.busin.service.CashRateService;
import com.oa.busin.service.CashService;
import com.oa.busin.service.ContainerService;
import com.oa.busin.service.CostService;
import com.oa.report.service.ReportService;
import com.opensymphony.xwork2.ActionSupport;

public class ReportAction
extends ActionSupport {
    private static final String VBusinreceivereport = null;
	private VBusinreceivereport reQuery;
    private VBusinprofitreport proQuery;
    private VBankmoneyreport bankQuery;
    private String startDate1;
    private String endDate1;
    private String startDate2;
    private String endDate2;
    private int flag;
    private int businId;
    private double cmoney;
    private VCostReport query;
    private ReportService reportService;
    private BusinService businService;
    private CostItemService costItemService;
    private CashRateService cashRateService;
    private CashService cashService;
    private CostService costService;
    private BusinTypeService businTypeService;
    private SalerService salerService;
    private ContainerService containerService;
    private AgentService agentService;
    private BankService bankService;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private InputStream excelStream;
    private String fileName;
    private String remarks;
    private CusService cusService;
    private VBusinprofitcusreport businProfitCusQuery;
    private int cusId;
    private short cflag;
    private int[] businIds;
    private Short payCompleteFlag;
    private int orderRule;
    private String payableDatetime;
    private short jiezhuan;
    private String container;
    private VCostReport2 query2;
    private int firstQuery;
    private int saleId;
    private TJiezhuan queryJiezhuan;
    private CostCashoutReport costCashoutReport;

    public String getBusinReceiveDReport() throws ParseException {
        Object[] obj;
        List<VBusinreceivereport> list;
        this.request.setAttribute("queryBean", reQuery);
    	this.request.setAttribute("salerList", salerService.getValidList());
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        String sdate = null;
        String edate = null;
        
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        if ((list = this.reportService.getBusinReceiveList(this.reQuery, obj = new Object[]{sdate, edate, this.flag, 0, this.orderRule,request.getSession().getAttribute("fun242")}, this.pageNow, this.pageSize)) != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
            	
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null && ((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    boolean scope1 = false;
                    boolean scope2 = false;
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope1 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    }
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope2 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    }
                    if (scope1 && scope2) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= ((VBusinreceivereport)list.get(i)).getCdate4().getTime()) continue;
                        ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                        continue;
                    }
                    if (!scope2) continue;
                    ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                    continue;
                }
                if (((VBusinreceivereport)list.get(i)).getCdate4() == null) continue;
                ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
            }
        }
        parseBusinReceiveList(list);
        this.request.setAttribute("receiveList", (Object)list);
        PageBean pagebean = this.reportService.getBusinReceivePages(this.reQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        double money1 = 0.0;
        double money2 = 0.0;
        double money4 = 0.0;
        double recieve = 0.0;
        double recieved = 0.0;
        double limit = 0.0;
        VBankmoneyreport sum = this.reportService.getRecieveSum(this.reQuery, obj);
        if (sum != null) {
            money1 = sum.getCmoney();
            money2 = sum.getMoney2();
            money4 = sum.getMoney4();
            recieve = sum.getOutMoney1();
            recieved = sum.getInMoney();
            limit = sum.getOutMoney2();
        }
        this.request.setAttribute("_money1", (Object)money1);
        this.request.setAttribute("_money2", (Object)money2);
        this.request.setAttribute("_money4", (Object)money4);
        this.request.setAttribute("recieve", (Object)recieve);
        this.request.setAttribute("recieved", (Object)recieved);
        this.request.setAttribute("limit", (Object)limit);
        this.request.setAttribute("businTypeList", (Object)this.businTypeService.getValidList());
        return "success";
    }

    private void parseBusinReceiveList(List<VBusinreceivereport> list) {
    	for (VBusinreceivereport report : list) {
    		Date payDate = report.getPayDate();
    		if(payDate != null) {
    			report.setPayDateStr(new SimpleDateFormat("dd-MM-yyyy").format(payDate));
    		}
		}
	}

	public String expRecieveReport() throws Exception {
        Object[] obj;
        List<VBusinreceivereport> list;
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        if ((list = this.reportService.getBusinReceiveList(this.reQuery, obj = new Object[]{sdate, edate, this.flag, 0, this.orderRule}, 1, 100000)) != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null && ((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    boolean scope1 = false;
                    boolean scope2 = false;
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope1 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    }
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope2 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    }
                    if (scope1 && scope2) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() < ((VBusinreceivereport)list.get(i)).getCdate4().getTime()) {
                            ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                        }
                    } else if (scope2) {
                        ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                    }
                } else if (((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                }
                if (this.jiezhuan != 2) continue;
                TJiezhuan jj = this.reportService.getJiezhuan(((VBusinreceivereport)list.get(i)).getBusinId().intValue());
                if (jj == null) {
                    jj = new TJiezhuan();
                    jj.setTBusin(this.businService.getEntityById((Serializable)((VBusinreceivereport)list.get(i)).getBusinId()));
                }
                jj.setCDate(CommonUtil.getDatetime());
                jj.setCMoney(((VBusinreceivereport)list.get(i)).getCmoney());
                jj.setCMoney2(((VBusinreceivereport)list.get(i)).getCmoney());
                jj.setCNotice(Short.valueOf((short)1));
                this.reportService.saveJiezhuan(jj);
                jj = null;
            }
        }
        parseBusinReceiveList(list);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("服务单收款进度统计");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("日期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("C/O");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("VAT No.");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("Description");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("Q'ty of Truck");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("Q'ty of Dox");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("Inv Amount");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("Vat Amount");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("D.N");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("发票1");
        cell.setCellStyle(headStyle);
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)11);
        cell.setCellValue("No-Date(D.N)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)12);
        cell.setCellValue("Re-Im");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)13);
        cell.setCellValue("发票2");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)14);
        cell.setCellValue("No-Date(Re-Im)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)15);
        cell.setCellValue("Tax INV");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)16);
        cell.setCellValue("含税发票");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)17);
        cell.setCellValue("No-Date(Tax INV)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)18);
        cell.setCellValue("应收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)19);
        cell.setCellValue("发票1预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)20);
        cell.setCellValue("发票2预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)21);
        cell.setCellValue("含税发票预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)22);
        cell.setCellValue("已收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)23);
        cell.setCellValue("差额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)24);
        cell.setCellValue("财务备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)25);
        cell.setCellValue("备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)26);
        cell.setCellValue("服务单号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)27);
        cell.setCellValue("Fty. inv No.");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)28);
        cell.setCellValue("PoL/PoD");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)29);
        cell.setCellValue("付款到期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)30);
        cell.setCellValue("超期天数");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 42);
        sheet.setColumnWidth(2, (short)n * 42);
        sheet.setColumnWidth(3, (short)n * 26);
        sheet.setColumnWidth(4, (short)n * 26);
        sheet.setColumnWidth(5, (short)n * 20);
        sheet.setColumnWidth(6, (short)n * 20);
        sheet.setColumnWidth(7, (short)n * 20);
        sheet.setColumnWidth(8, (short)n * 20);
        sheet.setColumnWidth(9, (short)n * 26);
        sheet.setColumnWidth(10, (short)n * 20);
        sheet.setColumnWidth(11, (short)n * 20);
        sheet.setColumnWidth(12, (short)n * 26);
        sheet.setColumnWidth(13, (short)n * 20);
        sheet.setColumnWidth(14, (short)n * 20);
        sheet.setColumnWidth(15, (short)n * 20);
        sheet.setColumnWidth(16, (short)n * 20);
        sheet.setColumnWidth(17, (short)n * 20);
        sheet.setColumnWidth(18, (short)n * 20);
        sheet.setColumnWidth(19, (short)n * 20);
        sheet.setColumnWidth(20, (short)n * 20);
        sheet.setColumnWidth(21, (short)n * 20);
        sheet.setColumnWidth(22, (short)n * 20);
        sheet.setColumnWidth(23, (short)n * 20);
        sheet.setColumnWidth(24, (short)n * 36);
        sheet.setColumnWidth(25, (short)n * 36);
        sheet.setColumnWidth(26, (short)n * 26);
        sheet.setColumnWidth(27, (short)n * 26);
        sheet.setColumnWidth(28, (short)n * 20);
        sheet.setColumnWidth(29, (short)n * 26);
        sheet.setColumnWidth(30, (short)n * 20);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                List rate2;
                List cash2;
                row = sheet.createRow(++num);
                double money1 = 0.0;
                double money2 = 0.0;
                double money4 = 0.0;
                double crate = 0.0;
                double inv = 0.0;
                double vat = 0.0;
                List cash = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)1);
                if (cash != null && cash.size() > 0) {
                    for (int j = 0; j < cash.size(); ++j) {
                        if (((TCash)cash.get(j)).getCMoney() == null || !(((TCash)cash.get(j)).getCMoney() > 0.0) || ((TCash)cash.get(j)).getCCount() == null || !(((TCash)cash.get(j)).getCCount() > 0.0)) continue;
                        money1 += ((TCash)cash.get(j)).getCMoney() * ((TCash)cash.get(j)).getCCount();
                    }
                }
                if ((cash2 = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && cash2.size() > 0) {
                    for (int j = 0; j < cash2.size(); ++j) {
                        if (((TCash)cash2.get(j)).getCMoney() == null || !(((TCash)cash2.get(j)).getCMoney() > 0.0) || ((TCash)cash2.get(j)).getCCount() == null || !(((TCash)cash2.get(j)).getCCount() > 0.0)) continue;
                        money2 += ((TCash)cash2.get(j)).getCMoney() * ((TCash)cash2.get(j)).getCCount();
                    }
                }
                if ((rate2 = this.cashRateService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && rate2.size() > 0) {
                    for (int j = 0; j < rate2.size(); ++j) {
                        if (((TCashRate)rate2.get(j)).getCMoney() == null || !(((TCashRate)rate2.get(j)).getCMoney() > 0.0) || ((TCashRate)rate2.get(j)).getCCount() == null || !(((TCashRate)rate2.get(j)).getCCount() > 0.0)) continue;
                        if (((TCashRate)rate2.get(j)).getTRate().getCRate() > 0.0) {
                            crate = ((TCashRate)rate2.get(j)).getTRate().getCRate() / 100.0;
                            money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount() * (1.0 + crate);
                            vat += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount() * crate;
                        } else {
                            money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount();
                        }
                        inv += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount();
                    }
                }
                cell = row.createCell((short)0);
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate2()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCusName2());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getVatno());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getDescription());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                if (!"".equals(((VBusinreceivereport)list.get(i)).getQtyoftruck())) {
                    cell.setCellValue(String.valueOf(((VBusinreceivereport)list.get(i)).getQtyoftruck()) + " Truck");
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                if (!"".equals(((VBusinreceivereport)list.get(i)).getQtyofdox())) {
                    cell.setCellValue(String.valueOf(((VBusinreceivereport)list.get(i)).getQtyofdox()) + " Dox");
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(inv);
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(vat);
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)9);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReim());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney1() - ((VBusinreceivereport)list.get(i)).getReceiveMoney1()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)11);
                if (((VBusinreceivereport)list.get(i)).getCdate1() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate1()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)12);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getInv());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)13);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney2() - ((VBusinreceivereport)list.get(i)).getReceiveMoney2()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)14);
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate2()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)15);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getNodate7());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)16);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney4() - ((VBusinreceivereport)list.get(i)).getReceiveMoney4()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)17);
                if (((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate4()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)18);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)19);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney1().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)20);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney2().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)21);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney4().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)22);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)23);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCmoney() - ((VBusinreceivereport)list.get(i)).getReceiveMoney1() - ((VBusinreceivereport)list.get(i)).getReceiveMoney2() - ((VBusinreceivereport)list.get(i)).getReceiveMoney3() - ((VBusinreceivereport)list.get(i)).getReceiveMoney4() - ((VBusinreceivereport)list.get(i)).getReceiveMoney());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)24);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getRemarks2());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)25);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getRemarks());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)26);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)27);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getFty());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)28);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getArrivalPort());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)29);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getPayDateStr());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)30);
                cell.setCellValue((((VBusinreceivereport)list.get(i)).getOverDays() != null ? String.valueOf(((VBusinreceivereport)list.get(i)).getOverDays()) : ""));
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "服务单收款进度统计";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    public String expRecieveReport2() throws Exception {
        Object[] obj;
        List list;
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        if ((list = this.reportService.getBusinReceiveList(this.reQuery, obj = new Object[]{sdate, edate, this.flag, 1, this.orderRule}, 1, 100000)) != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null && ((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    boolean scope1 = false;
                    boolean scope2 = false;
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope1 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    }
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope2 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    }
                    if (scope1 && scope2) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= ((VBusinreceivereport)list.get(i)).getCdate4().getTime()) continue;
                        ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                        continue;
                    }
                    if (!scope2) continue;
                    ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                    continue;
                }
                if (((VBusinreceivereport)list.get(i)).getCdate4() == null) continue;
                ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
            }
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("服务单收款进度统计(归档)");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("日期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("C/O");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("VAT No.");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("Description");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("Q'ty of Truck");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("Q'ty of Dox");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("Inv Amount");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("Vat Amount");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("D.N");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("发票1");
        cell.setCellStyle(headStyle);
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)11);
        cell.setCellValue("No-Date(D.N)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)12);
        cell.setCellValue("Re-Im");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)13);
        cell.setCellValue("发票2");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)14);
        cell.setCellValue("No-Date(Re-Im)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)15);
        cell.setCellValue("Tax INV");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)16);
        cell.setCellValue("含税发票");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)17);
        cell.setCellValue("No-Date(Tax INV)");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)18);
        cell.setCellValue("应收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)19);
        cell.setCellValue("发票1预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)20);
        cell.setCellValue("发票2预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)21);
        cell.setCellValue("含税发票预收款");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)22);
        cell.setCellValue("已收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)23);
        cell.setCellValue("差额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)24);
        cell.setCellValue("财务备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)25);
        cell.setCellValue("备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)26);
        cell.setCellValue("服务单号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)27);
        cell.setCellValue("Fty. inv No.");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)28);
        cell.setCellValue("PoL/PoD");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 42);
        sheet.setColumnWidth(2, (short)n * 42);
        sheet.setColumnWidth(3, (short)n * 26);
        sheet.setColumnWidth(4, (short)n * 26);
        sheet.setColumnWidth(5, (short)n * 20);
        sheet.setColumnWidth(6, (short)n * 20);
        sheet.setColumnWidth(7, (short)n * 20);
        sheet.setColumnWidth(8, (short)n * 20);
        sheet.setColumnWidth(9, (short)n * 26);
        sheet.setColumnWidth(10, (short)n * 20);
        sheet.setColumnWidth(11, (short)n * 20);
        sheet.setColumnWidth(12, (short)n * 26);
        sheet.setColumnWidth(13, (short)n * 20);
        sheet.setColumnWidth(14, (short)n * 20);
        sheet.setColumnWidth(15, (short)n * 20);
        sheet.setColumnWidth(16, (short)n * 20);
        sheet.setColumnWidth(17, (short)n * 20);
        sheet.setColumnWidth(18, (short)n * 20);
        sheet.setColumnWidth(19, (short)n * 20);
        sheet.setColumnWidth(20, (short)n * 20);
        sheet.setColumnWidth(21, (short)n * 20);
        sheet.setColumnWidth(22, (short)n * 20);
        sheet.setColumnWidth(23, (short)n * 20);
        sheet.setColumnWidth(24, (short)n * 36);
        sheet.setColumnWidth(25, (short)n * 36);
        sheet.setColumnWidth(26, (short)n * 26);
        sheet.setColumnWidth(27, (short)n * 26);
        sheet.setColumnWidth(28, (short)n * 20);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                List rate2;
                List cash2;
                row = sheet.createRow(++num);
                double money1 = 0.0;
                double money2 = 0.0;
                double money4 = 0.0;
                double crate = 0.0;
                double inv = 0.0;
                double vat = 0.0;
                List cash = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)1);
                if (cash != null && cash.size() > 0) {
                    for (int j = 0; j < cash.size(); ++j) {
                        if (((TCash)cash.get(j)).getCMoney() == null || !(((TCash)cash.get(j)).getCMoney() > 0.0) || ((TCash)cash.get(j)).getCCount() == null || !(((TCash)cash.get(j)).getCCount() > 0.0)) continue;
                        money1 += ((TCash)cash.get(j)).getCMoney() * ((TCash)cash.get(j)).getCCount();
                    }
                }
                if ((cash2 = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && cash2.size() > 0) {
                    for (int j = 0; j < cash2.size(); ++j) {
                        if (((TCash)cash2.get(j)).getCMoney() == null || !(((TCash)cash2.get(j)).getCMoney() > 0.0) || ((TCash)cash2.get(j)).getCCount() == null || !(((TCash)cash2.get(j)).getCCount() > 0.0)) continue;
                        money2 += ((TCash)cash2.get(j)).getCMoney() * ((TCash)cash2.get(j)).getCCount();
                    }
                }
                if ((rate2 = this.cashRateService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && rate2.size() > 0) {
                    for (int j = 0; j < rate2.size(); ++j) {
                        if (((TCashRate)rate2.get(j)).getCMoney() == null || !(((TCashRate)rate2.get(j)).getCMoney() > 0.0) || ((TCashRate)rate2.get(j)).getCCount() == null || !(((TCashRate)rate2.get(j)).getCCount() > 0.0)) continue;
                        if (((TCashRate)rate2.get(j)).getTRate().getCRate() > 0.0) {
                            crate = ((TCashRate)rate2.get(j)).getTRate().getCRate() / 100.0;
                            money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount() * (1.0 + crate);
                            vat += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount() * crate;
                        } else {
                            money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount();
                        }
                        inv += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount();
                    }
                }
                cell = row.createCell((short)0);
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate2()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCusName2());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getVatno());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getDescription());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                if (!"".equals(((VBusinreceivereport)list.get(i)).getQtyoftruck())) {
                    cell.setCellValue(String.valueOf(((VBusinreceivereport)list.get(i)).getQtyoftruck()) + " Truck");
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                if (!"".equals(((VBusinreceivereport)list.get(i)).getQtyofdox())) {
                    cell.setCellValue(String.valueOf(((VBusinreceivereport)list.get(i)).getQtyofdox()) + " Dox");
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(inv);
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(vat);
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)9);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReim());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney1() - ((VBusinreceivereport)list.get(i)).getReceiveMoney1()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)11);
                if (((VBusinreceivereport)list.get(i)).getCdate1() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate1()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)12);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getInv());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)13);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney2() - ((VBusinreceivereport)list.get(i)).getReceiveMoney2()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)14);
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate2()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)15);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getNodate7());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)16);
                cell.setCellValue("$" + Double.parseDouble(new BigDecimal(((VBusinreceivereport)list.get(i)).getMoney4() - ((VBusinreceivereport)list.get(i)).getReceiveMoney4()).setScale(2, 4).toString()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)17);
                if (((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinreceivereport)list.get(i)).getCdate4()));
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)18);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)19);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney1().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)20);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney2().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)21);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney4().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)22);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getReceiveMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)23);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getCmoney() - ((VBusinreceivereport)list.get(i)).getReceiveMoney1() - ((VBusinreceivereport)list.get(i)).getReceiveMoney2() - ((VBusinreceivereport)list.get(i)).getReceiveMoney3() - ((VBusinreceivereport)list.get(i)).getReceiveMoney4() - ((VBusinreceivereport)list.get(i)).getReceiveMoney());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)24);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getRemarks2());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)25);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getRemarks());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)26);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)27);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getFty());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)28);
                cell.setCellValue(((VBusinreceivereport)list.get(i)).getArrivalPort());
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "服务单收款进度统计(归档)";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    public String getBusinReceiveDReport2() throws ParseException {
        double money4;
        double money2;
        double money1;
        Object[] obj;
        List list;
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        if ((list = this.reportService.getBusinReceiveList(this.reQuery, obj = new Object[]{sdate, edate, this.flag, 1, this.orderRule}, this.pageNow, this.pageSize)) != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                List rate2;
                List cash2;
                money1 = 0.0;
                money2 = 0.0;
                money4 = 0.0;
                double crate = 0.0;
                List cash = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)1);
                if (cash != null && cash.size() > 0) {
                    for (int j = 0; j < cash.size(); ++j) {
                        if (((TCash)cash.get(j)).getCMoney() == null || !(((TCash)cash.get(j)).getCMoney() > 0.0) || ((TCash)cash.get(j)).getCCount() == null || !(((TCash)cash.get(j)).getCCount() > 0.0)) continue;
                        money1 += ((TCash)cash.get(j)).getCMoney() * ((TCash)cash.get(j)).getCCount();
                    }
                }
                if ((cash2 = this.cashService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && cash2.size() > 0) {
                    for (int j = 0; j < cash2.size(); ++j) {
                        if (((TCash)cash2.get(j)).getCMoney() == null || !(((TCash)cash2.get(j)).getCMoney() > 0.0) || ((TCash)cash2.get(j)).getCCount() == null || !(((TCash)cash2.get(j)).getCCount() > 0.0)) continue;
                        money2 += ((TCash)cash2.get(j)).getCMoney() * ((TCash)cash2.get(j)).getCCount();
                    }
                }
                if ((rate2 = this.cashRateService.getList(((VBusinreceivereport)list.get(i)).getBusinId().intValue(), (short)2)) != null && rate2.size() > 0) {
                    for (int j = 0; j < rate2.size(); ++j) {
                        if (((TCashRate)rate2.get(j)).getCMoney() == null || !(((TCashRate)rate2.get(j)).getCMoney() > 0.0) || ((TCashRate)rate2.get(j)).getCCount() == null || !(((TCashRate)rate2.get(j)).getCCount() > 0.0)) continue;
                        if (((TCashRate)rate2.get(j)).getTRate().getCRate() > 0.0) {
                            crate = ((TCashRate)rate2.get(j)).getTRate().getCRate() / 100.0;
                            money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount() * (1.0 + crate);
                            continue;
                        }
                        money4 += ((TCashRate)rate2.get(j)).getCMoney() * ((TCashRate)rate2.get(j)).getCCount();
                    }
                }
                ((VBusinreceivereport)list.get(i)).setEmpName("$" + Double.parseDouble(new BigDecimal(money1 - ((VBusinreceivereport)list.get(i)).getReceiveMoney1()).setScale(2, 4).toString()));
                ((VBusinreceivereport)list.get(i)).setDescription("$" + Double.parseDouble(new BigDecimal(money2 - ((VBusinreceivereport)list.get(i)).getReceiveMoney2()).setScale(2, 4).toString()));
                ((VBusinreceivereport)list.get(i)).setNodate5("$" + Double.parseDouble(new BigDecimal(money4 - ((VBusinreceivereport)list.get(i)).getReceiveMoney4()).setScale(2, 4).toString()));
                if (((VBusinreceivereport)list.get(i)).getCdate2() != null && ((VBusinreceivereport)list.get(i)).getCdate4() != null) {
                    boolean scope1 = false;
                    boolean scope2 = false;
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope1 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate2().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope1 = true;
                        }
                    }
                    if (this.startDate1 != null && !"".equals(this.startDate1) && this.endDate1 != null && !"".equals(this.endDate1)) {
                        if (((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime() && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    } else {
                        if (this.startDate1 != null && !"".equals(this.startDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() >= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"))).getTime()) {
                            scope2 = true;
                        }
                        if (this.endDate1 != null && !"".equals(this.endDate1) && ((VBusinreceivereport)list.get(i)).getCdate4().getTime() <= Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 23:59:59"))).getTime()) {
                            scope2 = true;
                        }
                    }
                    if (scope1 && scope2) {
                        if (((VBusinreceivereport)list.get(i)).getCdate2().getTime() >= ((VBusinreceivereport)list.get(i)).getCdate4().getTime()) continue;
                        ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                        continue;
                    }
                    if (!scope2) continue;
                    ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
                    continue;
                }
                if (((VBusinreceivereport)list.get(i)).getCdate4() == null) continue;
                ((VBusinreceivereport)list.get(i)).setCdate2(((VBusinreceivereport)list.get(i)).getCdate4());
            }
        }
        this.request.setAttribute("receiveList", (Object)list);
        PageBean pagebean = this.reportService.getBusinReceivePages(this.reQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        money1 = 0.0;
        money2 = 0.0;
        money4 = 0.0;
        double recieve = 0.0;
        double limit = 0.0;
        double recieved = 0.0;
        VBankmoneyreport sum = this.reportService.getRecieveSum(this.reQuery, obj);
        if (sum != null) {
            money1 = sum.getCmoney();
            money2 = sum.getMoney2();
            money4 = sum.getMoney4();
            recieve = sum.getOutMoney1();
            limit = sum.getOutMoney2();
            recieved = sum.getInMoney();
            limit = sum.getOutMoney2();
        }
        this.request.setAttribute("_money1", (Object)money1);
        this.request.setAttribute("_money2", (Object)money2);
        this.request.setAttribute("_money4", (Object)money4);
        this.request.setAttribute("recieve", (Object)recieve);
        this.request.setAttribute("limit", (Object)limit);
        this.request.setAttribute("recieved", (Object)recieved);
        this.request.setAttribute("limit", (Object)limit);
        return "success";
    }

    public void updateRecieveMoney() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.businService.updateRecieveMoney(this.businId, this.cmoney, this.flag);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("更新已收款失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateRecieveRemarks() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCRecieveRemarks(this.remarks);
        try {
            try {
                writer = this.response.getWriter();
                this.businService.saveEntity(bb);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String cancelNotice() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCAcNotice(Short.valueOf((short)0));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void updateRecieveRemarks2() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCRecieveRemarks2(this.remarks);
        try {
            try {
                writer = this.response.getWriter();
                this.businService.saveEntity(bb);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateCostRemarks() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        TCost cc = this.costService.getCostById(this.businId);
        cc.setCRemarks4(this.remarks);
        try {
            try {
                writer = this.response.getWriter();
                this.costService.saveCost(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("更新备注失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateCostMoney() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        TCost cc = this.costService.getCostById(this.businId);
        cc.setCMoney(Double.valueOf(this.cmoney));
        try {
            try {
                writer = this.response.getWriter();
                this.costService.saveCost(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("更新备注失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String getBusinProfitReport() throws ParseException {
    	this.request.setAttribute("queryBean", proQuery);
    	this.request.setAttribute("salerList", salerService.getValidList());
        this.request.setAttribute("businTypeList", (Object)this.businTypeService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate2 != null && !"".equals(this.startDate2)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
        }
        if (this.endDate2 != null && !"".equals(this.endDate2)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 0,request.getSession().getAttribute("fun243")};
        List list = this.reportService.getBusinProfitList(this.proQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("proList", (Object)list);
        PageBean pagebean = this.reportService.getBusinProfitPages(this.proQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        Double profitTotal = this.reportService.getBusinProfitSum(this.proQuery,obj); 
        this.request.setAttribute("profitTotal",profitTotal);
		
		 
        return "success";
    }

    public void getEmpProfitReportCommon(int flag) throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate2 != null && !"".equals(this.startDate2)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
        }
        if (this.endDate2 != null && !"".equals(this.endDate2)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 23:59:59"));
        }
        Object[] obj = new Object[]{sdate, edate};
        List list = null;
        if (flag == 1) {
            list = this.reportService.getEmpProfitReport(this.proQuery, obj, this.pageNow, this.pageSize);
            this.request.setAttribute("proList", (Object)list);
        } else {
            list = this.reportService.getEmpProfitReport(this.proQuery, obj, 1, 100000);
        }
        if (flag == 1) {
            PageBean pagebean = this.reportService.getEmpProfitReportPages(this.proQuery, obj, this.pageNow, this.pageSize);
            this.request.setAttribute("pageBean", (Object)pagebean);
        }
        if (flag == 1) {
            double sumMoney = 0.0;
            double sumMoney2 = 0.0;
            VBankmoneyreport sum = this.reportService.getEmpProfitSum(this.proQuery, obj);
            if (sum != null) {
                sumMoney = sum.getOutMoney1();
                sumMoney2 = sum.getOutMoney2();
            }
            this.request.setAttribute("sumMoney", (Object)sumMoney);
            this.request.setAttribute("sumMoney2", (Object)sumMoney2);
        }
        if (flag == 2) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("利润统计(按制单人)");
            int num = 0;
            HSSFRow row = sheet.createRow(num);
            row.setHeight((short)760);
            HSSFCell cell = row.createCell((short)0);
            HSSFCellStyle headStyle = workbook.createCellStyle();
            headStyle.setVerticalAlignment((short)1);
            headStyle.setAlignment((short)2);
            HSSFFont headFont = workbook.createFont();
            headFont.setFontHeightInPoints((short)12);
            headFont.setBoldweight((short)700);
            headStyle.setFont(headFont);
            cell.setCellStyle(headStyle);
            double n = 256.0;
            cell = row.createCell((short)0);
            cell.setCellValue("业务员 saler");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)1);
            cell.setCellValue("成本金额");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)2);
            cell.setCellValue("发票1");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)3);
            cell.setCellValue("发票2");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)4);
            cell.setCellValue("含税发票");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)5);
            cell.setCellValue("实收金额");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)6);
            cell.setCellValue("应得利润");
            cell.setCellStyle(headStyle);
            cell = row.createCell((short)7);
            cell.setCellValue("实际利润");
            cell.setCellStyle(headStyle);
            sheet.setColumnWidth(0, (short)n * 16);
            sheet.setColumnWidth(1, (short)n * 22);
            sheet.setColumnWidth(2, (short)n * 22);
            sheet.setColumnWidth(3, (short)n * 22);
            sheet.setColumnWidth(4, (short)n * 22);
            sheet.setColumnWidth(5, (short)n * 22);
            sheet.setColumnWidth(6, (short)n * 22);
            sheet.setColumnWidth(7, (short)n * 22);
            HSSFCellStyle colStyle = workbook.createCellStyle();
            colStyle.setVerticalAlignment((short)1);
            colStyle.setAlignment((short)2);
            colStyle.setWrapText(true);
            HSSFFont colFont = workbook.createFont();
            colFont.setFontHeightInPoints((short)12);
            colStyle.setFont(colFont);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); ++i) {
                    row = sheet.createRow(++num);
                    cell = row.createCell((short)0);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getEmpName());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)1);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getCostMoney().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)2);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getCashMoney().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)3);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getCashMoney2().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)4);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getCashMoney4().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)5);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getReceiveMoney().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)6);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getProfit().doubleValue());
                    cell.setCellStyle(colStyle);
                    cell = row.createCell((short)7);
                    cell.setCellValue(((VEmpprofitreport)list.get(i)).getProfit2().doubleValue());
                    cell.setCellStyle(colStyle);
                }
            }
            String targetFileName = "利润统计(按制单人)";
            String userAgent = this.request.getHeader("USER-AGENT");
            targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
            this.workbook2InputStream(workbook, targetFileName);
        }
    }

    public String getEmpProfitReport() throws Exception {
        this.getEmpProfitReportCommon(1);
        return "success";
    }

    public String expEmpProfitReport() throws Exception {
        this.getEmpProfitReportCommon(2);
        return "success";
    }

    public String getBusinProfitReport2() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate2 != null && !"".equals(this.startDate2)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
        }
        if (this.endDate2 != null && !"".equals(this.endDate2)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 1};
        List list = this.reportService.getBusinProfitList(this.proQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("proList", (Object)list);
        PageBean pagebean = this.reportService.getBusinProfitPages(this.proQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        double sumMoney = 0.0;
        double sumMoney2 = 0.0;
        VBankmoneyreport sum = this.reportService.getProfitSum(this.proQuery, obj);
        if (sum != null) {
            sumMoney = sum.getOutMoney1();
            sumMoney2 = sum.getOutMoney2();
        }
        this.request.setAttribute("sumMoney", (Object)sumMoney);
        this.request.setAttribute("sumMoney2", (Object)sumMoney2);
        return "success";
    }

    public String expProfitReport() throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate2 != null && !"".equals(this.startDate2)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
        }
        if (this.endDate2 != null && !"".equals(this.endDate2)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 1};
        List list = this.reportService.getBusinProfitList(this.proQuery, obj, 1, 100000);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("利润统计（归档）");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("日期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("服务单号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("Container #");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("外包");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("S.Dam Truck");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("Em Chanthy");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("Vat to S.Dam");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("成本金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("发票1");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("发票2");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)11);
        cell.setCellValue("含税发票");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)12);
        cell.setCellValue("实收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)13);
        cell.setCellValue("应得利润");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)14);
        cell.setCellValue("应得利润百分比");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)15);
        cell.setCellValue("实际利润");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)16);
        cell.setCellValue("完结标记");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 42);
        sheet.setColumnWidth(2, (short)n * 26);
        sheet.setColumnWidth(3, (short)n * 16);
        sheet.setColumnWidth(4, (short)n * 16);
        sheet.setColumnWidth(5, (short)n * 16);
        sheet.setColumnWidth(6, (short)n * 16);
        sheet.setColumnWidth(7, (short)n * 16);
        sheet.setColumnWidth(8, (short)n * 20);
        sheet.setColumnWidth(9, (short)n * 20);
        sheet.setColumnWidth(10, (short)n * 20);
        sheet.setColumnWidth(11, (short)n * 20);
        sheet.setColumnWidth(12, (short)n * 20);
        sheet.setColumnWidth(13, (short)n * 20);
        sheet.setColumnWidth(14, (short)n * 20);
        sheet.setColumnWidth(15, (short)n * 20);
        sheet.setColumnWidth(16, (short)n * 20);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                row = sheet.createRow(++num);
                double money1 = 0.0;
                double money2 = 0.0;
                double money3 = 0.0;
                double money4 = 0.0;
                double crate = 0.0;
                cell = row.createCell((short)0);
                if (((VBusinprofitreport)list.get(i)).getCdate() != null) {
                    cell.setCellValue(this.formatter.format(((VBusinprofitreport)list.get(i)).getCdate()));
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                if (((VBusinprofitreport)list.get(i)).getConNum() != null) {
                    cell.setCellValue(((VBusinprofitreport)list.get(i)).getConNum());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getWbMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getTruck().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getEm().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getVat().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getCostMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)9);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getCashMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getCashMoney2().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)11);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getCashMoney4().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)12);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getReceiveMoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)13);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getProfit().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)14);
                if (((VBusinprofitreport)list.get(i)).getCostMoney() != null && ((VBusinprofitreport)list.get(i)).getCostMoney() > 0.0) {
                    cell.setCellValue(String.valueOf(Double.parseDouble(new BigDecimal(((VBusinprofitreport)list.get(i)).getProfit() / ((VBusinprofitreport)list.get(i)).getCostMoney() * 100.0).setScale(2, 4).toString())) + "%");
                } else {
                    cell.setCellValue("0.00%");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)15);
                cell.setCellValue(((VBusinprofitreport)list.get(i)).getProfit2().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)16);
                if (((VBusinprofitreport)list.get(i)).getComplete() == 1) {
                    cell.setCellValue("已完结");
                } else {
                    cell.setCellValue("未完结");
                }
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "利润统计（归档）";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    public String getBankMoneyReport() {
        List list = this.reportService.getBankMoneyList(this.bankQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("bankList", (Object)list);
        PageBean pagebean = this.reportService.getBankMoneyPages(this.bankQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String costReport() throws ParseException {
        List itemList = this.costItemService.getValidList();
        if (itemList != null && itemList.size() > 0) {
            for (int i = itemList.size() - 1; i >= 0; --i) {
                if (((TCostItem)itemList.get(i)).getCType() != 2) continue;
                itemList.remove(i);
            }
        }
        this.request.setAttribute("itemList", (Object)itemList);
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 1};
        if (this.query == null) {
            VCostReport qq = new VCostReport();
            VCostReportId qid = new VCostReportId();
            qid.setHiddenFlag(Integer.valueOf(0));
            qq.setId(qid);
            this.query = qq;
        }
        List list = this.reportService.getCostReport(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.reportService.getCostReportPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String costReport2() throws ParseException {
        this.request.setAttribute("costGroup", (Object)this.businService.getCostGroupValidList());
        List itemList = this.costItemService.getValidList();
        if (itemList != null && itemList.size() > 0) {
            for (int i = itemList.size() - 1; i >= 0; --i) {
                if (((TCostItem)itemList.get(i)).getCType() != 2) continue;
                itemList.remove(i);
            }
        }
        this.request.setAttribute("itemList", (Object)itemList);
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        } else if (this.flag == 0) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.startDate1 = this.formatter.format(CommonUtil.getDatetime());
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        } else if (this.flag == 0) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.endDate1 = this.formatter.format(CommonUtil.getDatetime());
        }
        Object[] obj = new Object[]{sdate, edate, 2, this.container};
        if (this.query2 == null) {
            VCostReport2 qq = new VCostReport2();
            VCostReport2Id qid = new VCostReport2Id();
            qid.setHiddenFlag(Integer.valueOf(0));
            qq.setId(qid);
            this.query2 = qq;
        }
        List list = this.reportService.getCostReport2(this.query2, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("list", (Object)list);
        PageBean pagebean = this.reportService.getCostReportPages2(this.query2, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        double sumMoney = this.reportService.getCostReportSum2(this.query2, obj);
        this.request.setAttribute("sumMoney", (Object)sumMoney);
        return "success";
    }

    public String expCostReport2() throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 2, this.container};
        if (this.query2 == null) {
            VCostReport2 qq = new VCostReport2();
            VCostReport2Id qid = new VCostReport2Id();
            qid.setHiddenFlag(Integer.valueOf(0));
            qq.setId(qid);
            this.query2 = qq;
        }
        List list = this.reportService.getCostReport2(this.query2, obj, 1, 100000);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Clearance Cost");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("Cost Date");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("成本金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("货柜号码");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("Client 客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("Ref");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("File 业务单号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("（成本项目组）名称");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 16);
        sheet.setColumnWidth(2, (short)n * 36);
        sheet.setColumnWidth(3, (short)n * 36);
        sheet.setColumnWidth(4, (short)n * 20);
        sheet.setColumnWidth(5, (short)n * 28);
        sheet.setColumnWidth(6, (short)n * 30);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                row = sheet.createRow(++num);
                cell = row.createCell((short)0);
                if (((VCostReport2)list.get(i)).getId().getCdate() != null && !"1999-01-01".equals(((VCostReport2)list.get(i)).getId().getCdate().toString())) {
                    cell.setCellValue(((VCostReport2)list.get(i)).getId().getCdate().toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VCostReport2)list.get(i)).getId().getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                String container = "";
                List cons = this.containerService.getList(((VCostReport2)list.get(i)).getId().getBid().intValue());
                if (cons != null && cons.size() > 0) {
                    StringBuffer conNum = new StringBuffer();
                    for (int j = 0; j < cons.size(); ++j) {
                        String type;
                        boolean value = false;
                        String _num = ((TContainer)cons.get(j)).getCContainerNum();
                        if (_num != null && !"".equals(_num)) {
                            conNum.append(_num).append(" ");
                            value = true;
                        }
                        if ((type = ((TContainer)cons.get(j)).getCContainerType()) != null && !"".equals(type)) {
                            conNum.append(type);
                            value = true;
                        }
                        if (!value) continue;
                        conNum.append("；");
                    }
                    if (!"".equals(conNum.toString().trim())) {
                        container = conNum.toString().substring(0, conNum.toString().length() - 1);
                    }
                }
                cell = row.createCell((short)2);
                cell.setCellValue(container);
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VCostReport2)list.get(i)).getId().getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VCostReport2)list.get(i)).getId().getRefno());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                cell.setCellValue(((VCostReport2)list.get(i)).getId().getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                if (((VCostReport2)list.get(i)).getId().getGroupid() > 0L) {
                    cell.setCellValue(((VCostReport2)list.get(i)).getId().getGroupName());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "Clearance Cost";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    public String expCostReport() throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, this.cflag};
        List list = this.reportService.getCostReport(this.query, obj, 1, 65535);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("服务单成本统计");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("单据日期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("服务单号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("成本项目");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("INV2");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("成本金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("RE");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("出纳1");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("出纳2");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("财务");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("审核");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)11);
        cell.setCellValue("备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)12);
        cell.setCellValue("归档标记");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)13);
        cell.setCellValue("隐藏标记");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 20);
        sheet.setColumnWidth(2, (short)n * 36);
        sheet.setColumnWidth(3, (short)n * 36);
        sheet.setColumnWidth(4, (short)n * 16);
        sheet.setColumnWidth(5, (short)n * 16);
        sheet.setColumnWidth(6, (short)n * 20);
        sheet.setColumnWidth(7, (short)n * 20);
        sheet.setColumnWidth(8, (short)n * 20);
        sheet.setColumnWidth(9, (short)n * 28);
        sheet.setColumnWidth(10, (short)n * 28);
        sheet.setColumnWidth(11, (short)n * 20);
        sheet.setColumnWidth(12, (short)n * 20);
        sheet.setColumnWidth(13, (short)n * 20);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                row = sheet.createRow(++num);
                cell = row.createCell((short)0);
                if (((VCostReport)list.get(i)).getId().getCdate() != null && !"1999-01-01".equals(((VCostReport)list.get(i)).getId().getCdate().toString())) {
                    cell.setCellValue(((VCostReport)list.get(i)).getId().getCdate().toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getItemName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getInv2());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getRe());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getRem());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getNt());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)9);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getRemarks());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getRemarks5());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)11);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getCost1());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)12);
                cell.setCellValue(((VCostReport)list.get(i)).getId().getStateName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)13);
                if (((VCostReport)list.get(i)).getId().getHiddenFlag() == 0) {
                    cell.setCellValue("未隐藏");
                } else {
                    cell.setCellValue("隐藏");
                }
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "服务单成本统计";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    private void workbook2InputStream(HSSFWorkbook workbook, String fileName) throws Exception {
        this.fileName = fileName;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write((OutputStream)baos);
        baos.flush();
        byte[] aa = baos.toByteArray();
        this.excelStream = new ByteArrayInputStream(aa, 0, aa.length);
        baos.close();
    }

    public String scheduleArchive() {
        try {
            this.businService.updateScheduleArchive(this.businId, (short)1, this.cmoney);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String scheduleArchive2() {
        try {
            this.businService.updateScheduleArchive2(this.businIds, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String cancelScheduleArchive() {
        try {
            this.businService.updateScheduleArchive(this.businId, (short)0, 0.0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String rateArchive() {
        try {
            this.businService.updateRateArchive(this.businId, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String rateBatchArchive() {
        try {
            this.businService.updateRateArchive(this.businIds, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String cancelRateArchive() {
        try {
            this.businService.updateRateArchive(this.businId, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String getBusinProfitCusList() throws ParseException {
        Calendar calendar;
        this.request.setAttribute("queryBean", businProfitCusQuery);
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        this.request.setAttribute("saleList", (Object)this.salerService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        } else if (this.firstQuery == 1) {
            calendar = Calendar.getInstance();
            calendar.add(2, -2);
            calendar.set(5, 1);
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            this.startDate1 = this.formatter.format(calendar.getTime());
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        } else if (this.firstQuery == 1) {
            calendar = Calendar.getInstance();
            calendar.set(5, calendar.getActualMaximum(5));
            calendar.add(2, -2);
            edate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            this.endDate1 = this.formatter.format(calendar.getTime());
        }
        Object[] obj = new Object[]{sdate, edate};
        try {
        	List list = this.reportService.getBusinProfitCusList(this.businProfitCusQuery, obj, this.pageNow, 60);
        	this.request.setAttribute("proList", (Object)list);
        	if (list != null && list.size() > 0) {
        		this.request.setAttribute("rowcount", (Object)list.size());
        	} else {
        		this.request.setAttribute("rowcount", (Object)0);
        	}
        	PageBean pagebean = this.reportService.getBusinProfitCusPages(this.businProfitCusQuery, obj, this.pageNow, 60);
        	this.request.setAttribute("pageBean", (Object)pagebean);
		} catch (Exception e) {
			e.printStackTrace();
		}
        double sumMoney = 0.0;
        double sumMoney2 = 0.0;
        sumMoney = this.reportService.getBusinProfitCusSum(this.businProfitCusQuery, obj);
        sumMoney2 = this.reportService.getBusinProfitCusSum2(this.businProfitCusQuery, obj);
        this.request.setAttribute("sumMoney", (Object)sumMoney);
        this.request.setAttribute("sumMoney2", (Object)sumMoney2);
        return "success";
    }

    public String expBusinReceiveCusReport() throws Exception {
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        String sdate = null;
        String edate = null;
        if (this.startDate1 != null && !"".equals(this.startDate1)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate1) + " 00:00:00"));
        }
        if (this.endDate1 != null && !"".equals(this.endDate1)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate1) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate};
        List list = this.reportService.getBusinProfitCusList(this.businProfitCusQuery, obj, 1, 1000000);
        this.request.setAttribute("proList", (Object)list);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("应收(客户总计)");
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        HSSFCell cell = row.createCell((short)0);
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        cell.setCellStyle(headStyle);
        double n = 256.0;
        cell = row.createCell((short)0);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("应收金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("差额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("该付款日期");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("超时提醒");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("备注");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("收款人");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("付款方式备注");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 36);
        sheet.setColumnWidth(1, (short)n * 22);
        sheet.setColumnWidth(2, (short)n * 22);
        sheet.setColumnWidth(3, (short)n * 22);
        sheet.setColumnWidth(4, (short)n * 22);
        sheet.setColumnWidth(5, (short)n * 36);
        sheet.setColumnWidth(6, (short)n * 22);
        sheet.setColumnWidth(7, (short)n * 36);
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); ++i) {
                row = sheet.createRow(++num);
                cell = row.createCell((short)0);
                cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getCmoney2().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                if (((VBusinprofitcusreport)list.get(i)).getPayableDate() != null) {
                    cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getPayableDate());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                if (((VBusinprofitcusreport)list.get(i)).getFlag2() == 1) {
                    cell.setCellValue("超时");
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                if (((VBusinprofitcusreport)list.get(i)).getRemarks() != null) {
                    cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getRemarks());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                if (((VBusinprofitcusreport)list.get(i)).getRemarks() != null) {
                    cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getSaleName());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                if (((VBusinprofitcusreport)list.get(i)).getRemarks2() != null) {
                    cell.setCellValue(((VBusinprofitcusreport)list.get(i)).getRemarks2());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "应收(客户总计)";
        String userAgent = this.request.getHeader("USER-AGENT");
        targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
        this.workbook2InputStream(workbook, targetFileName);
        return "success";
    }

    public void updateCusRemarks() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                TProfitCus cc = this.reportService.getProfitCusByCusid(this.cusId);
                if (cc == null) {
                    cc = new TProfitCus();
                    cc.setTCustomer(this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId)));
                }
                cc.setCRemarks(this.remarks);
                this.reportService.updateProfitCus(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("自动更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateCusRemarks2() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                TProfitCus cc = this.reportService.getProfitCusByCusid(this.cusId);
                if (cc == null) {
                    cc = new TProfitCus();
                    cc.setTCustomer(this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId)));
                    cc.setCRemarks("");
                }
                cc.setCRemarks2(this.remarks);
                this.reportService.updateProfitCus(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("自动更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateCusPayableDatetime() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                TProfitCus cc = this.reportService.getProfitCusByCusid(this.cusId);
                if (cc == null) {
                    cc = new TProfitCus();
                    cc.setTCustomer(this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId)));
                }
                if (this.payableDatetime == null || "".equals(this.payableDatetime.trim())) {
                    cc.setCPayableDate(null);
                } else {
                    cc.setCPayableDate(Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.formatter3.parse(String.valueOf(this.payableDatetime) + " 00:00:00"))));
                }
                cc.setCRemarks("");
                this.reportService.updateProfitCus(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("自动更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateCusSale() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                TProfitCus cc = this.reportService.getProfitCusByCusid(this.cusId);
                if (cc == null) {
                    cc = new TProfitCus();
                    cc.setTCustomer(this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId)));
                    cc.setCRemarks("");
                }
                if (this.saleId == 0) {
                    cc.setCSaleId(null);
                } else {
                    cc.setCSaleId(Integer.valueOf(this.saleId));
                }
                this.reportService.updateProfitCus(cc);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("自动更新失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String updateCusFlag() {
        TProfitCus cc = this.reportService.getProfitCusByCusid(this.cusId);
        if (cc == null) {
            cc = new TProfitCus();
            cc.setTCustomer(this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId)));
            cc.setCRemarks("");
        }
        cc.setCFlag(Short.valueOf(this.cflag));
        try {
            this.reportService.updateProfitCus(cc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String payComplete() {
        try {
            this.reportService.updatePayComplete(this.businId, this.payCompleteFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String payComplete2() {
        try {
            this.reportService.updatePayComplete2(this.businIds, this.payCompleteFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String jiezhuanReport() {
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        List list = this.reportService.getJiezhuanList(this.queryJiezhuan, null, this.pageNow, this.pageSize);
        this.request.setAttribute("reportList", (Object)list);
        PageBean pagebean = this.reportService.getJiezhuanPages(this.queryJiezhuan, null, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String cancelJiezhuanNotice() {
        TJiezhuan jz = this.reportService.getJiezhuan(this.businId);
        if (jz != null) {
            jz.setCNotice(Short.valueOf((short)0));
            try {
                this.reportService.saveJiezhuan(jz);
            }
            catch (Exception e) {
                e.printStackTrace();
                return "input";
            }
        }
        return "success";
    }

    public void exportReport() throws Exception {
    	try {
    		String targetFileName = "利润统计";
    		String userAgent = this.request.getHeader("USER-AGENT");
    		targetFileName = StringUtils.contains((CharSequence)userAgent, (CharSequence)"MSIE") ? URLEncoder.encode(targetFileName, "UTF-8") : (StringUtils.contains((CharSequence)userAgent, (CharSequence)"Mozilla") ? new String(targetFileName.getBytes("UTF-8"), "ISO8859-1") : URLEncoder.encode(targetFileName, "UTF-8"));
    		response.setHeader("Content-Disposition", "attachment;Filename=" + targetFileName + ".xls");
    		
    		String sdate = null;
    		String edate = null;
    		if (this.startDate2 != null && !"".equals(this.startDate2)) {
    			sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
    		}
    		if (this.endDate2 != null && !"".equals(this.endDate2)) {
    			edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 00:00:00"));
    		}
    		Object[] obj = new Object[]{sdate, edate, 0};
    		List<VBusinprofitreport> list = this.reportService.getBusinProfitList(this.proQuery, obj, 1, 65535);
    		String[] headers = {"客户名称","服务单号","Container #","成本金额","发票1","发票2","含税发票","实收金额","应得利润","实际利润","制单人","审核状态"};
    		String[] fields = {"cusName","billNo","conNum","costMoney","cashMoney","cashMoney2","cashMoney4","receiveMoney","profit","profit2","empName","recieveFlagStr"};
    		parse(list);
    		ExportUtil.exportExcel(list, fields, headers, response.getOutputStream(), "利润统计");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	private void parse(List<VBusinprofitreport> list) {
		for (VBusinprofitreport vBusinprofitreport : list) {
			vBusinprofitreport.setRecieveFlagStr(vBusinprofitreport.getRecieveFlag() == 1?"已审核":"未审核");
		}
	}
	
	public String getCostCashoutReport() throws Exception {
		this.request.setAttribute("agentList", (Object)this.agentService.getList(null,1,65535));
		this.request.setAttribute("cusList", (Object)this.cusService.getList(null,1,65535));
		Map<String, String[]> parameterMap = request.getParameterMap();
		if(parameterMap.size() > 0) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String billNo = request.getParameter("billNo");
			String agent = request.getParameter("agent");
			String status = request.getParameter("status");
			String cusId = request.getParameter("cusId");
			costCashoutReport = new CostCashoutReport();
			costCashoutReport.setStartDate(startDate);
			costCashoutReport.setEndDate(endDate);
			costCashoutReport.setBillNo(billNo);
			costCashoutReport.setAgent(agent);
			costCashoutReport.setStatus(status);
			if(StringUtils.isNotBlank(cusId)) {
				costCashoutReport.setCusId(Integer.parseInt(cusId));
			}
			request.setAttribute("query", costCashoutReport);
			List<CostCashoutReport> list = reportService.getCostCashoutReport(costCashoutReport,this.pageNow, this.pageSize);
			this.request.setAttribute("list", list);
			parseCostCashoutReportList(list);
	        PageBean pagebean = this.reportService.getCostCashoutReportPages(this.costCashoutReport, this.pageNow, this.pageSize);
	        this.request.setAttribute("pageBean", (Object)pagebean);
		}
		
		return "success";
	}

	private void parseCostCashoutReportList(List<CostCashoutReport> list) {
		for (CostCashoutReport costCashoutReport : list) {
			if(costCashoutReport.getDiffMoney() != null) {
				if(costCashoutReport.getDiffMoney().doubleValue() > 0) {
					costCashoutReport.setStatus("未支付");
				}else {
					costCashoutReport.setStatus("已支付");
				}
			}
		}
	}

	public String getBankAccountReport() throws Exception {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		BankAccountReport bankAccount = new BankAccountReport();
		bankAccount.setStartDate(startDate);
		bankAccount.setEndDate(endDate);
		request.setAttribute("query", bankAccount);
		List<TBank> bankList = bankService.getList(null,this.pageNow, this.pageSize);
		Map<Integer,BankAccountReport> bankMap = new LinkedHashMap<Integer,BankAccountReport>();
		for (TBank tBank : bankList) {
			BankAccountReport bean = new BankAccountReport();
			bean.setBankId(String.valueOf(tBank.getCId()));
			bean.setBankName(tBank.getCName());
			bankMap.put(tBank.getCId(),bean);
		}
		List<BankAccountReport> list = reportService.getBankAccountReport(bankAccount,this.pageNow, this.pageSize,bankMap);
		this.request.setAttribute("bankList", list);
        PageBean pagebean = this.reportService.getBankAccountReportPages(bankAccount, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
		return "success";
	}
	public VBusinreceivereport getReQuery() {
        return this.reQuery;
    }

    public void setReQuery(VBusinreceivereport reQuery) {
        this.reQuery = reQuery;
    }

    public String getStartDate1() {
        return this.startDate1;
    }

    public void setStartDate1(String startDate1) {
        this.startDate1 = startDate1;
    }

    public String getEndDate1() {
        return this.endDate1;
    }

    public void setEndDate1(String endDate1) {
        this.endDate1 = endDate1;
    }

    public String getStartDate2() {
        return this.startDate2;
    }

    public void setStartDate2(String startDate2) {
        this.startDate2 = startDate2;
    }

    public String getEndDate2() {
        return this.endDate2;
    }

    public void setEndDate2(String endDate2) {
        this.endDate2 = endDate2;
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

    public void setReportService(ReportService reportService) {
        this.reportService = reportService;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public VBusinprofitreport getProQuery() {
        return this.proQuery;
    }

    public void setProQuery(VBusinprofitreport proQuery) {
        this.proQuery = proQuery;
    }

    public VBankmoneyreport getBankQuery() {
        return this.bankQuery;
    }

    public void setBankQuery(VBankmoneyreport bankQuery) {
        this.bankQuery = bankQuery;
    }

    public void setBusinService(BusinService businService) {
        this.businService = businService;
    }

    public int getBusinId() {
        return this.businId;
    }

    public void setBusinId(int businId) {
        this.businId = businId;
    }

    public double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(double cmoney) {
        this.cmoney = cmoney;
    }

    public VCostReport getQuery() {
        return this.query;
    }

    public void setQuery(VCostReport query) {
        this.query = query;
    }

    public void setCostItemService(CostItemService costItemService) {
        this.costItemService = costItemService;
    }

    public InputStream getExcelStream() {
        return this.excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setCashRateService(CashRateService cashRateService) {
        this.cashRateService = cashRateService;
    }

    public void setCashService(CashService cashService) {
        this.cashService = cashService;
    }

    public void setCostService(CostService costService) {
        this.costService = costService;
    }

    public VBusinprofitcusreport getBusinProfitCusQuery() {
        return this.businProfitCusQuery;
    }

    public void setBusinProfitCusQuery(VBusinprofitcusreport businProfitCusQuery) {
        this.businProfitCusQuery = businProfitCusQuery;
    }

    public void setCusService(CusService cusService) {
        this.cusService = cusService;
    }

    public int getCusId() {
        return this.cusId;
    }

    public short getCflag() {
        return this.cflag;
    }

    public void setCflag(short cflag) {
        this.cflag = cflag;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int[] getBusinIds() {
        return this.businIds;
    }

    public void setBusinIds(int[] businIds) {
        this.businIds = businIds;
    }

    public Short getPayCompleteFlag() {
        return this.payCompleteFlag;
    }

    public void setPayCompleteFlag(Short payCompleteFlag) {
        this.payCompleteFlag = payCompleteFlag;
    }

    public int getOrderRule() {
        return this.orderRule;
    }

    public void setOrderRule(int orderRule) {
        this.orderRule = orderRule;
    }

    public void setBusinTypeService(BusinTypeService businTypeService) {
        this.businTypeService = businTypeService;
    }

    public String getPayableDatetime() {
        return this.payableDatetime;
    }

    public void setPayableDatetime(String payableDatetime) {
        this.payableDatetime = payableDatetime;
    }

    public void setSalerService(SalerService salerService) {
        this.salerService = salerService;
    }

    public int getSaleId() {
        return this.saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getContainer() {
        return this.container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public void setContainerService(ContainerService containerService) {
        this.containerService = containerService;
    }

    public VCostReport2 getQuery2() {
        return this.query2;
    }

    public void setQuery2(VCostReport2 query2) {
        this.query2 = query2;
    }

    public short getJiezhuan() {
        return this.jiezhuan;
    }

    public void setJiezhuan(short jiezhuan) {
        this.jiezhuan = jiezhuan;
    }

    public TJiezhuan getQueryJiezhuan() {
        return this.queryJiezhuan;
    }

    public void setQueryJiezhuan(TJiezhuan queryJiezhuan) {
        this.queryJiezhuan = queryJiezhuan;
    }

    public int getFirstQuery() {
        return this.firstQuery;
    }

    public void setFirstQuery(int firstQuery) {
        this.firstQuery = firstQuery;
    }

	public void setCostCashoutReport(CostCashoutReport costCashoutReport) {
		this.costCashoutReport = costCashoutReport;
	}

	public AgentService getAgentService() {
		return agentService;
	}

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}
    
}

