package com.example.khaledsabry.entertainment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.R;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class CompanyProductionViewHolder extends RecyclerView.ViewHolder {
    TextView companyName;
    ImageView poster;
    public CompanyProductionViewHolder(View itemView) {
        super(itemView);
        companyName = itemView.findViewById(R.id.companyid);
        poster = itemView.findViewById(R.id.posterimageid);
    }

    public void updateUi(ProductionCompany productionCompany) {
        companyName.setText(productionCompany.getName());
        ImageController.putImageMidQuality(productionCompany.getCompanyImage(), poster);
    }
}
