/**
 * *****************************************************
 * Copyright (C) Kongtrolink techology Co.ltd - All Rights Reserved
 *
 * This file is part of Kongtrolink techology Co.Ltd property.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 ******************************************************
 */
package com.krry.entity;

import com.krry.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mosaico
 */
public class Alarm implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4839953781820463795L;
	private String _id;
    private String state;	    // 实时性状态
    private String value;
    private String level;
    private String details;
    private Date tReport;
    private Date tRecover;
    private FacadeView signal;
    private FacadeView device;
    private FacadeView fsu;
    private FacadeView site;
    private String tierCode;
    private TierInfo[] siteTier;
    private int alarmNo;    // 告警序号，平台生成

    public static final int TYPE_SYS = 0;
    public static final int TYPE_FSU = 1;
    public static final int TYPE_DEV = 2;
    private int type = TYPE_DEV;   // 告警类型：0 - 系统告警；1 - 监控单元告警；2 - 设备告警
    
    private Date tCheck;	    // 确认时间
    private FacadeView checkOperator;   // 确认操作人
    private Date tClear;	    // 清除时间
    private FacadeView clearOperator;	// 清除操作人
    private Boolean shield;	    // 是否被屏蔽
    private String operationState;	    // FSU运行状态\
    private Date delayTime;   //离线告警延迟
    private int delayMsgInform = 0;        //告警延迟短信通知，默认为0，表示不需要通知，为1表示需要通知。用于FSU离线告警和普通告警延迟
    private int delayNoticeInform = 0;      //延迟告警公告通知
    private String workId;                  //工单id
    private String workCode;                //工单流水号
    private String checkIdea;               //确认意见

    //PDF导出字段
    private String signalName;              //告警名称
    private String siteName;                //站点名称
    private String deviceName;              //设备名称
    private String reportStr;              //上报时间
    private String recoverStr;             //消除时间
    private String siteString;              //站点分级
    private String company; //归属公司

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSiteString() {
        return siteString;
    }

    public void setSiteString(String siteString) {
        this.siteString = siteString;
    }

    public String getReportStr() {
        return reportStr;
    }

    public void setReportStr(String reportStr) {
        this.reportStr = reportStr;
    }

    public String getRecoverStr() {
        return recoverStr;
    }

    public void setRecoverStr(String recoverStr) {
        this.recoverStr = recoverStr;
    }

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCheckIdea() {
        return checkIdea;
    }

    public void setCheckIdea(String checkIdea) {
        this.checkIdea = checkIdea;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    private String hadSendWork = EnumWorkStatus.NOSEDN.getName();    //是否派单，新告警默认未派单，AlarmWorkConfigService中可以少操作一步
    private String workStatus = EnumWorkStatus.NOSEDN.getName();              //工单状态未派单


    public int getDelayMsgInform() {
        return delayMsgInform;
    }

    public void setDelayMsgInform(int delayMsgInform) {
        this.delayMsgInform = delayMsgInform;
    }

    public int getDelayNoticeInform() {
        return delayNoticeInform;
    }

    public void setDelayNoticeInform(int delayNoticeInform) {
        this.delayNoticeInform = delayNoticeInform;
    }

    public Date getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Date delayTime) {
        this.delayTime = delayTime;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date gettReport() {
        return tReport;
    }

    public void settReport(Date tReport) {
        this.tReport = tReport;
    }
    
    public void settRecord(String tRecord) {
        this.tReport = DateUtil.getInstance().parse(tRecord);
    }

    public Date gettRecover() {
        return tRecover;
    }

    public void settRecover(Date tRecover) {
        this.tRecover = tRecover;
    }
    
    public void settRecover(String tRecover) {
        this.tRecover = DateUtil.getInstance().parse(tRecover);
    }
    
    public FacadeView getSignal() {
        return signal;
    }

    public void setSignal(FacadeView signal) {
        this.signal = signal;
    }

    public FacadeView getDevice() {
        return device;
    }

    public void setDevice(FacadeView device) {
        this.device = device;
    }

    public FacadeView getFsu() {
        return fsu;
    }

    public void setFsu(FacadeView fsu) {
        this.fsu = fsu;
    }

    public FacadeView getSite() {
        return site;
    }

    public void setSite(FacadeView site) {
        this.site = site;
    }

    public String getTierCode() {
        return tierCode;
    }

    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }

    public TierInfo[] getSiteTier() {
        return siteTier;
    }

    public void setSiteTier(TierInfo[] siteTier) {
        this.siteTier = siteTier;
    }

    public String getSiteTierString() {
        if (siteTier == null) {
            return null;
        }
        
        String strSiteTier = "";
        TierInfo tempTierInfo;
        for (int i = 0; i < siteTier.length; i ++) {
            tempTierInfo = siteTier[i];
            strSiteTier += tempTierInfo.getName();
            if (i == siteTier.length - 1) {
                break;
            }
            strSiteTier += "-";
        }
        return strSiteTier;
    }

    public String checkSiteTierString(){
        if (siteTier == null){
            return null;
        }

        String strSiteTier = "";
        TierInfo tempTierInfo;
        if (siteTier.length == 1 || siteTier.length == 2){
            for (int i = 0; i < siteTier.length; i++) {
                tempTierInfo = siteTier[i];
                strSiteTier += tempTierInfo.getName();
                if (i == siteTier.length - 1) {
                    break;
                }
                strSiteTier += "-";
            }
        }else {
            for (int i = 1; i < siteTier.length; i ++) {
                tempTierInfo = siteTier[i];
                strSiteTier += tempTierInfo.getName();
                if (i == siteTier.length - 1) {
                    break;
                }
                strSiteTier += "-";
            }
        }

        return strSiteTier;
    }

    public int getAlarmNo() {
        return alarmNo;
    }

    public void setAlarmNo(int alarmNo) {
        this.alarmNo = alarmNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date gettCheck() {
        return tCheck;
    }

    public void settCheck(Date tCheck) {
        this.tCheck = tCheck;
    }

    public FacadeView getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(FacadeView checkOperator) {
        this.checkOperator = checkOperator;
    }

    public Date gettClear() {
        return tClear;
    }

    public void settClear(Date tClear) {
        this.tClear = tClear;
    }

    public FacadeView getClearOperator() {
        return clearOperator;
    }

    public void setClearOperator(FacadeView clearOperator) {
        this.clearOperator = clearOperator;
    }

    public Boolean isShield() {
        return shield == null ? false : shield;
    }

    public void setShield(Boolean shield) {
        this.shield = shield;
    }

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }

    @Override
    public String toString() {
        return "Alarm{" + "_id=" + _id + ", state=" + state + ", value=" + value + ", level=" + level + ", details=" + details + ", tReport=" + tReport + ", tRecover=" + tRecover + ", signal=" + signal + ", device=" + device + ", site=" + site + ", tierCode=" + tierCode + ", siteTier=" + siteTier + ", alarmNo=" + alarmNo + ", type=" + type + ", tCheck=" + tCheck + ", checkOperator=" + checkOperator + ", tClear=" + tClear + ", clearOperator=" + clearOperator + ", shield=" + shield + '}';
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHadSendWork() {
        return hadSendWork;
    }

    public void setHadSendWork(String hadSendWork) {
        this.hadSendWork = hadSendWork;
    }
}
