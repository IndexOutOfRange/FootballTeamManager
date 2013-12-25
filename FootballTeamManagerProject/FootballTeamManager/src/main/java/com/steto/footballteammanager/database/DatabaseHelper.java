package com.steto.footballteammanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.steto.footballteammanager.model.Championnat;
import com.steto.footballteammanager.model.Equipe;
import com.steto.footballteammanager.model.Joueur;
import com.steto.footballteammanager.model.Match;

import java.sql.SQLException;

/**
 * Created by Stephane on 22/12/13.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "FootballTeamManager.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Championnat.class);
            TableUtils.createTable(connectionSource, Equipe.class);
            TableUtils.createTable(connectionSource, Joueur.class);
            TableUtils.createTable(connectionSource, Match.class);
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Could not create new tables", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {

    }
}
