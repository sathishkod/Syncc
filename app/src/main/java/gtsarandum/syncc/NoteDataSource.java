package gtsarandum.syncc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.SQLException;

/**
 * Created by root on 30.08.14.
 */
public class NoteDataSource {

    //attr
    private SQLiteDatabase database;
    private NoteSQLiteHelper noteSQLiteHelper;
    private String[] noteCollumns={
            NoteSQLiteHelper._NOTE_ID,      //0
            NoteSQLiteHelper._NOTE_TEXT,    //1
            NoteSQLiteHelper._NOTE_TITLE    //2
    };

    public NoteDataSource(Context context){
        noteSQLiteHelper=new NoteSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database=noteSQLiteHelper.getWritableDatabase();
    }

    public void close(){
        noteSQLiteHelper.close();
    }

    public SynccNote createSynccNote (String text, String title){
        ContentValues contentValues=new ContentValues();
        contentValues.put(NoteSQLiteHelper._NOTE_TEXT, text);
        contentValues.put(NoteSQLiteHelper._NOTE_TITLE, title);
        long insertId=database.insert(NoteSQLiteHelper.TABLE_NOTES,null,contentValues);
        Cursor cursor=database.query(
                NoteSQLiteHelper.TABLE_NOTES,
                noteCollumns,
                NoteSQLiteHelper._NOTE_TEXT+" = "+insertId,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        return cursorToNote(cursor);

    }

    private SynccNote cursorToNote(Cursor cursor){
        String id="noId";
        String text="noText";
        String title="noTitle";

        try {
            id=cursor.getString(0);
            text=cursor.getString(1);
            title=cursor.getString(2);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new SynccNote(id,text,title);
    }
}
