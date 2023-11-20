package co.za.planner.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class DailyPlanDAO {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DailyPlanDAO(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }




    public long insertDailyPlan(Date date, String mustDo, String appointment, String toDoList, String notes) {
        ContentValues values = new ContentValues();
        values.put(DailyPlanContract.DailyPlanEntry.COLUMN_NAME_DATE, date.getTime());
        values.put(DailyPlanContract.DailyPlanEntry.COLUMN_NAME_MUST_DO, mustDo);
        values.put(DailyPlanContract.DailyPlanEntry.COLUMN_NAME_APPOINTMENT, appointment);
        values.put(DailyPlanContract.DailyPlanEntry.COLUMN_NAME_TO_DO_LIST, toDoList);
        values.put(DailyPlanContract.DailyPlanEntry.COLUMN_NAME_NOTES, notes);

        return database.insert(DailyPlanContract.DailyPlanEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllDailyPlans() {
        String[] projection = {
                DailyPlanContract.DailyPlanEntry._ID,
                DailyPlanContract.DailyPlanEntry.COLUMN_NAME_DATE,
                DailyPlanContract.DailyPlanEntry.COLUMN_NAME_MUST_DO,
                DailyPlanContract.DailyPlanEntry.COLUMN_NAME_APPOINTMENT,
                DailyPlanContract.DailyPlanEntry.COLUMN_NAME_TO_DO_LIST,
                DailyPlanContract.DailyPlanEntry.COLUMN_NAME_NOTES,
        };

        return database.query(
                DailyPlanContract.DailyPlanEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

    public int deleteDailyPlan(long id) {
        String selection = DailyPlanContract.DailyPlanEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        return database.delete(
                DailyPlanContract.DailyPlanEntry.TABLE_NAME,
                selection,
                selectionArgs
        );
    }

}
