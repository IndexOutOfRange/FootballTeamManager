package com.steto.footballteammanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.steto.footballteammanager.R;
import com.steto.footballteammanager.adapter.ListeMatchAdapter;
import com.steto.footballteammanager.dao.MatchDao;
import com.steto.footballteammanager.database.DatabaseHelper;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Match;
import com.steto.footballteammanager.view.CellListeCampionnat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephane on 23/12/13.
 */
public class ListeMatch extends Activity{

    private List<Match> mMatches;
    private ListeMatchAdapter mAdapter;
    private MatchDao mMatchDao;
    private Championnat mChampionnat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_match);


        mChampionnat = (Championnat) getIntent().getExtras().get(CellListeCampionnat.EXTRA_INPUT_CHAMPIONNAT);

        List<Match> matches = null;
        try {
            mMatchDao = new DatabaseHelper(this).getDao(Match.class);
            matches = mMatchDao.getAllMatchFromChampionnat(mChampionnat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(matches == null) {
            mMatches = new ArrayList<Match>();
        } else {
            mMatches = new ArrayList<Match>(matches);
        }

        mAdapter = new ListeMatchAdapter(this, mMatches);
        ((ListView)findViewById(R.id.list_match)).setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_liste_match, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        super.onMenuItemSelected(featureId, item);
        switch (item.getItemId()) {
            case R.id.menu_add_match:
                Intent intent = new Intent(this, CreateMatch.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
