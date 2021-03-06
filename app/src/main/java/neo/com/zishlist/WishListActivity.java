package neo.com.zishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import neo.com.zishlist.database.RestaurantDAO;
import neo.com.zishlist.database.RestaurantDO;


public class WishListActivity extends Activity {

    /* Database Accessor */
    private RestaurantDAO restaurantDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        // get List View Object
        final ListView listview = (ListView) findViewById(R.id.listView);

        // initialize database
        restaurantDAO = new RestaurantDAO(this);
        try {
            restaurantDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // get restaurants from database & populate to a list
        List<RestaurantDO> restaurantDOs = restaurantDAO.getAllRestaurants();
        final ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < restaurantDOs.size(); i++) {
            list.add(restaurantDOs.get(i).getName());
        }

        // add user input
        Intent launchingIntent = getIntent();
        String restaurantWish = launchingIntent.getStringExtra("RESTAURANT_NAME");

        list.add(restaurantWish);

        // attach list to Array Adapter and that to List View object
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list);
        listview.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wish_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
