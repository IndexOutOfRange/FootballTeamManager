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

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephane on 23/12/13.
 */
public class ListeMatch extends Activity {

    private static final int CREATE_MATCH_REQUEST_CODE = 5;
    private static final String EXTRA_SAVED_LIST_MATCH = "EXTRA_SAVED_LIST_MATCH";


    private List<Match> mMatches = new ArrayList<Match>();
    private ListeMatchAdapter mAdapter;
    private MatchDao mMatchDao;
    private Championnat mChampionnat;
    private ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_match);


        mChampionnat = (Championnat) getIntent().getExtras().get(CellListeCampionnat.EXTRA_INPUT_CHAMPIONNAT);

        List<Match> matches = null;
        if (savedInstanceState == null) {
            try {
                mMatchDao = new DatabaseHelper(this).getDao(Match.class);
                matches = mMatchDao.getAllMatchFromChampionnat(mChampionnat);
                setMatches(matches);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            setMatches((List<Match>) savedInstanceState.get(EXTRA_SAVED_LIST_MATCH));
        }
        mAdapter = new ListeMatchAdapter(this, mMatches);
        mList = ((ListView) findViewById(R.id.list_match));
        mList.setAdapter(mAdapter);
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
                intent.putExtra(CreateMatch.EXTRA_INPUT_CHAMPIONNAT, mChampionnat);
                startActivityForResult(intent, CREATE_MATCH_REQUEST_CODE);
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_SAVED_LIST_MATCH, (Serializable) mMatches);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_MATCH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if(mMatches != null) {
                    mMatches.add((Match) data.getExtras().get(CreateMatch.EXTRA_OUTPUT_MATCH));
                    mAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    public List<Match> getMatches() {
        return mMatches;
    }

    public void setMatches(List<Match> matches) {
        mMatches.clear();
        mMatches.addAll(matches);
    }
}
