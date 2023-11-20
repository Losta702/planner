package co.za.planner.dao;

import android.provider.BaseColumns;

import java.sql.Date;

public class DailyPlanContract {

    private DailyPlanContract() {}

    public static class DailyPlanEntry implements BaseColumns {
        public static final String TABLE_NAME = "daily_plans";

        public static final String COLUMN_NAME_DATE = "date";

        public static final String COLUMN_NAME_MUST_DO = "must_do";
        public static final String COLUMN_NAME_APPOINTMENT = "appointment";

        public static final String COLUMN_NAME_TO_DO_LIST = "to_do_list";

        public static final String COLUMN_NAME_NOTES = "notes";

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DailyPlanEntry.TABLE_NAME + " (" +
                    DailyPlanEntry._ID + " INTEGER PRIMARY KEY," +
                    DailyPlanEntry.COLUMN_NAME_DATE + " DATE," +
                    DailyPlanEntry.COLUMN_NAME_MUST_DO + " TEXT," +
                    DailyPlanEntry.COLUMN_NAME_APPOINTMENT + " TEXT" +
                    DailyPlanEntry.COLUMN_NAME_TO_DO_LIST + "TEXT" +
                    DailyPlanEntry.COLUMN_NAME_NOTES + "Text" +")";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DailyPlanEntry.TABLE_NAME;
}
