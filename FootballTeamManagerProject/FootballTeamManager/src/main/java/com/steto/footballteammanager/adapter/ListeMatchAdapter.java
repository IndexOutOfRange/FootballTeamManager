package com.steto.footballteammanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.steto.footballteammanager.model.Match;
import com.steto.footballteammanager.view.CellListeMatch;

import java.util.List;

/**
 * Created by Stephane on 23/12/13.
 */
public class ListeMatchAdapter extends BaseAdapter {

    private Context mContext;
    private List<Match> mMatches;

    public ListeMatchAdapter(Context ctx, List<Match> matches) {
        mContext = ctx;
        mMatches = matches;
    }

    @Override
    public int getCount() {
        return mMatches.size();
    }

    @Override
    public Object getItem(int position) {
        return mMatches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = new CellListeMatch(mContext);
        }
        ((CellListeMatch)convertView).setData(mMatches.get(position));

        return convertView;
    }
}
