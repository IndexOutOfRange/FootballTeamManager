package com.steto.footballteammanager.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.j256.ormlite.dao.Dao;
import com.steto.footballteammanager.R;
import com.steto.footballteammanager.adapter.ListeChampionnatAdapter;
import com.steto.footballteammanager.dao.ChampionnatDao;
import com.steto.footballteammanager.database.DatabaseHelper;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Equipe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephane on 22/12/13.
 */
public class ListeChampionnat extends Activity {

    private ListeChampionnatAdapter mAdapter;
    private ArrayList<Championnat> mChampionnats;
    private Equipe mEquipe;
    private ChampionnatDao mChampionnatDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_liste_championnat);
        mEquipe = (Equipe) getIntent().getExtras().get(WelcomeConfigPage.EXTRA_INPUT_EQUIPE);

        List<Championnat> champs = null;
        try {
            mChampionnatDao = new DatabaseHelper(this).getDao(Championnat.class);
            champs = mChampionnatDao.getChampionnatFromEquipe(mEquipe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(champs == null) {
            mChampionnats = new ArrayList<Championnat>();
        } else {
            mChampionnats = new ArrayList<Championnat>(champs);
        }

        mAdapter = new ListeChampionnatAdapter(this, mChampionnats);
        ((ListView) findViewById(R.id.list_championnat)).setAdapter(mAdapter);
        Log.i("ListeChmpionnat", "create de ListeChampionnat");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_liste_championnat, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        super.onMenuItemSelected(featureId, item);
        switch (item.getItemId()) {
            case R.id.menu_add_championnat:
                createChampionnat();
                break;
        }
        return true;
    }

    private void createChampionnat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_create_championnat, null);
        builder.setView(view);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {

                    Championnat championnat = new Championnat();
                    championnat.setEquipe(mEquipe);
                    String name = ((EditText) view.findViewById(R.id.edit_championnat_name)).getText().toString();
                    championnat.setName(name);
                    mChampionnatDao.createOrUpdate(championnat);
                    mChampionnats.add(championnat);
                    mAdapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
