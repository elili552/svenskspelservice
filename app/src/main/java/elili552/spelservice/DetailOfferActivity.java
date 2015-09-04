package elili552.spelservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.regex.Pattern;


public class DetailOfferActivity extends Activity {

    ImageView detailOfferImage;
    TextView detailOfferTitle;
    TextView detailOfferDescription;
    TextView detailOfferText;
    TextView detailOfferThru;
    TextView detailOfferUrl;
    TextView detailOfferCompany;
    TextView detailOfferType;
    Button addToPersonalListBtn;
    String offerId = "";
    String offerTitle = "";
    String offerDescription = "";
    String offerText = "";
    String offerThru = "";
    String offerUrl= "";
    String offerCompany = "";
    String offerType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_offer);

        Intent intent = getIntent();

        // Get intent data
        offerTitle = intent.getStringExtra("title");
        offerDescription = intent.getStringExtra("description");
        offerText = intent.getStringExtra("text");
        offerThru = intent.getStringExtra("thru");
        offerUrl = intent.getStringExtra("url");
        //offerCompany = intent.getStringExtra("company");
        //offerType = intent.getStringExtra("offertype");

        // attach to layout our TextViews etc
        detailOfferImage = (ImageView) findViewById(R.id.offer_activity_image);
        detailOfferTitle = (TextView) findViewById(R.id.offer_activity_title_label);
        detailOfferDescription = (TextView) findViewById(R.id.offer_activity_description_label);
        detailOfferText = (TextView) findViewById(R.id.offer_activity_offer_text);
        detailOfferThru = (TextView) findViewById(R.id.offer_activity_thru);
        detailOfferUrl = (TextView) findViewById(R.id.offer_activity_url);
        detailOfferCompany = (TextView) findViewById(R.id.offer_activity_company);
        detailOfferType = (TextView) findViewById(R.id.offer_activity_type);

        Picasso.with(this)
                .load("http://svenskspelservice.se/wp-content/uploads/candian_gold_coin_wallpaper_2-1-360x150.jpg")
                .into(detailOfferImage);
        detailOfferTitle.setText(offerTitle);
        detailOfferDescription.setText(offerDescription);
        detailOfferText.setText(offerText);
        detailOfferThru.setText(offerThru);
        detailOfferUrl.setText(offerUrl);
        detailOfferCompany.setText(offerCompany);
        detailOfferType.setText(offerType);

        // make our urls become clickable (opens them in the browser)
        Linkify.addLinks(detailOfferUrl, Linkify.WEB_URLS);
        AddListenerOnButton();
    }

    public void AddListenerOnButton() {
        addToPersonalListBtn = (Button) findViewById(R.id.offer_activity_addPersonalListBtn);
        addToPersonalListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_offer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
