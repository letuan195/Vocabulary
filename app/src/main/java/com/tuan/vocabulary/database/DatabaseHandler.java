package com.tuan.vocabulary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tuan.vocabulary.review.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Vocabulary";
    private static final String TABLE_DICT = "Dictionary";
    private static final String KEY_ID = "id";
    private static final String KEY_E = "English";
    private static final String KEY_V = "Vietnamese";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_DICT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_E + " TEXT,"
                + KEY_V + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICT);
        onCreate(db);
    }

    public void addWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_E, word.getKey());
        values.put(KEY_V, word.getValue());
        // Inserting Row
        db.insert(TABLE_DICT, null, values);
        db.close(); // Closing database connection
    }

    public Word getWord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DICT, new String[] { KEY_ID,
                        KEY_E, KEY_V }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Word word = new Word();
        word.setID(Integer.parseInt(cursor.getString(0)));
        word.setKey(cursor.getString(1));
        word.setValue(cursor.getString(2));
        return word;
    }
    public ArrayList<Word> getAllWords() {
        ArrayList<Word> words = new ArrayList<Word>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DICT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setID(Integer.parseInt(cursor.getString(0)));
                word.setKey(cursor.getString(1));
                word.setValue(cursor.getString(2));
                words.add(word);
            } while (cursor.moveToNext());
        }
        return words;
    }
    public int updateWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_E, word.getKey());
        values.put(KEY_V, word.getValue());
        // updating row
        return db.update(TABLE_DICT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getID()) });
    }

    public void deleteWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DICT, KEY_ID + " = ?",
                new String[] { String.valueOf(word.getID()) });
        db.close();
    }

    public boolean findWord(String key, String value){
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_DICT + " WHERE " +KEY_E+ " = '" +key+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0){
            return false;
        }
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(2).equals(value)){
                    return true;
                }
            } while (cursor.moveToNext());
        }
        return false;
    }
}
