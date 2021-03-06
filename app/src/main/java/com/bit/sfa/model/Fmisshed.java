package com.bit.sfa.model;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class Fmisshed {
    public String RefNo;
    public String TxnDate;
    public String ManuRef;
    public String CostCode;
    public String LocCode;
    public String DebCodeM;
    public String RepCodeM;
    public String Remarks;
    public String TxnType;
    public String TotalAmt;
    public String GIType;
    public String GITypeS;
    public String Prtcopy;
    public String GlPost;
    public String GlBatch;
    public String AddUser;
    public String AddDate;
    public String AddMach;
    public String RecordId;
    public String IsIssue;// 0 =  not issue, 1 =  ActiveIssue, 2 =  syncedIssue

    public String getIsIssue() {
        return IsIssue;
    }

    public void setIsIssue(String isIssue) {
        IsIssue = isIssue;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getTxnDate() {
        return TxnDate;
    }

    public void setTxnDate(String txnDate) {
        TxnDate = txnDate;
    }

    public String getManuRef() {
        return ManuRef;
    }

    public void setManuRef(String manuRef) {
        ManuRef = manuRef;
    }

    public String getCostCode() {
        return CostCode;
    }

    public void setCostCode(String costCode) {
        CostCode = costCode;
    }

    public String getLocCode() {
        return LocCode;
    }

    public void setLocCode(String locCode) {
        LocCode = locCode;
    }

    public String getDebCodeM() {
        return DebCodeM;
    }

    public void setDebCodeM(String debCodeM) {
        DebCodeM = debCodeM;
    }

    public String getRepCodeM() {
        return RepCodeM;
    }

    public void setRepCodeM(String repCodeM) {
        RepCodeM = repCodeM;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getTxnType() {
        return TxnType;
    }

    public void setTxnType(String txnType) {
        TxnType = txnType;
    }

    public String getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        TotalAmt = totalAmt;
    }

    public String getGIType() {
        return GIType;
    }

    public void setGIType(String GIType) {
        this.GIType = GIType;
    }

    public String getGITypeS() {
        return GITypeS;
    }

    public void setGITypeS(String GITypeS) {
        this.GITypeS = GITypeS;
    }

    public String getPrtcopy() {
        return Prtcopy;
    }

    public void setPrtcopy(String prtcopy) {
        Prtcopy = prtcopy;
    }

    public String getGlPost() {
        return GlPost;
    }

    public void setGlPost(String glPost) {
        GlPost = glPost;
    }

    public String getGlBatch() {
        return GlBatch;
    }

    public void setGlBatch(String glBatch) {
        GlBatch = glBatch;
    }

    public String getAddUser() {
        return AddUser;
    }

    public void setAddUser(String addUser) {
        AddUser = addUser;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String addDate) {
        AddDate = addDate;
    }

    public String getAddMach() {
        return AddMach;
    }

    public void setAddMach(String addMach) {
        AddMach = addMach;
    }

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }

    @Override
    public String toString() {
        return "RefNo='" + RefNo + '\'' +
                ", Remarks='" + Remarks + '\'';
    }
}
