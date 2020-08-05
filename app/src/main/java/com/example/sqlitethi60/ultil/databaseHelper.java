package com.example.sqlitethi60.ultil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlitethi60.model.Students;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "student.db";
    private static final String DATABASE_TABLE= "studentTab";

    private static final String ID = "_id";
    private static final String NAME= "_name";
    private static final String NUMBER = "_number";
    private static final String EMAIL = "_email";
    private Context context;

    public databaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ DATABASE_TABLE+" ("+
                ID +" INTEGER PRIMARY KEY, "+
                NAME+" TEXT, "+
                NUMBER +" TEXT, "+
                EMAIL+" TEXT)";
        db.execSQL(sql);

        Students students = new Students("hoai anh","234123412","asdas@ggmail.com");
        createSampleData(db,students);

    }

    private void createSampleData(SQLiteDatabase db,Students students){
        ContentValues cv = new ContentValues();

        cv.put(NAME,students.getName());
        cv.put(NUMBER,students.getNumber());
        cv.put(EMAIL,students.getEmail());

        db.insert(DATABASE_TABLE,null,cv);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(db);
    }
    public List<Students> getAllStudent(){
        List<Students > list = new ArrayList<Students>();
        String sql = "SELECT *FROM "+ DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Students students = new Students();
                students.setId(cursor.getInt(0));
                students.setName(cursor.getString(1));
                students.setNumber(cursor.getString(2));
                students.setEmail(cursor.getString(3));
                list.add(students);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public void addStudent(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(NAME,students.getName());
        cv.put(NUMBER,students.getNumber());
        cv.put(EMAIL,students.getEmail());

        db.insert(DATABASE_TABLE,null,cv);
        db.close();
    }

    public int updateStudent(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(NAME,students.getName());
        cv.put(NUMBER,students.getNumber());
        cv.put(EMAIL,students.getEmail());

        return db.update(DATABASE_TABLE,cv,ID +"=?",new String[]{String.valueOf(students.getId())});
    }

    public int deleteStudent(Students students){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv =new ContentValues();

        cv.put(NAME,students.getName());
        cv.put(NUMBER,students.getNumber());
        cv.put(EMAIL,students.getEmail());

        return db.delete(DATABASE_TABLE,ID +"=?",new String[]{String.valueOf(students.getId())});
    }
}
