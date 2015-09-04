package elili552.spelservice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class OfferAdapter extends ArrayAdapter<Offer> {

    private ArrayList<Offer> offers;
    private Activity context;

    public OfferAdapter(Activity context, ArrayList<Offer> offers) {
        super(context, R.layout.list_row, offers);
        this.context = context;
        this.offers = offers;
    }


    public int getCount() {
        return offers.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View rowView = convertView;

        if (rowView == null) {
            //convertView = layoutInflater.inflate(R.layout.list_row, null);
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_row, null);

            // Configure the view holder
            OfferHolder viewHolder = new OfferHolder();
            viewHolder.mOfferImage = (ImageView) rowView.findViewById(R.id.row_image);
            viewHolder.mOfferTitle = (TextView) rowView.findViewById(R.id.row_title);
            viewHolder.mOfferCompany = (TextView) rowView.findViewById(R.id.row_company);
            rowView.setTag(viewHolder);
        }
         // Fill data
        OfferHolder holder = (OfferHolder) rowView.getTag();
        Offer offer = offers.get(position); // we are using a list that starts with index 0 not 1

        Picasso.with(context)
                .load(offer.getOfferImage())
                .into(holder.mOfferImage);

        holder.mOfferTitle.setText(offer.getOfferTitle());
        holder.mOfferCompany.setText(offer.getOfferCompany());

        return rowView;
    }

    static class OfferHolder
    {
        ImageView mOfferImage;
        TextView mOfferTitle;
        TextView mOfferCompany;
    }
}
