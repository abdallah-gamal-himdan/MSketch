package com.example.msketch.Helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.msketch.ui.clients.ClientsViewModel;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    final ArrayList<String> mItems;
    final int mCount;
    private static final int ROW_ITEMS = 3;

    /**
     * Default constructor
     * @param items to fill data to
     */
    public GridAdapter(final ArrayList<ClientsViewModel> items) {

        mCount = items.size() * ROW_ITEMS;
        mItems = new ArrayList<String>(mCount);

        // for small size of items it's ok to do it here, sync way
        for (ClientsViewModel item : items) {
            // get separate string parts, divided by ,


            // remove spaces from parts

        }
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Object getItem(final int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        final TextView text = (TextView) view.findViewById(android.R.id.text1);

        text.setText(mItems.get(position));

        return view;
    }
}
