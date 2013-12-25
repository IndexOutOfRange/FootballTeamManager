package com.steto.footballteammanager.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steto.footballteammanager.R;
import com.steto.footballteammanager.activity.ListeMatch;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Match;

import java.sql.SQLException;

/**
 * Created by Stephane on 23/12/13.
 */
public class CellListeCampionnat extends LinearLayout {
    public static final String EXTRA_INPUT_CHAMPIONNAT = "EXTRA_INPUT_CHAMPIONNAT";
    private TextView mTvChampResume;
    private Championnat mChampionnat;
    private TextView mTvChampName;
    private Context mContext;

    public CellListeCampionnat(Context context) {
        super(context);
        mContext = context;
        inflate(context, R.layout.cell_liste_championnat, this);
        mTvChampName = ((TextView) findViewById(R.id.tv_championnat_name));
        mTvChampResume = ((TextView) findViewById(R.id.championnat_resume));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ListeMatch.class);
                intent.putExtra(EXTRA_INPUT_CHAMPIONNAT, mChampionnat);
                mContext.startActivity(intent);
            }
        });
    }

    public void setData(Championnat champ) {
        mChampionnat = champ;
        mTvChampName.setText(mChampionnat.getName());
        int nbVictoire = 0;
        int nbDefaite = 0;
        int nbNul = 0;
        if (champ.getAllMatches() != null && champ.getAllMatches().size() != 0) {
            for (Match current : champ.getAllMatches()) {
                if (current.getNbButsMarques() > current.getNbButsEncaisses()) {
                    nbVictoire++;
                } else if (current.getNbButsMarques() < current.getNbButsEncaisses()) {
                    nbDefaite++;
                } else {
                    nbNul++;
                }
            }
        }
        mTvChampResume.setText(nbVictoire + " victoires, " + nbDefaite + " defaites, " + nbNul + " match nul");
    }


}
