package com.smb.presentation.shows;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.smb.R;
import com.smb.base.BaseFragment;
import com.smb.core.models.CategorizedShow;
import com.smb.core.models.Category;
import com.smb.core.models.Show;
import com.smb.core.models.holders.ShowsDataHolder;
import com.smb.views.HorizontalRecyclerView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.smb.views.HorizontalRecyclerView.addTo;

public class ShowsFragment extends BaseFragment implements ShowsView {
    public static final String TAG = "ShowsFragment";
    @InjectPresenter
    ShowsPresenter mShowsPresenter;
    private LinearLayout container;

    public static ShowsFragment newInstance() {
        ShowsFragment fragment = new ShowsFragment();

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
        return R.layout.fragment_shows;
    }

    @Override
    public void onPresenterReady() {
        showProgress();
        mShowsPresenter.getData();
    }

    @Override
    public void onDataFetched(ShowsDataHolder data) {
        hideProgress();
        List<Show> recommended = data.getRecommended();
        if (recommended != null && recommended.size() > 0) {
            HorizontalRecyclerView horizontalRecyclerView = addTo(container.getContext(), container);
            horizontalRecyclerView.getRecyclerView().setAdapter(new ShowsAdapter(recommended));
            horizontalRecyclerView.getTitle().setText(R.string.recommended_title);
        }

        List<Show> subscriptions = data.getSubscriptions();
        if (subscriptions != null && subscriptions.size() > 0) {
            HorizontalRecyclerView horizontalRecyclerView = addTo(container.getContext(), container);
            horizontalRecyclerView.getRecyclerView().setAdapter(new ShowsAdapter(subscriptions));
            horizontalRecyclerView.getTitle().setText(R.string.followed_title);
        }

        List<CategorizedShow> categories = data.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            CategorizedShow categorizedShow = categories.get(i);
            Category category = categorizedShow.getCategory();
            List<Show> shows = categorizedShow.getShows();
            HorizontalRecyclerView horizontalRecyclerView = addTo(container.getContext(), container);
            horizontalRecyclerView.getRecyclerView().setAdapter(new ShowsAdapter(shows));
            horizontalRecyclerView.getTitle().setText(category.getName());
        }
    }
}
