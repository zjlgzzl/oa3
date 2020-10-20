package com.oa.busin.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.common.auth.action.UserInfo;
import com.common.auth.service.UserService;
import com.common.emp.service.EmpService;
import com.common.entity.TBiaoji;
import com.common.entity.TBusin;
import com.common.entity.TBusinHis;
import com.common.entity.TCash;
import com.common.entity.TCashRate;
import com.common.entity.TContainer;
import com.common.entity.TCost;
import com.common.entity.TCostGroup;
import com.common.entity.TCostGroupInfo;
import com.common.entity.TCostGroupItem;
import com.common.entity.TCostItem;
import com.common.entity.TCustomer;
import com.common.entity.TEmp;
import com.common.entity.TFinanceGroup;
import com.common.entity.TFinanceGroupItem;
import com.common.entity.TRate;
import com.common.entity.TService;
import com.common.entity.VBusin;
import com.common.entity.VBusinAsk;
import com.common.entity.VBusinInvUser;
import com.common.entity.VBusinSumReport;
import com.common.entity.VCostGroupSum;
import com.common.entity.VEmp;
import com.common.entity.VLog;
import com.common.util.BeanUtils;
import com.common.util.CommonUtil;
import com.common.util.PageBean;
import com.oa.agent.service.AgentService;
import com.oa.base.service.BusinTypeService;
import com.oa.base.service.CostItemService;
import com.oa.base.service.CusService;
import com.oa.base.service.SalerService;
import com.oa.base.service.ServiceTypeService;
import com.oa.busin.service.BusinService;
import com.oa.busin.service.BusinServiceService;
import com.oa.busin.service.CashRateService;
import com.oa.busin.service.CashService;
import com.oa.busin.service.ContainerService;
import com.oa.busin.service.CostService;
import com.oa.rate.service.RateService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class BusinAction
extends ActionSupport {
	private AgentService agentService;
    private BusinService businService;
    private CusService cusService;
    private ContainerService containerService;
    private ServiceTypeService serviceTypeService;
    private BusinServiceService businServiceService;
    private CostItemService costItemService;
    private CostService costService;
    private EmpService empService;
    private CashService cashService;
    private BusinTypeService businTypeService;
    private CashRateService cashRateService;
    private RateService rateService;
    private SalerService salerService;
    private UserService userService;
    private TBusin busin;
    private VBusin query;
    private String startDate;
    private String endDate;
    private String startDate2;
    private String endDate2;
    private int businId;
    private String cdate;
    private String CArrivalDate;
    private String CArrivalDate2;
    private String CDeliveryDate;
    private String CCompleteDate;
    private String CNodate2;
    private String CNodate4;
    private String CNodate6;
    private String CNodate8;
    private String CCdDate;
    private String CCostDate;
    private String appDate;
    private String relDate;
    private String clearDate;
    private int pageNow = 1;
    private int pageSize = 20;
    private List<TContainer> cons;
    private List<TService> services;
    private List<TCost> costs;
    private List<TCost> costsGroup;
    private List<TCash> cash;
    private List<TCashRate> rate;
    private List<TCash> cash2;
    private List<TCashRate> rate2;
    private List<Integer> typeId;
    private List<String> typeName;
    private List<Integer> costItemId;
    private List<String> costItemName;
    private List<Integer> cashItemId;
    private List<String> cashItemName;
    private List<Integer> empId;
    private List<String> empName;
    private List<Integer> rateId;
    private List<Double> rateName;
    private String remarks2;
    private String remarks3;
    private String recieveRemarks;
    private int printUserId;
    private int listFlag;
    private String conNum;
    private String CContainerDate;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = this.request.getSession();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatter3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private int[] printFlag;
    private InputStream excelStream;
    private String fileName;
    private Short archiveFlag;
    private Short orderFlag;
    private int[] delCost;
    private String billNo;
    private String CConNum;
    private String archiveRemarks;
    private String fileRemarks;
    private short hiddenFlag;
    private String editDatetime;
    private int cusId;
    private short flag;
    private short _lock;
    private String businCode;
    private String businCode2;
    private int _businId;
    private List<TCostGroupInfo> groupInfoList;
    private int[] groupPrintFlag;
    private int[] printRadioFlag;
    private double cmoney;
    private int[] businIds;
    private TFinanceGroup group;
    private TFinanceGroup groupQuery;
    private int groupId;
    private List<TFinanceGroupItem> groupItems;
    private int itemsCount;
    private TCostGroup cgroup;
    private TCostGroup cgroupQuery;
    private int cgroupId;
    private List<TCostGroupItem> cgroupItems;
    private int citemsCount;
    private VBusinInvUser invUserQuery;
    private VBusinAsk askQuery;
    private VBusinSumReport businSumReportQuery;
    private String costInfo;
    private int costFlag;
    private double total;
    private TBiaoji queryBiaoji;

    public void getLogList() {
        List logList = this.businService.getLogList(this.businId);
        if (logList != null && logList.size() > 0) {
            for (int i = 0; i < logList.size(); ++i) {
                if (!((VLog)logList.get(i)).getCnote().contains("$") && !((VLog)logList.get(i)).getCnote().contains("Riel") && !((VLog)logList.get(i)).getCnote().contains("Cost Item") && !((VLog)logList.get(i)).getCnote().contains("Re")) continue;
                ((VLog)logList.get(i)).setNewCostFlag(Short.valueOf((short)1));
            }
        }
        this.request.setAttribute("logList", (Object)logList);
    }

    public void getCusList() {
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
    }

    public void updateArchiveRemarks() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCArchiveRemarks(this.archiveRemarks);
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新备注失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateKaipiaoRemarks() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCKaipiaoRemarks(this.archiveRemarks);
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新备注失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateFileRemarks() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCFileRemarks(this.fileRemarks);
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新备注失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
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
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCRecieveRemarks3(this.recieveRemarks);
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新已回款备注失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void getCostItemList() {
        List list = this.costItemService.getValidList();
        ArrayList<TCostItem> costList = new ArrayList<TCostItem>();
        ArrayList<TCostItem> cashList = new ArrayList<TCostItem>();
        StringBuffer costStr = new StringBuffer();
        costStr.append("[");
        StringBuffer cashStr = new StringBuffer();
        cashStr.append("[");
        if (list != null && list.size() > 0) {
            ArrayList<Integer> tmpId = new ArrayList<Integer>();
            ArrayList<String> tmpName = new ArrayList<String>();
            for (int i = 0; i < list.size(); ++i) {
                if (((TCostItem)list.get(i)).getCType() == 1) {
                    tmpId.add(((TCostItem)list.get(i)).getCId());
                    tmpName.add(((TCostItem)list.get(i)).getCName());
                    costList.add((TCostItem)list.get(i));
                    costStr.append("{id:").append(((TCostItem)list.get(i)).getCId()).append(",name:'").append(((TCostItem)list.get(i)).getCName()).append("'}").append(",");
                    continue;
                }
                cashList.add((TCostItem)list.get(i));
                cashStr.append("{id:").append(((TCostItem)list.get(i)).getCId()).append(",name:'").append(((TCostItem)list.get(i)).getCName().replace("'", "\\\\'")).append("'}").append(",");
            }
            this.costItemId = tmpId;
            this.costItemName = tmpName;
        }
        this.request.setAttribute("costStr", (Object)(String.valueOf(costStr.toString().substring(0, costStr.toString().length() - 1)) + "]"));
        String str = cashStr.toString();
        this.request.setAttribute("cashStr", str.substring(0, str.length() - 1) + "]");
        this.request.setAttribute("costItemList", costList);
        this.request.setAttribute("cashItemList", cashList);
    }

    public void getUserList(int flag) {
        List list = null;
        list = flag == 1 ? this.empService.getValidList() : this.empService.getValidAllList();
        if (list != null && list.size() > 0) {
            ArrayList<Integer> tmpId = new ArrayList<Integer>();
            ArrayList<String> tmpName = new ArrayList<String>();
            for (int i = 0; i < list.size(); ++i) {
                tmpId.add(((VEmp)list.get(i)).getUserid());
                tmpName.add(((VEmp)list.get(i)).getEmpName());
            }
            this.empId = tmpId;
            this.empName = tmpName;
        }
        this.request.setAttribute("userList", (Object)list);
    }

    public void getContainerCount() {
        if (this.cons == null) {
            this.request.setAttribute("conCount", (Object)0);
        } else {
            this.request.setAttribute("conCount", (Object)this.cons.size());
        }
    }

    public void getServiceCount() {
        if (this.services == null) {
            this.request.setAttribute("serviceCount", (Object)0);
        } else {
            this.request.setAttribute("serviceCount", (Object)this.services.size());
        }
    }

    public void getCostCount() {
        if (this.costs == null) {
            this.request.setAttribute("costCount", (Object)0);
        } else {
            this.request.setAttribute("costCount", (Object)this.costs.size());
        }
    }

    public void getCashCount() {
        if (this.cash == null) {
            this.request.setAttribute("cashCount", (Object)0);
        } else {
            this.request.setAttribute("cashCount", (Object)this.cash.size());
        }
        if (this.cash2 == null) {
            this.request.setAttribute("cash2Count", (Object)0);
        } else {
            this.request.setAttribute("cash2Count", (Object)this.cash2.size());
        }
    }

    public void getRateCount() {
        if (this.rate == null) {
            this.request.setAttribute("rateCount", (Object)0);
        } else {
            this.request.setAttribute("rateCount", (Object)this.rate.size());
        }
        if (this.rate2 == null) {
            this.request.setAttribute("rate2Count", (Object)0);
        } else {
            this.request.setAttribute("rate2Count", (Object)this.rate2.size());
        }
    }

    public void getBusinType() {
        this.request.setAttribute("businTypeList", (Object)this.businTypeService.getValidList());
    }

    public void getRateInfo() {
        List list = this.rateService.getValidList();
        if (list != null && list.size() > 0) {
            ArrayList<Integer> tmpId = new ArrayList<Integer>();
            ArrayList<Double> tmpName = new ArrayList<Double>();
            for (int i = 0; i < list.size(); ++i) {
                tmpId.add(((TRate)list.get(i)).getCId());
                tmpName.add(((TRate)list.get(i)).getCRate());
            }
            this.rateId = tmpId;
            this.rateName = tmpName;
        }
        this.request.setAttribute("rateList", (Object)list);
    }

    public void init(int flag) {
        this.getCusList();
        this.getCostItemList();
        this.getUserList(flag);
        this.getContainerCount();
        this.getServiceCount();
        this.getCostCount();
        this.getCashCount();
        this.getBusinType();
        this.getRateCount();
        this.getRateInfo();
        this.getSalerList();
        this.request.setAttribute("agentList", (Object)this.agentService.getList(null,1,65535));
        this.request.setAttribute("financeGroup", (Object)this.businService.getGroupValidList());
        this.request.setAttribute("costGroup", (Object)this.businService.getCostGroupValidList());
    }

    public String getList() throws ParseException {
        this.getCusList();
        this.getBusinType();
        String sdate = null;
        String edate = null;
        String sdate2 = null;
        String edate2 = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        if (this.startDate2 != null && !"".equals(this.startDate2)) {
            sdate2 = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate2) + " 00:00:00"));
        }
        if (this.endDate2 != null && !"".equals(this.endDate2)) {
            edate2 = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate2) + " 00:00:00"));
        }
        if (this.businCode != null && !"".equals(this.businCode.trim()) || this.businCode2 != null && !"".equals(this.businCode2.trim())) {
            if (this.query == null) {
                this.query = new VBusin();
            }
            if (this.businCode != null && !"".equals(this.businCode.trim())) {
                this.query.setBillNo(this.businCode);
            } else if (this.businCode2 != null && !"".equals(this.businCode2.trim())) {
                this.query.setBillNo(this.businCode2);
            }
        }
		/*
		 * if(query == null) { query = new VBusin(); }
		 * query.setUserId((Integer)request.getSession().getAttribute("userID"));
		 */
        Object[] obj = new Object[]{sdate, edate, this.listFlag, this.conNum, this.billNo, 0, sdate2, edate2};
        List list = this.businService.getList(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String updateRecieveFlag() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCRecieveFlag(Short.valueOf((short)1));
        bb.setCState(Short.valueOf((short)2));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String updateRecieveFlag2() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCRecieveFlag(Short.valueOf((short)0));
        bb.setCState(Short.valueOf((short)1));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String unlockList() {
        Object[] arrobject = new Object[6];
        arrobject[2] = 0;
        arrobject[5] = 1;
        Object[] obj = arrobject;
        List list = this.businService.getList(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getPages(this.query, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String expBusinList() throws Exception {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate, this.listFlag, this.conNum, this.billNo};
        List list = this.businService.getList(this.query, obj, 1, 65536);
        this.request.setAttribute("businList", (Object)list);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("业务单据");
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
        cell.setCellValue("单据编号");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)2);
        cell.setCellValue("单据类型");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)3);
        cell.setCellValue("收货人");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)4);
        cell.setCellValue("客户名称");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)5);
        cell.setCellValue("制单人");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)6);
        cell.setCellValue("状态");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)7);
        cell.setCellValue("完结标志");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)8);
        cell.setCellValue("BILL NO");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)9);
        cell.setCellValue("CONTAINER INFO");
        cell.setCellStyle(headStyle);
        cell = row.createCell((short)10);
        cell.setCellValue("CBM");
        cell.setCellStyle(headStyle);
        sheet.setColumnWidth(0, (short)n * 18);
        sheet.setColumnWidth(1, (short)n * 32);
        sheet.setColumnWidth(2, (short)n * 16);
        sheet.setColumnWidth(3, (short)n * 48);
        sheet.setColumnWidth(4, (short)n * 40);
        sheet.setColumnWidth(5, (short)n * 12);
        sheet.setColumnWidth(6, (short)n * 20);
        sheet.setColumnWidth(7, (short)n * 20);
        sheet.setColumnWidth(8, (short)n * 30);
        sheet.setColumnWidth(9, (short)n * 30);
        sheet.setColumnWidth(10, (short)n * 30);
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
                cell.setCellValue(this.formatter.format(((VBusin)list.get(i)).getBusinDate()));
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)1);
                cell.setCellValue(((VBusin)list.get(i)).getBillNo());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)2);
                cell.setCellValue(((VBusin)list.get(i)).getTypeName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)3);
                cell.setCellValue(((VBusin)list.get(i)).getReceive());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)4);
                cell.setCellValue(((VBusin)list.get(i)).getCusName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)5);
                cell.setCellValue(((VBusin)list.get(i)).getEmpName());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)6);
                if (((VBusin)list.get(i)).getBusinState() == 1) {
                    cell.setCellValue("编制");
                } else if (((VBusin)list.get(i)).getBusinState() == 2) {
                    cell.setCellValue("归档(未完成)");
                } else if (((VBusin)list.get(i)).getBusinState() == 3) {
                    cell.setCellValue("归档(已完成)");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)7);
                if (((VBusin)list.get(i)).getCompleteFlag() == 0) {
                    cell.setCellValue("未完结");
                } else if (((VBusin)list.get(i)).getBusinState() == 1) {
                    cell.setCellValue("已完结");
                }
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)8);
                if (((VBusin)list.get(i)).getTakeNo() != null) {
                    cell.setCellValue(((VBusin)list.get(i)).getTakeNo());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(colStyle);
                StringBuffer conNum = new StringBuffer();
                this.cons = this.containerService.getList(((VBusin)list.get(i)).getBusinId().intValue());
                if (this.cons != null && this.cons.size() > 0) {
                    for (int j = 0; j < this.cons.size(); ++j) {
                        String num2 = ((TContainer)this.cons.get(j)).getCContainerNum();
                        if (num2 == null || "".equals(num2)) continue;
                        conNum.append(num2).append("；");
                    }
                }
                cell = row.createCell((short)9);
                cell.setCellValue(conNum.toString());
                cell.setCellStyle(colStyle);
                cell = row.createCell((short)10);
                cell.setCellValue(((VBusin)list.get(i)).getCbm());
                cell.setCellStyle(colStyle);
                
            }
        }
        String targetFileName = "业务单据";
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

    public void getSalerList() {
        this.request.setAttribute("salerList", (Object)this.salerService.getValidList());
    }

    public String addBusin() {
        this.getCusList();
        this.getCostItemList();
        this.getUserList(1);
        this.getBusinType();
        this.getSalerList();
        this.init(1);
        return "success";
    }

    public String saveBusin() {
        StringBuffer log1 = new StringBuffer();
        StringBuffer log2 = new StringBuffer();
        if (this.cdate == null || "".equals(this.cdate.trim())) {
            this.init(1);
            this.request.setAttribute("errInfo", (Object)"服务单日期不能为空");
            return "input";
        }
        try {
            this.busin.setCDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.cdate) + " 00:00:00"))));
        }
        catch (ParseException e) {
            e.printStackTrace();
            this.init(1);
            this.request.setAttribute("errInfo", (Object)"服务单日期格式不正确");
            return "input";
        }
        if (this.appDate != null && !"".equals(this.appDate.trim())) {
            try {
                this.busin.setCAppDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.appDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"申请日期格式不正确");
                return "input";
            }
        }
        if (this.relDate != null && !"".equals(this.relDate.trim())) {
            try {
                this.busin.setCRelDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.relDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"获得日期格式不正确");
                return "input";
            }
        }
        if (this.clearDate != null && !"".equals(this.clearDate.trim())) {
            try {
                this.busin.setCClearDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.clearDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"清关日期格式不正确");
                return "input";
            }
        }
        if (this.CArrivalDate != null && !"".equals(this.CArrivalDate)) {
            try {
                this.busin.setCArrivalDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CArrivalDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"ETD日期格式不正确");
                return "input";
            }
        }
        if (this.CArrivalDate2 != null && !"".equals(this.CArrivalDate2)) {
            try {
                this.busin.setCArrivalDate2(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CArrivalDate2) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"ETA日期格式不正确");
                return "input";
            }
        }
        if (this.CDeliveryDate != null && !"".equals(this.CDeliveryDate)) {
            try {
                this.busin.setCDeliveryDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CDeliveryDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"交货日期格式不正确");
                return "input";
            }
        }
        if (this.CCompleteDate != null && !"".equals(this.CCompleteDate)) {
            try {
                this.busin.setCCompleteDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CCompleteDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"卸货完毕日期格式不正确");
                return "input";
            }
        }
        if (this.CContainerDate != null && !"".equals(this.CContainerDate)) {
            try {
                this.busin.setCContainerDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CContainerDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"还柜日期格式不正确");
                return "input";
            }
        }
        if (this.CNodate2 != null && !"".equals(this.CNodate2)) {
            try {
                this.busin.setCNodate2(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        }
        if (this.CNodate4 != null && !"".equals(this.CNodate4)) {
            try {
                this.busin.setCNodate4(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate4) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        }
        if (this.CNodate6 != null && !"".equals(this.CNodate6)) {
            try {
                this.busin.setCNodate6(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate6) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        }
        if (this.CNodate8 != null && !"".equals(this.CNodate8)) {
            try {
                this.busin.setCNodate8(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate8) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        }
        if (this.CCdDate != null && !"".equals(this.CCdDate)) {
            try {
                this.busin.setCCdDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CCdDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"CD Date格式不正确");
                return "input";
            }
        } else {
            this.busin.setCCdDate(null);
        }
        if (this.CCostDate != null && !"".equals(this.CCostDate)) {
            try {
                this.busin.setCCostDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CCostDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"Cost Date格式不正确");
                return "input";
            }
        } else {
            this.busin.setCCostDate(null);
        }
        if (this.busin.getTCustomerByCCusid2() == null || this.busin.getTCustomerByCCusid2().getCId() == null || this.busin.getTCustomerByCCusid2().getCId() == 0) {
            this.busin.setTCustomerByCCusid2(null);
        }
        if (this.busin.getTSalerByCSaleman() == null || this.busin.getTSalerByCSaleman().getCId() == null || this.busin.getTSalerByCSaleman().getCId() == 0) {
            this.busin.setTSalerByCSaleman(null);
        }
        if (this.busin.getTSalerByCKf() == null || this.busin.getTSalerByCKf().getCId() == null || this.busin.getTSalerByCKf().getCId() == 0) {
            this.busin.setTSalerByCKf(null);
        }
        if (this.busin.getCId() != null) {
            TBusin bb = this.businService.getEntityById((Serializable)this.busin.getCId());
            if ((this.CNodate4 == null || "".equals(this.CNodate4)) && bb.getCNodate4() != null && !"".equals(bb.getCNodate4())) {
                this.busin.setCNodate4(bb.getCNodate4());
                this.busin.setCNodate3(bb.getCNodate3());
            }
            if ((this.CNodate8 == null || "".equals(this.CNodate8)) && bb.getCNodate8() != null && !"".equals(bb.getCNodate8())) {
                this.busin.setCNodate8(bb.getCNodate8());
                this.busin.setCNodate7(bb.getCNodate7());
            }
            if (!bb.getTBusinType().getCId().equals(this.busin.getTBusinType().getCId())) {
                log1.append("TYPE OF FILE：").append(bb.getTBusinType().getCName()).append("改为").append(this.businTypeService.getEntityById((Serializable)this.busin.getTBusinType().getCId()).getCName()).append("；");
            }
            if (!(bb.getCSend() == null && this.busin.getCSend() == null || bb.getCSend() != null && this.busin.getCSend() != null && bb.getCSend().trim().equals(this.busin.getCSend().trim()))) {
                log1.append("SHIPPER：");
                if (bb.getCSend() != null) {
                    log1.append(bb.getCSend().trim());
                }
                log1.append("改为");
                if (this.busin.getCSend() != null) {
                    log1.append(this.busin.getCSend().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCReceive() == null && this.busin.getCReceive() == null || bb.getCReceive() != null && this.busin.getCReceive() != null && bb.getCReceive().trim().equals(this.busin.getCReceive().trim()))) {
                log1.append("CONSIGNEE：");
                if (bb.getCReceive() != null) {
                    log1.append(bb.getCReceive().trim());
                }
                log1.append("改为");
                if (this.busin.getCReceive() != null) {
                    log1.append(this.busin.getCReceive().trim());
                }
                log1.append("；");
            }
            if (!bb.getTCustomerByCCusid().getCId().equals(this.busin.getTCustomerByCCusid().getCId())) {
                log1.append("CLIENT：").append(bb.getTCustomerByCCusid().getCAddr()).append("改为").append(this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId()).getCAddr()).append("；");
            }
            if (!(bb.getTCustomerByCCusid2() == null && (this.busin.getTCustomerByCCusid2() == null || this.busin.getTCustomerByCCusid2().getCId() == null || this.busin.getTCustomerByCCusid2().getCId() == 0) || bb.getTCustomerByCCusid2() != null && bb.getTCustomerByCCusid2().getCId() != null && bb.getTCustomerByCCusid2().getCId() > 0 && this.busin.getTCustomerByCCusid2() != null && bb.getTCustomerByCCusid2().getCId() != null && bb.getTCustomerByCCusid2().getCId() > 0 && bb.getTCustomerByCCusid2().getCId().equals(this.busin.getTCustomerByCCusid2().getCId()))) {
                log1.append("C/O：");
                if (bb.getTCustomerByCCusid2() != null && bb.getTCustomerByCCusid2().getCId() != null && bb.getTCustomerByCCusid2().getCId() > 0) {
                    log1.append(bb.getTCustomerByCCusid2().getCAddr());
                }
                log1.append("改为");
                if (this.busin.getTCustomerByCCusid2() != null && this.busin.getTCustomerByCCusid2().getCId() != null && this.busin.getTCustomerByCCusid2().getCId() > 0) {
                    log1.append(this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid2().getCId()).getCAddr());
                }
                log1.append("；");
            }
            if (!(bb.getCTakeNo() == null && this.busin.getCTakeNo() == null || bb.getCTakeNo() != null && this.busin.getCTakeNo() != null && bb.getCTakeNo().trim().equals(this.busin.getCTakeNo().trim()))) {
                log1.append("BILL NO：");
                if (bb.getCTakeNo() != null) {
                    log1.append(bb.getCTakeNo().trim());
                }
                log1.append("改为");
                if (this.busin.getCTakeNo() != null) {
                    log1.append(this.busin.getCTakeNo().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCGoodsType() == null && this.busin.getCGoodsType() == null || bb.getCGoodsType() != null && this.busin.getCGoodsType() != null && bb.getCGoodsType().trim().equals(this.busin.getCGoodsType().trim()))) {
                log1.append("COMMODITIES：");
                if (bb.getCGoodsType() != null) {
                    log1.append(bb.getCGoodsType().trim());
                }
                log1.append("改为");
                if (this.busin.getCGoodsType() != null) {
                    log1.append(this.busin.getCGoodsType().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCAppDate() == null && this.busin.getCAppDate() == null || bb.getCAppDate() != null && this.busin.getCAppDate() != null && this.formatter.format(bb.getCAppDate()).equals(this.formatter.format(this.busin.getCAppDate())))) {
                log1.append("APPLY DATE：");
                if (bb.getCAppDate() != null) {
                    log1.append(this.formatter.format(bb.getCAppDate()));
                }
                log1.append("改为");
                if (this.busin.getCAppDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCAppDate()));
                }
                log1.append("；");
            }
            if (!(bb.getShippingLine() == null && this.busin.getShippingLine() == null || bb.getShippingLine() != null && this.busin.getShippingLine() != null && bb.getShippingLine().trim().equals(this.busin.getShippingLine().trim()))) {
                log1.append("SHIPPING LINE：");
                if (bb.getShippingLine() != null) {
                    log1.append(bb.getShippingLine().trim());
                }
                log1.append("改为");
                if (this.busin.getShippingLine() != null) {
                    log1.append(this.busin.getShippingLine().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCClearDate() == null && this.busin.getCClearDate() == null || bb.getCClearDate() != null && this.busin.getCClearDate() != null && this.formatter.format(bb.getCClearDate()).equals(this.formatter.format(this.busin.getCClearDate())))) {
                log1.append("CLEARANCE DATE：");
                if (bb.getCClearDate() != null) {
                    log1.append(this.formatter.format(bb.getCClearDate()));
                }
                log1.append("改为");
                if (this.busin.getCClearDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCClearDate()));
                }
                log1.append("；");
            }
            if (!(bb.getCContainerDate() == null && this.busin.getCContainerDate() == null || bb.getCContainerDate() != null && this.busin.getCContainerDate() != null && this.formatter.format(bb.getCContainerDate()).equals(this.formatter.format(this.busin.getCContainerDate())))) {
                log1.append("RETURN EMPTY DATE：");
                if (bb.getCContainerDate() != null) {
                    log1.append(this.formatter.format(bb.getCContainerDate()));
                }
                log1.append("改为");
                if (this.busin.getCContainerDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCContainerDate()));
                }
                log1.append("；");
            }
            if (!(bb.getCArrivalPort() == null && this.busin.getCArrivalPort() == null || bb.getCArrivalPort() != null && this.busin.getCArrivalPort() != null && bb.getCArrivalPort().trim().equals(this.busin.getCArrivalPort().trim()))) {
                log1.append("PoL/PoD：");
                if (bb.getCArrivalPort() != null) {
                    log1.append(bb.getCArrivalPort().trim());
                }
                log1.append("改为");
                if (this.busin.getCArrivalPort() != null) {
                    log1.append(this.busin.getCArrivalPort().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCArrivalDate() == null && this.busin.getCArrivalDate() == null || bb.getCArrivalDate() != null && this.busin.getCArrivalDate() != null && this.formatter.format(bb.getCArrivalDate()).equals(this.formatter.format(this.busin.getCArrivalDate())))) {
                log1.append("ETD：");
                if (bb.getCArrivalDate() != null) {
                    log1.append(this.formatter.format(bb.getCArrivalDate()));
                }
                log1.append("改为");
                if (this.busin.getCArrivalDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCArrivalDate()));
                }
                log1.append("；");
            }
            if (!(bb.getCArrivalDate2() == null && this.busin.getCArrivalDate2() == null || bb.getCArrivalDate2() != null && this.busin.getCArrivalDate2() != null && this.formatter.format(bb.getCArrivalDate2()).equals(this.formatter.format(this.busin.getCArrivalDate2())))) {
                log1.append("ETA：");
                if (bb.getCArrivalDate2() != null) {
                    log1.append(this.formatter.format(bb.getCArrivalDate2()));
                }
                log1.append("改为");
                if (this.busin.getCArrivalDate2() != null) {
                    log1.append(this.formatter.format(this.busin.getCArrivalDate2()));
                }
                log1.append("；");
            }
            if (!(bb.getCVessel() == null && this.busin.getCVessel() == null || bb.getCVessel() != null && this.busin.getCVessel() != null && bb.getCVessel().trim().equals(this.busin.getCVessel().trim()) || bb.getCVessel() == null && this.busin.getCVessel() != null && "".equals(this.busin.getCVessel().trim()))) {
                log1.append("Ex-Vessel：");
                if (bb.getCVessel() != null) {
                    log1.append(bb.getCVessel().trim());
                }
                log1.append("改为");
                if (this.busin.getCVessel() != null) {
                    log1.append(this.busin.getCVessel().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCCdno() == null && this.busin.getCCdno() == null || bb.getCCdno() != null && this.busin.getCCdno() != null && bb.getCCdno().trim().equals(this.busin.getCCdno().trim()) || bb.getCCdno() == null && this.busin.getCCdno() != null && "".equals(this.busin.getCCdno().trim()))) {
                log1.append("CD No.：");
                if (bb.getCCdno() != null) {
                    log1.append(bb.getCCdno().trim());
                }
                log1.append("改为");
                if (this.busin.getCCdno() != null) {
                    log1.append(this.busin.getCCdno().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCCdDate() == null && this.busin.getCCdDate() == null || bb.getCCdDate() != null && this.busin.getCCdDate() != null && this.formatter.format(bb.getCCdDate()).equals(this.formatter.format(this.busin.getCCdDate())))) {
                log1.append("CD Date：");
                if (bb.getCCdDate() != null) {
                    log1.append(this.formatter.format(bb.getCCdDate()));
                }
                log1.append("改为");
                if (this.busin.getCCdDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCCdDate()));
                }
                log1.append("；");
            }
            if (!(bb.getCCostDate() == null && this.busin.getCCostDate() == null || bb.getCCostDate() != null && this.busin.getCCostDate() != null && this.formatter.format(bb.getCCostDate()).equals(this.formatter.format(this.busin.getCCostDate())))) {
                log1.append("Cost Date：");
                if (bb.getCCostDate() != null) {
                    log1.append(this.formatter.format(bb.getCCostDate()));
                }
                log1.append("改为");
                if (this.busin.getCCostDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCCostDate()));
                }
                log1.append("；");
            }
            if (!(bb.getTSalerByCApplyby() == null && (this.busin.getTSalerByCApplyby() == null || this.busin.getTSalerByCApplyby().getCId() == null || this.busin.getTSalerByCApplyby().getCId() == 0) || bb.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0 && this.busin.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0 && bb.getTSalerByCApplyby().getCId().equals(this.busin.getTSalerByCApplyby().getCId()))) {
                log1.append("Apply By：");
                if (bb.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0) {
                    log1.append(bb.getTSalerByCApplyby().getCName());
                }
                log1.append("改为");
                if (this.busin.getTSalerByCApplyby() != null && this.busin.getTSalerByCApplyby().getCId() != null && this.busin.getTSalerByCApplyby().getCId() > 0) {
                    log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCApplyby().getCId()).getCName());
                }
                log1.append("；");
            }
            if (!(bb.getCExchange() == null && this.busin.getCExchange() == null || bb.getCExchange() != null && this.busin.getCExchange() != null && bb.getCExchange().equals(this.busin.getCExchange()))) {
                log1.append("Exchange：");
                if (bb.getCExchange() != null) {
                    log1.append(bb.getCExchange());
                }
                log1.append("改为");
                if (this.busin.getCExchange() != null) {
                    log1.append(this.busin.getCExchange());
                }
                log1.append("；");
            }
            if (!(bb.getCDeliveryDate() == null && this.busin.getCDeliveryDate() == null || bb.getCDeliveryDate() != null && this.busin.getCDeliveryDate() != null && this.formatter.format(bb.getCDeliveryDate()).equals(this.formatter.format(this.busin.getCDeliveryDate())))) {
                log1.append("DELIVERY DATE：");
                if (bb.getCDeliveryDate() != null) {
                    log1.append(this.formatter.format(bb.getCDeliveryDate()));
                }
                log1.append("改为");
                if (this.busin.getCDeliveryDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCDeliveryDate()));
                }
                log1.append("；");
            }
            if (!(bb.getCCompleteDate() == null && this.busin.getCCompleteDate() == null || bb.getCCompleteDate() != null && this.busin.getCCompleteDate() != null && this.formatter.format(bb.getCCompleteDate()).equals(this.formatter.format(this.busin.getCCompleteDate())))) {
                log1.append("UNLOAD DATE：");
                if (bb.getCCompleteDate() != null) {
                    log1.append(this.formatter.format(bb.getCCompleteDate()));
                }
                log1.append("改为");
                if (this.busin.getCCompleteDate() != null) {
                    log1.append(this.formatter.format(this.busin.getCCompleteDate()));
                }
                log1.append("；");
            }
            if (!(bb.getTSalerByCSaleman() == null && (this.busin.getTSalerByCSaleman() == null || this.busin.getTSalerByCSaleman().getCId() == null || this.busin.getTSalerByCSaleman().getCId() == 0) || bb.getTSalerByCSaleman() != null && bb.getTSalerByCSaleman().getCId() != null && bb.getTSalerByCSaleman().getCId() > 0 && this.busin.getTSalerByCSaleman() != null && bb.getTSalerByCSaleman().getCId() != null && bb.getTSalerByCSaleman().getCId() > 0 && bb.getTSalerByCSaleman().getCId().equals(this.busin.getTSalerByCSaleman().getCId()))) {
                log1.append("业务员 saler：");
                if (bb.getTSalerByCSaleman() != null && bb.getTSalerByCSaleman().getCId() != null && bb.getTSalerByCSaleman().getCId() > 0) {
                    log1.append(this.salerService.getEntityById((Serializable)bb.getTSalerByCSaleman().getCId()).getCName());
                } else {
                    log1.append("PLEASE CHOOSE");
                }
                log1.append("改为");
                if (this.busin.getTSalerByCSaleman() != null && this.busin.getTSalerByCSaleman().getCId() != null && this.busin.getTSalerByCSaleman().getCId() > 0) {
                    log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCSaleman().getCId()).getCName());
                } else {
                    log1.append("PLEASE CHOOSE");
                }
                log1.append("；");
            }
            if (!(bb.getTSalerByCKf() == null && (this.busin.getTSalerByCKf() == null || this.busin.getTSalerByCKf().getCId() == null || this.busin.getTSalerByCKf().getCId() == 0) || bb.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0 && this.busin.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0 && bb.getTSalerByCKf().getCId().equals(this.busin.getTSalerByCKf().getCId()))) {
                log1.append("客服：");
                if (bb.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0) {
                    log1.append(this.salerService.getEntityById((Serializable)bb.getTSalerByCKf().getCId()).getCName());
                } else {
                    log1.append("PLEASE CHOOSE");
                }
                log1.append("改为");
                if (this.busin.getTSalerByCKf() != null && this.busin.getTSalerByCKf().getCId() != null && this.busin.getTSalerByCKf().getCId() > 0) {
                    log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCKf().getCId()).getCName());
                } else {
                    log1.append("PLEASE CHOOSE");
                }
                log1.append("；");
            }
            if (!(bb.getCAgent1() == null && this.busin.getCAgent1() == null || bb.getCAgent1() != null && this.busin.getCAgent1() != null && bb.getCAgent1().trim().equals(this.busin.getCAgent1().trim()))) {
                log1.append("前程代理：");
                if (bb.getCAgent1() != null) {
                    log1.append(bb.getCAgent1().trim());
                }
                log1.append("改为");
                if (this.busin.getCAgent1() != null) {
                    log1.append(this.busin.getCAgent1().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCAgent2() == null && this.busin.getCAgent2() == null || bb.getCAgent2() != null && this.busin.getCAgent2() != null && bb.getCAgent2().trim().equals(this.busin.getCAgent2().trim()))) {
                log1.append("后程代理：");
                if (bb.getCAgent2() != null) {
                    log1.append(bb.getCAgent2().trim());
                }
                log1.append("改为");
                if (this.busin.getCAgent2() != null) {
                    log1.append(this.busin.getCAgent2().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCConNum() == null && this.busin.getCConNum() == null || bb.getCConNum() != null && this.busin.getCConNum() != null && bb.getCConNum().trim().equals(this.busin.getCConNum().trim()))) {
                log1.append("Container #：");
                if (bb.getCConNum() != null) {
                    log1.append(bb.getCConNum().trim());
                }
                log1.append("改为");
                if (this.busin.getCConNum() != null) {
                    log1.append(this.busin.getCConNum().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCFty() == null && this.busin.getCFty() == null || bb.getCFty() != null && this.busin.getCFty() != null && bb.getCFty().trim().equals(this.busin.getCFty().trim()))) {
                log1.append("Fty. inv No.：");
                if (bb.getCFty() != null) {
                    log1.append(bb.getCFty().trim());
                }
                log1.append("改为");
                if (this.busin.getCFty() != null) {
                    log1.append(this.busin.getCFty().trim());
                }
                log1.append("；");
            }
            if (!(bb.getCShipment() == null && this.busin.getCShipment() == null || bb.getCShipment() != null && this.busin.getCShipment() != null && bb.getCShipment().trim().equals(this.busin.getCShipment().trim()))) {
                log1.append("shipment：");
                if (bb.getCShipment() != null) {
                    log1.append(bb.getCShipment().trim());
                }
                log1.append("改为");
                if (this.busin.getCShipment() != null) {
                    log1.append(this.busin.getCShipment().trim());
                }
                log1.append("；");
            }
            if (!((bb.getCRemarks() == null || "".equals(bb.getCRemarks().trim())) && (this.busin.getCRemarks() == null || "".equals(this.busin.getCRemarks().trim())) || bb.getCRemarks() != null && this.busin.getCRemarks() != null && bb.getCRemarks().trim().equals(this.busin.getCRemarks().trim()))) {
                log2.append("REMARKS：");
                if (bb.getCRemarks() != null) {
                    log2.append(bb.getCRemarks().trim());
                }
                log2.append("改为");
                if (this.busin.getCRemarks() != null) {
                    log2.append(this.busin.getCRemarks().trim());
                }
                log2.append("；");
            }
            if (!((bb.getCRemarks3() == null || "".equals(bb.getCRemarks3().trim())) && (this.busin.getCRemarks3() == null || "".equals(this.busin.getCRemarks3().trim())) || bb.getCRemarks3() != null && this.busin.getCRemarks3() != null && bb.getCRemarks3().trim().equals(this.busin.getCRemarks3().trim()))) {
                log2.append("业务单备注2：");
                if (bb.getCRemarks3() != null) {
                    log2.append(bb.getCRemarks3().trim());
                }
                log2.append("改为");
                if (this.busin.getCRemarks3() != null) {
                    log2.append(this.busin.getCRemarks3().trim());
                }
                log2.append("；");
            }
            if (!((bb.getCNoticeRemarks() == null || "".equals(bb.getCNoticeRemarks().trim())) && (this.busin.getCNoticeRemarks() == null || "".equals(this.busin.getCNoticeRemarks().trim())) || bb.getCNoticeRemarks() != null && this.busin.getCNoticeRemarks() != null && bb.getCNoticeRemarks().trim().equals(this.busin.getCNoticeRemarks().trim()))) {
                log2.append("开票提醒：");
                if (bb.getCNoticeRemarks() != null) {
                    log2.append(bb.getCNoticeRemarks().trim());
                }
                log2.append("改为");
                if (this.busin.getCNoticeRemarks() != null) {
                    log2.append(this.busin.getCNoticeRemarks().trim());
                }
                log2.append("；");
            }
            if (!((bb.getCDnRemarks() == null || "".equals(bb.getCDnRemarks().trim())) && (this.busin.getCDnRemarks() == null || "".equals(this.busin.getCDnRemarks().trim())) || bb.getCDnRemarks() != null && this.busin.getCDnRemarks() != null && bb.getCDnRemarks().trim().equals(this.busin.getCDnRemarks().trim()))) {
                log2.append("DN REMARKS：");
                if (bb.getCDnRemarks() != null) {
                    log2.append(bb.getCDnRemarks().trim());
                }
                log2.append("改为");
                if (this.busin.getCDnRemarks() != null) {
                    log2.append(this.busin.getCDnRemarks().trim());
                }
                log2.append("；");
            }
            if (!((bb.getCInvRemarks() == null || "".equals(bb.getCInvRemarks().trim())) && (this.busin.getCInvRemarks() == null || "".equals(this.busin.getCInvRemarks().trim())) || bb.getCInvRemarks() != null && this.busin.getCInvRemarks() != null && bb.getCInvRemarks().trim().equals(this.busin.getCInvRemarks().trim()))) {
                log2.append("INV REMARKS：");
                if (bb.getCInvRemarks() != null) {
                    log2.append(bb.getCInvRemarks().trim());
                }
                log2.append("改为");
                if (this.busin.getCInvRemarks() != null) {
                    log2.append(this.busin.getCInvRemarks().trim());
                }
                log2.append("；");
            }
            int askNoticeFlag = 0;
            boolean askMofity = false;
            if (this.busin.getCAskRemarks() == null || "".equals(this.busin.getCAskRemarks())) {
                askNoticeFlag = 0;
            } else if ((bb.getCAskRemarks() == null || "".equals(bb.getCAskRemarks())) && this.busin.getCAskRemarks() != null && !"".equals(this.busin.getCAskRemarks())) {
                askNoticeFlag = 1;
                askMofity = true;
            } else if (!(bb.getCAskRemarks() == null || "".equals(bb.getCAskRemarks()) || this.busin.getCAskRemarks() == null || "".equals(this.busin.getCAskRemarks()) || bb.getCAskRemarks().equals(this.busin.getCAskRemarks()))) {
                askNoticeFlag = 1;
                askMofity = true;
            }
            if (askNoticeFlag == 0 && this.busin.getCAskRemarks() != null && !"".equals(this.busin.getCAskRemarks()) && this.busin.getCAskNotice() != null && this.busin.getCAskNotice() == 1) {
                askNoticeFlag = 1;
            }
            this.busin.setCAskNotice(Integer.valueOf(askNoticeFlag));
            if (askMofity) {
                this.busin.setCAskDate(CommonUtil.getDatetime());
                this.busin.setCAsk(Integer.valueOf(CommonUtil.getUserId()));
            }
        }
        String[] logs = new String[]{log1.toString(), log2.toString(), this.editDatetime};
        try {
            this.businService.saveEntity(this.busin, this.cons, this.services, this.costs, this.costsGroup, this.cash, this.delCost, logs);
        }
        catch (Exception e) {
            this.init(1);
            e.printStackTrace();
            return "input";
        }
        if (this._businId > 0) {
            this.request.setAttribute("_businId", (Object)this._businId);
        }
        return "success";
    }

    public String saveCashBusin() throws ParseException {
        StringBuffer log1 = new StringBuffer();
        StringBuffer log2 = new StringBuffer();
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        short acNotice = 0;
        if (!bb.getTBusinType().getCId().equals(this.busin.getTBusinType().getCId())) {
            log1.append("TYPE OF FILE：").append(bb.getTBusinType().getCName()).append("改为").append(this.businTypeService.getEntityById((Serializable)this.busin.getTBusinType().getCId()).getCName()).append("；");
        }
        if (!(bb.getCNodate2() == null && (this.CNodate2 == null || "".equals(this.CNodate2.trim())) || bb.getCNodate2() != null && this.CNodate2 != null && !"".equals(this.CNodate2.trim()) && this.formatter.format(bb.getCNodate2()).equals(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00"))))) {
            log1.append("No-Date(D.N1)：");
            if (bb.getCNodate2() != null) {
                log1.append(this.formatter.format(bb.getCNodate2()));
            }
            log1.append("改为");
            if (this.CNodate2 != null && !"".equals(this.CNodate2.trim())) {
                log1.append(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00")));
            }
            log1.append("；");
        }
        if (!(bb.getCArrivalDate() == null && this.busin.getCArrivalDate() == null || bb.getCArrivalDate() != null && this.busin.getCArrivalDate() != null && this.formatter.format(bb.getCArrivalDate()).equals(this.formatter.format(this.busin.getCArrivalDate())))) {
            log1.append("ETD：");
            if (bb.getCArrivalDate() != null) {
                log1.append(this.formatter.format(bb.getCArrivalDate()));
            }
            log1.append("改为");
            if (this.CArrivalDate != null) {
                log1.append(this.CArrivalDate);
            }
            log1.append("；");
        }
        if (!(bb.getCArrivalDate2() == null && this.busin.getCArrivalDate2() == null || bb.getCArrivalDate2() != null && this.busin.getCArrivalDate2() != null && this.formatter.format(bb.getCArrivalDate2()).equals(this.formatter.format(this.busin.getCArrivalDate2())))) {
            log1.append("ETA：");
            if (bb.getCArrivalDate2() != null) {
                log1.append(this.formatter.format(bb.getCArrivalDate2()));
            }
            log1.append("改为");
            if (this.CArrivalDate2 != null) {
                log1.append(this.CArrivalDate2);
            }
            log1.append("；");
        }
        if (!(bb.getCVessel() == null && this.busin.getCVessel() == null || bb.getCVessel() != null && this.busin.getCVessel() != null && bb.getCVessel().trim().equals(this.busin.getCVessel().trim()) || bb.getCVessel() == null && this.busin.getCVessel() != null && "".equals(this.busin.getCVessel().trim()))) {
            log1.append("Ex-Vessel：");
            if (bb.getCVessel() != null) {
                log1.append(bb.getCVessel().trim());
            }
            log1.append("改为");
            if (this.busin.getCVessel() != null) {
                log1.append(this.busin.getCVessel().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCCdno() == null && this.busin.getCCdno() == null || bb.getCCdno() != null && this.busin.getCCdno() != null && bb.getCCdno().trim().equals(this.busin.getCCdno().trim()) || bb.getCCdno() == null && this.busin.getCCdno() != null && "".equals(this.busin.getCCdno().trim()))) {
            log1.append("CD No.：");
            if (bb.getCCdno() != null) {
                log1.append(bb.getCCdno().trim());
            }
            log1.append("改为");
            if (this.busin.getCCdno() != null) {
                log1.append(this.busin.getCCdno().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCCdDate() == null && this.CCdDate == null || bb.getCCdDate() != null && this.CCdDate != null && this.formatter.format(bb.getCCdDate()).equals(this.CCdDate))) {
            log1.append("CD Date：");
            if (bb.getCCdDate() != null) {
                log1.append(this.formatter.format(bb.getCCdDate()));
            }
            log1.append("改为");
            if (this.CCdDate != null) {
                log1.append(this.CCdDate);
            }
            log1.append("；");
        }
        if (!(bb.getCCostDate() == null && this.CCostDate == null || bb.getCCostDate() != null && this.CCostDate != null && this.formatter.format(bb.getCCostDate()).equals(this.CCostDate))) {
            log1.append("Cost Date：");
            if (bb.getCCostDate() != null) {
                log1.append(this.formatter.format(bb.getCCostDate()));
            }
            log1.append("改为");
            if (this.CCostDate != null) {
                log1.append(this.CCostDate);
            }
            log1.append("；");
        }
        if (!(bb.getTSalerByCApplyby() == null && (this.busin.getTSalerByCApplyby() == null || this.busin.getTSalerByCApplyby().getCId() == null || this.busin.getTSalerByCApplyby().getCId() == 0) || bb.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0 && this.busin.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0 && bb.getTSalerByCApplyby().getCId().equals(this.busin.getTSalerByCApplyby().getCId()))) {
            log1.append("Apply By：");
            if (bb.getTSalerByCApplyby() != null && bb.getTSalerByCApplyby().getCId() != null && bb.getTSalerByCApplyby().getCId() > 0) {
                log1.append(bb.getTSalerByCApplyby().getCName());
            }
            log1.append("改为");
            if (this.busin.getTSalerByCApplyby() != null && this.busin.getTSalerByCApplyby().getCId() != null && this.busin.getTSalerByCApplyby().getCId() > 0) {
                log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCApplyby().getCId()).getCName());
            }
            log1.append("；");
        }
        if (!(bb.getCExchange() == null && this.busin.getCExchange() == null || bb.getCExchange() != null && this.busin.getCExchange() != null && bb.getCExchange().equals(this.busin.getCExchange()))) {
            log1.append("Exchange：");
            if (bb.getCExchange() != null) {
                log1.append(bb.getCExchange());
            }
            log1.append("改为");
            if (this.busin.getCExchange() != null) {
                log1.append(this.busin.getCExchange());
            }
            log1.append("；");
        }
        if (!(bb.getCNodate2() == null && (this.CNodate2 == null || "".equals(this.CNodate2.trim())) || bb.getCNodate2() != null && this.CNodate2 != null && !"".equals(this.CNodate2.trim()) && this.formatter.format(bb.getCNodate2()).equals(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00"))))) {
            log1.append("No-Date(D.N1)：");
            if (bb.getCNodate2() != null) {
                log1.append(this.formatter.format(bb.getCNodate2()));
            }
            log1.append("改为");
            if (this.CNodate2 != null && !"".equals(this.CNodate2.trim())) {
                log1.append(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00")));
            }
            log1.append("；");
            acNotice = 1;
        }
        if (!(bb.getCNodate4() == null && (this.CNodate4 == null || "".equals(this.CNodate4.trim())) || bb.getCNodate4() != null && this.CNodate4 != null && !"".equals(this.CNodate4.trim()) && this.formatter.format(bb.getCNodate4()).equals(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate4) + " 00:00:00"))))) {
            log1.append("No-Date(D.N2)：");
            if (bb.getCNodate4() != null) {
                log1.append(this.formatter.format(bb.getCNodate4()));
            }
            log1.append("改为");
            if (this.CNodate4 != null && !"".equals(this.CNodate4.trim())) {
                log1.append(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate4) + " 00:00:00")));
            }
            log1.append("；");
            acNotice = 1;
        }
        if (!(bb.getCNodate8() == null && (this.CNodate8 == null || "".equals(this.CNodate8.trim())) || bb.getCNodate8() != null && this.CNodate8 != null && !"".equals(this.CNodate8.trim()) && this.formatter.format(bb.getCNodate8()).equals(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate8) + " 00:00:00"))))) {
            log1.append("No-Date(INV2)：");
            if (bb.getCNodate8() != null) {
                log1.append(this.formatter.format(bb.getCNodate8()));
            }
            log1.append("改为");
            if (this.CNodate8 != null && !"".equals(this.CNodate8.trim())) {
                log1.append(this.formatter.format(this.formatter3.parse(String.valueOf(this.CNodate8) + " 00:00:00")));
            }
            log1.append("；");
            acNotice = 1;
        }
        if (!(bb.getTSalerByCSaleman() == null && (this.busin.getTSalerByCSaleman() == null || this.busin.getTSalerByCSaleman().getCId() == null || this.busin.getTSalerByCSaleman().getCId() == 0) || bb.getTSalerByCSaleman() != null && bb.getTSalerByCSaleman().getCId() != null && bb.getTSalerByCSaleman().getCId() > 0 && bb.getTSalerByCSaleman().getCId().equals(this.busin.getTSalerByCSaleman().getCId()))) {
            log1.append("业务员 saler：");
            if (bb.getTSalerByCSaleman() != null && bb.getTSalerByCSaleman().getCId() != null && bb.getTSalerByCSaleman().getCId() > 0) {
                log1.append(this.salerService.getEntityById((Serializable)bb.getTSalerByCSaleman().getCId()).getCName());
            } else {
                log1.append("PLEASE CHOOSE");
            }
            log1.append("改为");
            if (this.busin.getTSalerByCSaleman() != null && this.busin.getTSalerByCSaleman().getCId() != null && this.busin.getTSalerByCSaleman().getCId() > 0) {
                log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCSaleman().getCId()).getCName());
            } else {
                log1.append("PLEASE CHOOSE");
            }
            log1.append("；");
        }
        if (!(bb.getTSalerByCKf() == null && (this.busin.getTSalerByCKf() == null || this.busin.getTSalerByCKf().getCId() == null || this.busin.getTSalerByCKf().getCId() == 0) || bb.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0 && this.busin.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0 && bb.getTSalerByCKf().getCId().equals(this.busin.getTSalerByCKf().getCId()))) {
            log1.append("客服：");
            if (bb.getTSalerByCKf() != null && bb.getTSalerByCKf().getCId() != null && bb.getTSalerByCKf().getCId() > 0) {
                log1.append(this.salerService.getEntityById((Serializable)bb.getTSalerByCKf().getCId()).getCName());
            } else {
                log1.append("PLEASE CHOOSE");
            }
            log1.append("改为");
            if (this.busin.getTSalerByCKf() != null && this.busin.getTSalerByCKf().getCId() != null && this.busin.getTSalerByCKf().getCId() > 0) {
                log1.append(this.salerService.getEntityById((Serializable)this.busin.getTSalerByCKf().getCId()).getCName());
            } else {
                log1.append("PLEASE CHOOSE");
            }
            log1.append("；");
        }
        if (!(bb.getCAgent1() == null && this.busin.getCAgent1() == null || bb.getCAgent1() != null && this.busin.getCAgent1() != null && bb.getCAgent1().trim().equals(this.busin.getCAgent1().trim()))) {
            log1.append("前程代理：");
            if (bb.getCAgent1() != null) {
                log1.append(bb.getCAgent1().trim());
            }
            log1.append("改为");
            if (this.busin.getCAgent1() != null) {
                log1.append(this.busin.getCAgent1().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCAgent2() == null && this.busin.getCAgent2() == null || bb.getCAgent2() != null && this.busin.getCAgent2() != null && bb.getCAgent2().trim().equals(this.busin.getCAgent2().trim()))) {
            log1.append("后程代理：");
            if (bb.getCAgent2() != null) {
                log1.append(bb.getCAgent2().trim());
            }
            log1.append("改为");
            if (this.busin.getCAgent2() != null) {
                log1.append(this.busin.getCAgent2().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCConNum() == null && this.busin.getCConNum() == null || bb.getCConNum() != null && this.busin.getCConNum() != null && bb.getCConNum().trim().equals(this.busin.getCConNum().trim()))) {
            log1.append("Container #：");
            if (bb.getCConNum() != null) {
                log1.append(bb.getCConNum().trim());
            }
            log1.append("改为");
            if (this.busin.getCConNum() != null) {
                log1.append(this.busin.getCConNum().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCFty() == null && this.busin.getCFty() == null || bb.getCFty() != null && this.busin.getCFty() != null && bb.getCFty().trim().equals(this.busin.getCFty().trim()))) {
            log1.append("Fty. inv No.：");
            if (bb.getCFty() != null) {
                log1.append(bb.getCFty().trim());
            }
            log1.append("改为");
            if (this.busin.getCFty() != null) {
                log1.append(this.busin.getCFty().trim());
            }
            log1.append("；");
        }
        if (!(bb.getCShipment() == null && this.busin.getCShipment() == null || bb.getCShipment() != null && this.busin.getCShipment() != null && bb.getCShipment().trim().equals(this.busin.getCShipment().trim()))) {
            log1.append("shipment：");
            if (bb.getCShipment() != null) {
                log1.append(bb.getCShipment().trim());
            }
            log1.append("改为");
            if (this.busin.getCShipment() != null) {
                log1.append(this.busin.getCShipment().trim());
            }
            log1.append("；");
        }
        if (!((bb.getCRemarks() == null || "".equals(bb.getCRemarks().trim())) && (this.busin.getCRemarks() == null || "".equals(this.busin.getCRemarks().trim())) || bb.getCRemarks() != null && this.busin.getCRemarks() != null && bb.getCRemarks().trim().equals(this.busin.getCRemarks().trim()))) {
            log2.append("REMARKS：");
            if (bb.getCRemarks() != null) {
                log2.append(bb.getCRemarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCRemarks() != null) {
                log2.append(this.busin.getCRemarks().trim());
            }
            log2.append("；");
        }
        if (!((bb.getCRemarks3() == null || "".equals(bb.getCRemarks3().trim())) && (this.busin.getCRemarks3() == null || "".equals(this.busin.getCRemarks3().trim())) || bb.getCRemarks3() != null && this.busin.getCRemarks3() != null && bb.getCRemarks3().trim().equals(this.busin.getCRemarks3().trim()))) {
            log2.append("业务单备注2：");
            if (bb.getCRemarks3() != null) {
                log2.append(bb.getCRemarks3().trim());
            }
            log2.append("改为");
            if (this.busin.getCRemarks3() != null) {
                log2.append(this.busin.getCRemarks3().trim());
            }
            log2.append("；");
        }
        if (!((bb.getCNoticeRemarks() == null || "".equals(bb.getCNoticeRemarks().trim())) && (this.busin.getCNoticeRemarks() == null || "".equals(this.busin.getCNoticeRemarks().trim())) || bb.getCNoticeRemarks() != null && this.busin.getCNoticeRemarks() != null && bb.getCNoticeRemarks().trim().equals(this.busin.getCNoticeRemarks().trim()))) {
            log2.append("开票提醒：");
            if (bb.getCNoticeRemarks() != null) {
                log2.append(bb.getCNoticeRemarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCNoticeRemarks() != null) {
                log2.append(this.busin.getCNoticeRemarks().trim());
            }
            log2.append("；");
        }
        if (!((bb.getCAskRemarks() == null || "".equals(bb.getCAskRemarks().trim())) && (this.busin.getCAskRemarks() == null || "".equals(this.busin.getCAskRemarks().trim())) || bb.getCAskRemarks() != null && this.busin.getCAskRemarks() != null && bb.getCAskRemarks().trim().equals(this.busin.getCAskRemarks().trim()))) {
            log2.append("问题：");
            if (bb.getCAskRemarks() != null) {
                log2.append(bb.getCAskRemarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCAskRemarks() != null) {
                log2.append(this.busin.getCAskRemarks().trim());
            }
            log2.append("；");
        }
        short noticeremarksFlag = 0;
        boolean noticeModify = false;
        if (this.busin.getCNoticeRemarks() == null || "".equals(this.busin.getCNoticeRemarks())) {
            noticeremarksFlag = 0;
        } else if ((bb.getCNoticeRemarks() == null || "".equals(bb.getCNoticeRemarks())) && this.busin.getCNoticeRemarks() != null && !"".equals(this.busin.getCNoticeRemarks())) {
            noticeremarksFlag = 1;
            noticeModify = true;
        } else if (!(bb.getCNoticeRemarks() == null || "".equals(bb.getCNoticeRemarks()) || this.busin.getCNoticeRemarks() == null || "".equals(this.busin.getCNoticeRemarks()) || bb.getCNoticeRemarks().equals(this.busin.getCNoticeRemarks()))) {
            noticeremarksFlag = 1;
            noticeModify = true;
        }
        if (noticeremarksFlag == 0 && this.busin.getCNoticeRemarks() != null && !"".equals(this.busin.getCNoticeRemarks()) && this.busin.getCNoticeFlag() != null && this.busin.getCNoticeFlag() == 1) {
            noticeremarksFlag = 1;
        }
        if (noticeremarksFlag == 1) {
            acNotice = 1;
        }
        int askNoticeFlag = 0;
        boolean askMofity = false;
        if (this.busin.getCAskRemarks() == null || "".equals(this.busin.getCAskRemarks())) {
            askNoticeFlag = 0;
        } else if ((bb.getCAskRemarks() == null || "".equals(bb.getCAskRemarks())) && this.busin.getCAskRemarks() != null && !"".equals(this.busin.getCAskRemarks())) {
            askNoticeFlag = 1;
            askMofity = true;
        } else if (!(bb.getCAskRemarks() == null || "".equals(bb.getCAskRemarks()) || this.busin.getCAskRemarks() == null || "".equals(this.busin.getCAskRemarks()) || bb.getCAskRemarks().equals(this.busin.getCAskRemarks()))) {
            askNoticeFlag = 1;
            askMofity = true;
        }
        if (askNoticeFlag == 0 && this.busin.getCAskRemarks() != null && !"".equals(this.busin.getCAskRemarks()) && this.busin.getCAskNotice() != null && this.busin.getCAskNotice() == 1) {
            askNoticeFlag = 1;
        }
        if (!((bb.getCDn1Remarks() == null || "".equals(bb.getCDn1Remarks().trim())) && (this.busin.getCDn1Remarks() == null || "".equals(this.busin.getCDn1Remarks().trim())) || bb.getCDn1Remarks() != null && this.busin.getCDn1Remarks() != null && bb.getCDn1Remarks().trim().equals(this.busin.getCDn1Remarks().trim()))) {
            log2.append("DN1 REMARKS：");
            if (bb.getCDn1Remarks() != null) {
                log2.append(bb.getCDn1Remarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCDn1Remarks() != null) {
                log2.append(this.busin.getCDn1Remarks().trim());
            }
            log2.append("；");
            acNotice = 1;
        }
        if (!((bb.getCDnRemarks() == null || "".equals(bb.getCDnRemarks().trim())) && (this.busin.getCDnRemarks() == null || "".equals(this.busin.getCDnRemarks().trim())) || bb.getCDnRemarks() != null && this.busin.getCDnRemarks() != null && bb.getCDnRemarks().trim().equals(this.busin.getCDnRemarks().trim()))) {
            log2.append("DN2 REMARKS：");
            if (bb.getCDnRemarks() != null) {
                log2.append(bb.getCDnRemarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCDnRemarks() != null) {
                log2.append(this.busin.getCDnRemarks().trim());
            }
            log2.append("；");
            acNotice = 1;
        }
        if (!((bb.getCInvRemarks() == null || "".equals(bb.getCInvRemarks().trim())) && (this.busin.getCInvRemarks() == null || "".equals(this.busin.getCInvRemarks().trim())) || bb.getCInvRemarks() != null && this.busin.getCInvRemarks() != null && bb.getCInvRemarks().trim().equals(this.busin.getCInvRemarks().trim()))) {
            log2.append("INV REMARKS：");
            if (bb.getCInvRemarks() != null) {
                log2.append(bb.getCInvRemarks().trim());
            }
            log2.append("改为");
            if (this.busin.getCInvRemarks() != null) {
                log2.append(this.busin.getCInvRemarks().trim());
            }
            log2.append("；");
            acNotice = 1;
        }
        List cashTmpList = this.cashService.getList(this.businId, (short)1);
        int dbid = 0;
        int businTmpId = 0;
        if (cashTmpList != null && cashTmpList.size() > 0) {
            block18: for (int i = 0; i < cashTmpList.size(); ++i) {
                dbid = ((TCash)cashTmpList.get(i)).getCId();
                if (this.cash == null || this.cash.size() <= 0) continue;
                for (int j = 0; j < this.cash.size(); ++j) {
                    if (this.cash.get(j) == null || ((TCash)this.cash.get(j)).getCId() == null || dbid != (businTmpId = ((TCash)this.cash.get(j)).getCId().intValue())) continue;
                    ((TCash)this.cash.get(j)).setCCreatedate(((TCash)cashTmpList.get(i)).getCCreatedate());
                    if (!(((TCash)this.cash.get(j)).getTCostItem() == null && ((TCash)cashTmpList.get(i)).getTCostItem() != null || ((TCash)this.cash.get(j)).getTCostItem() != null && ((TCash)cashTmpList.get(i)).getTCostItem() == null || ((TCash)this.cash.get(j)).getTCostItem() != null && ((TCash)cashTmpList.get(i)).getTCostItem() != null && !((TCash)this.cash.get(j)).getTCostItem().getCId().equals(((TCash)cashTmpList.get(i)).getTCostItem().getCId()) || ((TCash)this.cash.get(j)).getCMoney() == null && ((TCash)cashTmpList.get(i)).getCMoney() != null || ((TCash)this.cash.get(j)).getCMoney() != null && ((TCash)cashTmpList.get(i)).getCMoney() == null || ((TCash)this.cash.get(j)).getCMoney() != null && ((TCash)cashTmpList.get(i)).getCMoney() != null && !((TCash)this.cash.get(j)).getCMoney().equals(((TCash)cashTmpList.get(i)).getCMoney()) || ((TCash)this.cash.get(j)).getCCount() == null && ((TCash)cashTmpList.get(i)).getCCount() != null || ((TCash)this.cash.get(j)).getCCount() != null && ((TCash)cashTmpList.get(i)).getCCount() == null) && (((TCash)this.cash.get(j)).getCCount() == null || ((TCash)cashTmpList.get(i)).getCCount() == null || ((TCash)this.cash.get(j)).getCCount().equals(((TCash)cashTmpList.get(i)).getCCount()))) continue;
                    if (bb.getCRecieveFlag() == 1) {
                        bb.setCNewCostFlag(Short.valueOf((short)1));
                    }
                    acNotice = 1;
                    continue block18;
                }
            }
        }
        List cash2TmpList = this.cashService.getList(this.businId, (short)2);
        dbid = 0;
        businTmpId = 0;
        if (cash2TmpList != null && cash2TmpList.size() > 0) {
            block20: for (int i = 0; i < cash2TmpList.size(); ++i) {
                dbid = ((TCash)cash2TmpList.get(i)).getCId();
                if (this.cash2 == null || this.cash2.size() <= 0) continue;
                for (int j = 0; j < this.cash2.size(); ++j) {
                    if (this.cash2.get(j) == null || ((TCash)this.cash2.get(j)).getCId() == null || dbid != (businTmpId = ((TCash)this.cash2.get(j)).getCId().intValue())) continue;
                    ((TCash)this.cash2.get(j)).setCCreatedate(((TCash)cash2TmpList.get(i)).getCCreatedate());
                    if (!(((TCash)this.cash2.get(j)).getTCostItem() == null && ((TCash)cash2TmpList.get(i)).getTCostItem() != null || ((TCash)this.cash2.get(j)).getTCostItem() != null && ((TCash)cash2TmpList.get(i)).getTCostItem() == null || ((TCash)this.cash2.get(j)).getTCostItem() != null && ((TCash)cash2TmpList.get(i)).getTCostItem() != null && !((TCash)this.cash2.get(j)).getTCostItem().getCId().equals(((TCash)cash2TmpList.get(i)).getTCostItem().getCId()) || ((TCash)this.cash2.get(j)).getCMoney() == null && ((TCash)cash2TmpList.get(i)).getCMoney() != null || ((TCash)this.cash2.get(j)).getCMoney() != null && ((TCash)cash2TmpList.get(i)).getCMoney() == null || ((TCash)this.cash2.get(j)).getCMoney() != null && ((TCash)cash2TmpList.get(i)).getCMoney() != null && !((TCash)this.cash2.get(j)).getCMoney().equals(((TCash)cash2TmpList.get(i)).getCMoney()) || ((TCash)this.cash2.get(j)).getCCount() == null && ((TCash)cash2TmpList.get(i)).getCCount() != null || ((TCash)this.cash2.get(j)).getCCount() != null && ((TCash)cash2TmpList.get(i)).getCCount() == null) && (((TCash)this.cash2.get(j)).getCCount() == null || ((TCash)cash2TmpList.get(i)).getCCount() == null || ((TCash)this.cash2.get(j)).getCCount().equals(((TCash)cash2TmpList.get(i)).getCCount()))) continue;
                    if (bb.getCRecieveFlag() == 1) {
                        bb.setCNewCostFlag(Short.valueOf((short)1));
                    }
                    acNotice = 1;
                    continue block20;
                }
            }
        }
        List rateTmpList = this.cashRateService.getList(this.businId, (short)1);
        int dbid2 = 0;
        int businTmpId2 = 0;
        if (rateTmpList != null && rateTmpList.size() > 0) {
            block22: for (int i = 0; i < rateTmpList.size(); ++i) {
                dbid2 = ((TCashRate)rateTmpList.get(i)).getCId();
                if (this.rate == null || this.rate.size() <= 0) continue;
                for (int j = 0; j < this.rate.size(); ++j) {
                    if (this.rate.get(j) == null || ((TCashRate)this.rate.get(j)).getCId() == null || dbid2 != (businTmpId2 = ((TCashRate)this.rate.get(j)).getCId().intValue())) continue;
                    ((TCashRate)this.rate.get(j)).setCCreatedate(((TCashRate)rateTmpList.get(i)).getCCreatedate());
                    if (!(((TCashRate)this.rate.get(j)).getTCostItem() == null && ((TCashRate)rateTmpList.get(i)).getTCostItem() != null || ((TCashRate)this.rate.get(j)).getTCostItem() != null && ((TCashRate)rateTmpList.get(i)).getTCostItem() == null || ((TCashRate)this.rate.get(j)).getTCostItem() != null && ((TCashRate)rateTmpList.get(i)).getTCostItem() != null && !((TCashRate)this.rate.get(j)).getTCostItem().getCId().equals(((TCashRate)rateTmpList.get(i)).getTCostItem().getCId()) || ((TCashRate)this.rate.get(j)).getCMoney() == null && ((TCashRate)rateTmpList.get(i)).getCMoney() != null || ((TCashRate)this.rate.get(j)).getCMoney() != null && ((TCashRate)rateTmpList.get(i)).getCMoney() == null || ((TCashRate)this.rate.get(j)).getCMoney() != null && ((TCashRate)rateTmpList.get(i)).getCMoney() != null && !((TCashRate)this.rate.get(j)).getCMoney().equals(((TCashRate)rateTmpList.get(i)).getCMoney()) || ((TCashRate)this.rate.get(j)).getCCount() == null && ((TCashRate)rateTmpList.get(i)).getCCount() != null || ((TCashRate)this.rate.get(j)).getCCount() != null && ((TCashRate)rateTmpList.get(i)).getCCount() == null) && (((TCashRate)this.rate.get(j)).getCCount() == null || ((TCashRate)rateTmpList.get(i)).getCCount() == null || ((TCashRate)this.rate.get(j)).getCCount().equals(((TCashRate)rateTmpList.get(i)).getCCount()))) continue;
                    if (bb.getCRecieveFlag() == 1) {
                        bb.setCNewCostFlag(Short.valueOf((short)1));
                    }
                    acNotice = 1;
                    continue block22;
                }
            }
        }
        List rateTmpList2 = this.cashRateService.getList(this.businId, (short)2);
        dbid2 = 0;
        businTmpId2 = 0;
        if (rateTmpList2 != null && rateTmpList2.size() > 0) {
            block24: for (int i = 0; i < rateTmpList2.size(); ++i) {
                dbid2 = ((TCashRate)rateTmpList2.get(i)).getCId();
                if (this.rate2 == null || this.rate2.size() <= 0) continue;
                for (int j = 0; j < this.rate2.size(); ++j) {
                    if (this.rate2.get(j) == null || ((TCashRate)this.rate2.get(j)).getCId() == null || dbid2 != (businTmpId2 = ((TCashRate)this.rate2.get(j)).getCId().intValue())) continue;
                    ((TCashRate)this.rate2.get(j)).setCCreatedate(((TCashRate)rateTmpList2.get(i)).getCCreatedate());
                    if (!(((TCashRate)this.rate2.get(j)).getTCostItem() == null && ((TCashRate)rateTmpList2.get(i)).getTCostItem() != null || ((TCashRate)this.rate2.get(j)).getTCostItem() != null && ((TCashRate)rateTmpList2.get(i)).getTCostItem() == null || ((TCashRate)this.rate2.get(j)).getTCostItem() != null && ((TCashRate)rateTmpList2.get(i)).getTCostItem() != null && !((TCashRate)this.rate2.get(j)).getTCostItem().getCId().equals(((TCashRate)rateTmpList2.get(i)).getTCostItem().getCId()) || ((TCashRate)this.rate2.get(j)).getCMoney() == null && ((TCashRate)rateTmpList2.get(i)).getCMoney() != null || ((TCashRate)this.rate2.get(j)).getCMoney() != null && ((TCashRate)rateTmpList2.get(i)).getCMoney() == null || ((TCashRate)this.rate2.get(j)).getCMoney() != null && ((TCashRate)rateTmpList2.get(i)).getCMoney() != null && !((TCashRate)this.rate2.get(j)).getCMoney().equals(((TCashRate)rateTmpList2.get(i)).getCMoney()) || ((TCashRate)this.rate2.get(j)).getCCount() == null && ((TCashRate)rateTmpList2.get(i)).getCCount() != null || ((TCashRate)this.rate2.get(j)).getCCount() != null && ((TCashRate)rateTmpList2.get(i)).getCCount() == null) && (((TCashRate)this.rate2.get(j)).getCCount() == null || ((TCashRate)rateTmpList2.get(i)).getCCount() == null || ((TCashRate)this.rate2.get(j)).getCCount().equals(((TCashRate)rateTmpList2.get(i)).getCCount()))) continue;
                    if (bb.getCRecieveFlag() == 1) {
                        bb.setCNewCostFlag(Short.valueOf((short)1));
                    }
                    acNotice = 1;
                    continue block24;
                }
            }
        }
        String[] logs = new String[]{log1.toString(), log2.toString(), this.editDatetime};
        if (this.CNodate2 != null && !"".equals(this.CNodate2)) {
            try {
                bb.setCNodate2(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate2) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        } else {
            bb.setCNodate2(null);
        }
        if (this.CNodate4 != null && !"".equals(this.CNodate4)) {
            try {
                bb.setCNodate4(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate4) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        } else {
            bb.setCNodate4(null);
        }
        if (this.CNodate6 != null && !"".equals(this.CNodate6)) {
            try {
                bb.setCNodate6(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate6) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        } else {
            bb.setCNodate6(null);
        }
        if (this.CNodate8 != null && !"".equals(this.CNodate8)) {
            try {
                bb.setCNodate8(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CNodate8) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"No-Date格式不正确");
                return "input";
            }
        } else {
            bb.setCNodate8(null);
        }
        if (this.CArrivalDate != null && !"".equals(this.CArrivalDate)) {
            try {
                bb.setCArrivalDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CArrivalDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"ETD日期格式不正确");
                return "input";
            }
        } else {
            bb.setCArrivalDate(null);
        }
        if (this.CArrivalDate2 != null && !"".equals(this.CArrivalDate2)) {
            try {
                bb.setCArrivalDate2(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CArrivalDate2) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"ETA日期格式不正确");
                return "input";
            }
        } else {
            bb.setCArrivalDate2(null);
        }
        if (this.CCdDate != null && !"".equals(this.CCdDate)) {
            try {
                bb.setCCdDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CCdDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"CD Date格式不正确");
                return "input";
            }
        } else {
            bb.setCCdDate(null);
        }
        if (this.CCostDate != null && !"".equals(this.CCostDate)) {
            try {
                bb.setCCostDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.CCostDate) + " 00:00:00"))));
            }
            catch (ParseException e) {
                e.printStackTrace();
                this.init(1);
                this.request.setAttribute("errInfo", (Object)"CD Date格式不正确");
                return "input";
            }
        } else {
            bb.setCCostDate(null);
        }
        bb.setCConNum(this.busin.getCConNum());
        bb.setCFty(this.busin.getCFty());
        bb.setCShipment(this.busin.getCShipment());
        bb.setCDn1Remarks(this.busin.getCDn1Remarks());
        bb.setCDnRemarks(this.busin.getCDnRemarks());
        bb.setCInvRemarks(this.busin.getCInvRemarks());
        if (this.busin.getTSalerByCSaleman() != null && this.busin.getTSalerByCSaleman().getCId() != null && this.busin.getTSalerByCSaleman().getCId() > 0) {
            bb.setTSalerByCSaleman(this.busin.getTSalerByCSaleman());
        } else {
            bb.setTSalerByCSaleman(null);
        }
        if (this.busin.getTSalerByCKf() != null && this.busin.getTSalerByCKf().getCId() != null && this.busin.getTSalerByCKf().getCId() > 0) {
            bb.setTSalerByCKf(this.busin.getTSalerByCKf());
        } else {
            bb.setTSalerByCKf(null);
        }
        bb.setTBusinType(this.busin.getTBusinType());
        bb.setCRemarks(this.busin.getCRemarks());
        bb.setCRemarks3(this.busin.getCRemarks3());
        bb.setCAgent1(this.busin.getCAgent1());
        bb.setCAgent2(this.busin.getCAgent2());
        bb.setCDescription(this.busin.getCDescription());
        bb.setCQtyOfTruck(this.busin.getCQtyOfTruck());
        bb.setCQtyOfDox(this.busin.getCQtyOfDox());
        bb.setCNoticeRemarks(this.busin.getCNoticeRemarks());
        bb.setCNoticeFlag(Short.valueOf(noticeremarksFlag));
        bb.setCAskRemarks(this.busin.getCAskRemarks());
        bb.setCAskNotice(Integer.valueOf(askNoticeFlag));
        bb.setCCdno(this.busin.getCCdno());
        bb.setCVessel(this.busin.getCVessel());
        bb.setTSalerByCApplyby(this.busin.getTSalerByCApplyby());
        bb.setCExchange(this.busin.getCExchange());
        bb.setCRefno(this.busin.getCRefno());
        if (bb.getCAcNotice() == null || bb.getCAcNotice() != 1 || acNotice != 0) {
            bb.setCAcNotice(Short.valueOf(acNotice));
        }
        if (noticeModify) {
            bb.setCNoticeLast(CommonUtil.getDatetime());
            bb.setCNoticeLastUserid(Integer.valueOf(CommonUtil.getUserId()));
        }
        if (askMofity) {
            bb.setCAskDate(CommonUtil.getDatetime());
            bb.setCAsk(Integer.valueOf(CommonUtil.getUserId()));
        }
        try {
            this.businService.saveCash(this.businId, this.cons, this.costs, this.costsGroup, this.cash, this.rate, this.cash2, this.rate2, (short)1, (short)1, bb, logs);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        if (this._businId > 0) {
            this.request.setAttribute("_businId", (Object)this._businId);
        }
        return "success";
    }

    public void updateReply() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCNoticeReply(this.archiveRemarks);
        bb.setCNoticeLast(CommonUtil.getDatetime());
        bb.setCNoticeLastUserid2(Integer.valueOf(CommonUtil.getUserId()));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新回复失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateAnswer() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCAnswerRemarks(this.archiveRemarks);
        bb.setCAskDate(CommonUtil.getDatetime());
        bb.setCAnswer(Integer.valueOf(CommonUtil.getUserId()));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            msg = "自动更新回复失败！";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String stopNotice() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCNoticeFlag(Short.valueOf((short)0));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String stopProblemNotice() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        bb.setCAskNotice(Integer.valueOf(0));
        try {
            this.businService.saveEntity(bb);
        }
        catch (Exception e1) {
            e1.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void checkLock() {
        String msg = "";
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        if (bb.getCLockFlag() != null && bb.getCLockFlag() == 1) {
            int lockBy = bb.getCLockBy();
            msg = "File was using by 【" + this.empService.getEntityById((Serializable)Integer.valueOf(lockBy)).getCName() + "】, please try again later.";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void updateLockFlag() {
        String msg = "";
        try {
            this.businService.updateLockFlag(this.businId, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            msg = "解除单据锁定标记失败！请手工解除服务单锁定。";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String unLockBusin() {
        try {
            this.businService.updateLockFlag(this.businId, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editBusin() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        try {
            this.businService.updateLockFlag(this.businId, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        this.cdate = this.formatter.format(this.busin.getCDate());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        if (this.busin.getCDeliveryDate() != null && !"".equals(this.busin.getCDeliveryDate())) {
            this.CDeliveryDate = this.formatter.format(this.busin.getCDeliveryDate());
        }
        if (this.busin.getCCompleteDate() != null && !"".equals(this.busin.getCCompleteDate())) {
            this.CCompleteDate = this.formatter.format(this.busin.getCCompleteDate());
        }
        if (this.busin.getCAppDate() != null && !"".equals(this.busin.getCAppDate())) {
            this.appDate = this.formatter.format(this.busin.getCAppDate());
        }
        if (this.busin.getCRelDate() != null && !"".equals(this.busin.getCRelDate())) {
            this.relDate = this.formatter.format(this.busin.getCRelDate());
        }
        if (this.busin.getCClearDate() != null && !"".equals(this.busin.getCClearDate())) {
            this.clearDate = this.formatter.format(this.busin.getCClearDate());
        }
        if (this.busin.getCContainerDate() != null && !"".equals(this.busin.getCContainerDate())) {
            this.CContainerDate = this.formatter.format(this.busin.getCContainerDate());
        }
        if (this.busin.getCNodate2() != null && !"".equals(this.busin.getCNodate2())) {
            this.CNodate2 = this.formatter.format(this.busin.getCNodate2());
        }
        if (this.busin.getCNodate4() != null && !"".equals(this.busin.getCNodate4())) {
            this.CNodate4 = this.formatter.format(this.busin.getCNodate4());
        }
        if (this.busin.getCNodate6() != null && !"".equals(this.busin.getCNodate6())) {
            this.CNodate6 = this.formatter.format(this.busin.getCNodate6());
        }
        if (this.busin.getCNodate8() != null && !"".equals(this.busin.getCNodate8())) {
            this.CNodate8 = this.formatter.format(this.busin.getCNodate8());
        }
        if (this.busin.getCCdDate() != null && !"".equals(this.busin.getCCdDate())) {
            this.CCdDate = this.formatter.format(this.busin.getCCdDate());
        }
        if (this.busin.getCCostDate() != null && !"".equals(this.busin.getCCostDate())) {
            this.CCostDate = this.formatter.format(this.busin.getCCostDate());
        }
        this.remarks2 = this.busin.getCRemarks2();
        this.remarks3 = this.busin.getCRemarks3();
        this.cons = this.containerService.getList(this.businId);
        this.services = this.businServiceService.getList(this.businId);
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        this.cash = this.cashService.getList(this.businId, (short)1);
        this.rate = this.cashRateService.getList(this.businId, (short)1);
        this.cash2 = this.cashService.getList(this.businId, (short)2);
        this.rate2 = this.cashRateService.getList(this.businId, (short)2);
        if (this.listFlag == 3 || this.listFlag == 4 || this.listFlag == 6 || this.listFlag == 9) {
            this.init(2);
        } else {
            this.init(1);
        }
        this.getLogList();
        if (this.costs == null) {
            this.request.setAttribute("costsSize", (Object)0);
        } else {
            this.request.setAttribute("costsSize", (Object)this.costs.size());
        }
        if (this.costsGroup == null) {
            this.request.setAttribute("costsGroupSize", (Object)0);
        } else {
            this.request.setAttribute("costsGroupSize", (Object)this.costsGroup.size());
        }
        this.editDatetime = this.formatter3.format(CommonUtil.getDatetime());
        return "success";
    }

    public void checkBjFlagBeforeEdit() {
        int bjFlag = this.businService.checkBiaojiNotExistsCostgroupinfo(this.businId);
        if (bjFlag == 0) {
            List bjList = this.businService.getCostGroupBiaojiReport(this.businId);
            if (bjList != null && bjList.size() > 0) {
                for (int i = 0; i < bjList.size(); ++i) {
                    BigDecimal data2;
                    BigDecimal data1 = new BigDecimal(((TBiaoji)bjList.get(i)).getCMoney());
                    if (data1.compareTo(data2 = new BigDecimal(((TBiaoji)bjList.get(i)).getCMoney2())) == 0) continue;
                    bjFlag = 1;
                    break;
                }
            }
        } else {
            bjFlag = 1;
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(bjFlag);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void getProfitByBusinId() {
        double profit = this.businService.getProfit(this.businId);
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(profit);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String viewBusin() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.cdate = this.formatter.format(this.busin.getCDate());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        if (this.busin.getCDeliveryDate() != null && !"".equals(this.busin.getCDeliveryDate())) {
            this.CDeliveryDate = this.formatter.format(this.busin.getCDeliveryDate());
        }
        if (this.busin.getCCompleteDate() != null && !"".equals(this.busin.getCCompleteDate())) {
            this.CCompleteDate = this.formatter.format(this.busin.getCCompleteDate());
        }
        if (this.busin.getCAppDate() != null && !"".equals(this.busin.getCAppDate())) {
            this.appDate = this.formatter.format(this.busin.getCAppDate());
        }
        if (this.busin.getCRelDate() != null && !"".equals(this.busin.getCRelDate())) {
            this.relDate = this.formatter.format(this.busin.getCRelDate());
        }
        if (this.busin.getCClearDate() != null && !"".equals(this.busin.getCClearDate())) {
            this.clearDate = this.formatter.format(this.busin.getCClearDate());
        }
        if (this.busin.getCContainerDate() != null && !"".equals(this.busin.getCContainerDate())) {
            this.CContainerDate = this.formatter.format(this.busin.getCContainerDate());
        }
        if (this.busin.getCNodate2() != null && !"".equals(this.busin.getCNodate2())) {
            this.CNodate2 = this.formatter.format(this.busin.getCNodate2());
        }
        if (this.busin.getCNodate4() != null && !"".equals(this.busin.getCNodate4())) {
            this.CNodate4 = this.formatter.format(this.busin.getCNodate4());
        }
        if (this.busin.getCNodate6() != null && !"".equals(this.busin.getCNodate6())) {
            this.CNodate6 = this.formatter.format(this.busin.getCNodate6());
        }
        if (this.busin.getCNodate8() != null && !"".equals(this.busin.getCNodate8())) {
            this.CNodate8 = this.formatter.format(this.busin.getCNodate8());
        }
        if (this.busin.getCCdDate() != null && !"".equals(this.busin.getCCdDate())) {
            this.CCdDate = this.formatter.format(this.busin.getCCdDate());
        }
        if (this.busin.getCCostDate() != null && !"".equals(this.busin.getCCostDate())) {
            this.CCostDate = this.formatter.format(this.busin.getCCostDate());
        }
        this.remarks2 = this.busin.getCRemarks2();
        this.remarks3 = this.busin.getCRemarks3();
        this.cons = this.containerService.getList(this.businId);
        this.services = this.businServiceService.getList(this.businId);
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        this.cash = this.cashService.getList(this.businId, (short)1);
        this.rate = this.cashRateService.getList(this.businId, (short)1);
        this.cash2 = this.cashService.getList(this.businId, (short)2);
        this.rate2 = this.cashRateService.getList(this.businId, (short)2);
        if (this.listFlag == 3 || this.listFlag == 4 || this.listFlag == 6 || this.listFlag == 9) {
            this.init(2);
        } else {
            this.init(1);
        }
        this.getLogList();
        if (this.costs == null) {
            this.request.setAttribute("costsSize", (Object)0);
        } else {
            this.request.setAttribute("costsSize", (Object)this.costs.size());
        }
        if (this.costsGroup == null) {
            this.request.setAttribute("costsGroupSize", (Object)0);
        } else {
            this.request.setAttribute("costsGroupSize", (Object)this.costsGroup.size());
        }
        boolean found = false;
        if (this.costsGroup != null && this.costsGroup.size() > 0) {
            List sumList = this.costService.getCostGroupSumByBusinId(this.businId);
            List bjList = this.businService.getCostGroupBiaojiReport(this.businId);
            if (sumList != null && sumList.size() > 0) {
                for (int i = 0; i < sumList.size(); ++i) {
                    if (bjList != null && bjList.size() > 0) {
                        found = false;
                        for (int j = 0; j < bjList.size(); ++j) {
                            BigDecimal data2;
                            if (((TBiaoji)bjList.get(j)).getTCostGroup().getCId() != ((VCostGroupSum)sumList.get(i)).getId().getGroupId()) continue;
                            found = true;
                            BigDecimal data1 = new BigDecimal(((TBiaoji)bjList.get(j)).getCMoney());
                            if (data1.compareTo(data2 = new BigDecimal(((TBiaoji)bjList.get(j)).getCMoney2())) == 0) break;
                            ((VCostGroupSum)sumList.get(i)).getId().setNoticeFlag(Integer.valueOf(1));
                            break;
                        }
                        if (found) continue;
                        ((VCostGroupSum)sumList.get(i)).getId().setNoticeFlag(Integer.valueOf(1));
                        continue;
                    }
                    ((VCostGroupSum)sumList.get(i)).getId().setNoticeFlag(Integer.valueOf(1));
                }
            }
            this.request.setAttribute("groupSumList", (Object)sumList);
        }
        this.groupInfoList = this.businService.getCostGroupInfoList(this.businId);
        if (this.groupInfoList != null && this.groupInfoList.size() > 0) {
            for (int i = 0; i < this.groupInfoList.size(); ++i) {
                if (((TCostGroupInfo)this.groupInfoList.get(i)).getCCostDate() == null) continue;
                ((TCostGroupInfo)this.groupInfoList.get(i)).setCCostDate_1(this.formatter.format(((TCostGroupInfo)this.groupInfoList.get(i)).getCCostDate()));
            }
            this.request.setAttribute("infoCount", (Object)this.groupInfoList.size());
        } else {
            this.request.setAttribute("infoCount", (Object)0);
        }
        this.request.setAttribute("groupInfoList", (Object)this.groupInfoList);
        return "success";
    }

    public String deleteBusin() {
        TBusin tmp = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        try {
        	TBusinHis his = new TBusinHis();
        	BeanUtils.copyProperties(tmp, his);
        	his.setCLastUserid(UserInfo.getUserId());
        	his.setCLastDate(Timestamp.valueOf(this.formatter2.format(new Date())));
        	
            this.businService.deleteEntity(this.businId,his);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public void edit() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.cdate = this.formatter.format(this.busin.getCDate());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        if (this.busin.getCDeliveryDate() != null && !"".equals(this.busin.getCDeliveryDate())) {
            this.CDeliveryDate = this.formatter.format(this.busin.getCDeliveryDate());
        }
        if (this.busin.getCCompleteDate() != null && !"".equals(this.busin.getCCompleteDate())) {
            this.CCompleteDate = this.formatter.format(this.busin.getCCompleteDate());
        }
        if (this.busin.getCAppDate() != null && !"".equals(this.busin.getCAppDate())) {
            this.appDate = this.formatter.format(this.busin.getCAppDate());
        }
        if (this.busin.getCRelDate() != null && !"".equals(this.busin.getCRelDate())) {
            this.relDate = this.formatter.format(this.busin.getCRelDate());
        }
        if (this.busin.getCClearDate() != null && !"".equals(this.busin.getCClearDate())) {
            this.clearDate = this.formatter.format(this.busin.getCClearDate());
        }
        if (this.busin.getCContainerDate() != null && !"".equals(this.busin.getCContainerDate())) {
            this.CContainerDate = this.formatter.format(this.busin.getCContainerDate());
        }
        if (this.busin.getCNodate2() != null && !"".equals(this.busin.getCNodate2())) {
            this.CNodate2 = this.formatter.format(this.busin.getCNodate2());
        }
        if (this.busin.getCNodate4() != null && !"".equals(this.busin.getCNodate4())) {
            this.CNodate4 = this.formatter.format(this.busin.getCNodate4());
        }
        if (this.busin.getCNodate6() != null && !"".equals(this.busin.getCNodate6())) {
            this.CNodate6 = this.formatter.format(this.busin.getCNodate6());
        }
        if (this.busin.getCNodate8() != null && !"".equals(this.busin.getCNodate8())) {
            this.CNodate8 = this.formatter.format(this.busin.getCNodate8());
        }
        this.remarks2 = this.busin.getCRemarks2();
        this.remarks3 = this.busin.getCRemarks3();
        this.cons = this.containerService.getList(this.businId);
        this.services = this.businServiceService.getList(this.businId);
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        if (this.costs == null) {
            this.request.setAttribute("costsSize", (Object)0);
        } else {
            this.request.setAttribute("costsSize", (Object)this.costs.size());
        }
        if (this.costsGroup == null) {
            this.request.setAttribute("costsGroupSize", (Object)0);
        } else {
            this.request.setAttribute("costsGroupSize", (Object)this.costsGroup.size());
        }
    }

    public String auditBusinY() {
        int count = this.costService.getFAuditCount(this.businId);
        try {
            if (count > 0) {
                this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)5, this.remarks2);
            } else {
                this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)3, this.remarks2);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String auditBusinN() {
        try {
            this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)4, this.remarks2);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String financeAuditBusinY() {
        try {
            this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)6, this.remarks3);
            this.businService.updateCostState((Serializable)Integer.valueOf(this.businId), (short)3);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String financeAuditBusinN() {
        try {
            this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)7, this.remarks3);
            this.businService.updateCostState((Serializable)Integer.valueOf(this.businId), (short)4);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String printCost() {
        if (this.printFlag == null) {
            this.request.setAttribute("errInfo", (Object)"请选择需要打印的项目");
            return "input";
        }
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        ArrayList<TCost> tmp = new ArrayList<TCost>();
        if (this.costs != null && this.costs.size() > 0) {
            for (int i = 0; i < this.costs.size(); ++i) {
                boolean p = false;
                for (int m = 0; m < this.printFlag.length; ++m) {
                    if (this.printFlag[m] != ((TCost)this.costs.get(i)).getCId()) continue;
                    p = true;
                    break;
                }
                if (!p) continue;
                tmp.add((TCost)this.costs.get(i));
            }
        }
        this.costs = tmp;
        this.request.setAttribute("costUserCount", (Object)1);
        this.request.setAttribute("costsCount", (Object)this.costs.size());
        this.init(2);
        this.request.setAttribute("cusName", (Object)this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId()).getCAddr());
        this.cons = this.containerService.getList(this.businId);
        if (this.cons != null && this.cons.size() > 0) {
            StringBuffer conNum = new StringBuffer();
            for (int i = 0; i < this.cons.size(); ++i) {
                String num = ((TContainer)this.cons.get(i)).getCContainerNum();
                if (num == null || "".equals(num)) continue;
                conNum.append(num).append("；");
            }
            if (!"".equals(conNum.toString().trim())) {
                this.request.setAttribute("conNum2", (Object)conNum.toString().substring(0, conNum.toString().length() - 1));
            }
        }
        return "success";
    }

    public String printCost2() {
        if (this.groupPrintFlag == null) {
            this.request.setAttribute("errInfo", (Object)"请选择需要打印的项目");
            return "input";
        }
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        int groupId = this.printRadioFlag[0];
        TCostGroupInfo info = this.businService.getCostGroupInfo(this.businId, groupId);
        this.busin.setCCostDate(info.getCCostDate());
        if (info.getCApplyBy() != null && info.getCApplyBy() > 0) {
            this.busin.setTSalerByCApplyby(this.salerService.getEntityById((Serializable)info.getCApplyBy()));
        }
        this.busin.setCExchange(info.getCExchange());
        this.busin.setCRefno(info.getCRefNo());
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        ArrayList<TCost> tmp = new ArrayList<TCost>();
        if (this.costsGroup != null && this.costsGroup.size() > 0) {
            for (int i = 0; i < this.costsGroup.size(); ++i) {
                boolean p = false;
                for (int m = 0; m < this.groupPrintFlag.length; ++m) {
                    if (this.groupPrintFlag[m] != ((TCost)this.costsGroup.get(i)).getCId()) continue;
                    p = true;
                    break;
                }
                if (!p || (((TCost)this.costsGroup.get(i)).getCMoney() == null || ((TCost)this.costsGroup.get(i)).getCMoney() == 0.0) && (((TCost)this.costsGroup.get(i)).getCRiel() == null || ((TCost)this.costsGroup.get(i)).getCRiel() == 0.0)) continue;
                tmp.add((TCost)this.costsGroup.get(i));
            }
        }
        this.costs = tmp;
        double sumMoney = 0.0;
        double sumRe = 0.0;
        if (this.costs != null && this.costs.size() > 0) {
            for (int i = 0; i < this.costs.size(); ++i) {
                if (((TCost)this.costs.get(i)).getCMoney() != null && ((TCost)this.costs.get(i)).getCMoney() > 0.0) {
                    sumMoney += ((TCost)this.costs.get(i)).getCMoney().doubleValue();
                }
                if (((TCost)this.costs.get(i)).getCRiel() == null || !(((TCost)this.costs.get(i)).getCRiel() > 0.0)) continue;
                sumRe += ((TCost)this.costs.get(i)).getCRiel().doubleValue();
            }
        }
        this.request.setAttribute("sumMoney", (Object)sumMoney);
        this.request.setAttribute("sumRe", (Object)sumRe);
        this.cons = this.containerService.getList(this.businId);
        if (this.cons != null && this.cons.size() > 0) {
            StringBuffer conNum = new StringBuffer();
            StringBuffer types = new StringBuffer();
            StringBuffer pkgs = new StringBuffer();
            for (int i = 0; i < this.cons.size(); ++i) {
                String pkg;
                String type;
                String num = ((TContainer)this.cons.get(i)).getCContainerNum();
                if (num != null && !"".equals(num)) {
                    conNum.append(num).append("；");
                }
                if ((type = ((TContainer)this.cons.get(i)).getCContainerType()) != null && !"".equals(type)) {
                    types.append(type).append("；");
                }
                if ((pkg = ((TContainer)this.cons.get(i)).getCCount()) == null || "".equals(pkg)) continue;
                pkgs.append(pkg).append("；");
            }
            if (!"".equals(conNum.toString().trim())) {
                this.request.setAttribute("conNum2", (Object)conNum.toString().substring(0, conNum.toString().length() - 1));
            }
            if (!"".equals(types.toString().trim())) {
                this.request.setAttribute("types", (Object)types.toString().substring(0, types.toString().length() - 1));
            }
            if (!"".equals(pkgs.toString().trim())) {
                this.request.setAttribute("pkgs", (Object)pkgs.toString().substring(0, pkgs.toString().length() - 1));
            }
        }
        return "success";
    }

    public void updatePrintCost2Money() {
        TBusin bb = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        if (this.flag == 1) {
            bb.setCDepositAmount(Double.valueOf(this.cmoney));
        } else {
            bb.setCCashReceived(Double.valueOf(this.cmoney));
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.businService.saveEntity(bb);
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("保存失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String printInvoice() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        this.cash = this.cashService.getList(this.businId, (short)1);
        ArrayList<TCash> tmp = new ArrayList<TCash>();
        if (this.cash != null && this.cash.size() > 0) {
            for (int i = 0; i < this.cash.size(); ++i) {
                tmp.add((TCash)this.cash.get(i));
            }
        }
        this.cash = tmp;
        TCustomer cus = this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId());
        this.request.setAttribute("cus", (Object)cus);
        List conList = this.containerService.getList(this.businId);
        String containerNum = "";
        String pkgs = "";
        if (conList != null && conList.size() > 0) {
            if (((TContainer)conList.get(0)).getCContainerNum() != null && !"".equals(((TContainer)conList.get(0)).getCContainerNum())) {
                containerNum = ((TContainer)conList.get(0)).getCContainerNum();
            }
            if (((TContainer)conList.get(0)).getCContainerType() != null && !"".equals(((TContainer)conList.get(0)).getCContainerType())) {
                containerNum = !"".equals(containerNum) ? String.valueOf(containerNum) + "/" + ((TContainer)conList.get(0)).getCContainerType() : ((TContainer)conList.get(0)).getCContainerType();
            }
            pkgs = ((TContainer)conList.get(0)).getCCount();
        }
        this.request.setAttribute("containerNum", (Object)containerNum);
        this.request.setAttribute("conList", (Object)conList);
        this.request.setAttribute("pkgs", (Object)pkgs);
        this.getCostItemList();
        return "success";
    }

    public String printInvoice2() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.busin.setCNodate1(this.busin.getCNodate3());
        this.busin.setCNodate2(this.busin.getCNodate4());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        this.cash = this.cashService.getList(this.businId, (short)2);
        ArrayList<TCash> tmp = new ArrayList<TCash>();
        if (this.cash != null && this.cash.size() > 0) {
            for (int i = 0; i < this.cash.size(); ++i) {
                tmp.add((TCash)this.cash.get(i));
            }
        }
        this.cash = tmp;
        TCustomer cus = this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId());
        this.request.setAttribute("cus", (Object)cus);
        List conList = this.containerService.getList(this.businId);
        String containerNum = "";
        String pkgs = "";
        if (conList != null && conList.size() > 0) {
            if (((TContainer)conList.get(0)).getCContainerNum() != null && !"".equals(((TContainer)conList.get(0)).getCContainerNum())) {
                containerNum = ((TContainer)conList.get(0)).getCContainerNum();
            }
            if (((TContainer)conList.get(0)).getCContainerType() != null && !"".equals(((TContainer)conList.get(0)).getCContainerType())) {
                containerNum = !"".equals(containerNum) ? String.valueOf(containerNum) + "/" + ((TContainer)conList.get(0)).getCContainerType() : ((TContainer)conList.get(0)).getCContainerType();
            }
            pkgs = ((TContainer)conList.get(0)).getCCount();
        }
        this.request.setAttribute("containerNum", (Object)containerNum);
        this.request.setAttribute("conList", (Object)conList);
        this.request.setAttribute("pkgs", (Object)pkgs);
        this.getCostItemList();
        return "success";
    }

    public String printInvoiceRate() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        this.rate = this.cashRateService.getList(this.businId, (short)1);
        ArrayList<TCashRate> tmp = new ArrayList<TCashRate>();
        double cmoney = 0.0;
        double crate = 0.0;
        if (this.rate != null && this.rate.size() > 0) {
            for (int i = 0; i < this.rate.size(); ++i) {
                tmp.add((TCashRate)this.rate.get(i));
            }
        }
        this.rate = tmp;
        TCustomer cus = this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId());
        this.request.setAttribute("cus", (Object)cus);
        List conList = this.containerService.getList(this.businId);
        String containerNum = "";
        String pkgs = "";
        if (conList != null && conList.size() > 0) {
            if (((TContainer)conList.get(0)).getCContainerNum() != null && !"".equals(((TContainer)conList.get(0)).getCContainerNum())) {
                containerNum = ((TContainer)conList.get(0)).getCContainerNum();
            }
            if (((TContainer)conList.get(0)).getCContainerType() != null && !"".equals(((TContainer)conList.get(0)).getCContainerType())) {
                containerNum = !"".equals(containerNum) ? String.valueOf(containerNum) + "/" + ((TContainer)conList.get(0)).getCContainerType() : ((TContainer)conList.get(0)).getCContainerType();
            }
            pkgs = ((TContainer)conList.get(0)).getCCount();
        }
        this.request.setAttribute("containerNum", (Object)containerNum);
        this.request.setAttribute("conList", (Object)conList);
        this.request.setAttribute("pkgs", (Object)pkgs);
        this.getCostItemList();
        return "success";
    }

    public String printInvoiceRate2() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.busin.setCNodate5(this.busin.getCNodate7());
        this.busin.setCNodate6(this.busin.getCNodate8());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        if (this.busin.getCArrivalDate2() != null && !"".equals(this.busin.getCArrivalDate2())) {
            this.CArrivalDate2 = this.formatter.format(this.busin.getCArrivalDate2());
        }
        this.rate = this.cashRateService.getList(this.businId, (short)2);
        ArrayList<TCashRate> tmp = new ArrayList<TCashRate>();
        double cmoney = 0.0;
        double crate = 0.0;
        if (this.rate != null && this.rate.size() > 0) {
            for (int i = 0; i < this.rate.size(); ++i) {
                tmp.add((TCashRate)this.rate.get(i));
            }
        }
        this.rate = tmp;
        TCustomer cus = this.cusService.getEntityById((Serializable)this.busin.getTCustomerByCCusid().getCId());
        this.request.setAttribute("cus", (Object)cus);
        List conList = this.containerService.getList(this.businId);
        String containerNum = "";
        String pkgs = "";
        if (conList != null && conList.size() > 0) {
            if (((TContainer)conList.get(0)).getCContainerNum() != null && !"".equals(((TContainer)conList.get(0)).getCContainerNum())) {
                containerNum = ((TContainer)conList.get(0)).getCContainerNum();
            }
            if (((TContainer)conList.get(0)).getCContainerType() != null && !"".equals(((TContainer)conList.get(0)).getCContainerType())) {
                containerNum = !"".equals(containerNum) ? String.valueOf(containerNum) + "/" + ((TContainer)conList.get(0)).getCContainerType() : ((TContainer)conList.get(0)).getCContainerType();
            }
            pkgs = ((TContainer)conList.get(0)).getCCount();
        }
        this.request.setAttribute("containerNum", (Object)containerNum);
        this.request.setAttribute("conList", (Object)conList);
        this.request.setAttribute("pkgs", (Object)pkgs);
        this.getCostItemList();
        return "success";
    }

    public String printBusinFile() {
        this.busin = this.businService.getEntityById((Serializable)Integer.valueOf(this.businId));
        this.cdate = this.formatter.format(this.busin.getCDate());
        if (this.busin.getCArrivalDate() != null && !"".equals(this.busin.getCArrivalDate())) {
            this.CArrivalDate = this.formatter.format(this.busin.getCArrivalDate());
        }
        TEmp tmp = this.empService.getEntityById((Serializable)this.busin.getTUser().getCEmpid());
        this.request.setAttribute("empName", (Object)tmp.getCName());
        this.cons = this.containerService.getList(this.businId);
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        return "success";
    }

    public String confirmCost() {
        try {
            this.costService.updateCostDetailState(this.costs, (short)5);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String confirmCash() {
        try {
            this.cashService.updateCashDetailState(this.cash, (short)3);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String editCashBusin() {
        this.edit();
        this.costs = this.costService.getList(this.businId, (short)0);
        this.costsGroup = this.costService.getList(this.businId, (short)1);
        this.cash = this.cashService.getList(this.businId, (short)1);
        this.rate = this.cashRateService.getList(this.businId, (short)1);
        this.cash2 = this.cashService.getList(this.businId, (short)2);
        this.rate2 = this.cashRateService.getList(this.businId, (short)2);
        this.init(2);
        try {
            this.businService.updateLockFlag(this.businId, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        this.editDatetime = this.formatter3.format(CommonUtil.getDatetime());
        if (this.busin.getCCdDate() != null && !"".equals(this.busin.getCCdDate())) {
            this.CCdDate = this.formatter.format(this.busin.getCCdDate());
        }
        if (this.busin.getCCostDate() != null && !"".equals(this.busin.getCCostDate())) {
            this.CCostDate = this.formatter.format(this.busin.getCCostDate());
        }
        return "success";
    }

    public String businClose() {
        try {
            this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), this.archiveFlag.shortValue(), "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        this.request.setAttribute("_businId", (Object)this._businId);
        return "success";
    }

    public String businOpen() {
        try {
            this.businService.updateEntityState((Serializable)Integer.valueOf(this.businId), (short)1, "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        this.request.setAttribute("_businId", (Object)this._businId);
        return "success";
    }

    public String businComplete() {
        try {
            this.businService.updatebusinComplete(this.businId, this.archiveFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String businLock() {
        try {
            this.businService.updateLock(this.businId, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String businUnlock() {
        try {
            this.businService.updateLock(this.businId, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String setHidden() {
        TCost cc = this.costService.getCostById(this.businId);
        cc.setCHidden(Short.valueOf(this.hiddenFlag));
        try {
            this.costService.saveCost(cc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setHiddenBatch() {
        try {
            this.costService.saveCostHidden(this.businIds, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setBaoxiao() {
        TCost cc = this.costService.getCostById(this.businId);
        cc.setCBaoxiao(Short.valueOf(this.hiddenFlag));
        try {
            this.costService.saveCost(cc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setBaoxiaoBatch() {
        try {
            this.costService.saveCostBaoxiao(this.businIds, (short)1);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setNewCostFlag() {
        TCost cc = this.costService.getCostById(this.businId);
        cc.setCNewCostFlag(Short.valueOf((short)0));
        try {
            this.costService.saveCost(cc);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setNewCostFlagBatch() {
        try {
            this.costService.saveCost(this.businIds, (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setNewCostFlag2() {
        try {
            this.businService.updateNewCostFlag2(this.businId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String setCostDupFlag() {
        try {
            this.businService.updateCostDupFlag(this.businId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void updatePrintState() {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.businService.updatePrintState((Serializable)Integer.valueOf(this.businId));
                writer.print("");
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("修改打印状态失败");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String setOrder() {
        try {
            this.businService.updateOrder(this.businId, this.orderFlag.shortValue());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String getBusinCusReport() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        }
        Object[] obj = new Object[]{sdate, edate};
        this.request.setAttribute("businCusList", (Object)this.businService.getBusinCusReport(obj));
        return "success";
    }

    public String updateCusFlag() {
        TCustomer cus = this.cusService.getEntityById((Serializable)Integer.valueOf(this.cusId));
        cus.setCFlag(Short.valueOf(this.flag));
        try {
            this.cusService.saveEntity(cus);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String updateLock() {
        try {
            this.businService.updateLock(this.businIds, this._lock);
        }
        catch (Exception e) {
            e.printStackTrace();
            if (this.query.getRecieveFlag() == 0) {
                return "success1";
            }
            return "success2";
        }
        if (this.query.getRecieveFlag() == 0) {
            return "success1";
        }
        return "success2";
    }

    public String getFinanceGroupList() {
        List list = this.businService.getGroupList(this.groupQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("groupList", (Object)list);
        PageBean pagebean = this.businService.getGroupPages(this.groupQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addFinanceGroup() {
        this.getCostItemList();
        return "success";
    }

    public String saveFinanceGroup() {
        int count = this.businService.checkName(this.group.getCName(), this.group.getCId());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"回款项目组名称重复");
            this.getCostItemList();
            return "input";
        }
        try {
            this.businService.saveGroup(this.group, this.groupItems);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.getCostItemList();
            return "input";
        }
        return "success";
    }

    public String editFinanceGroup() {
        this.group = this.businService.getGroupById(this.groupId);
        this.groupItems = this.businService.getGroupDetail(this.groupId);
        if (this.groupItems != null && this.groupItems.size() > 0) {
            this.itemsCount = this.groupItems.size();
        }
        this.getCostItemList();
        return "success";
    }

    public String deleteFinanceGroup() {
        try {
            this.businService.deleteGroup(this.groupId);
        }
        catch (Exception e) {
            this.request.setAttribute("errInfo", (Object)"已经使用，不允许删除");
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void getGroupItemsByGroupId() {
        block8: {
            JSONArray jsonArray = new JSONArray();
            ArrayList<String> arrayList = new ArrayList<String>(20);
            StringBuffer data = new StringBuffer();
            List list = this.businService.getGroupDetail(this.groupId);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); ++i) {
                    data.append("{itemId:").append(((TFinanceGroupItem)list.get(i)).getTCostItem().getCId()).append(",itemName:\"").append(((TFinanceGroupItem)list.get(i)).getTCostItem().getCName()).append("\"}");
                    arrayList.add(data.toString());
                    data.setLength(0);
                }
            }
            jsonArray = JSONArray.fromObject(arrayList);
            this.response.setContentType("text/html;charset=utf-8");
            this.response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                try {
                    writer = this.response.getWriter();
                    if (jsonArray == null) {
                        writer.print("");
                        break block8;
                    }
                    writer.print((Object)jsonArray);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    writer.flush();
                    writer.close();
                }
            }
            finally {
                writer.flush();
                writer.close();
            }
        }
    }

    public String getCostGroupList() {
        List list = this.businService.getGroupList(this.cgroupQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("groupList", (Object)list);
        PageBean pagebean = this.businService.getGroupPages(this.cgroupQuery, null, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String addCostGroup() {
        this.getCostItemList();
        return "success";
    }

    public String saveCostGroup() {
        int count = this.businService.checkCostGroupName(this.cgroup.getCName(), this.cgroup.getCId());
        if (count > 0) {
            this.request.setAttribute("errInfo", (Object)"成本项目组名称重复");
            this.getCostItemList();
            return "input";
        }
        try {
            this.businService.saveGroup(this.cgroup, this.cgroupItems);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.getCostItemList();
            return "input";
        }
        return "success";
    }

    public String editCostGroup() {
        this.cgroup = this.businService.getCostGroupById(this.cgroupId);
        this.cgroupItems = this.businService.getCostGroupDetail(this.cgroupId);
        if (this.cgroupItems != null && this.cgroupItems.size() > 0) {
            this.citemsCount = this.cgroupItems.size();
        }
        this.getCostItemList();
        return "success";
    }

    public String deleteCostGroup() {
        try {
            this.businService.deleteCostGroup(this.cgroupId);
        }
        catch (Exception e) {
            this.request.setAttribute("errInfo", (Object)"已经使用，不允许删除");
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public void getCostGroupItemsByGroupId() {
        block8: {
            JSONArray jsonArray = new JSONArray();
            ArrayList<String> arrayList = new ArrayList<String>(20);
            StringBuffer data = new StringBuffer();
            List list = this.businService.getCostGroupDetail(this.cgroupId);
            TCostGroup group = this.businService.getCostGroupById(this.cgroupId);
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); ++i) {
                    data.append("{itemId:").append(((TCostGroupItem)list.get(i)).getTCostItem().getCId()).append(",itemName:\"").append(((TCostGroupItem)list.get(i)).getTCostItem().getCName()).append("\"").append(",groupName:\"").append(group.getCName()).append("\"}");
                    arrayList.add(data.toString());
                    data.setLength(0);
                }
            }
            jsonArray = JSONArray.fromObject(arrayList);
            this.response.setContentType("text/html;charset=utf-8");
            this.response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                try {
                    writer = this.response.getWriter();
                    if (jsonArray == null) {
                        writer.print("");
                        break block8;
                    }
                    writer.print((Object)jsonArray);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    writer.flush();
                    writer.close();
                }
            }
            finally {
                writer.flush();
                writer.close();
            }
        }
    }

    public String getInvByEmpReport() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        } else {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.startDate = this.formatter.format(CommonUtil.getDatetime());
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        } else {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.endDate = this.formatter.format(CommonUtil.getDatetime());
        }
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        Object[] obj = new Object[]{sdate, edate, 1};
        List list = this.businService.getInvUserList(this.invUserQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getInvUserPages(this.invUserQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String getInvNoticeReport() throws ParseException {
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        Object[] arrobject = new Object[3];
        arrobject[2] = 2;
        Object[] obj = arrobject;
        List list = this.businService.getInvUserList(this.invUserQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getInvUserPages(this.invUserQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String getProblemNoticeReport() throws ParseException {
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        Object[] obj = new Object[]{};
        List list = this.businService.getProblemNoticeList(this.askQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getProblemNoticePages(this.askQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String getBusinSumReport() throws ParseException {
        String sdate = null;
        String edate = null;
        if (this.startDate != null && !"".equals(this.startDate)) {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.startDate) + " 00:00:00"));
        } else {
            sdate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.startDate = this.formatter.format(CommonUtil.getDatetime());
        }
        if (this.endDate != null && !"".equals(this.endDate)) {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(this.formatter3.parse(String.valueOf(this.endDate) + " 00:00:00"));
        } else {
            edate = new SimpleDateFormat("yyyy-MM-dd").format(CommonUtil.getDatetime());
            this.endDate = this.formatter.format(CommonUtil.getDatetime());
        }
        this.request.setAttribute("empList", (Object)this.empService.getValidAllList());
        Object[] obj = new Object[]{sdate, edate};
        List list = this.businService.getBusinSumReport(this.businSumReportQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("businList", (Object)list);
        PageBean pagebean = this.businService.getBusinSumReportPages(this.businSumReportQuery, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public void updateCostGroupInfo() throws ParseException {
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        String returnValue = "";
        TCostGroupInfo info = this.businService.getCostGroupInfo(this.businId, this.groupId);
        if (this.costFlag == 1) {
            if (this.costInfo != null && !"".equals(this.costInfo.trim())) {
                info.setCCostDate(Timestamp.valueOf(this.formatter2.format(this.formatter3.parse(String.valueOf(this.costInfo) + " 00:00:00"))));
                if (info.getCRefNo() == null || "".equals(info.getCRefNo().trim())) {
                    returnValue = this.businService.getMaxRefno();
                    info.setCRefNo(returnValue);
                }
            } else {
                info.setCCostDate(null);
                info.setCRefNo(null);
            }
        } else if (this.costFlag == 2) {
            if (this.costInfo != null && !"".equals(this.costInfo.trim())) {
                info.setCApplyBy(Integer.valueOf(Integer.parseInt(this.costInfo)));
            } else {
                info.setCApplyBy(null);
            }
        } else if (this.costFlag == 3) {
            if (this.costInfo != null && !"".equals(this.costInfo.trim())) {
                info.setCExchange(Double.valueOf(Double.parseDouble(this.costInfo)));
            } else {
                info.setCExchange(null);
            }
        } else if (this.costFlag == 4) {
            if (this.costInfo != null && !"".equals(this.costInfo.trim())) {
                info.setCRefNo(this.costInfo);
            } else {
                info.setCRefNo(null);
            }
        }
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                this.businService.saveCostGroupInfo(info);
                writer.print(returnValue);
            }
            catch (Exception e) {
                e.printStackTrace();
                writer.print("error");
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public void businBiaoji() {
        TBiaoji bb = this.businService.getBiaoji(this.businId, this.groupId);
        if (bb == null) {
            bb = new TBiaoji();
            bb.setTBusin(this.businService.getEntityById((Serializable)Integer.valueOf(this.businId)));
            bb.setCDate(CommonUtil.getDatetime());
            bb.setTCostGroup(this.businService.getCostGroupById(this.groupId));
        }
        VCostGroupSum ss = this.costService.getCostGroupSumByBusinId(this.businId, this.groupId);
        bb.setCMoney(ss.getId().getTotal());
        bb.setCMoney2(ss.getId().getTotal());
        bb.setCNotice(Short.valueOf((short)0));
        String msg = "";
        try {
            this.businService.saveBiaoji(bb);
        }
        catch (Exception e) {
            e.printStackTrace();
            msg = "标记失败，请检查网络";
        }
        this.response.setContentType("text/html;charset=utf-8");
        this.response.setCharacterEncoding("utf-8");
        PrintWriter writer = null;
        try {
            try {
                writer = this.response.getWriter();
                writer.print(msg);
            }
            catch (IOException e) {
                e.printStackTrace();
                writer.flush();
                writer.close();
            }
        }
        finally {
            writer.flush();
            writer.close();
        }
    }

    public String getCostGroupNoticeReport() {
        this.request.setAttribute("cusList", (Object)this.cusService.getValidList());
        Object[] obj = null;
        List list = this.businService.getCostGroupBiaojiReport(this.queryBiaoji, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("reportList", (Object)list);
        PageBean pagebean = this.businService.getCostGroupBiaojiReportPages(this.queryBiaoji, obj, this.pageNow, this.pageSize);
        this.request.setAttribute("pageBean", (Object)pagebean);
        return "success";
    }

    public String cancelBiaojiNotice() {
        TBiaoji bb = this.businService.getBiaoji(this.businId, this.groupId);
        bb.setCNotice(Short.valueOf((short)0));
        try {
            this.businService.saveBiaoji(bb);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "input";
        }
        return "success";
    }

    public String homePage() {
        return "success";
    }

    public TBusin getBusin() {
        return this.busin;
    }

    public void setBusin(TBusin busin) {
        this.busin = busin;
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

    public void setBusinService(BusinService businService) {
        this.businService = businService;
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

    public int getBusinId() {
        return this.businId;
    }

    public void setBusinId(int businId) {
        this.businId = businId;
    }

    public String getCdate() {
        return this.cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public List<TContainer> getCons() {
        return this.cons;
    }

    public void setCons(List<TContainer> cons) {
        this.cons = cons;
    }

    public void setContainerService(ContainerService containerService) {
        this.containerService = containerService;
    }

    public void setServiceTypeService(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    public List<Integer> getTypeId() {
        return this.typeId;
    }

    public void setTypeId(List<Integer> typeId) {
        this.typeId = typeId;
    }

    public List<String> getTypeName() {
        return this.typeName;
    }

    public void setTypeName(List<String> typeName) {
        this.typeName = typeName;
    }

    public List<TService> getServices() {
        return this.services;
    }

    public void setServices(List<TService> services) {
        this.services = services;
    }

    public void setBusinServiceService(BusinServiceService businServiceService) {
        this.businServiceService = businServiceService;
    }

    public List<TCost> getCosts() {
        return this.costs;
    }

    public void setCosts(List<TCost> costs) {
        this.costs = costs;
    }

    public void setCostService(CostService costService) {
        this.costService = costService;
    }

    public void setCostItemService(CostItemService costItemService) {
        this.costItemService = costItemService;
    }

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }

    public List<Integer> getCostItemId() {
        return this.costItemId;
    }

    public void setCostItemId(List<Integer> costItemId) {
        this.costItemId = costItemId;
    }

    public List<String> getCostItemName() {
        return this.costItemName;
    }

    public void setCostItemName(List<String> costItemName) {
        this.costItemName = costItemName;
    }

    public List<Integer> getEmpId() {
        return this.empId;
    }

    public void setEmpId(List<Integer> empId) {
        this.empId = empId;
    }

    public List<String> getEmpName() {
        return this.empName;
    }

    public void setEmpName(List<String> empName) {
        this.empName = empName;
    }

    public String getCArrivalDate() {
        return this.CArrivalDate;
    }

    public void setCArrivalDate(String cArrivalDate) {
        this.CArrivalDate = cArrivalDate;
    }

    public String getCDeliveryDate() {
        return this.CDeliveryDate;
    }

    public void setCDeliveryDate(String cDeliveryDate) {
        this.CDeliveryDate = cDeliveryDate;
    }

    public String getCCompleteDate() {
        return this.CCompleteDate;
    }

    public void setCCompleteDate(String cCompleteDate) {
        this.CCompleteDate = cCompleteDate;
    }

    public List<TCash> getCash() {
        return this.cash;
    }

    public void setCash(List<TCash> cash) {
        this.cash = cash;
    }

    public void setCashService(CashService cashService) {
        this.cashService = cashService;
    }

    public VBusin getQuery() {
        return this.query;
    }

    public void setQuery(VBusin query) {
        this.query = query;
    }

    public String getRemarks2() {
        return this.remarks2;
    }

    public void setRemarks2(String remarks2) {
        this.remarks2 = remarks2;
    }

    public String getRemarks3() {
        return this.remarks3;
    }

    public void setRemarks3(String remarks3) {
        this.remarks3 = remarks3;
    }

    public int getPrintUserId() {
        return this.printUserId;
    }

    public void setPrintUserId(int printUserId) {
        this.printUserId = printUserId;
    }

    public int getListFlag() {
        return this.listFlag;
    }

    public void setListFlag(int listFlag) {
        this.listFlag = listFlag;
    }

    public void setBusinTypeService(BusinTypeService businTypeService) {
        this.businTypeService = businTypeService;
    }

    public List<Integer> getCashItemId() {
        return this.cashItemId;
    }

    public void setCashItemId(List<Integer> cashItemId) {
        this.cashItemId = cashItemId;
    }

    public List<String> getCashItemName() {
        return this.cashItemName;
    }

    public void setCashItemName(List<String> cashItemName) {
        this.cashItemName = cashItemName;
    }

    public String getAppDate() {
        return this.appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getRelDate() {
        return this.relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    public String getClearDate() {
        return this.clearDate;
    }

    public void setClearDate(String clearDate) {
        this.clearDate = clearDate;
    }

    public String getConNum() {
        return this.conNum;
    }

    public void setConNum(String conNum) {
        this.conNum = conNum;
    }

    public String getCContainerDate() {
        return this.CContainerDate;
    }

    public void setCContainerDate(String cContainerDate) {
        this.CContainerDate = cContainerDate;
    }

    public List<TCashRate> getRate() {
        return this.rate;
    }

    public void setRate(List<TCashRate> rate) {
        this.rate = rate;
    }

    public void setCashRateService(CashRateService cashRateService) {
        this.cashRateService = cashRateService;
    }

    public void setRateService(RateService rateService) {
        this.rateService = rateService;
    }

    public List<Integer> getRateId() {
        return this.rateId;
    }

    public void setRateId(List<Integer> rateId) {
        this.rateId = rateId;
    }

    public List<Double> getRateName() {
        return this.rateName;
    }

    public void setRateName(List<Double> rateName) {
        this.rateName = rateName;
    }

    public int[] getPrintFlag() {
        return this.printFlag;
    }

    public void setPrintFlag(int[] printFlag) {
        this.printFlag = printFlag;
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

    public String getCNodate2() {
        return this.CNodate2;
    }

    public void setCNodate2(String cNodate2) {
        this.CNodate2 = cNodate2;
    }

    public String getCNodate4() {
        return this.CNodate4;
    }

    public void setCNodate4(String cNodate4) {
        this.CNodate4 = cNodate4;
    }

    public Short getOrderFlag() {
        return this.orderFlag;
    }

    public void setOrderFlag(Short orderFlag) {
        this.orderFlag = orderFlag;
    }

    public int[] getDelCost() {
        return this.delCost;
    }

    public void setDelCost(int[] delCost) {
        this.delCost = delCost;
    }

    public String getBillNo() {
        return this.billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public List<TCash> getCash2() {
        return this.cash2;
    }

    public void setCash2(List<TCash> cash2) {
        this.cash2 = cash2;
    }

    public List<TCashRate> getRate2() {
        return this.rate2;
    }

    public void setRate2(List<TCashRate> rate2) {
        this.rate2 = rate2;
    }

    public String getCConNum() {
        return this.CConNum;
    }

    public void setCConNum(String cConNum) {
        this.CConNum = cConNum;
    }

    public String getCNodate6() {
        return this.CNodate6;
    }

    public void setCNodate6(String cNodate6) {
        this.CNodate6 = cNodate6;
    }

    public String getCNodate8() {
        return this.CNodate8;
    }

    public void setCNodate8(String cNodate8) {
        this.CNodate8 = cNodate8;
    }

    public String getArchiveRemarks() {
        return this.archiveRemarks;
    }

    public void setArchiveRemarks(String archiveRemarks) {
        this.archiveRemarks = archiveRemarks;
    }

    public short getHiddenFlag() {
        return this.hiddenFlag;
    }

    public void setHiddenFlag(short hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public String getFileRemarks() {
        return this.fileRemarks;
    }

    public void setFileRemarks(String fileRemarks) {
        this.fileRemarks = fileRemarks;
    }

    public String getRecieveRemarks() {
        return this.recieveRemarks;
    }

    public void setRecieveRemarks(String recieveRemarks) {
        this.recieveRemarks = recieveRemarks;
    }

    public void setSalerService(SalerService salerService) {
        this.salerService = salerService;
    }

    public String getEditDatetime() {
        return this.editDatetime;
    }

    public void setEditDatetime(String editDatetime) {
        this.editDatetime = editDatetime;
    }

    public int getCusId() {
        return this.cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public short getFlag() {
        return this.flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public short get_lock() {
        return this._lock;
    }

    public void set_lock(short _lock) {
        this._lock = _lock;
    }

    public int[] getBusinIds() {
        return this.businIds;
    }

    public void setBusinIds(int[] businIds) {
        this.businIds = businIds;
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

    public TFinanceGroup getGroup() {
        return this.group;
    }

    public void setGroup(TFinanceGroup group) {
        this.group = group;
    }

    public TFinanceGroup getGroupQuery() {
        return this.groupQuery;
    }

    public void setGroupQuery(TFinanceGroup groupQuery) {
        this.groupQuery = groupQuery;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<TFinanceGroupItem> getGroupItems() {
        return this.groupItems;
    }

    public void setGroupItems(List<TFinanceGroupItem> groupItems) {
        this.groupItems = groupItems;
    }

    public int getItemsCount() {
        return this.itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public VBusinInvUser getInvUserQuery() {
        return this.invUserQuery;
    }

    public void setInvUserQuery(VBusinInvUser invUserQuery) {
        this.invUserQuery = invUserQuery;
    }

    public VBusinSumReport getBusinSumReportQuery() {
        return this.businSumReportQuery;
    }

    public void setBusinSumReportQuery(VBusinSumReport businSumReportQuery) {
        this.businSumReportQuery = businSumReportQuery;
    }

    public String getCArrivalDate2() {
        return this.CArrivalDate2;
    }

    public void setCArrivalDate2(String cArrivalDate2) {
        this.CArrivalDate2 = cArrivalDate2;
    }

    public TCostGroup getCgroup() {
        return this.cgroup;
    }

    public void setCgroup(TCostGroup cgroup) {
        this.cgroup = cgroup;
    }

    public TCostGroup getCgroupQuery() {
        return this.cgroupQuery;
    }

    public void setCgroupQuery(TCostGroup cgroupQuery) {
        this.cgroupQuery = cgroupQuery;
    }

    public int getCgroupId() {
        return this.cgroupId;
    }

    public void setCgroupId(int cgroupId) {
        this.cgroupId = cgroupId;
    }

    public List<TCostGroupItem> getCgroupItems() {
        return this.cgroupItems;
    }

    public void setCgroupItems(List<TCostGroupItem> cgroupItems) {
        this.cgroupItems = cgroupItems;
    }

    public int getCitemsCount() {
        return this.citemsCount;
    }

    public void setCitemsCount(int citemsCount) {
        this.citemsCount = citemsCount;
    }

    public String getCCdDate() {
        return this.CCdDate;
    }

    public void setCCdDate(String cCdDate) {
        this.CCdDate = cCdDate;
    }

    public String getCCostDate() {
        return this.CCostDate;
    }

    public void setCCostDate(String cCostDate) {
        this.CCostDate = cCostDate;
    }

    public double getCmoney() {
        return this.cmoney;
    }

    public void setCmoney(double cmoney) {
        this.cmoney = cmoney;
    }

    public List<TCost> getCostsGroup() {
        return this.costsGroup;
    }

    public void setCostsGroup(List<TCost> costsGroup) {
        this.costsGroup = costsGroup;
    }

    public List<TCostGroupInfo> getGroupInfoList() {
        return this.groupInfoList;
    }

    public void setGroupInfoList(List<TCostGroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
    }

    public String getCostInfo() {
        return this.costInfo;
    }

    public void setCostInfo(String costInfo) {
        this.costInfo = costInfo;
    }

    public int getCostFlag() {
        return this.costFlag;
    }

    public void setCostFlag(int costFlag) {
        this.costFlag = costFlag;
    }

    public int[] getGroupPrintFlag() {
        return this.groupPrintFlag;
    }

    public void setGroupPrintFlag(int[] groupPrintFlag) {
        this.groupPrintFlag = groupPrintFlag;
    }

    public int[] getPrintRadioFlag() {
        return this.printRadioFlag;
    }

    public void setPrintRadioFlag(int[] printRadioFlag) {
        this.printRadioFlag = printRadioFlag;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TBiaoji getQueryBiaoji() {
        return this.queryBiaoji;
    }

    public void setQueryBiaoji(TBiaoji queryBiaoji) {
        this.queryBiaoji = queryBiaoji;
    }

    public VBusinAsk getAskQuery() {
        return this.askQuery;
    }

    public void setAskQuery(VBusinAsk askQuery) {
        this.askQuery = askQuery;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getBusinCode() {
        return this.businCode;
    }

    public void setBusinCode(String businCode) {
        this.businCode = businCode;
    }

    public String getBusinCode2() {
        return this.businCode2;
    }

    public void setBusinCode2(String businCode2) {
        this.businCode2 = businCode2;
    }

    public int get_businId() {
        return this._businId;
    }

    public void set_businId(int _businId) {
        this._businId = _businId;
    }

	public void setAgentService(AgentService agentService) {
		this.agentService = agentService;
	}
    
}

