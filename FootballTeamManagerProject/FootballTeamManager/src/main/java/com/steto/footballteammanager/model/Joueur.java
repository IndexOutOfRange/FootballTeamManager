package com.steto.footballteammanager.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Stephane on 22/12/13.
 */
@DatabaseTable
public class Joueur implements Serializable{

    public Equipe getEquipe() {
        return mEquipe;
    }

    public void setEquipe(Equipe equipe) {
        mEquipe = equipe;
    }

    public enum Poste {
        GARDIEN, DEFENSEUR, STOPPEUR, AILIER, ATTAQUANT
    }

    @DatabaseField(id = true)
    private String mName;

    @DatabaseField
    private int mNbButs;

    @DatabaseField
    private int mNbPassesDecisives;

    @DatabaseField
    private Poste mPoste;

    @DatabaseField (foreign = true)
    private Equipe mEquipe;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getNbButs() {
        return mNbButs;
    }

    public void setNbButs(int mNbButs) {
        this.mNbButs = mNbButs;
    }

    public int getNbPassesDecisives() {
        return mNbPassesDecisives;
    }

    public void setNbPassesDecisives(int mNbPassesDecisives) {
        this.mNbPassesDecisives = mNbPassesDecisives;
    }

    public Poste getPoste() {
        return mPoste;
    }

    public void setPoste(Poste mPoste) {
        this.mPoste = mPoste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Joueur.class != o.getClass()) return false;

        Joueur joueur = (Joueur) o;

        if (mName != null ? !mName.equals(joueur.mName) : joueur.mName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mName != null ? mName.hashCode() : 0;
    }
}
