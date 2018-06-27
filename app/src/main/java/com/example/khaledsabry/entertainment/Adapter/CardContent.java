package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.khaledsabry.entertainment.R;


/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

public class CardContent extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;
    CardView cardView;

    public CardContent(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.card_image);
        textView = itemView.findViewById(R.id.card_text);
        cardView = itemView.findViewById(R.id.card_card);
    }
/*
    public void updateUi(Station station) {
        textView.setText(station.getTitle());
        imageView.setImageResource(station.getResource());}
*/
}
