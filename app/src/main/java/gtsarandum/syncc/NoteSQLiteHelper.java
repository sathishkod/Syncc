package gtsarandum.syncc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by root on 30.08.14.
 */
public class NoteSQLiteHelper extends SQLiteOpenHelper {

    //
    public static final String TABLE_NOTES="notes";
    public static final String _NOTE_ID="_id";
    public static final String _NOTE_TEXT="_text";
    public static final String _NOTE_TITLE="_title";

    // create table "notes"
    // ("_id" integer primary key autoincrement,
    //"_text" text not null,"_title" text not null);
    private static final String DATABASE_CREATE_TABLE_NOTE=
            "create table "+
            TABLE_NOTES + "("+
            _NOTE_ID+
            " integer primary key autoincrement, "+
            _NOTE_TEXT+
            " text not null,"+
            _NOTE_TITLE+
            " text not null);"
            ;

    //
    private static final String DATABASE_NAME="notes.db";
    private static final int DATABASE_VERSION=1;

    public NoteSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);
        onCreate(sqLiteDatabase);
    }
}
