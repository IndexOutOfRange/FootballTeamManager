package com.steto.footballteammanager.model;

import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.steto.footballteammanager.dao.ChampionnatDao;

import java.io.Serializable;

/**
 * Created by Stephane on 22/12/13.
 */
@DatabaseTable(daoClass = ChampionnatDao.class)
public class Championnat implements Serializable{

    public  static final String COLUMN_EQUIPE = "COLUMN_EQUIPE";

    @DatabaseField(generatedId = true)
    private Integer mId;

    @DatabaseField
    private String mName;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Match> mAllMatches;

    @DatabaseField(foreign = true, columnName = COLUMN_EQUIPE)
    private Equipe mEquipe;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Equipe getEquipe() {
        return mEquipe;
    }

    public void setEquipe(Equipe equipe) {
        mEquipe = equipe;
    }

    public ForeignCollection<Match> getAllMatches() {
        return mAllMatches;
    }

    public void setAllMatches(ForeignCollection<Match> allMatches) {
        mAllMatches = allMatches;
    }
}
