/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TContainerInfo
 *  com.common.entity.TContainerRecord
 *  com.common.entity.VConcompute
 *  com.common.entity.VContainerRecord
 *  com.common.entity.VConview
 *  com.common.util.PageBean
 *  com.oa.container.action.ConRecordAction
 *  com.oa.container.service.ConInfoService
 *  com.oa.container.service.ConRecordService
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.apache.commons.lang3.StringUtils
 *  org.apache.poi.hssf.usermodel.HSSFCell
 *  org.apache.poi.hssf.usermodel.HSSFCellStyle
 *  org.apache.poi.hssf.usermodel.HSSFFont
 *  org.apache.poi.hssf.usermodel.HSSFRow
 *  org.apache.poi.hssf.usermodel.HSSFSheet
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.struts2.ServletActionContext
 */
package com.oa.container.action;

import com.common.entity.TContainerInfo;
import com.common.entity.TContainerRecord;
import com.common.entity.VConcompute;
import com.common.entity.VContainerRecord;
import com.common.entity.VConview;
import com.common.util.PageBean;
import com.oa.container.service.ConInfoService;
import com.oa.container.service.ConRecordService;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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

public class ConRecordAction
extends ActionSupport {
    private ConRecordService conRecordService;
    private ConInfoService conInfoService;
    private VContainerRecord query;
    private TContainerRecord record;
    private String startDate;
    private String endDate;
    private String code;
    private int id;
    private String cdate;
    private VConview reportQuery;
    private VConcompute computeQuery;
    private int flag;
    private int pageNow = 1;
    private int pageSize = 20;
    private Short cstate;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private InputStream excelStream;
    private String fileName;

    public String getList() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 1};
        List list = this.conRecordService.getList(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("conRecordList", (Object)list);
        PageBean pagebean = this.conRecordService.getPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String getList2() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, 2};
        List list = this.conRecordService.getList(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("conRecordList", (Object)list);
        PageBean pagebean = this.conRecordService.getPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public void getConInfo() {
        this.flag = this.record != null && this.record.getTContainerInfo() != null ? this.record.getTContainerInfo().getCId() : 0;
        if (this.flag == 0) {
            this.request.setAttribute("conInfoList", (Object)this.conInfoService.getValidList((Serializable)Integer.valueOf(-1)));
        } else {
            this.request.setAttribute("conInfoList", (Object)this.conInfoService.getValidList((Serializable)Integer.valueOf(this.flag)));
        }
    }

    public String addConRecord() {
        this.getConInfo();
        return "success";
    }

    public String saveConRecord() {
        if (this.cdate == null || "".equals(this.cdate.trim())) {
            this.getConInfo();
            this.request.setAttribute("errInfo", (Object)"日期不能为空");
            return "input";
        }
        try {
            this.record.setCDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.cdate) + " 00:00:00"))));
        }
        catch (ParseException e) {
            e.printStackTrace();
            this.getConInfo();
            this.request.setAttribute("errInfo", (Object)"日期格式不正确");
            return "input";
        }
        if (this.record.getTContainerInfo().getCId() == null || this.record.getTContainerInfo().getCId() == -1) {
            this.getConInfo();
            this.request.setAttribute("errInfo", (Object)"货柜编号不能为空");
            return "input";
        }
        if (this.record.getCOpertype() == -1) {
            this.getConInfo();
            this.request.setAttribute("errInfo", (Object)"操作类型不能为空");
            return "input";
        }
        if (this.record.getCOpertype() == 3) {
            this.record.setCMoney(null);
        }
        try {
            this.conRecordService.saveEntity(this.record);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editConRecord() {
        this.record = this.conRecordService.getEntityById((Serializable)Integer.valueOf(this.id));
        this.cdate = this.formatter.format(this.record.getCDate());
        this.getConInfo();
        return "success";
    }

    public String deleteConRecord() {
        try {
            this.conRecordService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String printRecord() {
        TContainerInfo tmp = this.conInfoService.getEntityById2((Serializable)this.record.getTContainerInfo().getCId());
        String infoCode = tmp.getCCode();
        this.request.setAttribute("infoCode", (Object)infoCode);
        String modelCode = tmp.getTContainerModel().getCCode();
        this.request.setAttribute("modelCode", (Object)modelCode);
        return "success";
    }

    public String expRecord() throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, this.cstate};
        List list = this.conRecordService.getList(this.query, obj, 1, 1000000);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("货柜进出登记");
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
        cell.setCellValue("货柜编号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("货柜型号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("操作类型");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("金额");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("Where");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("经手人");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("归档状态");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 26);
        sheet.setColumnWidth(2, (short)n * 20);
        sheet.setColumnWidth(3, (short)n * 20);
        sheet.setColumnWidth(4, (short)n * 20);
        sheet.setColumnWidth(5, (short)n * 24);
        sheet.setColumnWidth(6, (short)n * 30);
        sheet.setColumnWidth(7, (short)n * 16);
        sheet.setColumnWidth(8, (short)n * 16);
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
                cell.setCellValue(this.formatter.format(((VContainerRecord)list.get(i)).getCdate()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                if (((VContainerRecord)list.get(i)).getCusname() != null) {
                    cell.setCellValue(((VContainerRecord)list.get(i)).getCusname());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VContainerRecord)list.get(i)).getInfoCode());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VContainerRecord)list.get(i)).getModelCode());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VContainerRecord)list.get(i)).getOperName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                if (((VContainerRecord)list.get(i)).getCmoney() != null) {
                    cell.setCellValue(((VContainerRecord)list.get(i)).getCmoney().doubleValue());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                cell.setCellValue(((VContainerRecord)list.get(i)).getConTo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(((VContainerRecord)list.get(i)).getEmpName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(((VContainerRecord)list.get(i)).getStateName());
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "货柜进出登记";
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

    public String conReportList() {
        List reportList = this.conRecordService.getReportList(this.reportQuery, this.pageNow, this.pageSize);
        this.request.setAttribute("reportList", (Object)reportList);
        PageBean pagebean = this.conRecordService.getReportPages(this.reportQuery, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        this.request.setAttribute("rowcount", (Object)reportList.size());
        int i = 0;
        int j = 0;
        int k = 0;
        double l = 0.0;
        if (reportList != null && reportList.size() > 0) {
            for (int m = 0; m < reportList.size(); ++m) {
                if (((VConview)reportList.get(m)).getCOpertype() == 3) {
                    ++i;
                } else if (((VConview)reportList.get(m)).getCOpertype() == 1) {
                    ++j;
                } else {
                    ++k;
                }
                l += ((VConview)reportList.get(m)).getInfoPrice().doubleValue();
            }
        }
        this.request.setAttribute("i", (Object)i);
        this.request.setAttribute("j", (Object)j);
        this.request.setAttribute("k", (Object)k);
        this.request.setAttribute("l", (Object)new BigDecimal(l).setScale(2, 4));
        return "success";
    }

    public String conComputeList() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate};
        List computeList = this.conRecordService.getComputeList(this.computeQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("computeList", (Object)computeList);
        PageBean pagebean = this.conRecordService.getComputePages(this.computeQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        double i = this.conRecordService.getConCost(this.computeQuery, obj);
        double j = 0.0;
        double k = 0.0;
        double l = 0.0;
        if (computeList != null && computeList.size() > 0) {
            for (int m = 0; m < computeList.size(); ++m) {
                if (computeList.get(m) == null || ((VConcompute)computeList.get(m)).getOpertype() == null) continue;
                if (((VConcompute)computeList.get(m)).getOpertype() == 1) {
                    j += ((VConcompute)computeList.get(m)).getCmoney().doubleValue();
                    continue;
                }
                if (((VConcompute)computeList.get(m)).getOpertype() != 2) continue;
                k += ((VConcompute)computeList.get(m)).getCmoney().doubleValue();
            }
        }
        l = j + k - i;
        this.request.setAttribute("i", (Object)new BigDecimal(i).setScale(2, 4));
        this.request.setAttribute("j", (Object)new BigDecimal(j).setScale(2, 4));
        this.request.setAttribute("k", (Object)new BigDecimal(k).setScale(2, 4));
        this.request.setAttribute("l", (Object)new BigDecimal(l).setScale(2, 4));
        return "success";
    }

    public String archiveRecord() {
        try {
            this.conRecordService.updateState(this.id, this.cstate.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public TContainerRecord getRecord() {
        return this.record;
    }

    public void setRecord(TContainerRecord record) {
        this.record = record;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setConRecordService(ConRecordService conRecordService) {
        this.conRecordService = conRecordService;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public VContainerRecord getQuery() {
        return this.query;
    }

    public void setQuery(VContainerRecord query) {
        this.query = query;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public void setConInfoService(ConInfoService conInfoService) {
        this.conInfoService = conInfoService;
    }

    public VConview getReportQuery() {
        return this.reportQuery;
    }

    public void setReportQuery(VConview reportQuery) {
        this.reportQuery = reportQuery;
    }

    public VConcompute getComputeQuery() {
        return this.computeQuery;
    }

    public void setComputeQuery(VConcompute computeQuery) {
        this.computeQuery = computeQuery;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Short getCstate() {
        return this.cstate;
    }

    public void setCstate(Short cstate) {
        this.cstate = cstate;
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
}

