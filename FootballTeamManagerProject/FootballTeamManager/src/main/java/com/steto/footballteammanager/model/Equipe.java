package com.steto.footballteammanager.model;

import com.j256.ormlite.dao.EagerForeignCollection;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Stephane on 22/12/13.
 */
@DatabaseTable
public class Equipe implements Serializable{

    @DatabaseField(id = true)
    private String mName;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Championnat> mAllChampionnat;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Joueur> mAllKnownJoueurs;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public ForeignCollection<Joueur> getAllKnownJoueurs() {
        return mAllKnownJoueurs;
    }

    public void setAllKnownJoueurs(ForeignCollection<Joueur> allKnownJoueurs) {
        mAllKnownJoueurs = allKnownJoueurs;
    }

    public ForeignCollection<Championnat> getAllChampionnat() {
        return mAllChampionnat;
    }

    public void setAllChampionnat(ForeignCollection<Championnat> allChampionnat) {
        mAllChampionnat = allChampionnat;
    }
}
