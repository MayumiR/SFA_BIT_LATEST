package com.bit.sfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bit.sfa.model.Fmisshed;
import com.bit.sfa.R;

import java.util.ArrayList;


public class GiftDetailsAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    ArrayList<Fmisshed> list;
    Context context;
    //ArrayList<FreeHed> arrayList;

    public GiftDetailsAdapter(Context context, ArrayList<Fmisshed> list){
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;

    }
    @Override
    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }
    @Override
    public Fmisshed getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView ==null) {
            viewHolder = new ViewHolder();
            convertView =inflater.inflate(R.layout.row_gift_details,parent,false);
            viewHolder.lblItem = (TextView) convertView.findViewById(R.id.row_item);
            viewHolder.lblStatus = (TextView) convertView.findViewById(R.id.row_status);
            viewHolder.lblRefNo = (TextView) convertView.findViewById(R.id.row_refno);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.lblItem.setText(list.get(position).getRemarks());
        if(list.get(position).getIsIssue().equals("1"))
            viewHolder.lblStatus.setText("Issued");
        else
            viewHolder.lblStatus.setText("Not Issued");
        viewHolder.lblRefNo.setText(list.get(position).getRefNo());

//        FreeHedDS freeHedDS = new FreeHedDS(context);
//        arrayList = freeHedDS.getFreeIssueItemDetailByRefno(list.get(position).getFTRANSODET_ITEMCODE(),"" );
//        for(FreeHed freeHed:arrayList){
//            int itemQty = (int) Float.parseFloat(freeHed.getFFREEHED_ITEM_QTY());
//            int enterQty = (int) Float.parseFloat(list.get(position).getFTRANSODET_QTY());
//
//            if(enterQty<itemQty){
//                //other products------this procut has't free items
//                viewHolder.showStatus.setBackgroundColor(Color.WHITE);
//            }else{
//                //free item eligible product
//                viewHolder.showStatus.setBackground(context.getResources().getDrawable(R.drawable.ic_free_b));
//            }
//
//
//        }


        return convertView;
    }
    private  static  class  ViewHolder{
        TextView lblItem;
        TextView lblStatus;
        TextView lblRefNo;


    }

}
