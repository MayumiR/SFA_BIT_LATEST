package com.bit.sfa.DataControl;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bit.sfa.model.CompanyBranch;
import com.bit.sfa.model.CompanySetting;
import com.bit.sfa.model.Control;
import com.bit.sfa.model.Expense;
import com.bit.sfa.model.FmDebtor;
import com.bit.sfa.model.FmItems;
import com.bit.sfa.model.FmSalRep;
import com.bit.sfa.model.Fmisshed;
import com.bit.sfa.model.ItemLoc;
import com.bit.sfa.model.ItemPri;
import com.bit.sfa.model.Items;
import com.bit.sfa.model.Reason;
import com.bit.sfa.model.Route;
import com.bit.sfa.model.RouteDet;
import com.bit.sfa.model.Town;
import com.bit.sfa.model.Type;
import com.bit.sfa.Settings.TaskType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Rashmi.
 */

public class Downloader extends AsyncTask<Void, Integer, String> {

    Context context;
    DownloadTaskListener taskListener;
    String ConnectionURL, downLoadURL;
    TaskType taskType;
    String downloadingDataType = "";
    String TAG = "SFA";
    int totalRecords = 0;
    ProgressDialog progressDialog;

    // WakeLock syncWL;

    public Downloader(Context context, DownloadTaskListener taskListener, TaskType taskType, String ConnURL, String downLoadURL) {

        this.context = context;
        this.taskListener = taskListener;
        this.ConnectionURL = ConnURL;
        this.taskType = taskType;
        this.downLoadURL = downLoadURL;

        this.totalRecords = 0;

        switch (taskType) {
            case DATABASENAME:
                downloadingDataType = "Main Server";
                break;

            case FITEMLOC:
                downloadingDataType = "FItemLoc";
                break;


            case FITENRDET:
                downloadingDataType = "FItenrDet";
                break;

            case FITENRHED:
                downloadingDataType = "fItenrHed";
                break;

            case FITEDEBDET:
                downloadingDataType = "fIteDebDet";
                break;

            case FITEMPRI:
                downloadingDataType = "fItemPri";
                break;

            case FITEMS:
                downloadingDataType = "fItems";
                break;

            case FMDEBTOR:
                downloadingDataType = "Fmdebtor";
                break;

            case FCONTROL:
                downloadingDataType = "fControl";
                break;

            case FCOMPANYSETTING:
                downloadingDataType = "fCompanySetting";
                break;

            case FAREA:
                downloadingDataType = "fArea";
                break;

//            case FLOCATIONS:
//                downloadingDataType = "fLocations";
//                break;

            case FCOMPANYBRANCH:
                downloadingDataType = "FCompanyBranch";
                break;

            case FMSALREP:
                downloadingDataType = "fmSalRep";
                break;

            case FREASON:
                downloadingDataType = "fReason";
                break;

            case FROUTE:
                downloadingDataType = "fRoute";
                break;


            case FEXPENSE:
                downloadingDataType = "fExpense";
                break;

            case FTOWN:
                downloadingDataType = "fTown";
                break;
            case FROUTEDET:
                downloadingDataType = "fRouteDet";
                break;


            case FTYPE:
                downloadingDataType = "fType";
                break;

            case FGROUP:
                downloadingDataType = "fGroup";
                break;

            case FBRAND:
                downloadingDataType = "fbrand";
                break;

            case FINVDETL3:
                downloadingDataType = "FinvDetL3";
                break;

            case FCOST:
                downloadingDataType = "FCost";
                break;

            case FREPLOC:
                downloadingDataType = "FRepLoc";
                break;
            case FMITEMS:
                downloadingDataType = "FMITEMS";
                break;

            case FORDSTAT:
                downloadingDataType = "FOrdStat";
                break;
            case FREPDALO:
                downloadingDataType = "FrepDalo";
                break;
            default:
                break;
        }
    }

    // @Override
    // protected void onPreExecute() {
    // super.onPreExecute();
    // String titleMsg = "Downloading " + downloadingDataType + " data";
    // progressDialog = ProgressDialog.show(context, "Wait", titleMsg);
    //
    // }

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        // progressDialog.setTitle("Downloading...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {


        String resultStr = "";

        int recordCount = 0;
        publishProgress(recordCount);

        try {

            URL json = new URL(ConnectionURL + downLoadURL);

            URLConnection jc = json.openConnection();
            BufferedReader readerfdblist = new BufferedReader(new InputStreamReader(jc.getInputStream()));

            String line = readerfdblist.readLine();
            resultStr = line;
            JSONObject jsonResponse = new JSONObject(line);
            System.out.println("jsonResponse" + jsonResponse.toString());


            switch (taskType) {
                case DATABASENAME:
                    // your work here
                    break;

                case FITEMLOC: {
                    JSONArray jsonArray = jsonResponse.getJSONArray("fmItemLocResult");
                    ArrayList<ItemLoc> list = new ArrayList<ItemLoc>();
                    totalRecords = jsonArray.length();

                   // ItemLocDS ds = new ItemLocDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jObject = (JSONObject) jsonArray.get(i);
                        ItemLoc loc = new ItemLoc();

                        // list.clear();
                        //loc based

                        loc.setFITEMLOC_ITEM_CODE(jObject.getString("ItemCode"));
                        loc.setFITEMLOC_LOC_CODE(jObject.getString("LocCode"));
                        loc.setFITEMLOC_QOH(jObject.getString("QOH"));

                        list.add(loc);

                        ++recordCount;
                        publishProgress(recordCount);


                    }

                   // Log.v("createOrUpdateItemLoc", "Result : " + ds.createOrUpdateItemLoc(list));

                }

                break;


                case FITEMPRI: {
                    ArrayList<ItemPri> list = new ArrayList<ItemPri>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fItemPriResult");
                    Log.v(TAG, "Array Length ItemPri DB :" + jsonArray.length());
                    totalRecords = jsonArray.length();
                    // String Type="0";

                    ItemPriDS ds = new ItemPriDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {

                        ItemPri pri = new ItemPri();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        // list.clear();

                        pri.setFITEMPRI_ADD_MACH(jObject.getString("AddMach"));
                        pri.setFITEMPRI_ADD_USER(jObject.getString("AddUser"));
                        pri.setFITEMPRI_ITEM_CODE(jObject.getString("ItemCode"));
                        pri.setFITEMPRI_PRICE(jObject.getString("Price"));
                        pri.setFITEMPRI_PRIL_CODE(jObject.getString("PrilCode"));
                        pri.setFITEMPRI_TXN_MACH(jObject.getString("TxnMach"));
                        pri.setFITEMPRI_TXN_USER(jObject.getString("Txnuser"));
                        pri.setFITEMPRI_COST_CODE(jObject.getString("CostCode"));

                        list.add(pri);

                        ++recordCount;
                        publishProgress(recordCount);

                    }

                    if (ds.createOrUpdateItemPri(list) > 0) {
                        Log.v("createOrUpdateItemPri", "Result : ItemPri Data Inserted successfully");
                    }

                }
                break;

                case FITEMS:
                    // downloadingDataType ="fItems";
                {
                    ArrayList<Items> list = new ArrayList<Items>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fItemsResult");
                    Log.v(TAG, "Array Length Items DB :" + jsonArray.length());
                    totalRecords = jsonArray.length();

                    ItemsDS ds = new ItemsDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {

                        Items itm = new Items();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        // list.clear();

                        itm.setFITEM_ADD_MATCH(jObject.getString("AddMach"));
                        itm.setFITEM_ADD_USER(jObject.getString("AddUser"));
                        itm.setFITEM_AVG_PRICE(jObject.getString("AvgPrice"));
                        itm.setFITEM_BRAND_CODE(jObject.getString("BrandCode"));
                        itm.setFITEM_GROUP_CODE(jObject.getString("GroupCode"));
                        itm.setFITEM_ITEM_CODE(jObject.getString("ItemCode"));
                        itm.setFITEM_ITEM_NAME(jObject.getString("ItemName"));
                        itm.setFITEM_ITEM_STATUS(jObject.getString("ItemStatus"));
                        itm.setFITEM_MUST_SALE(jObject.getString("MustSale"));
                        itm.setFITEM_NOU_CASE(jObject.getString("NOUCase"));
                        itm.setFITEM_ORD_SEQ(jObject.getString("OrdSeq"));
                        itm.setFITEM_PRIL_CODE(jObject.getString("PrilCode"));
                        itm.setFITEM_RE_ORDER_LVL(jObject.getString("ReOrderLvl"));
                        itm.setFITEM_RE_ORDER_QTY(jObject.getString("ReOrderQty"));
                        itm.setFITEM_TAX_COM_CODE(jObject.getString("TaxComCode"));
                        itm.setFITEM_TYPE_CODE(jObject.getString("TypeCode"));
                        itm.setFITEM_UNIT_CODE(jObject.getString("UnitCode"));
                        itm.setFITEM_VEN_P_CODE(jObject.getString("VenPcode"));
                        itm.setFITEM_CAT_CODE(jObject.getString("CatCode"));
                        itm.setFITEM_PACK(jObject.getString("Pack"));
                        itm.setFITEM_PACK_SIZE(jObject.getString("PackSize"));
                        itm.setFITEM_SUP_CODE(jObject.getString("SupCode"));
                        itm.setFITEM_MUST_FREE(jObject.getString("ChkMustFre"));

                        list.add(itm);

                        ++recordCount;
                        publishProgress(recordCount);

                    }

                    if (ds.createOrUpdateItems(list) > 0) {
                        Log.v("createOrUpdateItems", "Result : Items Data Inserted successfully");
                    }

                }
                break;


                case FCONTROL: {
                    ArrayList<Control> list = new ArrayList<Control>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fControlResult");
                    Log.v(TAG, "Array Length Control DB :" + jsonArray.length());
                    totalRecords = jsonArray.length();
                    String Type = "0";
                    for (int i = 0; i < jsonArray.length(); i++) {

                        Control ctrl = new Control();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        ctrl.setFCONTROL_COM_NAME(jObject.getString("ComName"));
                        ctrl.setFCONTROL_COM_ADD1(jObject.getString("ComAdd1"));
                        ctrl.setFCONTROL_COM_ADD2(jObject.getString("ComAdd2"));
                        ctrl.setFCONTROL_COM_ADD3(jObject.getString("ComAdd3"));
                        ctrl.setFCONTROL_COM_TEL1(jObject.getString("comtel1"));
                        ctrl.setFCONTROL_COM_TEL2(jObject.getString("comtel2"));
                        ctrl.setFCONTROL_COM_FAX(jObject.getString("comfax1"));
                        ctrl.setFCONTROL_COM_EMAIL(jObject.getString("comemail"));
                        ctrl.setFCONTROL_COM_WEB(jObject.getString("comweb"));
                        ctrl.setFCONTROL_BASECUR(jObject.getString("basecur"));
                        ctrl.setFCONTROL_CONAGE1(jObject.getString("conage1"));
                        ctrl.setFCONTROL_CONAGE2(jObject.getString("conage2"));
                        ctrl.setFCONTROL_CONAGE3(jObject.getString("conage3"));
                        ctrl.setFCONTROL_CONAGE4(jObject.getString("conage4"));
                        ctrl.setFCONTROL_CONAGE5(jObject.getString("conage5"));
                        ctrl.setFCONTROL_CONAGE6(jObject.getString("conage6"));
                        ctrl.setFCONTROL_CONAGE7(jObject.getString("conage7"));
                        ctrl.setFCONTROL_CONAGE8(jObject.getString("conage8"));
                        ctrl.setFCONTROL_CONAGE9(jObject.getString("conage9"));
                        ctrl.setFCONTROL_CONAGE10(jObject.getString("conage10"));
                        ctrl.setFCONTROL_CONAGE11(jObject.getString("conage11"));
                        ctrl.setFCONTROL_CONAGE12(jObject.getString("conage12"));
                        ctrl.setFCONTROL_CONAGE13(jObject.getString("conage13"));
                        ctrl.setFCONTROL_CONAGE14(jObject.getString("conage14"));
                        ctrl.setFCONTROL_SALESACC(jObject.getString("IntegSeqNo"));
                        ctrl.setFCONTROL_SYSTYPE(jObject.getString("SysType"));

                        Type = jObject.getString("SysType");

                        list.add(ctrl);

                        ++recordCount;
                        publishProgress(recordCount);
                    }

                    ControlDS ds = new ControlDS(context);
                    if (ds.createOrUpdateFControl(list) > 0) {

                        Log.v("createOrUpdateControl", "Result : Control Data Inserted successfully");

                        return "" + Type;
                    }
                }
                break;

                case FCOMPANYSETTING: {
                    ArrayList<CompanySetting> list = new ArrayList<CompanySetting>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fCompanySettingMResult");
                    Log.v(TAG, "Array Length CompanySetting DB :" + jsonArray.length());
                    totalRecords = jsonArray.length();

                    CompanySettingDS ds = new CompanySettingDS(context);


                    list.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {

                        CompanySetting setting = new CompanySetting();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        //list.clear();

                        setting.setFCOMPANYSETTING_CHAR_VAL(jObject.getString("cCharVal"));
                        setting.setFCOMPANYSETTING_COMPANY_CODE(jObject.getString("cCompanyCode"));
                        setting.setFCOMPANYSETTING_LOCATION_CHAR(jObject.getString("cLocationChar"));
                        setting.setFCOMPANYSETTING_REMARKS(jObject.getString("cRemarks"));
                        setting.setFCOMPANYSETTING_GRP(jObject.getString("cSettingGrp"));
                        setting.setFCOMPANYSETTING_SETTINGS_CODE(jObject.getString("cSettingsCode"));
                        setting.setFCOMPANYSETTING_NUM_VAL(jObject.getString("nNumVal"));
                        setting.setFCOMPANYSETTING_TYPE(jObject.getString("nType"));

                        list.add(setting);

                        ++recordCount;
                        publishProgress(recordCount);


                    }

                    if (ds.createOrUpdateFCompanySetting(list) > 0) {
                        Log.v("CompanySetting", "Result : CompanySetting Data Inserted successfully");
                    }

                }

                break;


                case FCOMPANYBRANCH: {
                    ArrayList<CompanyBranch> list = new ArrayList<CompanyBranch>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("FCompanyBranchResult");
                    Log.v(TAG, "Array Length CompanyBranch DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    CompanyBranchDS ds = new CompanyBranchDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        CompanyBranch branch = new CompanyBranch();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        branch.setFCOMPANYBRANCH_BRANCH_CODE(jObject.getString("BranchCode"));
                        branch.setFCOMPANYBRANCH_CSETTINGS_CODE(jObject.getString("cSettingsCode"));
                        branch.setFCOMPANYBRANCH_NNUM_VAL(jObject.getString("nNumVal"));
                        branch.setFCOMPANYBRANCH_NYEAR_VAL(jObject.getString("nYear"));
                        branch.setFCOMPANYBRANCH_NMONTH_VAL(jObject.getString("nMonth"));

                        list.add(branch);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateFCompanyBranch(list) > 0) {
                        Log.v("CompanyBranch", "Result : CompanyBranch Data Inserted successfully");
                    }
                }
                break;
                case FREASON: {
                    ArrayList<Reason> list = new ArrayList<Reason>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fReasonResult");
                    Log.v(TAG, "Array Length Reason DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    ReasonDS ds = new ReasonDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Reason reason = new Reason();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        reason.setFREASON_ADD_DATE(jObject.getString("AddDate"));
                        reason.setFREASON_ADD_MACH(jObject.getString("AddMach"));
                        reason.setFREASON_ADD_USER(jObject.getString("AddUser"));
                        reason.setFREASON_CODE(jObject.getString("ReaCode"));
                        reason.setFREASON_NAME(jObject.getString("ReaName"));
                        reason.setFREASON_REATCODE(jObject.getString("ReaTcode"));
                        reason.setFREASON_TYPE(jObject.getString("Type"));

                        list.add(reason);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateFReason(list) > 0) {
                        Log.v("CreateOrUpdateFReason", "Result : FReason Data Inserted successfully");
                    }

                }
                break;

                case FROUTE:

                {
                    ArrayList<Route> list = new ArrayList<Route>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fmRouteResult");
                    Log.v(TAG, "Array Length Route DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    RouteDS ds = new RouteDS(context);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Route route = new Route();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        route.setFROUTE_ADDDATE(jObject.getString("AddDate"));
                        route.setFROUTE_ADD_MACH(jObject.getString("AddMach"));
                        route.setFROUTE_ADD_USER(jObject.getString("AddUser"));
                        route.setFROUTE_AREACODE(jObject.getString("AreaCode"));
                        route.setFROUTE_DEALCODE(jObject.getString("DealCode"));
                        route.setFROUTE_FREQNO(jObject.getString("FreqNo"));
                        route.setFROUTE_KM(jObject.getString("Km"));
                        route.setFROUTE_MINPROCALL(jObject.getString("MinProcall"));
                        route.setFROUTE_RDALORATE(jObject.getString("RDAloRate"));
                        route.setFROUTE_RDTARGET(jObject.getString("RDTarget"));
                        route.setFROUTE_REMARKS(jObject.getString("Remarks"));
                        route.setFROUTE_REPCODE(jObject.getString("RepCode"));
                        route.setFROUTE_ROUTECODE(jObject.getString("RouteCode"));
                        route.setFROUTE_ROUTE_NAME(jObject.getString("RouteName"));
                        route.setFROUTE_STATUS(jObject.getString("Status"));
                        route.setFROUTE_TONNAGE(jObject.getString("Tonnage"));

                        list.add(route);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateFRoute(list) > 0) {
                        Log.v("CreateOrUpdateFRoute", "Result : Route Data Inserted successfully");
                    }
                }
                break;

//                case FBANK:
//                    // downloadingDataType="fBank";
//                {
//                    ArrayList<Bank> list = new ArrayList<Bank>();
//                    JSONArray jsonArray = jsonResponse.getJSONArray("fbankResult");
//                    Log.v(TAG, "Array Length Bank DB :" + jsonArray.length());
//
//                    totalRecords = jsonArray.length();
//
//                    BankDS ds = new BankDS(context);
//                    //Log.v(TAG, "Deleted Count: " + ds.deleteAll());
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Bank bank = new Bank();
//
//                        JSONObject jObject = (JSONObject) jsonArray.get(i);
//
//                        //list.clear();
//
//                        bank.setFBANK_BANK_CODE(jObject.getString("Bankcode"));
//                        bank.setFBANK_BANK_NAME(jObject.getString("Bankname"));
//                        bank.setFBANK_BANK_ACC_NO(jObject.getString("Bankaccno"));
//                        bank.setFBANK_BRANCH(jObject.getString("Branch"));
//                        bank.setFBANK_ADD1(jObject.getString("Bankadd1"));
//                        bank.setFBANK_ADD2(jObject.getString("Bankadd2"));
//                        bank.setFBANK_ADD_DATE(jObject.getString("AddDate"));
//                        bank.setFBANK_ADD_MACH(jObject.getString("AddMach"));
//                        bank.setFBANK_ADD_USER(jObject.getString("AddUser"));
//
//                        list.add(bank);
//
//                        ++recordCount;
//                        publishProgress(recordCount);
//
//
//
//                    }
//
//                    if (ds.createOrUpdateBank(list) > 0) {
//                        Log.v("CreateOrUpdateFbank", "Result : bank Data Inserted successfully");
//                    }
//                }

                //break;

//                case FDDBNOTE:
//                    // downloadingDataType="fDdbNote";
//                {
//                    ArrayList<FDDbNote> list = new ArrayList<FDDbNote>();
//                    JSONArray jsonArray = jsonResponse.getJSONArray("fDdbNoteWithConditionResult");
//                    Log.v(TAG, "Array Length fDdbNote DB :" + jsonArray.length());
//
//                    totalRecords = jsonArray.length();
//
//                    FDDbNoteDS ds = new FDDbNoteDS(context);
//                    Log.v(TAG, "Deleted Count: " + ds.deleteAll());
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        FDDbNote fdDbNote = new FDDbNote();
//
//                        JSONObject jObject = (JSONObject) jsonArray.get(i);
//
//                        //list.clear();
//
//                        //double totbal = 0, pdaamt = 0;
//                        BigDecimal totbal = new BigDecimal(jObject.getString("TotBal"));
//                        BigDecimal pdaamt = new BigDecimal(jObject.getString("PdaAmt"));
//                        BigDecimal roundOff_totbal = totbal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
//                        BigDecimal roundOff_pdaamt = pdaamt.setScale(2, BigDecimal.ROUND_HALF_EVEN);
//                        //totbal = Double.parseDouble(jObject.getString("TotBal"));
//                        //pdaamt = Double.parseDouble(jObject.getString("PdaAmt"));
//
//                        fdDbNote.setFDDBNOTE_ADD_DATE(jObject.getString("AddDate"));
//                        fdDbNote.setFDDBNOTE_ADD_MACH(jObject.getString("AddMach"));
//                        fdDbNote.setFDDBNOTE_ADD_USER(jObject.getString("AddUser"));
//                        fdDbNote.setFDDBNOTE_AMT(jObject.getString("Amt"));
//                        fdDbNote.setFDDBNOTE_B_AMT(jObject.getString("BAmt"));
//                        fdDbNote.setFDDBNOTE_B_TAX_AMT(jObject.getString("BTaxAmt"));
//                        fdDbNote.setFDDBNOTE_CUR_CODE(jObject.getString("CurCode"));
//                        fdDbNote.setFDDBNOTE_CUR_RATE(jObject.getString("CurRate"));
//                        fdDbNote.setFDDBNOTE_DEB_CODE(jObject.getString("DebCode"));
//                        fdDbNote.setFDDBNOTE_MANU_REF(jObject.getString("ManuRef"));
//                        fdDbNote.setFDDBNOTE_OV_PAY_AMT(jObject.getString("OvPayAmt"));
//                        fdDbNote.setFDDBNOTE_REF_INV(jObject.getString("RefInv"));
//                        fdDbNote.setFDDBNOTE_REFNO(jObject.getString("RefNo"));
//                        fdDbNote.setFDDBNOTE_REFNO1(jObject.getString("RefNo1"));
//                        fdDbNote.setFDDBNOTE_REMARKS(jObject.getString("Remarks"));
//                        fdDbNote.setFDDBNOTE_REP_CODE(jObject.getString("RepCode"));
//                        fdDbNote.setFDDBNOTE_SALE_REF_NO(jObject.getString("SaleRefNo"));
//                        fdDbNote.setFDDBNOTE_TAX_AMT(jObject.getString("TaxAmt"));
//                        fdDbNote.setFDDBNOTE_TAX_COM_CODE(jObject.getString("TaxComCode"));
//                        fdDbNote.setFDDBNOTE_TOT_BAL(String.valueOf((roundOff_totbal.subtract(roundOff_pdaamt))));
//                        fdDbNote.setFDDBNOTE_TOT_BAL1(jObject.getString("TotBal1"));
//                        fdDbNote.setFDDBNOTE_TXN_DATE(jObject.getString("TxnDate"));
//                        fdDbNote.setFDDBNOTE_TXN_TYPE(jObject.getString("TxnType"));
//                        fdDbNote.setFDDBNOTE_REPNAME(jObject.getString("RepName"));
//                        fdDbNote.setFDDBNOTE_ENTREMARK(jObject.getString("EntRemark"));
//                        fdDbNote.setFDDBNOTE_PDAAMT(jObject.getString("PdaAmt"));
//
//                        list.add(fdDbNote);
//
//                        ++recordCount;
//                        publishProgress(recordCount);
//
//
//                    }
//
//                    if (ds.createOrUpdateFDDbNoteNew(list) > 0) {
//                        Log.v("CreateOrUpdateFexpense", "Result : ddbnote Data Inserted successfully");
//                    }
//
//                }
//                break;

                case FEXPENSE:
                    // downloadingDataType="fExpense";
                {
                    ArrayList<Expense> list = new ArrayList<Expense>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fExpenseResult");
                    Log.v(TAG, "Array Length Expense DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    ExpenseDS ds = new ExpenseDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Expense expense = new Expense();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        expense.setFEXPENSE_ADD_DATE(jObject.getString("AddDate"));
                        expense.setFEXPENSE_ADD_MACH(jObject.getString("AddMach"));
                        expense.setFEXPENSE_ADD_USER(jObject.getString("AddUser"));
                        expense.setFEXPENSE_CODE(jObject.getString("ExpCode"));
                        expense.setFEXPENSE_GRP_CODE(jObject.getString("ExpGrpCode"));
                        expense.setFEXPENSE_NAME(jObject.getString("ExpName"));
                        expense.setFEXPENSE_STATUS(jObject.getString("Status"));

                        list.add(expense);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateFExpense(list) > 0) {
                        Log.v("CreateOrUpdateFexpense", "Result : expense Data Inserted successfully");
                    }
                }
                break;

                case FTOWN:
                    // downloadingDataType="fTown";
                {
                    ArrayList<Town> list = new ArrayList<Town>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fTownResult");
                    Log.v(TAG, "Array Length Route DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    TownDS ds = new TownDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Town town = new Town();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        town.setFTOWN_ADDDATE(jObject.getString("AddDate"));
                        town.setFTOWN_ADD_MACH(jObject.getString("AddMach"));
                        town.setFTOWN_ADD_USER(jObject.getString("AddUser"));
                        town.setFTOWN_DISTR_CODE(jObject.getString("DistrCode"));
                        town.setFTOWN_CODE(jObject.getString("TownCode"));
                        town.setFTOWN_NAME(jObject.getString("TownName"));

                        list.add(town);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateTown(list) > 0) {
                        Log.v("CreateOrUpdateFRoute", "Result : Route Data Inserted successfully");
                    }
                }
                break;

//                case FMERCH:
//                    // downloadingDataType="FMerch";
//                {
//                    ArrayList<Merch> list = new ArrayList<Merch>();
//                    JSONArray jsonArray = jsonResponse.getJSONArray("FMerchResult");
//                    Log.v(TAG, "Array Length Merch DB :" + jsonArray.length());
//
//                    totalRecords = jsonArray.length();
//
//                    MerchDS ds = new MerchDS(context);
//                    Log.v(TAG, "Deleted Count: " + ds.deleteAll());
//
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        Merch merch = new Merch();
//
//                        JSONObject jObject = (JSONObject) jsonArray.get(i);
//
//                        list.clear();
//
//                        merch.setFMERCH_CODE(jObject.getString("MerchCode"));
//                        merch.setFMERCH_NAME(jObject.getString("MerchName"));
//                        merch.setFMERCH_ADD_USER(jObject.getString("AddUser"));
//                        merch.setFMERCH_ADD_DATE(jObject.getString("AddDate"));
//                        merch.setFMERCH_ADD_MACH(jObject.getString("AddMach"));
//                        merch.setFMERCH_TIMESTAMP_COLUMN(jObject.getString("timestamp_column"));
//
//                        list.add(merch);
//
//                        ++recordCount;
//                        publishProgress(recordCount);
//
//                        if (ds.createOrUpdateFMerch(list) > 0) {
//                            Log.v("CreateOrUpdateFmerch", "Result : merch Data Inserted successfully");
//                        }
//
//                    }
//                }
//                break;

                case FROUTEDET:
                    // downloadingDataType ="fRouteDet";
                {
                    ArrayList<RouteDet> list = new ArrayList<RouteDet>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fmRouteDetResult");
                    Log.v(TAG, "Array Length RouteDet DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    RouteDetDS ds = new RouteDetDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        RouteDet routeDet = new RouteDet();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        // list.clear();

                        routeDet.setFROUTEDET_DEB_CODE(jObject.getString("DebCode"));
                        routeDet.setFROUTEDET_ROUTE_CODE(jObject.getString("RouteCode"));

                        list.add(routeDet);

                        ++recordCount;
                        publishProgress(recordCount);

                    }

                    if (ds.createOrUpdateFRouteDet(list) > 0) {
                        Log.v("CreateOrUpdatefRouteDet", "Result : Route Data Inserted successfully");
                    }

                }
                break;


                case FTYPE: {
                    ArrayList<Type> list = new ArrayList<Type>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("fTypeResult");
                    Log.v(TAG, "Array Length Type DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();

                    TypeDS ds = new TypeDS(context);


                    for (int i = 0; i < jsonArray.length(); i++) {
                        Type type = new Type();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        list.clear();

                        type.setFTYPE_ADD_DATE(jObject.getString("AddDate"));
                        type.setFTYPE_ADD_MACH(jObject.getString("AddMach"));
                        type.setFTYPE_ADD_USER(jObject.getString("AddUser"));
                        type.setFTYPE_CODE(jObject.getString("TypeCode"));
                        type.setFTYPE_NAME(jObject.getString("TypeName"));

                        list.add(type);

                        ++recordCount;
                        publishProgress(recordCount);


                    }
                    if (ds.createOrUpdateType(list) > 0) {
                        Log.v("CreateOrUpdateFRoute", "Result : Route Data Inserted successfully");
                    }

                }
                break;

                case FMITEMS: {
                    Log.v("FMITEMS_jsonResponse", jsonResponse.toString());

                    ArrayList<FmItems> arrayList = new ArrayList<FmItems>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("FmitemsResult");
                    Log.v(TAG, "Array Length FmitemsResult DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();
                    FmItemDS ds = new FmItemDS(context);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FmItems items = new FmItems();


                        JSONObject jObject = (JSONObject) jsonArray.get(i);
                        items.setADD_DATE(jObject.getString("ADD_DATE"));
                        items.setADD_MACH(jObject.getString("ADD_MACH"));
                        items.setADD_USER(jObject.getString("ADD_USER"));
                        items.setGITEM_CODE(jObject.getString("GITEM_CODE"));
                        items.setGITEM_NAME(jObject.getString("GITEM_NAME"));
                        items.setGITEM_NAMED(jObject.getString("GITEM_NAMED"));
                        items.setGITYPE(jObject.getString("GITYPE"));
                        items.setGITYPES(jObject.getString("GITYPES"));
                        items.setRECORD_ID(jObject.getString("RECORD_ID"));
                        items.setREMARKS(jObject.getString("REMARKS"));
                        items.setUOM(jObject.getString("UOM"));

                        arrayList.add(items);
                        ++recordCount;
                        publishProgress(recordCount);
                    }

                    if (ds.InsertFmItems(arrayList) > 0) {
                        Log.v("createOrUpdateFmItems", "Result : FrepDalo Data Inserted successfully");
                    }
                }
                break;


                case FMSALREP: {
                    ArrayList<FmSalRep> arrayList = new ArrayList<FmSalRep>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("FmSalRepResult");
                    Log.v(TAG, "Array Length FCountrymgrResult DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();
                    FmSalRepDS ds = new FmSalRepDS(context);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FmSalRep salRep = new FmSalRep();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);
                        salRep.setAddDate(jObject.getString("AddDate"));
                        salRep.setAddMach(jObject.getString("AddMach"));
                        salRep.setAddUser(jObject.getString("AddUser"));
                        salRep.setAreascode(jObject.getString("AreaSCode"));
                        salRep.setLocCode(jObject.getString("LocCode"));
                        salRep.setRecordId(jObject.getString("RecordId"));

                        salRep.setRepAdd1(jObject.getString("RepAdd1"));
                        salRep.setRepAdd2(jObject.getString("RepAdd2"));
                        salRep.setRepAdd3(jObject.getString("RepAdd3"));
                        salRep.setRepCodem(jObject.getString("RepCodem"));
                        salRep.setRepEMail(jObject.getString("RepEMail"));
                        salRep.setRepIdNo(jObject.getString("RepIdNo"));

                        salRep.setRepMobil(jObject.getString("RepMobil"));
                        salRep.setRouteCode(jObject.getString("RouteCode"));
                        salRep.setRepNamem(jObject.getString("RepNamem"));
                        salRep.setRepTele(jObject.getString("RepTele"));
                        salRep.setPrefix(jObject.getString("Prefix"));
                        salRep.setMackid(jObject.getString("MacId"));
                        salRep.setPassword(jObject.getString("Password"));
                        salRep.setStatus(jObject.getString("Status"));

                        arrayList.add(salRep);
                        ++recordCount;
                        publishProgress(recordCount);
                    }

                    if (ds.InsertFmSalRep(arrayList) > 0) {
                        Log.v("createOrUpdateFrepDalo", "Result : FmSalRepResult Data Inserted successfully");
                        return "" + jsonArray.length();
                    }
                }
                break;


                case FMDEBTOR: {
                    ArrayList<FmDebtor> arrayList = new ArrayList<FmDebtor>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("FmDebtorResult");
                    Log.v(TAG, "Array Length FmDebtorResult DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();
                    FmDebtorDS ds = new FmDebtorDS(context);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        FmDebtor debtor = new FmDebtor();

                        JSONObject jObject = (JSONObject) jsonArray.get(i);
                        debtor.setAddDate(jObject.getString("AddDate"));
                        debtor.setAddMach(jObject.getString("AddMach"));
                        debtor.setAddUser(jObject.getString("AddUser"));
                        debtor.setRepCodem(jObject.getString("RepCodem"));
                        debtor.setDebCodeM(jObject.getString("DebCodeM"));
                        debtor.setRecordid(jObject.getString("Recordid"));
                        debtor.setDebNameM(jObject.getString("DebNameM"));
                        debtor.setRouteCode(jObject.getString("RouteCode"));
                        arrayList.add(debtor);
                        ++recordCount;
                        publishProgress(recordCount);
                    }

                    if (ds.InsertFmDebtor(arrayList) > 0) {
                        Log.v("createOrUpdateFrepDalo", "Result : FmAreaSubResult Data Inserted successfully");
                    }
                }

                break;




                case FMISS_HED: {
                    ArrayList<Fmisshed> arrayList = new ArrayList<Fmisshed>();
                    JSONArray jsonArray = jsonResponse.getJSONArray("FmisshedResult");
                    Log.v(TAG, "Array Length FmisshedResult DB :" + jsonArray.length());

                    totalRecords = jsonArray.length();
                    FmisshedDS ds = new FmisshedDS(context);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        Fmisshed fmisshed = new Fmisshed();
                        JSONObject jObject = (JSONObject) jsonArray.get(i);

                        fmisshed.setRefNo(jObject.getString("RefNo"));
                        fmisshed.setTxnDate(jObject.getString("TxnDate"));
                        fmisshed.setManuRef(jObject.getString("ManuRef"));
                        fmisshed.setCostCode(jObject.getString("CostCode"));
                        fmisshed.setLocCode(jObject.getString("LocCode"));
                        fmisshed.setDebCodeM(jObject.getString("DebCodeM"));
                        fmisshed.setRepCodeM(jObject.getString("RepCodeM"));
                        fmisshed.setRemarks(jObject.getString("Remarks"));
                        fmisshed.setTxnType(jObject.getString("TxnType"));
                        fmisshed.setTotalAmt(jObject.getString("TotalAmt"));
                        fmisshed.setGIType(jObject.getString("GIType"));
                        fmisshed.setGITypeS(jObject.getString("GITypeS"));
                        fmisshed.setPrtcopy(jObject.getString("Prtcopy"));
                        fmisshed.setGlPost(jObject.getString("GlPost"));
                        fmisshed.setGlBatch(jObject.getString("GlBatch"));
                        fmisshed.setAddUser(jObject.getString("AddUser"));
                        fmisshed.setAddDate(jObject.getString("AddDate"));
                        fmisshed.setAddMach(jObject.getString("AddMach"));
                        fmisshed.setRecordId(jObject.getString("RecordId"));


                        arrayList.add(fmisshed);
                        ++recordCount;
                        publishProgress(recordCount);
                    }
                    if (ds.InsertFmisshed(arrayList) > 0) {
                        Log.v("createOrUpdateFrepDalo", "Result : FmisshedResult Data Inserted successfully");
                    }
                }
                break;

                default:
                    break;
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("FileNotFoundException" + e.getMessage());
            return "FileNotFound";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("Exception Insert" + e.getMessage());
            e.printStackTrace();
        }

        // return JSON String
        return resultStr;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        String titleMsg = "Downloading " + downloadingDataType + " data";
        progressDialog.setMessage(titleMsg + " " + progress[0] + "/" + totalRecords);

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        taskListener.onTaskCompleted(taskType, result);
       // Toast.makeText(context,taskType+" Downloaded",Toast.LENGTH_SHORT).show();
    }

}