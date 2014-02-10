package com.steto.footballteammanager.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;
import com.steto.footballteammanager.R;
import com.steto.footballteammanager.adapter.ListJoueurAdapter;
import com.steto.footballteammanager.dao.MatchDao;
import com.steto.footballteammanager.database.DatabaseHelper;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Joueur;
import com.steto.footballteammanager.model.Match;
import com.steto.footballteammanager.view.CellListeJoueur;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Stephane on 23/12/13.
 */
public class CreateMatch extends Activity {
    public static final String EXTRA_INPUT_CHAMPIONNAT = "EXTRA_INPUT_CHAMPIONNAT";
    public static final String EXTRA_OUTPUT_MATCH = "EXTRA_OUTPUT_MATCH";

    private Button mBtnAddPlayer;
    private EditText mETButContre;
    private EditText mETButPour;
    private EditText mETAdversaire;
    private EditText mETLieu;
    private EditText mETDate;
    private Championnat mChampionnat;
    private List<Joueur> mAllJoueur = new ArrayList<Joueur>();
    private ListJoueurAdapter mAdapter;
    private ListView mListJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
        mETButPour = (EditText) findViewById(R.id.edit_text_but_pour);
        mETAdversaire = (EditText) findViewById(R.id.edit_text_adversaire);
        mETLieu = (EditText) findViewById(R.id.edit_text_lieu);
        mETDate = (EditText) findViewById(R.id.edit_text_date);
        mBtnAddPlayer = (Button) findViewById(R.id.btn_add_player);
        mETButContre = (EditText) findViewById(R.id.edit_text_but_contre);
        mListJoueur = (ListView) findViewById(R.id.list_joueur);
        mChampionnat = (Championnat) getIntent().getExtras().get(EXTRA_INPUT_CHAMPIONNAT);

        mBtnAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddJoueur();
            }
        });

        try {
            Dao<Joueur, String> dao = new DatabaseHelper(this).getDao(Joueur.class);
            setAllJoueur(dao.queryForAll());
            mAdapter = new ListJoueurAdapter(mAllJoueur, this, new CellListeJoueur.ButsFilledListener() {
                @Override
                public void onButsFilled(Joueur joueur, int buts) {
                    joueur.setNbButs(joueur.getNbButs() + buts);
                }
            }, new CellListeJoueur.PassesFilledListener() {
                @Override
                public void onPassesFilled(Joueur joueur, int passes) {
                    joueur.setNbPassesDecisives(joueur.getNbPassesDecisives() + passes);
                }
            }, new CellListeJoueur.DeleteButtonListener() {
                @Override
                public void onDeleteClicked(Joueur joueur) {
                    mAllJoueur.remove(joueur);
                }
            });
            mListJoueur.setAdapter(mAdapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDialogAddJoueur() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_create_championnat, null);
        builder.setView(view);
        ((TextView)view.findViewById(R.id.dialog_title)).setText("Donnez un nom au joueur");
        ((EditText)view.findViewById(R.id.edit_championnat_name)).setHint("Nom");

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Joueur joueur = new Joueur();
                String name = ((EditText) view.findViewById(R.id.edit_championnat_name)).getText().toString();
                joueur.setName(name);
                mAllJoueur.add(joueur);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ajouter_match, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        super.onMenuItemSelected(featureId, item);
        switch (item.getItemId()) {
            case R.id.menu_validate_match:
                validateMatch();
                break;
        }
        return true;
    }

    private void validateMatch() {
        try {
            String lieu;
            String date;
            String adversaire;
            String butPour;
            String butContre;
            Date dateParsed;
            int butPourParsed;
            int butContreParsed;
            lieu = mETLieu.getText().toString();
            date = mETDate.getText().toString();
            adversaire = mETAdversaire.getText().toString();
            butContre = mETButContre.getText().toString();
            butPour = mETButPour.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            dateParsed = sdf.parse(date);
            butContreParsed = Integer.parseInt(butContre);
            butPourParsed = Integer.parseInt(butPour);
            Match match = new Match(dateParsed,lieu, butContreParsed, butPourParsed, adversaire, mChampionnat);
            MatchDao dao = new DatabaseHelper(this).getDao(Match.class);
            dao.createOrUpdate(match);
            Intent in = new Intent();
            in.putExtra(EXTRA_OUTPUT_MATCH, match);
            setResult(RESULT_OK, in);
            finish();
        } catch (ParseException e) {
            Log.e("parse", e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Joueur> getAllJoueur() {
        return mAllJoueur;
    }

    public void setAllJoueur(List<Joueur> allJoueur) {
        mAllJoueur.clear();
        mAllJoueur.addAll(allJoueur);
    }
}
