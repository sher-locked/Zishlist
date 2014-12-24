package neo.com.zishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLException;

import neo.com.zishlist.database.RestaurantDAO;
import neo.com.zishlist.database.RestaurantDO;


public class MainActivity extends Activity {

    /* Database Accessor */
    private RestaurantDAO restaurantDAO;

    // Wish Inputs
    private EditText nameInput;
    private EditText placeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText) findViewById(R.id.nameInput);
        placeInput = (EditText) findViewById(R.id.placeInput);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * Add Wish to Wish-list action
     */
    public void addWish(View view) {

        String restaurantName = nameInput.getText().toString();
        String restaurantPlace = placeInput.getText().toString();

        // add to the database
        RestaurantDO restaurantDO = new RestaurantDO();
        restaurantDO.setName(restaurantName);
        restaurantDO.setPlace(restaurantPlace);

        // initialize database and add restaurant
        restaurantDAO = new RestaurantDAO(this);
        try {
            restaurantDAO.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        restaurantDAO.createRestaurant(restaurantDO);
        restaurantDAO.close();

        Intent addWishIntent = new Intent(this, WishListActivity.class);
        addWishIntent.putExtra("RESTAURANT_NAME", restaurantName);
        setResult(RESULT_OK, addWishIntent);

        startActivity(addWishIntent);
    }
}
