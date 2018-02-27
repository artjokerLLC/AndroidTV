package com.smb.presentation.home.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smb.R;
import com.smb.core.models.Prize;
import com.smb.core.models.Sponsor;

import java.util.List;

/**
 * Created by dev on 20.02.18.
 */

public class AlmostYoursPrizesAdapter extends AbstractPreviewAdapter {
    private List<Prize> prizes;

    public AlmostYoursPrizesAdapter(List<Prize> prizes) {
        super();
        this.prizes = prizes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(getView(parent, R.layout.adapter_item_almost_your_prize));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Prize topPrize = prizes.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        loadImageTo(viewHolder.prize, topPrize);
        Sponsor sponsor = topPrize.getSponsor();
        viewHolder.sponsor.setText(String.format("by %s", sponsor.getName()));
        viewHolder.carats.setText(String.format("%s / %s", String.valueOf(sponsor.getCaratsEarned()), String.valueOf(sponsor.getCaratsaAvailable())));
    }

    @Override
    public int getItemCount() {
        return prizes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prize;
        TextView sponsor;
        TextView carats;


        public ViewHolder(View itemView) {
            super(itemView);
            prize = itemView.findViewById(R.id.imagePrize);
            sponsor = itemView.findViewById(R.id.sponsorTextView);
            carats = itemView.findViewById(R.id.caratsCountTextView);
        }
    }
}
