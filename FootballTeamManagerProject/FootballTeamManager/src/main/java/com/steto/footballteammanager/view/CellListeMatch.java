package com.steto.footballteammanager.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steto.footballteammanager.R;
import com.steto.footballteammanager.activity.DetailMatch;
import com.steto.footballteammanager.model.Match;

/**
 * Created by Stephane on 23/12/13.
 */
public class CellListeMatch extends LinearLayout {

    public static final String EXTRA_INPUT_MATCH = "EXTRA_INPUT_MATCH";
    private final ImageView mMatchIcon;
    private final TextView mMatchDate;
    private final TextView mMatchAdvName;
    private final TextView mMatchScore;
    private Context mContext;
    private Match mMatch;

    public CellListeMatch(Context context) {
        super(context);
        mContext = context;
        inflate(context, R.layout.cell_liste_match, this);
        mMatchIcon = (ImageView)findViewById(R.id.match_icon);
        mMatchDate = (TextView)findViewById(R.id.match_date);
        mMatchAdvName = (TextView)findViewById(R.id.match_adversaire_name);
        mMatchScore = (TextView)findViewById(R.id.resultat_score);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, DetailMatch.class);
//                intent.putExtra(EXTRA_INPUT_MATCH, mMatch);
//                mContext.startActivity(intent);

            }
        });
    }

    public void setData(Match match) {
        mMatchDate.setText(match.getDate().toString());
        mMatchAdvName.setText(match.getAdversaireName());
        mMatchScore.setText(match.getNbButsMarques() + "-" + match.getNbButsEncaisses());
        if(match.getNbButsEncaisses() > match.getNbButsMarques()) {
            mMatchIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher));
        } else if( match.getNbButsEncaisses() < match.getNbButsMarques()) {
            mMatchIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher));
        } else {
            mMatchIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_launcher));
        }
    }
}
