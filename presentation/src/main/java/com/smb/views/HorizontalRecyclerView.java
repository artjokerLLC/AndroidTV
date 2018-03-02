package com.smb.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smb.R;

/**
 * Created by dev on 01.03.18.
 */

public class HorizontalRecyclerView extends RelativeLayout {

    private RecyclerView recyclerView;
    private TextView title;

    public HorizontalRecyclerView(Context context) {
        super(context);
        setup(context);
    }

    public HorizontalRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public HorizontalRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);

    }

    private void setup(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.horizontal_recycler, this, true);
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        title = root.findViewById(R.id.title);
    }


    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    public TextView getTitle() {
        return title;
    }


}
