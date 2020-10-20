/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.entity.TFinance
 *  com.common.entity.TFinanceDetail
 *  com.common.entity.VFinance
 *  com.common.util.PageBean
 *  com.oa.bank.service.BankService
 *  com.oa.finance.action.FinanceInAction
 *  com.oa.finance.service.FinanceService
 *  com.oa.finance.service.FinanceTypeService
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
package com.oa.finance.action;

import com.common.entity.TFinance;
import com.common.entity.TFinanceDetail;
import com.common.entity.VFinance;
import com.common.util.PageBean;
import com.oa.bank.service.BankService;
import com.oa.finance.service.FinanceService;
import com.oa.finance.service.FinanceTypeService;
import com.opensymphony.xwork2.ActionSupport;
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

public class FinanceInAction
extends ActionSupport {
    private FinanceService financeService;
    private BankService bankService;
    private VFinance query;
    private TFinance finance;
    private int id;
    private String startDate;
    private String endDate;
    private String cdate;
    private List<TFinanceDetail> detail;
    private FinanceTypeService financeTypeService;
    private int pageNow = 1;
    private int pageSize = 20;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private String remarks;
    private int listFlag;
    private InputStream excelStream;
    private String fileName;
    private Short archiveFlag;
    private String detailRemarks;
    private int[] financeId;

    public String financeBatchInClose() {
        try {
            this.financeService.updateArchiveflag(this.financeId, this.archiveFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String getList() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, this.listFlag, this.detailRemarks};
        List list = this.financeService.getList(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("financeList", (Object)list);
        if (list != null && list.size() > 0) {
            StringBuffer detailRemarks = new StringBuffer();
            String tmp = null;
            for (int i = 0; i < list.size(); ++i) {
                List detailList = this.financeService.getDetailList(((VFinance)list.get(i)).getCid().intValue());
                if (detailList != null && detailList.size() > 0) {
                    detailRemarks.setLength(0);
                    for (int j = 0; j < detailList.size(); ++j) {
                        tmp = ((TFinanceDetail)detailList.get(j)).getCRemarks();
                        if (tmp == null || tmp.length() <= 0) continue;
                        detailRemarks.append(tmp).append(";");
                    }
                }
                if (detailRemarks.toString() != null && !"".equals(detailRemarks.toString())) {
                    ((VFinance)list.get(i)).setIsCashName(detailRemarks.toString().substring(0, detailRemarks.toString().length() - 1));
                    continue;
                }
                ((VFinance)list.get(i)).setIsCashName(null);
            }
        }
        PageBean pagebean = this.financeService.getPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        this.getFinanceType();
        double cmoney = 0.0;
        cmoney = this.financeService.getSumMoney(this.query, obj);
        this.request.setAttribute("cmoney", (Object)new BigDecimal(cmoney).setScale(2, 4));
        return "success";
    }

    public String expFinanceIn() throws Exception {
        Object[] obj;
        List list;
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        if ((list = this.financeService.getList(this.query, obj = new Object[]{sdate, edate, this.listFlag, this.detailRemarks}, 1, 65536)) != null && list.size() > 0) {
            StringBuffer detailRemarks = new StringBuffer();
            String tmp = null;
            for (int i = 0; i < list.size(); ++i) {
                List detailList = this.financeService.getDetailList(((VFinance)list.get(i)).getCid().intValue());
                if (detailList != null && detailList.size() > 0) {
                    detailRemarks.setLength(0);
                    for (int j = 0; j < detailList.size(); ++j) {
                        tmp = ((TFinanceDetail)detailList.get(j)).getCRemarks();
                        if (tmp == null || tmp.length() <= 0) continue;
                        detailRemarks.append(tmp).append(";");
                    }
                }
                if (detailRemarks.toString() != null && !"".equals(detailRemarks.toString())) {
                    ((VFinance)list.get(i)).setIsCashName(detailRemarks.toString().substring(0, detailRemarks.toString().length() - 1));
                    continue;
                }
                ((VFinance)list.get(i)).setIsCashName(null);
            }
        }
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("��������Ǽ�");
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
        cell.setCellValue("����");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)1);
        cell.setCellValue("����ƾ֤���");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("����");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("��������");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("��������");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("��ϸ��ע");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("����");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("�ܽ��");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("������");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("�Ƿ��ӡ");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("״̬");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)11);
        cell.setCellValue("��ע");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)12);
        cell.setCellValue("remarks");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 16);
        sheet.setColumnWidth(1, (short)n * 26);
        sheet.setColumnWidth(2, (short)n * 20);
        sheet.setColumnWidth(3, (short)n * 26);
        sheet.setColumnWidth(4, (short)n * 16);
        sheet.setColumnWidth(5, (short)n * 24);
        sheet.setColumnWidth(6, (short)n * 30);
        sheet.setColumnWidth(7, (short)n * 16);
        sheet.setColumnWidth(8, (short)n * 12);
        sheet.setColumnWidth(9, (short)n * 10);
        sheet.setColumnWidth(10, (short)n * 10);
        sheet.setColumnWidth(11, (short)n * 32);
        sheet.setColumnWidth(12, (short)n * 32);
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
                cell.setCellValue(this.formatter.format(((VFinance)list.get(i)).getCdate()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VFinance)list.get(i)).getFinanceNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VFinance)list.get(i)).getCpay());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VFinance)list.get(i)).getFtypeName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VFinance)list.get(i)).getBankName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                cell.setCellValue(((VFinance)list.get(i)).getIsCashName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                cell.setCellValue(((VFinance)list.get(i)).getDescription());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                cell.setCellValue(((VFinance)list.get(i)).getCmoney().doubleValue());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                cell.setCellValue(((VFinance)list.get(i)).getEmpName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)9);
                cell.setCellValue(((VFinance)list.get(i)).getStateName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue(((VFinance)list.get(i)).getArchivingName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)11);
                cell.setCellValue(((VFinance)list.get(i)).getRemarks());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)12);
                cell.setCellValue(((VFinance)list.get(i)).getRemarks2());
                cell.setCellStyle(colStyle);
            }
        }
        String targetFileName = "��������Ǽ�";
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

    public void getBank() {
        this.request.setAttribute("bankList", (Object)this.bankService.getValidList());
    }

    public void initDetailList() {
        if (this.id > 0) {
            this.detail = this.financeService.getDetailList(this.id);
            this.request.setAttribute("detailCount", (Object)this.detail.size());
        }
    }

    public void getFinanceType() {
        this.request.setAttribute("financeTypeList", (Object)this.financeTypeService.getValidList(1));
    }

    public String addFinance() {
        this.getBank();
        this.getFinanceType();
        return "success";
    }

    public String saveFinance() {
        if (this.cdate == null || "".equals(this.cdate.trim())) {
            this.getBank();
            this.getFinanceType();
            this.initDetailList();
            this.request.setAttribute("errInfo", (Object)"���ڲ���Ϊ��");
            return "input";
        }
        try {
            this.finance.setCDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.cdate) + " 00:00:00"))));
        }
        catch (ParseException e) {
            e.printStackTrace();
            this.getBank();
            this.getFinanceType();
            this.initDetailList();
            this.request.setAttribute("errInfo", (Object)"���ڸ�ʽ����ȷ");
            return "input";
        }
        if (this.finance.getCPay() == null || "".equals(this.finance.getCPay().trim())) {
            this.getBank();
            this.getFinanceType();
            this.initDetailList();
            this.request.setAttribute("errInfo", (Object)"���Բ���Ϊ��");
            return "input";
        }
        if (this.finance.getCIsCash() == null || this.finance.getCIsCash() == -1) {
            this.getBank();
            this.getFinanceType();
            this.initDetailList();
            this.request.setAttribute("errInfo", (Object)"�ֽ�/֧Ʊ����Ϊ��");
            return "input";
        }
        if (this.finance.getTBank().getCId() == -1) {
            this.finance.setTBank(null);
        }
        int j = 0;
        for (int i = 0; i < this.detail.size(); ++i) {
            if (this.detail.get(i) == null || ((TFinanceDetail)this.detail.get(i)).getCMoney() != null && !(((TFinanceDetail)this.detail.get(i)).getCMoney() <= 0.0)) continue;
            this.getBank();
            this.getFinanceType();
            j = i + 1;
            this.request.setAttribute("errInfo", (Object)("��" + j + "���������Ϊ��"));
            return "input";
        }
        this.finance.setCType(Short.valueOf((short)1));
        try {
            this.financeService.saveEntity(this.finance, this.detail);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editFinance() {
        this.getBank();
        this.getFinanceType();
        this.finance = this.financeService.getEntityById((Serializable)Integer.valueOf(this.id));
        this.cdate = this.formatter.format(this.finance.getCDate());
        this.initDetailList();
        return "success";
    }

    public String printFinance() {
        this.finance = this.financeService.getEntityById((Serializable)this.finance.getCId());
        this.cdate = this.formatter.format(this.finance.getCDate());
        this.detail = this.financeService.getDetailList(this.finance.getCId().intValue());
        this.request.setAttribute("ftype", (Object)this.financeTypeService.getEntityById((Serializable)this.finance.getTFinancetype().getCId()).getCName());
        this.request.setAttribute("bank", (Object)this.bankService.getEntityById((Serializable)this.finance.getTBank().getCId()).getCName());
        double sumMoney = 0.0;
        if (this.detail != null && this.detail.size() > 0) {
            for (int i = 0; i < this.detail.size(); ++i) {
                sumMoney += ((TFinanceDetail)this.detail.get(i)).getCMoney().doubleValue();
            }
        }
        this.request.setAttribute("sumMoney", (Object)sumMoney);
        return "success";
    }

    public String deleteFinance() {
        try {
            this.financeService.deleteEntity((Serializable)Integer.valueOf(this.id));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String updateState() {
        this.finance = this.financeService.getEntityById((Serializable)this.finance.getCId());
        this.detail = this.financeService.getDetailList(this.finance.getCId().intValue());
        this.finance.setCState(Short.valueOf((short)2));
        try {
            this.financeService.saveEntity(this.finance, this.detail);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String updateStop() {
        try {
            this.financeService.updateArchivingState(this.id, this.archiveFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String updateOpen() {
        try {
            this.financeService.updateArchivingState(this.id, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void updateRemarks() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.financeService.updateRemarks(this.id, this.remarks);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("�Զ����±�עʧ�ܣ�");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateRemarks2() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.financeService.updateRemarks2(this.id, this.remarks);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("�Զ����±�עʧ�ܣ�");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public VFinance getQuery() {
        return this.query;
    }

    public void setQuery(VFinance query) {
        this.query = query;
    }

    public TFinance getFinance() {
        return this.finance;
    }

    public void setFinance(TFinance finance) {
        this.finance = finance;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setFinanceService(FinanceService financeService) {
        this.financeService = financeService;
    }

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public List<TFinanceDetail> getDetail() {
        return this.detail;
    }

    public void setDetail(List<TFinanceDetail> detail) {
        this.detail = detail;
    }

    public void setFinanceTypeService(FinanceTypeService financeTypeService) {
        this.financeTypeService = financeTypeService;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getListFlag() {
        return this.listFlag;
    }

    public void setListFlag(int listFlag) {
        this.listFlag = listFlag;
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

    public Short getArchiveFlag() {
        return this.archiveFlag;
    }

    public void setArchiveFlag(Short archiveFlag) {
        this.archiveFlag = archiveFlag;
    }

    public String getDetailRemarks() {
        return this.detailRemarks;
    }

    public void setDetailRemarks(String detailRemarks) {
        this.detailRemarks = detailRemarks;
    }

    public int[] getFinanceId() {
        return this.financeId;
    }

    public void setFinanceId(int[] financeId) {
        this.financeId = financeId;
    }
}

