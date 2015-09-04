package elili552.spelservice;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ListActivity {
    // we are going to use JSON data for the app
    private static final String TAG_OFFERS = "offers";
    private static final String TAG_ID = "id";
    private static final String TAG_THUMBNAIL = "thumbnail";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_TEXT = "text";
    private static final String TAG_THRU = "thru";
    private static final String TAG_URL = "url";
    private static final String TAG_COMPANY = "company";
    private static final String TAG_OFFERTYPE = "offertype";

    private ProgressDialog pDialog;
    private OfferAdapter adapter;
    private ListView listView;

    private JSONArray offers = null;
    private ArrayList<Offer> offerList;
    private ArrayList<HashMap<String, String>> JSONOfferList; // will hold all the json data for offers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONOfferList = new ArrayList<HashMap<String, String>>();
        listView = getListView();

        new GetOffers().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long offset) {

                String title = ((TextView) view.findViewById(R.id.row_title)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.row_company)).getText().toString();
                Log.v("the offers title", title);
                Log.v("the offers description", description);
                Intent detailView = new Intent(MainActivity.this, DetailOfferActivity.class);

                detailView.putExtra("title", title);
                detailView.putExtra("description", JSONOfferList.get(position).get("description"));
                detailView.putExtra("text", JSONOfferList.get(position).get("text"));
                detailView.putExtra("thru", JSONOfferList.get(position).get("thru"));
                //detailView.putExtra("url", JSONOfferList.get(position).get("url"));
                /*detailView.putExtra("company", JSONOfferList.get(position).get("company"));
                detailView.putExtra("type", JSONOfferList.get(position).get("type"));*/

                startActivity(detailView);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public ListView getMainActivityListView() { return listView;}
    public OfferAdapter getMainActivityAdapter() { return adapter;}

    public String loadJSONFromAssets() {
        String JSONData = null;

        try {
            Log.d("TAG", Arrays.toString(getAssets().list(".")));
        } catch (IOException e) {
            Log.e("TAG", e.getLocalizedMessage(), e);
        }
        try {

            InputStream is = getResources().openRawResource(R.raw.data);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            JSONData = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("IOexception message", e.getMessage());
            return null;
        }
        return JSONData;
    }



    private class GetOffers extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Hämtar tillgängliga erbjudanden...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            offerList = new ArrayList<Offer>();
            String jsondata = loadJSONFromAssets();
            Log.d("loaded data: ", "> " + jsondata);

            if (jsondata != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsondata);
                    // get JSON array node
                    offers = jsonObj.getJSONArray(TAG_OFFERS);

                    // Loop through all offers
                    for (int i = 0; i < offers.length(); i++) {
                        JSONObject c = offers.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String thumbnail = c.getString(TAG_THUMBNAIL);
                        String title = c.getString(TAG_TITLE);
                        String description = c.getString(TAG_DESCRIPTION);
                        String text = c.getString(TAG_TEXT);
                        String thru = c.getString(TAG_THRU);
                        String url = c.getString(TAG_URL);
                        String company = c.getString(TAG_COMPANY);
                        String offertype = c.getString(TAG_OFFERTYPE);

                        // temporary hashmap for a single offer

                        HashMap<String, String> offer = new HashMap<String, String>();

                        // add each child node to hashmap key => value

                        offer.put(TAG_ID, id);
                        offer.put(TAG_THUMBNAIL, thumbnail);
                        offer.put(TAG_TITLE, title);
                        offer.put(TAG_DESCRIPTION, description);
                        offer.put(TAG_TEXT, text);
                        offer.put(TAG_THRU, thru);
                        offer.put(TAG_URL, url);
                        offer.put(TAG_COMPANY, company);
                        offer.put(TAG_OFFERTYPE, offertype);


                        //add offer to the offer list
                        JSONOfferList.add(offer);
                        Offer offerData = new Offer(offer);
                        offerList.add(offerData);
                        //offerList.add(new Offer(id, thumbnail, title, description, text, thru, url, company, offertype));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("Loading JSON", "Could not load any data from erbjudanden.json");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog window
            if (pDialog.isShowing())
                pDialog.dismiss();

            adapter = new OfferAdapter(MainActivity.this, offerList);
            getMainActivityListView().setAdapter(getMainActivityAdapter());
        }
    }
}

