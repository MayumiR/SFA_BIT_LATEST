package com.bit.sfa.model;

/**
 * Created by Sathiyaraja on 7/23/2018.
 */

public class FmDebtor {

    public String DebCodeM;

    public String DebNameM;

    public String RepCodem;

    public String AddUser;

    public String AddDate;

    public String AddMach;

    public String Recordid;

    public String RouteCode;

    public String getRouteCode() {
        return RouteCode;
    }

    public void setRouteCode(String routeCode) {
        RouteCode = routeCode;
    }

    public String getDebCodeM() {
        return DebCodeM;
    }

    public void setDebCodeM(String debCodeM) {
        DebCodeM = debCodeM;
    }

    public String getDebNameM() {
        return DebNameM;
    }

    public void setDebNameM(String debNameM) {
        DebNameM = debNameM;
    }

    public String getRepCodem() {
        return RepCodem;
    }

    public void setRepCodem(String repCodem) {
        RepCodem = repCodem;
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

    public String getRecordid() {
        return Recordid;
    }

    public void setRecordid(String recordid) {
        Recordid = recordid;
    }

    @Override
    public String toString() {
        return  DebNameM ;
    }
}
