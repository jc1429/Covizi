package com.example.covizi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "User.db";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String PASSWORD = "password";
    private static final String STATE = "state";
    private static final String IC = "icnumber";
    private static final String PHONE = "phonenumber";
    private static final String FEVER = "fever";
    private static final String COUGH = "cough";
    private static final String SHORTBREATH = "shortbreath";
    private static final String FATIGUE = "fatigue";
    private static final String MUSCLEACHE = "muscleache";
    private static final String LOSSTASTE = "losstaste";
    private static final String VOMIT = "vomit";
    private static final String POSITIVE = "positive";
    private static final String CLOSECONTACT = "closecontact";
    private static final String TRAVEL = "travel";
    private static final String STATUS = "status";


    //Constructor of DatabaseHelper
    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS(" + IC+ " TEXT PRIMARY KEY, " + FIRSTNAME + " TEXT NOT NULL, " + LASTNAME + " TEXT NOT NULL, " + STATE + " TEXT NOT NULL, " +
                PHONE + " TEXT NOT NULL UNIQUE, " + STATUS + " TEXT NOT NULL," + PASSWORD+ " TEXT NOT NULL)");
        db.execSQL("CREATE TABLE SYMPTOMS(" + IC+ " TEXT PRIMARY KEY, " + FEVER + " TEXT NOT NULL, " + COUGH + " TEXT NOT NULL, " +
               SHORTBREATH + " TEXT NOT NULL, " + FATIGUE+ " TEXT NOT NULL, " + MUSCLEACHE+ " TEXT NOT NULL," + LOSSTASTE + " TEXT NOT NULL,"
        + VOMIT+ " TEXT NOT NULL, " + POSITIVE + " TEXT NOT NULL, " + CLOSECONTACT + " TEXT NOT NULL, " + TRAVEL+ " TEXT NOT NULL)");
    }

    //Drop table if already exists
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS SYMTOMPS");

        onCreate(db);
    }

    //To insert users data
    public Boolean insertData(String ic, String firstname, String lastname, String state, String status, String phonenum, String passwords) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIRSTNAME, firstname);
        contentValues.put(LASTNAME, lastname);
        contentValues.put(PHONE, phonenum);
        contentValues.put(STATE, state);
        contentValues.put(STATUS, status);
        contentValues.put(PASSWORD, passwords);
        contentValues.put(IC, ic);
        long result = db.insert("users", null, contentValues);

        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    //To insert users data
    public Boolean insertUserSymptomData(String ic, String fever, String cough, String shortbreath, String fatigue, String muscleache, String losstaste, String vomit,
                                         String positive, String closecontact, String travel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IC, ic);
        contentValues.put(FEVER, fever);
        contentValues.put(COUGH, cough);
        contentValues.put(SHORTBREATH, shortbreath);
        contentValues.put(FATIGUE, fatigue);
        contentValues.put(MUSCLEACHE, muscleache);
        contentValues.put(LOSSTASTE, losstaste);
        contentValues.put(VOMIT, vomit);
        contentValues.put(POSITIVE, positive);
        contentValues.put(CLOSECONTACT, closecontact);
        contentValues.put(TRAVEL, travel);
        long result = db.insert("SYMPTOMS", null, contentValues);

        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    //To check user
    public Boolean checkuser(String ic) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE " + IC + " = ? ", new String[]{ic});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    //To check any same phone number in the database
    public Boolean checkphone(String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE " + PHONE + " = ? ", new String[]{phone});
        if(cursor.getCount()>0){
            return false;}
        else{
            return true;}
    }

    //To get user data
    public User getUserData(String icnum){
        SQLiteDatabase db = this.getReadableDatabase();
        String ic = null;
        String firstname = null;
        String lastname = null;
        String state = null;
        String phone = null;
        String pass = null;
        String status = null;

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE " + IC + " = ? ", new String[]{icnum});
        if (cursor.moveToFirst()) {
            ic = cursor.getString(0);
            firstname = cursor.getString(1);
            lastname = cursor.getString(2);
            state = cursor.getString(3);
            phone = cursor.getString(4);
            status = cursor.getString(5);
            pass = cursor.getString(6);
        }
        cursor.close();
        db.close();
        User user = new User(firstname,lastname,phone,pass,ic,state,status);
        return user;
    }

    //To get user's symptoms data
    public Symptoms getUserSymptomData(String icnum){
        SQLiteDatabase db = this.getReadableDatabase();
        String ic = null;
        String fever = null;
        String cough = null;
        String shortbreath = null;
        String fatigue = null;
        String muscleache = null;
        String losstaste = null;
        String vomit = null;
        String positive = null;
        String closecontact = null;
        String travel = null;



        Cursor cursor = db.rawQuery("SELECT * FROM symptoms WHERE " + IC + " = ? ", new String[]{icnum});
        if (cursor.moveToFirst()) {
            ic = cursor.getString(0);
            fever = cursor.getString(1);
            cough = cursor.getString(2);
            shortbreath = cursor.getString(3);
            fatigue = cursor.getString(4);
            muscleache = cursor.getString(5);
            losstaste = cursor.getString(6);
            vomit = cursor.getString(7);
            positive = cursor.getString(8);
            closecontact = cursor.getString(9);
            travel = cursor.getString(10);
        }
        cursor.close();
        db.close();
        Symptoms symptoms = new Symptoms(ic,fever,cough,shortbreath,fatigue,muscleache,losstaste,vomit,positive,closecontact,travel);
        return symptoms;
    }

    //To update user data
    public Boolean updateUserData(String ic,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PHONE, phone);
        long result = db.update("users", contentValues, IC + " = ?", new String[]{ic});

        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

    //To update symptoms data
    public Boolean UserStatus(String ic, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STATUS, status);
        long result = db.update("users", contentValues, IC + " = ?", new String[]{ic});

        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


    //To update symptoms data
    public Boolean UpdateUserSymptoms(String ic, String fever, String cough, String shortbreath, String fatigue, String muscleache, String losstaste, String vomit,
                                String positive, String closecontact, String travel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IC, ic);
        contentValues.put(FEVER, fever);
        contentValues.put(COUGH, cough);
        contentValues.put(SHORTBREATH, shortbreath);
        contentValues.put(FATIGUE, fatigue);
        contentValues.put(MUSCLEACHE, muscleache);
        contentValues.put(LOSSTASTE, losstaste);
        contentValues.put(VOMIT, vomit);
        contentValues.put(POSITIVE, positive);
        contentValues.put(CLOSECONTACT, closecontact);
        contentValues.put(TRAVEL, travel);
        long result = db.update("SYMPTOMS", contentValues, IC + " = ?", new String[]{ic});

        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }


    //To verify user identity
    public Boolean checkpass(String ic_num, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE " + IC + " = ? AND " + PASSWORD + " =? ", new String[]{ic_num, pass});

        if (cursor.getCount() == 1)
            return true;
        else
            return false;
    }

    //Delete particular user
    public Boolean deleteUser(String ic_num){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("users",IC + " = ?", new String[]{ic_num});

        if (result == -1)
            return false;
        else
            return true;
    }

    //To check any other user has same phone number in the database
    public Boolean checkedetphone(String phone, String ic) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE " + PHONE + " = ? AND " + IC + " != ?", new String[]{phone, ic});
        if(cursor.getCount()>0){
            return false;}
        else{
            return true;}
    }

}

