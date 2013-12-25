package com.steto.footballteammanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.steto.footballteammanager.dao.MatchDao;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Stephane on 22/12/13.
 */
@DatabaseTable(daoClass = MatchDao.class)
public class Match implements Serializable{
    public static final String COLUMN_CHAMPIONNAT = "COLUMN_CHAMPIONNAT";

    @DatabaseField( generatedId = true)
    private int mId;

    @DatabaseField
    private Date mDate;

    @DatabaseField
    private String mLieu;

    @DatabaseField
    private int mNbButsEncaisses;

    @DatabaseField
    private int mNbButsMarques;

    @DatabaseField
    private String mAdversaireName;

    @DatabaseField(foreign = true, columnName = COLUMN_CHAMPIONNAT)
    private Championnat mChampionnat;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getLieu() {
        return mLieu;
    }

    public void setLieu(String lieu) {
        mLieu = lieu;
    }

    public int getNbButsEncaisses() {
        return mNbButsEncaisses;
    }

    public void setNbButsEncaisses(int nbButsEncaisses) {
        mNbButsEncaisses = nbButsEncaisses;
    }

    public int getNbButsMarques() {
        return mNbButsMarques;
    }

    public void setNbButsMarques(int nbButsMarques) {
        mNbButsMarques = nbButsMarques;
    }

    public String getAdversaireName() {
        return mAdversaireName;
    }

    public void setAdversaireName(String adversaireName) {
        mAdversaireName = adversaireName;
    }
}
