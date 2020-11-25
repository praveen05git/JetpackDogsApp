package com.hencesimplified.androidjetpackjava.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DogBreed.class}, version = 1)
public abstract class DogDatabase extends RoomDatabase {

    private static DogDatabase databaseInstance;

    public static DogDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), DogDatabase.class, "dogdatabase").build();
        }
        return databaseInstance;
    }

    public abstract DogDao dogDao();
}
