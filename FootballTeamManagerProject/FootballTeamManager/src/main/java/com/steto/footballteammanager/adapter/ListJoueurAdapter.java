package com.steto.footballteammanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.steto.footballteammanager.model.Joueur;
import com.steto.footballteammanager.view.CellListeJoueur;

import java.util.List;

/**
 * Created by Stephane on 10/02/14.
 */
public class ListJoueurAdapter extends BaseAdapter {

    private List<Joueur> mAllJoueur;
    private Context mContext;
    private CellListeJoueur.ButsFilledListener mButsFilledListener;
    private CellListeJoueur.PassesFilledListener mPassesFilledListener;
    private CellListeJoueur.DeleteButtonListener mDeleteButtonListener;

    public ListJoueurAdapter(List<Joueur> allJoueur, Context context, CellListeJoueur.ButsFilledListener butsFilledListener, CellListeJoueur.PassesFilledListener passesFilledListener, CellListeJoueur.DeleteButtonListener deleteButtonListener) {
        mAllJoueur = allJoueur;
        mContext = context;
        mButsFilledListener = butsFilledListener;
        mPassesFilledListener = passesFilledListener;
        mDeleteButtonListener = deleteButtonListener;
    }

    @Override
    public int getCount() {
        return mAllJoueur.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllJoueur.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = new CellListeJoueur(mContext, mButsFilledListener, mPassesFilledListener, mDeleteButtonListener);
        }
        ((CellListeJoueur)convertView).setData(mAllJoueur.get(position));

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mAllJoueur.isEmpty();
    }
}
