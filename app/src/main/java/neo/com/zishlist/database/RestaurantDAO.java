package neo.com.zishlist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The data accessor object.
 * It maintains database connection & handles CRUD operations.
 */
public class RestaurantDAO {

    // database helpers
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    // database columns
    private String[] allColumns = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NAME,
            MySQLiteHelper.COLUMN_PLACE
    };

    /*
     * Public Constructor.
     */
    public RestaurantDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    // database access operations
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    // data access operations

    /*
     * Add a restaurant entry to the database.
     */
    public RestaurantDO createRestaurant(RestaurantDO restaurantDO) {

        // inflate row
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, restaurantDO.getName());
        values.put(MySQLiteHelper.COLUMN_PLACE, restaurantDO.getPlace());

        // move cursor to insertedId
        long insertedId = database.insert(
                MySQLiteHelper.TABLE_RESTAURANT,
                null,
                values);
        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_RESTAURANT,
                allColumns,
                MySQLiteHelper.COLUMN_ID + "=" + insertedId,
                null, null, null, null
        );
        cursor.moveToFirst();

        // get restaurant from id
        RestaurantDO newRestaurantDO = cursorToRestaurant(cursor);
        cursor.close();

        return newRestaurantDO;
    }


    /*
     * Helper method to get RestaurantDO from Id.
     */
    private RestaurantDO cursorToRestaurant(Cursor cursor) {

        RestaurantDO restaurantDO = new RestaurantDO();

        // inflate DO
        restaurantDO.setId(cursor.getLong(0));
        restaurantDO.setName(cursor.getString(1));
        restaurantDO.setPlace(cursor.getString(2));

        return restaurantDO;
    }


    /*
     * Get all restaurant entries from the database.
     */
    public List<RestaurantDO> getAllRestaurants() {

        List<RestaurantDO> allRestaurants = new ArrayList<RestaurantDO>();

        // move cursor to first row
        Cursor cursor = database.query(
                MySQLiteHelper.TABLE_RESTAURANT,
                allColumns,
                null, null, null, null, null
        );
        cursor.moveToFirst();

        // iterate to get all restaurants
        while (!cursor.isAfterLast()) {
            RestaurantDO restaurantDO = cursorToRestaurant(cursor);
            allRestaurants.add(restaurantDO);
            cursor.moveToNext();
        }
        cursor.close();

        return allRestaurants;
    }

}
