package neo.com.zishlist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * A helper class to get access to the Database Object.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PLACE = "place";

    private static final String DATABASE_NAME = "restaurants.db";
    private static final int DATABASE_VERSION = 1;

    /*
     * Private Method to create the database.
     */
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RESTAURANT + "("
            + COLUMN_ID + " integer primary key auto-increment, "
            + COLUMN_NAME + "name of the restaurant & "
            + COLUMN_PLACE + " place of the restaurant.);";


    /*
     * Public constructor.
     */
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /*
     * Implementing abstract method onCreate()
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }


    /*
     * Implementing abstract method onUpgrade()
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        onCreate(db);
    }
}
