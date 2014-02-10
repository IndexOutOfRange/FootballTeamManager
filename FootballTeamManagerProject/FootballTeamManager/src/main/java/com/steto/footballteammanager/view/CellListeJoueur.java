package com.steto.footballteammanager.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.steto.footballteammanager.R;
import com.steto.footballteammanager.model.Joueur;

/**
 * Created by Stephane on 10/02/14.
 */
public class CellListeJoueur extends LinearLayout {
    private final EditText mETPassesDec;
    private final EditText mETButs;
    private final TextView mTVName;
    private final ImageView mIVDel;
    private Joueur mJoueur;
    private ButsFilledListener mButsFilledListener;
    private PassesFilledListener mPassesFilledListener;
    private DeleteButtonListener mDeleteButtonListener;

    public CellListeJoueur(Context context, ButsFilledListener butsFilledListener, PassesFilledListener passesFilledListener, DeleteButtonListener deleteButtonListener) {
        super(context);
        mButsFilledListener = butsFilledListener;
        mPassesFilledListener = passesFilledListener;
        mDeleteButtonListener = deleteButtonListener;
        inflate(context, R.layout.cell_liste_joueur, this);
        mETPassesDec = (EditText) findViewById(R.id.et_passes_dec);
        mETButs = (EditText) findViewById(R.id.et_buts);
        mTVName = (TextView) findViewById(R.id.tv_nom_joueur);
        mIVDel = (ImageView) findViewById(R.id.delete_joueur);
    }

    public void setData(Joueur joueur) {
        mJoueur = joueur;

        mETButs.addTextChangedListener(new ButsTextWatcher());
        mETPassesDec.addTextChangedListener(new PassesTextWatcher());
        mIVDel.setOnClickListener(new DeletePlayerClickListener());
    }

    private class ButsTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            try {
                int buts = Integer.parseInt(s.toString());
                mButsFilledListener.onButsFilled(mJoueur, buts);
            } catch (NumberFormatException e ) {
                Log.e("passes", "passes", e);
            }
        }
    }

    private class PassesTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            try {
                int passes = Integer.parseInt(s.toString());
                mPassesFilledListener.onPassesFilled(mJoueur, passes);
            } catch (NumberFormatException e ) {
                Log.e("buts", "buts", e);
            }
        }
    }

    private class DeletePlayerClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            mDeleteButtonListener.onDeleteClicked(mJoueur);
        }
    }

    public interface ButsFilledListener {
        public void onButsFilled(Joueur joueur, int buts);
    }

    public interface PassesFilledListener {
        public void onPassesFilled(Joueur joueur, int passes);
    }

    public interface DeleteButtonListener {
        public void onDeleteClicked(Joueur joueur);
    }
}
