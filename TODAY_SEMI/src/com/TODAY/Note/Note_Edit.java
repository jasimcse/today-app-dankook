/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.TODAY.Note;

import com.TODAY.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Note_Edit extends Activity {
    //private EditText mTitleText;
    private EditText mBodyText;
    private Long mRowId;
    private NotesDbAdapter mDbHelper;
	private Cursor mNotesCursor;
    
    private String day; //년,월,일을 받아오는 day
    
    private boolean IsNew = true; 
    //날짜를 클릭했을때 메모가 저장되어있지않으면 createNote()실행하고 아니면 	updateNote()실행하게 하려고 만듬
    //기본은 true
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();        
        
		Intent i = getIntent();
        day = i.getStringExtra("day"); //년, 월, 일을 스트링으로 받음(day)
  
        setContentView(R.layout.note_edit);
       
        mBodyText = (EditText) findViewById(R.id.body);

        Button confirmButton = (Button) findViewById(R.id.confirm);
        Button cancelButton = (Button) findViewById(R.id.cancel);
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
									: null;
		}
		

		// 이렇게 갔다쓰면 된다.
//		Cursor cur = mDbHelper.fetchNoteByDay("2012-5-2");
//		if(cur.getCount() > 0)
//		{
//			Log.i("2012-5-2's Memo",cur.getString(cur.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
//		}
		
		
		Log.i("mRowID!?", String.valueOf(mRowId));


		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			//삭제버튼 이벤트
            public void onClick(View view) {
            	mDbHelper.deleteNote(day);
                setResult(RESULT_OK);
                finish();
            }

        });
 
        confirmButton.setOnClickListener(new View.OnClickListener() {
        	//저장버튼 이벤트
            public void onClick(View view) {
            	Log.i("MBody Context", mBodyText.getText().toString());
            	if(IsNew) 
            		mDbHelper.createNote(mBodyText.getText().toString(), day);
            	else
            		mDbHelper.updateNote(mBodyText.getText().toString(), day);
	       //위에서 설명한데로 IsNes를 이용해서 create, update실행     	
                setResult(RESULT_OK);
                finish();
            	
            }
        });        
    }
    
    private void populateFields() {
        if (day != null) {				// Day의 형식은 2012-5-2일이다.
            Cursor note = mDbHelper.fetchNoteByDay(day); 		// 결국 mDbHelper를 이용하여 cursur를 얻게 되고.. 이 note를 이용해서 뿌려주게 된다.
            Log.i("Day",day);
            if( note.getCount() > 0 )
            {
	            startManagingCursor(note);	            
	            mBodyText.setText(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
	            IsNew = false;
            }//day를  조회해서 메모가 있으면 디비에 저장된 body를 뿌려주고
        }	 //아니면 createNote()실행(IsNew가 False니깐)
    }

    @Override
    protected void onResume() { //Activity가 새로 호출될때 실행
        super.onResume();
        
        populateFields();			// 여기를 항상 먼저 실행해서 DB가 있는지 확인한다.
    }
    
    /*
	private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.fetchNote(mRowId);
            startManagingCursor(note);
           // mTitleText.setText(note.getString(
           //         note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { 
    	//Activity 가 stop 되고 새로 시작되기 전에 Activity 가 죽을 때 호출된다.
    	super.onSaveInstanceState(outState);
        //saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() { //Activity가 종료할때 호출
        super.onPause();
        saveState();
    }
    */
    /*
    private void saveState() { 
    //    String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        
        
        if (mRowId == null) {
            long id = mDbHelper.createNote(body,day);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateNote(mRowId, body);
        }
    }
*/
}
