package com.steto.footballteammanager.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Match;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Stephane on 25/12/13.
 */
public class MatchDao extends BaseDaoImpl {
    public MatchDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Match.class);
    }

    public List<Match> getAllMatchFromChampionnat(Championnat championnat) throws SQLException {
        return queryForEq(Match.COLUMN_CHAMPIONNAT, championnat);
    }
}
