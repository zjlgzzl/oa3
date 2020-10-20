
package com.common.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class TBusin
implements Serializable {
    private Integer CId;
    private TCustomer TCustomerByCCusid;
    private TBusinType TBusinType;
    private TSaler TSalerByCApplyby;
    private TUser TUser;
    private TSaler TSalerByCKf;
    private TCustomer TCustomerByCCusid2;
    private TSaler TSalerByCSaleman;
    private Timestamp CDate;
    private String CBillNo;
    private String CSend;
    private String CReceive;
    private String CTakeNo;
    private String CGoodsType;
    private String CArrivalPort;
    private Timestamp CArrivalDate;
    private Timestamp CDeliveryDate;
    private Timestamp CCompleteDate;
    private Short CState;
    private Timestamp CCreateDate;
    private Integer CLastUserid;
    private Timestamp CLastDate;
    private Short CBusinAuditFlg;
    private Short CFinanceAuditFlg;
    private String CRemarks;
    private String CRemarks2;
    private String CRemarks3;
    private Timestamp CAppDate;
    private Timestamp CRelDate;
    private Timestamp CClearDate;
    private Timestamp CContainerDate;
    private Short CCostPrintFlag;
    private String shippingLine;
    private Short CCostArchive;
    private String CNodate1;
    private Timestamp CNodate2;
    private String CVat;
    private Integer COrder;
    private String COther1;
    private String CNodate3;
    private Timestamp CNodate4;
    private Double CRecieveMoney;
    private Short CScheduleArchive;
    private Short CRateArchive;
    private Short CLock;
    private Short CLockFlag;
    private String CConNum;
    private String CFty;
    private String CShipment;
    private String CNodate5;
    private Timestamp CNodate6;
    private String CNodate7;
    private Timestamp CNodate8;
    private Double CRecieveMoney2;
    private String CArchiveRemarks;
    private Double CRecieveMoney1;
    private Double CRecieveMoney3;
    private Double CRecieveMoney4;
    private String CRecieveRemarks;
    private String CDnRemarks;
    private String CInvRemarks;
    private Short CComplete;
    private String CFileRemarks;
    private Short CRecieveFlag;
    private String CRecieveRemarks2;
    private String CRecieveRemarks3;
    private Short CNewCostFlag;
    private Short CPayComplete;
    private Timestamp CLastDate2;
    private String CAgent1;
    private String CAgent2;
    private String CKaipiaoRemarks;
    private String CDescription;
    private String CQtyOfTruck;
    private String CQtyOfDox;
    private String CDn1Remarks;
    private Timestamp CArrivalDate2;
    private String CNoticeRemarks;
    private Short CNoticeFlag;
    private String CNoticeReply;
    private String CCdno;
    private Timestamp CCdDate;
    private Timestamp CCostDate;
    private String CVessel;
    private Double CExchange;
    private String CRefno;
    private Double CDepositAmount;
    private Double CCashReceived;
    private Short CAcNotice;
    private Timestamp CNoticeLast;
    private Short CAddGroup;
    private Integer CNoticeLastUserid;
    private Integer CNoticeLastUserid2;
    private Integer CAsk;
    private Integer CAnswer;
    private String CAskRemarks;
    private String CAnswerRemarks;
    private Integer CAskNotice;
    private Timestamp CAskDate;
    private Timestamp CCashDate;
    private Double CCashMoney1;
    private Double CCashMoney2;
    private Double CCashMoney4;
    private Double CCostSum1;
    private Double CCostSum2;
    private Integer CLockBy;
    private Set TJiezhuans = new HashSet(0);
    private Set TCashRates = new HashSet(0);
    private Set TCashs = new HashSet(0);
    private Set TServices = new HashSet(0);
    private Set TBusinLogs = new HashSet(0);
    private Set TContainers = new HashSet(0);
    private Set TBiaojis = new HashSet(0);
    private Set TCosts = new HashSet(0);

    
    private String CCbm;
    private String CPkg;
    private String CWeight;
    public TBusin() {
    }

    public TBusin(TCustomer TCustomerByCCusid, TBusinType TBusinType2, TSaler TSalerByCApplyby, TUser TUser2, TSaler TSalerByCKf, TCustomer TCustomerByCCusid2, TSaler TSalerByCSaleman, Timestamp CDate, String CBillNo, String CSend, String CReceive, String CTakeNo, String CGoodsType, String CArrivalPort, Timestamp CArrivalDate, Timestamp CDeliveryDate, Timestamp CCompleteDate, Short CState, Timestamp CCreateDate, Integer CLastUserid, Timestamp CLastDate, Short CBusinAuditFlg, Short CFinanceAuditFlg, String CRemarks, String CRemarks2, String CRemarks3, Timestamp CAppDate, Timestamp CRelDate, Timestamp CClearDate, Timestamp CContainerDate, Short CCostPrintFlag, String shippingLine, Short CCostArchive, String CNodate1, Timestamp CNodate2, String CVat, Integer COrder, String COther1, String CNodate3, Timestamp CNodate4, Double CRecieveMoney, Short CScheduleArchive, Short CRateArchive, Short CLock, Short CLockFlag, String CConNum, String CFty, String CShipment, String CNodate5, Timestamp CNodate6, String CNodate7, Timestamp CNodate8, Double CRecieveMoney2, String CArchiveRemarks, Double CRecieveMoney1, Double CRecieveMoney3, Double CRecieveMoney4, String CRecieveRemarks, String CDnRemarks, String CInvRemarks, Short CComplete, String CFileRemarks, Short CRecieveFlag, String CRecieveRemarks2, String CRecieveRemarks3, Short CNewCostFlag, Short CPayComplete, Timestamp CLastDate2, String CAgent1, String CAgent2, String CKaipiaoRemarks, String CDescription, String CQtyOfTruck, String CQtyOfDox, String CDn1Remarks, Timestamp CArrivalDate2, String CNoticeRemarks, Short CNoticeFlag, String CNoticeReply, String CCdno, Timestamp CCdDate, Timestamp CCostDate, String CVessel, Double CExchange, String CRefno, Double CDepositAmount, Double CCashReceived, Short CAcNotice, Timestamp CNoticeLast, Short CAddGroup, Integer CNoticeLastUserid, Integer CNoticeLastUserid2, Integer CAsk, Integer CAnswer, String CAskRemarks, String CAnswerRemarks, Integer CAskNotice, Timestamp CAskDate, Timestamp CCashDate, Double CCashMoney1, Double CCashMoney2, Double CCashMoney4, Double CCostSum1, Double CCostSum2, Integer CLockBy, Set TJiezhuans, Set TCashRates, Set TCashs, Set TServices, Set TBusinLogs, Set TContainers, Set TBiaojis, Set TCosts) {
        this.TCustomerByCCusid = TCustomerByCCusid;
        this.TBusinType = TBusinType2;
        this.TSalerByCApplyby = TSalerByCApplyby;
        this.TUser = TUser2;
        this.TSalerByCKf = TSalerByCKf;
        this.TCustomerByCCusid2 = TCustomerByCCusid2;
        this.TSalerByCSaleman = TSalerByCSaleman;
        this.CDate = CDate;
        this.CBillNo = CBillNo;
        this.CSend = CSend;
        this.CReceive = CReceive;
        this.CTakeNo = CTakeNo;
        this.CGoodsType = CGoodsType;
        this.CArrivalPort = CArrivalPort;
        this.CArrivalDate = CArrivalDate;
        this.CDeliveryDate = CDeliveryDate;
        this.CCompleteDate = CCompleteDate;
        this.CState = CState;
        this.CCreateDate = CCreateDate;
        this.CLastUserid = CLastUserid;
        this.CLastDate = CLastDate;
        this.CBusinAuditFlg = CBusinAuditFlg;
        this.CFinanceAuditFlg = CFinanceAuditFlg;
        this.CRemarks = CRemarks;
        this.CRemarks2 = CRemarks2;
        this.CRemarks3 = CRemarks3;
        this.CAppDate = CAppDate;
        this.CRelDate = CRelDate;
        this.CClearDate = CClearDate;
        this.CContainerDate = CContainerDate;
        this.CCostPrintFlag = CCostPrintFlag;
        this.shippingLine = shippingLine;
        this.CCostArchive = CCostArchive;
        this.CNodate1 = CNodate1;
        this.CNodate2 = CNodate2;
        this.CVat = CVat;
        this.COrder = COrder;
        this.COther1 = COther1;
        this.CNodate3 = CNodate3;
        this.CNodate4 = CNodate4;
        this.CRecieveMoney = CRecieveMoney;
        this.CScheduleArchive = CScheduleArchive;
        this.CRateArchive = CRateArchive;
        this.CLock = CLock;
        this.CLockFlag = CLockFlag;
        this.CConNum = CConNum;
        this.CFty = CFty;
        this.CShipment = CShipment;
        this.CNodate5 = CNodate5;
        this.CNodate6 = CNodate6;
        this.CNodate7 = CNodate7;
        this.CNodate8 = CNodate8;
        this.CRecieveMoney2 = CRecieveMoney2;
        this.CArchiveRemarks = CArchiveRemarks;
        this.CRecieveMoney1 = CRecieveMoney1;
        this.CRecieveMoney3 = CRecieveMoney3;
        this.CRecieveMoney4 = CRecieveMoney4;
        this.CRecieveRemarks = CRecieveRemarks;
        this.CDnRemarks = CDnRemarks;
        this.CInvRemarks = CInvRemarks;
        this.CComplete = CComplete;
        this.CFileRemarks = CFileRemarks;
        this.CRecieveFlag = CRecieveFlag;
        this.CRecieveRemarks2 = CRecieveRemarks2;
        this.CRecieveRemarks3 = CRecieveRemarks3;
        this.CNewCostFlag = CNewCostFlag;
        this.CPayComplete = CPayComplete;
        this.CLastDate2 = CLastDate2;
        this.CAgent1 = CAgent1;
        this.CAgent2 = CAgent2;
        this.CKaipiaoRemarks = CKaipiaoRemarks;
        this.CDescription = CDescription;
        this.CQtyOfTruck = CQtyOfTruck;
        this.CQtyOfDox = CQtyOfDox;
        this.CDn1Remarks = CDn1Remarks;
        this.CArrivalDate2 = CArrivalDate2;
        this.CNoticeRemarks = CNoticeRemarks;
        this.CNoticeFlag = CNoticeFlag;
        this.CNoticeReply = CNoticeReply;
        this.CCdno = CCdno;
        this.CCdDate = CCdDate;
        this.CCostDate = CCostDate;
        this.CVessel = CVessel;
        this.CExchange = CExchange;
        this.CRefno = CRefno;
        this.CDepositAmount = CDepositAmount;
        this.CCashReceived = CCashReceived;
        this.CAcNotice = CAcNotice;
        this.CNoticeLast = CNoticeLast;
        this.CAddGroup = CAddGroup;
        this.CNoticeLastUserid = CNoticeLastUserid;
        this.CNoticeLastUserid2 = CNoticeLastUserid2;
        this.CAsk = CAsk;
        this.CAnswer = CAnswer;
        this.CAskRemarks = CAskRemarks;
        this.CAnswerRemarks = CAnswerRemarks;
        this.CAskNotice = CAskNotice;
        this.CAskDate = CAskDate;
        this.CCashDate = CCashDate;
        this.CCashMoney1 = CCashMoney1;
        this.CCashMoney2 = CCashMoney2;
        this.CCashMoney4 = CCashMoney4;
        this.CCostSum1 = CCostSum1;
        this.CCostSum2 = CCostSum2;
        this.CLockBy = CLockBy;
        this.TJiezhuans = TJiezhuans;
        this.TCashRates = TCashRates;
        this.TCashs = TCashs;
        this.TServices = TServices;
        this.TBusinLogs = TBusinLogs;
        this.TContainers = TContainers;
        this.TBiaojis = TBiaojis;
        this.TCosts = TCosts;
    }

    public Integer getCId() {
        return this.CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public TCustomer getTCustomerByCCusid() {
        return this.TCustomerByCCusid;
    }

    public void setTCustomerByCCusid(TCustomer TCustomerByCCusid) {
        this.TCustomerByCCusid = TCustomerByCCusid;
    }

    public TBusinType getTBusinType() {
        return this.TBusinType;
    }

    public void setTBusinType(TBusinType TBusinType2) {
        this.TBusinType = TBusinType2;
    }

    public TSaler getTSalerByCApplyby() {
        return this.TSalerByCApplyby;
    }

    public void setTSalerByCApplyby(TSaler TSalerByCApplyby) {
        this.TSalerByCApplyby = TSalerByCApplyby;
    }

    public TUser getTUser() {
        return this.TUser;
    }

    public void setTUser(TUser TUser2) {
        this.TUser = TUser2;
    }

    public TSaler getTSalerByCKf() {
        return this.TSalerByCKf;
    }

    public void setTSalerByCKf(TSaler TSalerByCKf) {
        this.TSalerByCKf = TSalerByCKf;
    }

    public TCustomer getTCustomerByCCusid2() {
        return this.TCustomerByCCusid2;
    }

    public void setTCustomerByCCusid2(TCustomer TCustomerByCCusid2) {
        this.TCustomerByCCusid2 = TCustomerByCCusid2;
    }

    public TSaler getTSalerByCSaleman() {
        return this.TSalerByCSaleman;
    }

    public void setTSalerByCSaleman(TSaler TSalerByCSaleman) {
        this.TSalerByCSaleman = TSalerByCSaleman;
    }

    public Timestamp getCDate() {
        return this.CDate;
    }

    public void setCDate(Timestamp CDate) {
        this.CDate = CDate;
    }

    public String getCBillNo() {
        return this.CBillNo;
    }

    public void setCBillNo(String CBillNo) {
        this.CBillNo = CBillNo;
    }

    public String getCSend() {
        return this.CSend;
    }

    public void setCSend(String CSend) {
        this.CSend = CSend;
    }

    public String getCReceive() {
        return this.CReceive;
    }

    public void setCReceive(String CReceive) {
        this.CReceive = CReceive;
    }

    public String getCTakeNo() {
        return this.CTakeNo;
    }

    public void setCTakeNo(String CTakeNo) {
        this.CTakeNo = CTakeNo;
    }

    public String getCGoodsType() {
        return this.CGoodsType;
    }

    public void setCGoodsType(String CGoodsType) {
        this.CGoodsType = CGoodsType;
    }

    public String getCArrivalPort() {
        return this.CArrivalPort;
    }

    public void setCArrivalPort(String CArrivalPort) {
        this.CArrivalPort = CArrivalPort;
    }

    public Timestamp getCArrivalDate() {
        return this.CArrivalDate;
    }

    public void setCArrivalDate(Timestamp CArrivalDate) {
        this.CArrivalDate = CArrivalDate;
    }

    public Timestamp getCDeliveryDate() {
        return this.CDeliveryDate;
    }

    public void setCDeliveryDate(Timestamp CDeliveryDate) {
        this.CDeliveryDate = CDeliveryDate;
    }

    public Timestamp getCCompleteDate() {
        return this.CCompleteDate;
    }

    public void setCCompleteDate(Timestamp CCompleteDate) {
        this.CCompleteDate = CCompleteDate;
    }

    public Short getCState() {
        return this.CState;
    }

    public void setCState(Short CState) {
        this.CState = CState;
    }

    public Timestamp getCCreateDate() {
        return this.CCreateDate;
    }

    public void setCCreateDate(Timestamp CCreateDate) {
        this.CCreateDate = CCreateDate;
    }

    public Integer getCLastUserid() {
        return this.CLastUserid;
    }

    public void setCLastUserid(Integer CLastUserid) {
        this.CLastUserid = CLastUserid;
    }

    public Timestamp getCLastDate() {
        return this.CLastDate;
    }

    public void setCLastDate(Timestamp CLastDate) {
        this.CLastDate = CLastDate;
    }

    public Short getCBusinAuditFlg() {
        return this.CBusinAuditFlg;
    }

    public void setCBusinAuditFlg(Short CBusinAuditFlg) {
        this.CBusinAuditFlg = CBusinAuditFlg;
    }

    public Short getCFinanceAuditFlg() {
        return this.CFinanceAuditFlg;
    }

    public void setCFinanceAuditFlg(Short CFinanceAuditFlg) {
        this.CFinanceAuditFlg = CFinanceAuditFlg;
    }

    public String getCRemarks() {
        return this.CRemarks;
    }

    public void setCRemarks(String CRemarks) {
        this.CRemarks = CRemarks;
    }

    public String getCRemarks2() {
        return this.CRemarks2;
    }

    public void setCRemarks2(String CRemarks2) {
        this.CRemarks2 = CRemarks2;
    }

    public String getCRemarks3() {
        return this.CRemarks3;
    }

    public void setCRemarks3(String CRemarks3) {
        this.CRemarks3 = CRemarks3;
    }

    public Timestamp getCAppDate() {
        return this.CAppDate;
    }

    public void setCAppDate(Timestamp CAppDate) {
        this.CAppDate = CAppDate;
    }

    public Timestamp getCRelDate() {
        return this.CRelDate;
    }

    public void setCRelDate(Timestamp CRelDate) {
        this.CRelDate = CRelDate;
    }

    public Timestamp getCClearDate() {
        return this.CClearDate;
    }

    public void setCClearDate(Timestamp CClearDate) {
        this.CClearDate = CClearDate;
    }

    public Timestamp getCContainerDate() {
        return this.CContainerDate;
    }

    public void setCContainerDate(Timestamp CContainerDate) {
        this.CContainerDate = CContainerDate;
    }

    public Short getCCostPrintFlag() {
        return this.CCostPrintFlag;
    }

    public void setCCostPrintFlag(Short CCostPrintFlag) {
        this.CCostPrintFlag = CCostPrintFlag;
    }

    public String getShippingLine() {
        return this.shippingLine;
    }

    public void setShippingLine(String shippingLine) {
        this.shippingLine = shippingLine;
    }

    public Short getCCostArchive() {
        return this.CCostArchive;
    }

    public void setCCostArchive(Short CCostArchive) {
        this.CCostArchive = CCostArchive;
    }

    public String getCNodate1() {
        return this.CNodate1;
    }

    public void setCNodate1(String CNodate1) {
        this.CNodate1 = CNodate1;
    }

    public Timestamp getCNodate2() {
        return this.CNodate2;
    }

    public void setCNodate2(Timestamp CNodate2) {
        this.CNodate2 = CNodate2;
    }

    public String getCVat() {
        return this.CVat;
    }

    public void setCVat(String CVat) {
        this.CVat = CVat;
    }

    public Integer getCOrder() {
        return this.COrder;
    }

    public void setCOrder(Integer COrder) {
        this.COrder = COrder;
    }

    public String getCOther1() {
        return this.COther1;
    }

    public void setCOther1(String COther1) {
        this.COther1 = COther1;
    }

    public String getCNodate3() {
        return this.CNodate3;
    }

    public void setCNodate3(String CNodate3) {
        this.CNodate3 = CNodate3;
    }

    public Timestamp getCNodate4() {
        return this.CNodate4;
    }

    public void setCNodate4(Timestamp CNodate4) {
        this.CNodate4 = CNodate4;
    }

    public Double getCRecieveMoney() {
        return this.CRecieveMoney;
    }

    public void setCRecieveMoney(Double CRecieveMoney) {
        this.CRecieveMoney = CRecieveMoney;
    }

    public Short getCScheduleArchive() {
        return this.CScheduleArchive;
    }

    public void setCScheduleArchive(Short CScheduleArchive) {
        this.CScheduleArchive = CScheduleArchive;
    }

    public Short getCRateArchive() {
        return this.CRateArchive;
    }

    public void setCRateArchive(Short CRateArchive) {
        this.CRateArchive = CRateArchive;
    }

    public Short getCLock() {
        return this.CLock;
    }

    public void setCLock(Short CLock) {
        this.CLock = CLock;
    }

    public Short getCLockFlag() {
        return this.CLockFlag;
    }

    public void setCLockFlag(Short CLockFlag) {
        this.CLockFlag = CLockFlag;
    }

    public String getCConNum() {
        return this.CConNum;
    }

    public void setCConNum(String CConNum) {
        this.CConNum = CConNum;
    }

    public String getCFty() {
        return this.CFty;
    }

    public void setCFty(String CFty) {
        this.CFty = CFty;
    }

    public String getCShipment() {
        return this.CShipment;
    }

    public void setCShipment(String CShipment) {
        this.CShipment = CShipment;
    }

    public String getCNodate5() {
        return this.CNodate5;
    }

    public void setCNodate5(String CNodate5) {
        this.CNodate5 = CNodate5;
    }

    public Timestamp getCNodate6() {
        return this.CNodate6;
    }

    public void setCNodate6(Timestamp CNodate6) {
        this.CNodate6 = CNodate6;
    }

    public String getCNodate7() {
        return this.CNodate7;
    }

    public void setCNodate7(String CNodate7) {
        this.CNodate7 = CNodate7;
    }

    public Timestamp getCNodate8() {
        return this.CNodate8;
    }

    public void setCNodate8(Timestamp CNodate8) {
        this.CNodate8 = CNodate8;
    }

    public Double getCRecieveMoney2() {
        return this.CRecieveMoney2;
    }

    public void setCRecieveMoney2(Double CRecieveMoney2) {
        this.CRecieveMoney2 = CRecieveMoney2;
    }

    public String getCArchiveRemarks() {
        return this.CArchiveRemarks;
    }

    public void setCArchiveRemarks(String CArchiveRemarks) {
        this.CArchiveRemarks = CArchiveRemarks;
    }

    public Double getCRecieveMoney1() {
        return this.CRecieveMoney1;
    }

    public void setCRecieveMoney1(Double CRecieveMoney1) {
        this.CRecieveMoney1 = CRecieveMoney1;
    }

    public Double getCRecieveMoney3() {
        return this.CRecieveMoney3;
    }

    public void setCRecieveMoney3(Double CRecieveMoney3) {
        this.CRecieveMoney3 = CRecieveMoney3;
    }

    public Double getCRecieveMoney4() {
        return this.CRecieveMoney4;
    }

    public void setCRecieveMoney4(Double CRecieveMoney4) {
        this.CRecieveMoney4 = CRecieveMoney4;
    }

    public String getCRecieveRemarks() {
        return this.CRecieveRemarks;
    }

    public void setCRecieveRemarks(String CRecieveRemarks) {
        this.CRecieveRemarks = CRecieveRemarks;
    }

    public String getCDnRemarks() {
        return this.CDnRemarks;
    }

    public void setCDnRemarks(String CDnRemarks) {
        this.CDnRemarks = CDnRemarks;
    }

    public String getCInvRemarks() {
        return this.CInvRemarks;
    }

    public void setCInvRemarks(String CInvRemarks) {
        this.CInvRemarks = CInvRemarks;
    }

    public Short getCComplete() {
        return this.CComplete;
    }

    public void setCComplete(Short CComplete) {
        this.CComplete = CComplete;
    }

    public String getCFileRemarks() {
        return this.CFileRemarks;
    }

    public void setCFileRemarks(String CFileRemarks) {
        this.CFileRemarks = CFileRemarks;
    }

    public Short getCRecieveFlag() {
        return this.CRecieveFlag;
    }

    public void setCRecieveFlag(Short CRecieveFlag) {
        this.CRecieveFlag = CRecieveFlag;
    }

    public String getCRecieveRemarks2() {
        return this.CRecieveRemarks2;
    }

    public void setCRecieveRemarks2(String CRecieveRemarks2) {
        this.CRecieveRemarks2 = CRecieveRemarks2;
    }

    public String getCRecieveRemarks3() {
        return this.CRecieveRemarks3;
    }

    public void setCRecieveRemarks3(String CRecieveRemarks3) {
        this.CRecieveRemarks3 = CRecieveRemarks3;
    }

    public Short getCNewCostFlag() {
        return this.CNewCostFlag;
    }

    public void setCNewCostFlag(Short CNewCostFlag) {
        this.CNewCostFlag = CNewCostFlag;
    }

    public Short getCPayComplete() {
        return this.CPayComplete;
    }

    public void setCPayComplete(Short CPayComplete) {
        this.CPayComplete = CPayComplete;
    }

    public Timestamp getCLastDate2() {
        return this.CLastDate2;
    }

    public void setCLastDate2(Timestamp CLastDate2) {
        this.CLastDate2 = CLastDate2;
    }

    public String getCAgent1() {
        return this.CAgent1;
    }

    public void setCAgent1(String CAgent1) {
        this.CAgent1 = CAgent1;
    }

    public String getCAgent2() {
        return this.CAgent2;
    }

    public void setCAgent2(String CAgent2) {
        this.CAgent2 = CAgent2;
    }

    public String getCKaipiaoRemarks() {
        return this.CKaipiaoRemarks;
    }

    public void setCKaipiaoRemarks(String CKaipiaoRemarks) {
        this.CKaipiaoRemarks = CKaipiaoRemarks;
    }

    public String getCDescription() {
        return this.CDescription;
    }

    public void setCDescription(String CDescription) {
        this.CDescription = CDescription;
    }

    public String getCQtyOfTruck() {
        return this.CQtyOfTruck;
    }

    public void setCQtyOfTruck(String CQtyOfTruck) {
        this.CQtyOfTruck = CQtyOfTruck;
    }

    public String getCQtyOfDox() {
        return this.CQtyOfDox;
    }

    public void setCQtyOfDox(String CQtyOfDox) {
        this.CQtyOfDox = CQtyOfDox;
    }

    public String getCDn1Remarks() {
        return this.CDn1Remarks;
    }

    public void setCDn1Remarks(String CDn1Remarks) {
        this.CDn1Remarks = CDn1Remarks;
    }

    public Timestamp getCArrivalDate2() {
        return this.CArrivalDate2;
    }

    public void setCArrivalDate2(Timestamp CArrivalDate2) {
        this.CArrivalDate2 = CArrivalDate2;
    }

    public String getCNoticeRemarks() {
        return this.CNoticeRemarks;
    }

    public void setCNoticeRemarks(String CNoticeRemarks) {
        this.CNoticeRemarks = CNoticeRemarks;
    }

    public Short getCNoticeFlag() {
        return this.CNoticeFlag;
    }

    public void setCNoticeFlag(Short CNoticeFlag) {
        this.CNoticeFlag = CNoticeFlag;
    }

    public String getCNoticeReply() {
        return this.CNoticeReply;
    }

    public void setCNoticeReply(String CNoticeReply) {
        this.CNoticeReply = CNoticeReply;
    }

    public String getCCdno() {
        return this.CCdno;
    }

    public void setCCdno(String CCdno) {
        this.CCdno = CCdno;
    }

    public Timestamp getCCdDate() {
        return this.CCdDate;
    }

    public void setCCdDate(Timestamp CCdDate) {
        this.CCdDate = CCdDate;
    }

    public Timestamp getCCostDate() {
        return this.CCostDate;
    }

    public void setCCostDate(Timestamp CCostDate) {
        this.CCostDate = CCostDate;
    }

    public String getCVessel() {
        return this.CVessel;
    }

    public void setCVessel(String CVessel) {
        this.CVessel = CVessel;
    }

    public Double getCExchange() {
        return this.CExchange;
    }

    public void setCExchange(Double CExchange) {
        this.CExchange = CExchange;
    }

    public String getCRefno() {
        return this.CRefno;
    }

    public void setCRefno(String CRefno) {
        this.CRefno = CRefno;
    }

    public Double getCDepositAmount() {
        return this.CDepositAmount;
    }

    public void setCDepositAmount(Double CDepositAmount) {
        this.CDepositAmount = CDepositAmount;
    }

    public Double getCCashReceived() {
        return this.CCashReceived;
    }

    public void setCCashReceived(Double CCashReceived) {
        this.CCashReceived = CCashReceived;
    }

    public Short getCAcNotice() {
        return this.CAcNotice;
    }

    public void setCAcNotice(Short CAcNotice) {
        this.CAcNotice = CAcNotice;
    }

    public Timestamp getCNoticeLast() {
        return this.CNoticeLast;
    }

    public void setCNoticeLast(Timestamp CNoticeLast) {
        this.CNoticeLast = CNoticeLast;
    }

    public Short getCAddGroup() {
        return this.CAddGroup;
    }

    public void setCAddGroup(Short CAddGroup) {
        this.CAddGroup = CAddGroup;
    }

    public Integer getCNoticeLastUserid() {
        return this.CNoticeLastUserid;
    }

    public void setCNoticeLastUserid(Integer CNoticeLastUserid) {
        this.CNoticeLastUserid = CNoticeLastUserid;
    }

    public Integer getCNoticeLastUserid2() {
        return this.CNoticeLastUserid2;
    }

    public void setCNoticeLastUserid2(Integer CNoticeLastUserid2) {
        this.CNoticeLastUserid2 = CNoticeLastUserid2;
    }

    public Integer getCAsk() {
        return this.CAsk;
    }

    public void setCAsk(Integer CAsk) {
        this.CAsk = CAsk;
    }

    public Integer getCAnswer() {
        return this.CAnswer;
    }

    public void setCAnswer(Integer CAnswer) {
        this.CAnswer = CAnswer;
    }

    public String getCAskRemarks() {
        return this.CAskRemarks;
    }

    public void setCAskRemarks(String CAskRemarks) {
        this.CAskRemarks = CAskRemarks;
    }

    public String getCAnswerRemarks() {
        return this.CAnswerRemarks;
    }

    public void setCAnswerRemarks(String CAnswerRemarks) {
        this.CAnswerRemarks = CAnswerRemarks;
    }

    public Integer getCAskNotice() {
        return this.CAskNotice;
    }

    public void setCAskNotice(Integer CAskNotice) {
        this.CAskNotice = CAskNotice;
    }

    public Timestamp getCAskDate() {
        return this.CAskDate;
    }

    public void setCAskDate(Timestamp CAskDate) {
        this.CAskDate = CAskDate;
    }

    public Timestamp getCCashDate() {
        return this.CCashDate;
    }

    public void setCCashDate(Timestamp CCashDate) {
        this.CCashDate = CCashDate;
    }

    public Double getCCashMoney1() {
        return this.CCashMoney1;
    }

    public void setCCashMoney1(Double CCashMoney1) {
        this.CCashMoney1 = CCashMoney1;
    }

    public Double getCCashMoney2() {
        return this.CCashMoney2;
    }

    public void setCCashMoney2(Double CCashMoney2) {
        this.CCashMoney2 = CCashMoney2;
    }

    public Double getCCashMoney4() {
        return this.CCashMoney4;
    }

    public void setCCashMoney4(Double CCashMoney4) {
        this.CCashMoney4 = CCashMoney4;
    }

    public Double getCCostSum1() {
        return this.CCostSum1;
    }

    public void setCCostSum1(Double CCostSum1) {
        this.CCostSum1 = CCostSum1;
    }

    public Double getCCostSum2() {
        return this.CCostSum2;
    }

    public void setCCostSum2(Double CCostSum2) {
        this.CCostSum2 = CCostSum2;
    }

    public Integer getCLockBy() {
        return this.CLockBy;
    }

    public void setCLockBy(Integer CLockBy) {
        this.CLockBy = CLockBy;
    }

    public Set getTJiezhuans() {
        return this.TJiezhuans;
    }

    public void setTJiezhuans(Set TJiezhuans) {
        this.TJiezhuans = TJiezhuans;
    }

    public Set getTCashRates() {
        return this.TCashRates;
    }

    public void setTCashRates(Set TCashRates) {
        this.TCashRates = TCashRates;
    }

    public Set getTCashs() {
        return this.TCashs;
    }

    public void setTCashs(Set TCashs) {
        this.TCashs = TCashs;
    }

    public Set getTServices() {
        return this.TServices;
    }

    public void setTServices(Set TServices) {
        this.TServices = TServices;
    }

    public Set getTBusinLogs() {
        return this.TBusinLogs;
    }

    public void setTBusinLogs(Set TBusinLogs) {
        this.TBusinLogs = TBusinLogs;
    }

    public Set getTContainers() {
        return this.TContainers;
    }

    public void setTContainers(Set TContainers) {
        this.TContainers = TContainers;
    }

    public Set getTBiaojis() {
        return this.TBiaojis;
    }

    public void setTBiaojis(Set TBiaojis) {
        this.TBiaojis = TBiaojis;
    }

    public Set getTCosts() {
        return this.TCosts;
    }

    public void setTCosts(Set TCosts) {
        this.TCosts = TCosts;
    }

	public String getCCbm() {
		return CCbm;
	}

	public void setCCbm(String cCbm) {
		CCbm = cCbm;
	}

	public String getCPkg() {
		return CPkg;
	}

	public void setCPkg(String cPkg) {
		CPkg = cPkg;
	}

	public String getCWeight() {
		return CWeight;
	}

	public void setCWeight(String cWeight) {
		CWeight = cWeight;
	}
    
}

