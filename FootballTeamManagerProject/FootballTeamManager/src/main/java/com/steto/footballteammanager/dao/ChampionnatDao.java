package com.steto.footballteammanager.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Equipe;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stephane on 23/12/13.
 */
public class ChampionnatDao extends BaseDaoImpl {

    public ChampionnatDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Championnat.class);
    }

    public List<Championnat> getChampionnatFromEquipe(Equipe equipe) throws SQLException {
        return queryForEq(Championnat.COLUMN_EQUIPE, equipe);
    }
}
