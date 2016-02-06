package DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by barri on 29/1/16.
 */
public class CustomDB extends SQLiteOpenHelper {

    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "Usuarios";

    //Declaracion del nombre de la tabla
    public static final String TABLE_NAME ="Usuarios";

    //Sentencia global de creacion de la base de datos
    public static final String USERS_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT, puntuacion INTEGER, notificacion TEXT);";



    public CustomDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERS_TABLE_CREATE);

    }

    public boolean checkUser(String user, String password, boolean op) {
        SQLiteDatabase db = this.getReadableDatabase();

        Boolean correct;

        if (op) {
            String[] columns = {"user"};
            String[] where = {user};
            Cursor c = db.query(
                    TABLE_NAME,                     // The table to query
                    columns,                        // The columns to return
                    "user=?",                       // The columns for the WHERE clause
                    where,                          // The values for the WHERE clause
                    null,                           // don't group the rows
                    null,                           // don't filter by row groups
                    null                            // The sort order
            );

            if (c.moveToFirst()) correct = true;
            else correct = false;
            c.close();
        }
        else {
            String[] columns = {"user", "password"};
            String[] where = {user, password};
            Cursor c = db.query(
                    TABLE_NAME,                     // The table to query
                    columns,                        // The columns to return
                    "user=? AND password=?",        // The columns for the WHERE clause
                    where,                          // The values for the WHERE clause
                    null,                           // don't group the rows
                    null,                           // don't filter by row groups
                    null                            // The sort order
            );
            if (c.moveToFirst()) correct = true;
            else correct = false;
            c.close();
        }

        return correct;
    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"user", "puntuacion"};
        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                columns,                                // The columns to return
                null,                                   // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                columns[1] + " ASC"                      // The sort order
        );
        return c;
    }

    public void createUser(String tableName, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    public boolean setPuntuacion(String user, int puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("puntuacion", puntuacion);
        String[] where = {user};
        return db.update(TABLE_NAME, cv, "user=?", where) > 0;
    }

    public String getNotificacion(String user) {
        String noti = "No he cogido nada de la BD";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"user", "notificacion"};
        String[] where = {user};
        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                columns,                                // The columns to return
                "user=?",                                   // The columns for the WHERE clause
                where,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        if (c.moveToFirst()) {
            noti = c.getString(c.getColumnIndex("notificacion"));
        }
        return noti;
    }

    public boolean setNotificacion(String user, String notificacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("notificacion", notificacion);
        String[] where = {user};
        return db.update(TABLE_NAME, cv, "user=?", where) > 0;
    }

    public int getPuntuacion(String user) {
        int punt = 935742100;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"user", "puntuacion"};
        String[] where = {user};
        Cursor c = db.query(
                TABLE_NAME,  // The table to query
                columns,                                // The columns to return
                "user=?",                                   // The columns for the WHERE clause
                where,                                   // The values for the WHERE clause
                null,                                   // don't group the rows
                null,                                   // don't filter by row groups
                null                                    // The sort order
        );
        if (c.moveToFirst()) {
            punt = c.getInt(c.getColumnIndex("puntuacion"));
        }
        return punt;
    }

    public boolean setPuntuacion(String user, String puntuacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("puntuacion", puntuacion);
        String[] where = {user};
        return db.update(TABLE_NAME, cv, "user=?", where) > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
