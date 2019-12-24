package com.bit.sfa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bit.sfa.model.FmDebtor;
import com.bit.sfa.R;

import java.util.ArrayList;

/**
 * Created by Rashmi on 11/20/2018.
 */

public class CustomerAdapter extends ArrayAdapter<FmDebtor> {
    Context context;
    ArrayList<FmDebtor> list;

    public CustomerAdapter(Context context, ArrayList<FmDebtor> list) {

        super(context, R.layout.row_customer_listview, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(final int position, View row, final ViewGroup parent) {

        LayoutInflater inflater = null;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.row_customer_listview, parent, false);

        LinearLayout layout = (LinearLayout) row.findViewById(R.id.linearLayout);
        TextView code = (TextView) row.findViewById(R.id.debCode);
        TextView name = (TextView) row.findViewById(R.id.debName);
        TextView address = (TextView) row.findViewById(R.id.debAddress);


        code.setText(list.get(position).getDebCodeM());
        name.setText(list.get(position).getDebNameM());

        return row;
    }
}