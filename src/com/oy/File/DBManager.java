package com.oy.File;


import com.oy.hy.Lifejob;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {
	private DatabaseHelper helper;
	private SQLiteDatabase db;
	private Context uer_context;
	
	public DBManager(Context context){
		uer_context = context;
		helper = new DatabaseHelper(uer_context, "oy_db");
		db = helper.getWritableDatabase();

	}
	
	
	public void add(Lifejob lifejobs)
	{

			//若为空则使用添加
			ContentValues values = new ContentValues();
			values.put("_id", lifejobs._id);
			values.put("buy", lifejobs.buy);
			values.put("food", lifejobs.food);
			values.put("wash", lifejobs.wash);
			values.put("clean", lifejobs.clean);
			
			db.insert("user", null, values);

	}
	
	public void updateData(Lifejob lifejob) {  
        ContentValues cv = new ContentValues();  
        cv.put("buy", lifejob.buy);  
        cv.put("food", lifejob.food);  
        cv.put("wash", lifejob.wash);  
        cv.put("clean", lifejob.clean);  
        db.update("user", cv,
        		"_id = ?",
        		new String[]{String.valueOf(lifejob._id)});  
    }  
	
	public Lifejob query(int getnum) {  
		
		Lifejob lifejobs = new Lifejob();
		Cursor cursor = db.rawQuery("SELECT * FROM user", null);  

		cursor.move(getnum);
		lifejobs.buy = cursor.getString(cursor.getColumnIndex("buy"));
		lifejobs.food = cursor.getString(cursor.getColumnIndex("food"));
		lifejobs.wash = cursor.getString(cursor.getColumnIndex("wash"));
		lifejobs.clean = cursor.getString(cursor.getColumnIndex("clean"));
		cursor.close();
		
        return lifejobs;
    }
	
	public Boolean exist(){
		Cursor cursor = db.rawQuery("SELECT * FROM user", null);  
		if(cursor.getCount()==0)
		{
			return false;
		}
		else {
			return true;
		}
	}
	
	public void closeDB() {  
        db.close();  
    }
}



