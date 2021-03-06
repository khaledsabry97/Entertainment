package com.example.khaledsabry.entertainment.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.FullPoster;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

//recycler view adapter for the production company
public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.ProductionCompanyViewHolder> {

    private ArrayList<ProductionCompany> productionCompanies;

    public ProductionCompanyAdapter(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    @NonNull
    @Override
    public ProductionCompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.production_company_cardview, parent, false);
        return new ProductionCompanyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionCompanyViewHolder holder, int position) {
        final ProductionCompany productionCompany = productionCompanies.get(position);
        holder.updateUi(productionCompany);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, FullPoster.newInstance(productionCompany.getCompanyImage())).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(productionCompanies == null)
            return 0;
        return productionCompanies.size();
    }


    class ProductionCompanyViewHolder extends RecyclerView.ViewHolder {
        TextView companyName;
        ImageView poster;

        public ProductionCompanyViewHolder(View itemView) {
            super(itemView);
            companyName = itemView.findViewById(R.id.companyid);
            poster = itemView.findViewById(R.id.posterimageid);
        }

        public void updateUi(ProductionCompany productionCompany) {
            companyName.setText(productionCompany.getName());
            ImageController.putImageMidQuality(productionCompany.getCompanyImage(), poster);
        }
    }
}
