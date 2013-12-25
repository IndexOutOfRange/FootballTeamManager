package com.steto.footballteammanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.j256.ormlite.dao.Dao;
import com.steto.footballteammanager.R;
import com.steto.footballteammanager.database.DatabaseHelper;
import com.steto.footballteammanager.model.Equipe;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stephane on 22/12/13.
 */
public class WelcomeConfigPage extends Activity {

    public static final String EXTRA_INPUT_EQUIPE = "EXTRA_INPUT_EQUIPE";
    private EditText mTeamNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_config_page);
        mTeamNameEditText = (EditText) findViewById(R.id.team_name_edit_text);
        findViewById(R.id.login_button).setOnClickListener(new OnOkLoginClickListener());
        try {
            Dao<Equipe, String> equipeDao = new DatabaseHelper(WelcomeConfigPage.this).getDao(Equipe.class);
            List<Equipe> allEquipe = equipeDao.queryForAll ();
            if(allEquipe!= null && !allEquipe.isEmpty()) {
                navigateToListeChampionnatScreen(allEquipe.get(0));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void navigateToListeChampionnatScreen(Equipe equipe){
        Intent in = new Intent(this, ListeChampionnat.class);
        in.putExtra(EXTRA_INPUT_EQUIPE, equipe);
        startActivity(in);
        finish();
    }

    private class OnOkLoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                String teamName = mTeamNameEditText.getText().toString();
                Dao<Equipe, String> equipeDao = new DatabaseHelper(WelcomeConfigPage.this).getDao(Equipe.class);
                Equipe equipe = new Equipe();
                equipe.setName(teamName);
                equipeDao.createOrUpdate(equipe);
                navigateToListeChampionnatScreen(equipe);
            } catch (SQLException e) {
                Log.e("WelcomeConfigPage", "error BDD", e);
            }
        }
    }
}
