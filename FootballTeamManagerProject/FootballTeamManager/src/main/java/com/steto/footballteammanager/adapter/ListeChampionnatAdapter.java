package com.steto.footballteammanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.view.CellListeCampionnat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Stephane on 23/12/13.
 */
public class ListeChampionnatAdapter extends BaseAdapter {
    private List<Championnat> mChampionnats;
    private Context mContext;

    public ListeChampionnatAdapter(Context ctx, List<Championnat> championnats) {
        mContext = ctx;
        mChampionnats = championnats;
    }

    @Override
    public int getCount() {
        return mChampionnats.size();
    }

    @Override
    public Object getItem(int position) {
        return mChampionnats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = new CellListeCampionnat(mContext);
        }
        ((CellListeCampionnat)convertView).setData(mChampionnats.get(position));

        return convertView;
    }
}
