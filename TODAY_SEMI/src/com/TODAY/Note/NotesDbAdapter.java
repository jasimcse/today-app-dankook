/*
 * Copyright (C) 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.TODAY.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class NotesDbAdapter {
	
	public static final String KEY_DAY = "day";
	public static final String KEY_BODY = "body";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "NotesDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
        "create table notes (_id integer primary key autoincrement, "
        + "body text not null, day text);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 3;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public NotesDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public NotesDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }
    


    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param body the body of the note
     * @return rowId or -1 if failed
     */
    
		ContentValues initialValues = new ContentValues();
		//메모를 저장해주는 부분
		public long createNote(String body, String day) {

			//Log.i("Day",day);//log로 day가 저장되는지 확인
        
        initialValues.put(KEY_BODY, body);
        initialValues.put(KEY_DAY, day); 

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     * 
     * @param rowId id of note to delete
     * @return true if deleted, false otherwise
     */
        
    public boolean deleteNote(String day) {

        return mDb.delete(DATABASE_TABLE, KEY_DAY + "='" + day + "'", null) > 0;
//삭제 쿼리 수행(day(년, 월, 일)을 이용해서 삭제
    }

    /**
     * Return a Cursor over the list of all notes in the database
     * 
     * @return Cursor over all notes
     */
    public Cursor fetchAllNotes() {
    	//이건 원래 전에 리스트에 뿌려줄때 쓰던것같은데 일단 지우진 않았음
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_BODY, KEY_DAY}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     * 
     * @param rowId id of note to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    
    public Cursor fetchNote(long rowId) throws SQLException {
    	//위와 마찬가지로 리스트에 뿌려줄때 쓰던쿼리인데 일단 지우진 않았음
        Cursor mCursor =

            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                     KEY_BODY, KEY_DAY}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    
    // 년, 월, 일을 가져옴
    public Cursor fetchNoteByDay(String day) throws SQLException {

        Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_BODY, KEY_DAY}, KEY_DAY + "='" + day + "'", 
            		null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //
    //
    public Cursor fetchNoteByMonth(int year, int month) throws SQLException { 
    //년, 월을 쿼리에서 가져옴(like를 이용해서 조회, 예를 들면 2012년 5월이면 2012년 5월에 메모가 저장된 날짜만 조회)
    	//select * from notes where day like '2012-5-%'; 이거랑 같음
        Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {KEY_DAY}, KEY_DAY + " like '" + year + "-" + month + "-%'", 
            		null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     * 
     * @param rowId id of note to update
     * @param title value to set note title to
     * @param body value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateNote(String body, String day) {
        ContentValues args = new ContentValues();
        args.put(KEY_BODY, body);

        return mDb.update(DATABASE_TABLE, args, KEY_DAY + "='" + day + "'", null) > 0;
  //이건 메모에 저장되어 있을때 클릭하면 메모가 있는 상태로 뜨게 되는데 거기서 메모를 수정하고 저장을 누르면 업데이트 된다.
    }
}
