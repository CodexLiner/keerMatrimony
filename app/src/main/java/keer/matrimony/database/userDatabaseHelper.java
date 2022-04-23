package keer.matrimony.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import keer.matrimony.models.data;

public class userDatabaseHelper extends SQLiteOpenHelper {
    public userDatabaseHelper(Context context) {
        super(context, userDatabaseModel.DbName , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(userDatabaseModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + userDatabaseModel.TABLE_NAME);
    }
    public long insertUser(data data) {
        if (data== null){
            Log.d("TAG", "insertNote :minus");
            return -1;

        }
        // get writable database as we want to write data
        userDatabaseModel c = getUser(0);
        if (c!= null){
            delete(0);
           Log.d("TAG", "insertNote :fg");
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userDatabaseModel.ID, data.getId());
        values.put(userDatabaseModel.P_ID, 0);
        values.put(userDatabaseModel.CITY, data.getCity());
        values.put(userDatabaseModel.COUNTRY, data.getCountry());
        values.put(userDatabaseModel.COUNTRYCODE, data.getCountry_code());
        values.put(userDatabaseModel.GENDER, data.getGender());
        values.put(userDatabaseModel.DOB, data.getDob());
        values.put(userDatabaseModel.EMAIL, data.getEmail());
        values.put(userDatabaseModel.MOBILE, data.getMobile());
        values.put(userDatabaseModel.PARTNER, data.getPartner_preference());
        values.put(userDatabaseModel.PROFILE, data.getProfile());
        values.put(userDatabaseModel.SUBCAST, data.getSubcaste());
        values.put(userDatabaseModel.STATE, data.getState());
        values.put(userDatabaseModel.LAST, data.getLast_name());
        values.put(userDatabaseModel.NAME, data.getFirst_name());
        values.put(userDatabaseModel.STATUS, data.getStatus());
        // insert row
        long id = db.insert(userDatabaseModel.TABLE_NAME, null, values);
        // close db connection
        Log.d("TAG", "insertNote :i"+id);
        db.close();
        // return newly inserted row id
        return id;
    }
    public void delete(int id)
    {
        String[] args={String.valueOf(id)};
        int x  =getWritableDatabase().delete(userDatabaseModel.TABLE_NAME, "pid=?", args);
    }
    public userDatabaseModel getUser(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

      try{
          Cursor cursor = db.rawQuery("select * from "
                  +userDatabaseModel.TABLE_NAME+" where "+userDatabaseModel.P_ID+"=?" , new String[]
          {String.valueOf(id)});
/*          Cursor cursor = db.query(userDatabaseModel.TABLE_NAME,
                  new String[]{
                          userDatabaseModel.ID ,
                          userDatabaseModel.NAME ,
                          userDatabaseModel.LAST ,
                          userDatabaseModel.EMAIL ,
                          userDatabaseModel.SUBCAST ,
                          userDatabaseModel.GENDER ,
                          userDatabaseModel.COUNTRY ,
                          userDatabaseModel.DOB ,
                          userDatabaseModel.COUNTRYCODE ,
                          userDatabaseModel.MOBILE ,
                          userDatabaseModel.PARTNER ,
                          userDatabaseModel.STATE ,
                          userDatabaseModel.PROFILE ,
                          userDatabaseModel.CITY ,
                          userDatabaseModel.STATUS },
                  userDatabaseModel.ID + "=?",
                  new String[]{String.valueOf(id)}, null, null, null, null);*/

          if (cursor != null)
              cursor.moveToFirst();

          // prepare db object
          userDatabaseModel note = new userDatabaseModel(
                  cursor.getInt(cursor.getColumnIndex(userDatabaseModel.ID)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.NAME)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.LAST)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.EMAIL)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.COUNTRYCODE)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.MOBILE)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.DOB)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.SUBCAST)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.COUNTRY)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.STATE)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.CITY)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.GENDER)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.PROFILE)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.PARTNER)),
                  cursor.getString(cursor.getColumnIndex(userDatabaseModel.STATUS))
                 );

          // close the db connection
          cursor.close();
          return note;
      }catch (Exception ignored){
          Log.d("TAG", "getUser: "+ignored);
      }

        return null;
    }
}
