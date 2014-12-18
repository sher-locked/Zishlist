package neo.com.zishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {

    /* Text Input */
    private EditText wishText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wishText = (EditText) findViewById(R.id.wishInput);
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

        String restaurantName = wishText.getText().toString();

        Intent addWishIntent = new Intent(this, WishListActivity.class);
        addWishIntent.putExtra("RESTAURANT_NAME", restaurantName);
        setResult(RESULT_OK, addWishIntent);

        startActivity(addWishIntent);
    }
}
