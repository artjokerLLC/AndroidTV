package com.smb.presentation.influencers;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.base.BaseFragment;
import com.smb.core.models.CategorizedInfluence;
import com.smb.core.models.complex.Influencer;
import com.smb.core.models.holders.InfluencersDataHolder;
import com.smb.views.HorizontalRecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.smb.views.HorizontalRecyclerView.addTo;

public class InfluencersFragment extends BaseFragment implements InfluencersView {
    public static final String TAG = "InfluencersFragment";
    @InjectPresenter
    InfluencersPresenter mInfluencersPresenter;
    private LinearLayout container;

    public static InfluencersFragment newInstance() {
        InfluencersFragment fragment = new InfluencersFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        container = view.findViewById(R.id.showsContainer);
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_influencers;
    }

    @Override
    public void onPresenterReady() {
        showProgress();

        mInfluencersPresenter.getData();
    }

    @Override
    public void onDataFetched(InfluencersDataHolder data) {

        hideProgress();

        addHorizontalList(data.getRecommended(), R.string.recommended_influencers_title);

        addHorizontalList(data.getFollowed(), R.string.followed_title);

        addHorizontalList(data.getCreators(), R.string.creators_title);

        List<CategorizedInfluence> categorizedInfluences = data.getCategorizedInfluences();
        for (int i = 0; i < categorizedInfluences.size(); i++) {
            CategorizedInfluence categorizedInfluence = categorizedInfluences.get(i);
            addHorizontalList(categorizedInfluence.getInfluencers(), categorizedInfluence.getCategory().getName());
        }

    }

    private void addHorizontalList(List<Influencer> influencers, @StringRes int title) {
        if (influencers != null && influencers.size() > 0) {
            HorizontalRecyclerView horizontalRecyclerView = addTo(container.getContext(), container);
            horizontalRecyclerView.getRecyclerView().setAdapter(new InfluencersAdapter(influencers));
            horizontalRecyclerView.getTitle().setText(title);
        }
    }

    private void addHorizontalList(List<Influencer> influencers, String title) {
        if (influencers != null && influencers.size() > 0) {
            HorizontalRecyclerView horizontalRecyclerView = addTo(container.getContext(), container);
            horizontalRecyclerView.getRecyclerView().setAdapter(new InfluencersAdapter(influencers));
            horizontalRecyclerView.getTitle().setText(title);
        }
    }
}
