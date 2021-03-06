package com.bit.sfa.model;

import java.util.ArrayList;

/**
 * Created by Sathiyaraja on 6/20/2018.
 */

public class PreSalesMapper {
    private String ConsoleDB;
    private String DistDB;
    private String SALEREP_DEALCODE;
    private String SALEREP_AREACODE;
    private String FORDHED_ID;
    private String FORDHED_REFNO;
    private String FORDHED_ADD_DATE;
    private String FORDHED_ADD_MACH;
    private String FORDHED_ADD_USER;
    private String FORDHED_APP_DATE;
    private String FORDHED_APPSTS;
    private String FORDHED_APP_USER;
    private String FORDHED_BP_TOTAL_DIS;
    private String FORDHED_B_TOTAL_AMT;
    private String FORDHED_B_TOTAL_DIS;
    private String FORDHED_B_TOTAL_TAX;
    private String FORDHED_COST_CODE;
    private String FORDHED_CUR_CODE;
    private String FORDHED_CUR_RATE;
    private String FORDHED_DEB_CODE;
    private String FORDHED_DIS_PER;
    private String FORDHED_START_TIME_SO;
    private String FORDHED_END_TIME_SO;
    private String FORDHED_LONGITUDE;
    private String FORDHED_LATITUDE;
    private String FORDHED_LOC_CODE;
    private String FORDHED_MANU_REF;
    private String FORDHED_RECORD_ID;
    private String FORDHED_REMARKS;
    private String FORDHED_REPCODE;
    private String FORDHED_TAX_REG;
    private String FORDHED_TIMESTAMP_COLUMN;
    private String FORDHED_TOTAL_AMT;
    private String FORDHED_TOTALDIS;
    private String FORDHED_TOTAL_TAX;
    private String FORDHED_TXN_TYPE;
    private String FORDHED_TXN_DATE;
    private String FORDHED_ADDRESS;
    private String FORDHED_TOTAL_ITM_DIS;
    private String FORDHED_TOT_MKR_AMT;
    private String FORDHED_IS_SYNCED;
    private String FORDHED_IS_ACTIVE;
    private String FORDHED_DELV_DATE;
    private String FORDHED_ROUTE_CODE;
    private boolean IS_SYNCED;
    private ArrayList<OrdDet> ordDet;
    private ArrayList<Fmisshed> issuList;
    private String FORDHED_HED_DIS_VAL;
    private String FORDHED_HED_DIS_PER_VAL;
    private String FORDHED_PAYMENT_TYPE;

    private String NextNumVal;

    public String getNextNumVal() {
        return NextNumVal;
    }

    public void setNextNumVal(String nextNumVal) {
        NextNumVal = nextNumVal;
    }

    public String getFORDHED_PAYMENT_TYPE() {
        return FORDHED_PAYMENT_TYPE;
    }

    public void setFORDHED_PAYMENT_TYPE(String fORDHED_PAYMENT_TYPE) {
        FORDHED_PAYMENT_TYPE = fORDHED_PAYMENT_TYPE;
    }

    public String getFORDHED_HED_DIS_VAL() {
        return FORDHED_HED_DIS_VAL;
    }

    public void setFORDHED_HED_DIS_VAL(String fORDHED_HED_DIS_VAL) {
        FORDHED_HED_DIS_VAL = fORDHED_HED_DIS_VAL;
    }

    public String getFORDHED_HED_DIS_PER_VAL() {
        return FORDHED_HED_DIS_PER_VAL;
    }

    public void setFORDHED_HED_DIS_PER_VAL(String fORDHED_HED_DIS_PER_VAL) {
        FORDHED_HED_DIS_PER_VAL = fORDHED_HED_DIS_PER_VAL;
    }



    public String getSALEREP_DEALCODE() {
        return SALEREP_DEALCODE;
    }

    public void setSALEREP_DEALCODE(String sALEREP_DEALCODE) {
        SALEREP_DEALCODE = sALEREP_DEALCODE;
    }

    public String getSALEREP_AREACODE() {
        return SALEREP_AREACODE;
    }

    public void setSALEREP_AREACODE(String sALEREP_AREACODE) {
        SALEREP_AREACODE = sALEREP_AREACODE;
    }

    public String getConsoleDB() {
        return ConsoleDB;
    }

    public void setConsoleDB(String consoleDB) {
        ConsoleDB = consoleDB;
    }

    public String getDistDB() {
        return DistDB;
    }

    public void setDistDB(String distDB) {
        DistDB = distDB;
    }

    public boolean isSynced() {
        return IS_SYNCED;
    }

    public void setSynced(boolean synced) {
        IS_SYNCED = synced;
    }

    public String getFORDHED_ID() {
        return FORDHED_ID;
    }

    public void setFORDHED_ID(String fORDHED_ID) {
        FORDHED_ID = fORDHED_ID;
    }

    public String getFORDHED_REFNO() {
        return FORDHED_REFNO;
    }

    public void setFORDHED_REFNO(String fORDHED_REFNO) {
        FORDHED_REFNO = fORDHED_REFNO;
    }

    public String getFORDHED_ADD_DATE() {
        return FORDHED_ADD_DATE;
    }

    public void setFORDHED_ADD_DATE(String fORDHED_ADD_DATE) {
        FORDHED_ADD_DATE = fORDHED_ADD_DATE;
    }

    public String getFORDHED_ADD_MACH() {
        return FORDHED_ADD_MACH;
    }

    public void setFORDHED_ADD_MACH(String fORDHED_ADD_MACH) {
        FORDHED_ADD_MACH = fORDHED_ADD_MACH;
    }

    public String getFORDHED_ADD_USER() {
        return FORDHED_ADD_USER;
    }

    public void setFORDHED_ADD_USER(String fORDHED_ADD_USER) {
        FORDHED_ADD_USER = fORDHED_ADD_USER;
    }

    public String getFORDHED_APP_DATE() {
        return FORDHED_APP_DATE;
    }

    public void setFORDHED_APP_DATE(String fORDHED_APP_DATE) {
        FORDHED_APP_DATE = fORDHED_APP_DATE;
    }

    public String getFORDHED_APPSTS() {
        return FORDHED_APPSTS;
    }

    public void setFORDHED_APPSTS(String fORDHED_APPSTS) {
        FORDHED_APPSTS = fORDHED_APPSTS;
    }

    public String getFORDHED_APP_USER() {
        return FORDHED_APP_USER;
    }

    public void setFORDHED_APP_USER(String fORDHED_APP_USER) {
        FORDHED_APP_USER = fORDHED_APP_USER;
    }

    public String getFORDHED_BP_TOTAL_DIS() {
        return FORDHED_BP_TOTAL_DIS;
    }

    public void setFORDHED_BP_TOTAL_DIS(String fORDHED_BP_TOTAL_DIS) {
        FORDHED_BP_TOTAL_DIS = fORDHED_BP_TOTAL_DIS;
    }

    public String getFORDHED_B_TOTAL_AMT() {
        return FORDHED_B_TOTAL_AMT;
    }

    public void setFORDHED_B_TOTAL_AMT(String fORDHED_B_TOTAL_AMT) {
        FORDHED_B_TOTAL_AMT = fORDHED_B_TOTAL_AMT;
    }

    public String getFORDHED_B_TOTAL_DIS() {
        return FORDHED_B_TOTAL_DIS;
    }

    public void setFORDHED_B_TOTAL_DIS(String fORDHED_B_TOTAL_DIS) {
        FORDHED_B_TOTAL_DIS = fORDHED_B_TOTAL_DIS;
    }

    public String getFORDHED_B_TOTAL_TAX() {
        return FORDHED_B_TOTAL_TAX;
    }

    public void setFORDHED_B_TOTAL_TAX(String fORDHED_B_TOTAL_TAX) {
        FORDHED_B_TOTAL_TAX = fORDHED_B_TOTAL_TAX;
    }

    public String getFORDHED_COST_CODE() {
        return FORDHED_COST_CODE;
    }

    public void setFORDHED_COST_CODE(String fORDHED_COST_CODE) {
        FORDHED_COST_CODE = fORDHED_COST_CODE;
    }

    public String getFORDHED_CUR_CODE() {
        return FORDHED_CUR_CODE;
    }

    public void setFORDHED_CUR_CODE(String fORDHED_CUR_CODE) {
        FORDHED_CUR_CODE = fORDHED_CUR_CODE;
    }

    public String getFORDHED_CUR_RATE() {
        return FORDHED_CUR_RATE;
    }

    public void setFORDHED_CUR_RATE(String fORDHED_CUR_RATE) {
        FORDHED_CUR_RATE = fORDHED_CUR_RATE;
    }

    public String getFORDHED_DEB_CODE() {
        return FORDHED_DEB_CODE;
    }

    public void setFORDHED_DEB_CODE(String fORDHED_DEB_CODE) {
        FORDHED_DEB_CODE = fORDHED_DEB_CODE;
    }

    public String getFORDHED_DIS_PER() {
        return FORDHED_DIS_PER;
    }

    public void setFORDHED_DIS_PER(String fORDHED_DIS_PER) {
        FORDHED_DIS_PER = fORDHED_DIS_PER;
    }

    public String getFORDHED_START_TIME_SO() {
        return FORDHED_START_TIME_SO;
    }

    public void setFORDHED_START_TIME_SO(String fORDHED_START_TIME_SO) {
        FORDHED_START_TIME_SO = fORDHED_START_TIME_SO;
    }

    public String getFORDHED_END_TIME_SO() {
        return FORDHED_END_TIME_SO;
    }

    public void setFORDHED_END_TIME_SO(String fORDHED_END_TIME_SO) {
        FORDHED_END_TIME_SO = fORDHED_END_TIME_SO;
    }

    public String getFORDHED_LONGITUDE() {
        return FORDHED_LONGITUDE;
    }

    public void setFORDHED_LONGITUDE(String fORDHED_LONGITUDE) {
        FORDHED_LONGITUDE = fORDHED_LONGITUDE;
    }

    public String getFORDHED_LATITUDE() {
        return FORDHED_LATITUDE;
    }

    public void setFORDHED_LATITUDE(String fORDHED_LATITUDE) {
        FORDHED_LATITUDE = fORDHED_LATITUDE;
    }

    public String getFORDHED_LOC_CODE() {
        return FORDHED_LOC_CODE;
    }

    public void setFORDHED_LOC_CODE(String fORDHED_LOC_CODE) {
        FORDHED_LOC_CODE = fORDHED_LOC_CODE;
    }

    public String getFORDHED_MANU_REF() {
        return FORDHED_MANU_REF;
    }

    public void setFORDHED_MANU_REF(String fORDHED_MANU_REF) {
        FORDHED_MANU_REF = fORDHED_MANU_REF;
    }

    public String getFORDHED_RECORD_ID() {
        return FORDHED_RECORD_ID;
    }

    public void setFORDHED_RECORD_ID(String fORDHED_RECORD_ID) {
        FORDHED_RECORD_ID = fORDHED_RECORD_ID;
    }

    public String getFORDHED_REMARKS() {
        return FORDHED_REMARKS;
    }

    public void setFORDHED_REMARKS(String fORDHED_REMARKS) {
        FORDHED_REMARKS = fORDHED_REMARKS;
    }

    public String getFORDHED_REPCODE() {
        return FORDHED_REPCODE;
    }

    public void setFORDHED_REPCODE(String fORDHED_REPCODE) {
        FORDHED_REPCODE = fORDHED_REPCODE;
    }

    public String getFORDHED_TAX_REG() {
        return FORDHED_TAX_REG;
    }

    public void setFORDHED_TAX_REG(String fORDHED_TAX_REG) {
        FORDHED_TAX_REG = fORDHED_TAX_REG;
    }

    public String getFORDHED_TIMESTAMP_COLUMN() {
        return FORDHED_TIMESTAMP_COLUMN;
    }

    public void setFORDHED_TIMESTAMP_COLUMN(String fORDHED_TIMESTAMP_COLUMN) {
        FORDHED_TIMESTAMP_COLUMN = fORDHED_TIMESTAMP_COLUMN;
    }

    public String getFORDHED_TOTAL_AMT() {
        return FORDHED_TOTAL_AMT;
    }

    public void setFORDHED_TOTAL_AMT(String fORDHED_TOTAL_AMT) {
        FORDHED_TOTAL_AMT = fORDHED_TOTAL_AMT;
    }

    public String getFORDHED_TOTALDIS() {
        return FORDHED_TOTALDIS;
    }

    public void setFORDHED_TOTALDIS(String fORDHED_TOTALDIS) {
        FORDHED_TOTALDIS = fORDHED_TOTALDIS;
    }

    public String getFORDHED_TOTAL_TAX() {
        return FORDHED_TOTAL_TAX;
    }

    public void setFORDHED_TOTAL_TAX(String fORDHED_TOTAL_TAX) {
        FORDHED_TOTAL_TAX = fORDHED_TOTAL_TAX;
    }

    public String getFORDHED_TXN_TYPE() {
        return FORDHED_TXN_TYPE;
    }

    public void setFORDHED_TXN_TYPE(String fORDHED_TXN_TYPE) {
        FORDHED_TXN_TYPE = fORDHED_TXN_TYPE;
    }

    public String getFORDHED_TXN_DATE() {
        return FORDHED_TXN_DATE;
    }

    public void setFORDHED_TXN_DATE(String fORDHED_TXN_DATE) {
        FORDHED_TXN_DATE = fORDHED_TXN_DATE;
    }

    public String getFORDHED_ADDRESS() {
        return FORDHED_ADDRESS;
    }

    public void setFORDHED_ADDRESS(String fORDHED_ADDRESS) {
        FORDHED_ADDRESS = fORDHED_ADDRESS;
    }

    public String getFORDHED_TOTAL_ITM_DIS() {
        return FORDHED_TOTAL_ITM_DIS;
    }

    public void setFORDHED_TOTAL_ITM_DIS(String fORDHED_TOTAL_ITM_DIS) {
        FORDHED_TOTAL_ITM_DIS = fORDHED_TOTAL_ITM_DIS;
    }

    public String getFORDHED_TOT_MKR_AMT() {
        return FORDHED_TOT_MKR_AMT;
    }

    public void setFORDHED_TOT_MKR_AMT(String fORDHED_TOT_MKR_AMT) {
        FORDHED_TOT_MKR_AMT = fORDHED_TOT_MKR_AMT;
    }

    public String getFORDHED_IS_SYNCED() {
        return FORDHED_IS_SYNCED;
    }

    public void setFORDHED_IS_SYNCED(String fORDHED_IS_SYNCED) {
        FORDHED_IS_SYNCED = fORDHED_IS_SYNCED;
    }

    public String getFORDHED_IS_ACTIVE() {
        return FORDHED_IS_ACTIVE;
    }

    public void setFORDHED_IS_ACTIVE(String fORDHED_IS_ACTIVE) {
        FORDHED_IS_ACTIVE = fORDHED_IS_ACTIVE;
    }

    public String getFORDHED_DELV_DATE() {
        return FORDHED_DELV_DATE;
    }

    public void setFORDHED_DELV_DATE(String fORDHED_DELV_DATE) {
        FORDHED_DELV_DATE = fORDHED_DELV_DATE;
    }

    public String getFORDHED_ROUTE_CODE() {
        return FORDHED_ROUTE_CODE;
    }

    public void setFORDHED_ROUTE_CODE(String fORDHED_ROUTE_CODE) {
        FORDHED_ROUTE_CODE = fORDHED_ROUTE_CODE;
    }

    public ArrayList<OrdDet> getOrdDet() {
        return ordDet;
    }

    public void setOrdDet(ArrayList<OrdDet> ordDet) {
        this.ordDet = ordDet;
    }

    public boolean isIS_SYNCED() {
        return IS_SYNCED;
    }

    public void setIS_SYNCED(boolean IS_SYNCED) {
        this.IS_SYNCED = IS_SYNCED;
    }

    public ArrayList<Fmisshed> getIssuList() {
        return issuList;
    }

    public void setIssuList(ArrayList<Fmisshed> issuList) {
        this.issuList = issuList;
    }
}
