package com.smb.ui.show;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.app.DetailsFragmentBackgroundController;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewSharedElementHelper;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.smb.R;
import com.smb.models.Show;

/**
 * Displays a card with more details using a {@link DetailsFragment}.
 */
public class ShowFragment extends DetailsFragment implements OnItemViewClickedListener,
        OnItemViewSelectedListener, ShowView {

    public static final String SHOW = "SHOW";

    public static final String TRANSITION_NAME = "t_for_transition";
    public static final String EXTRA_CARD = "card";

    private static final long ACTION_PLAY = 1;
    private static final long ACTION_RENT = 2;
    private static final long ACTION_WISHLIST = 3;
    private static final long ACTION_RELATED = 4;

    private Action mActionPlay;
    private Action mActionRent;
    private Action mActionWishList;
    private Action mActionRelated;
    private ArrayObjectAdapter mRowsAdapter;
    //    private DetailedCard data;
    private final DetailsFragmentBackgroundController mDetailsBackground =
            new DetailsFragmentBackgroundController(this);

    private ShowPresenter showPresenter;

    public static ShowFragment newInstance(String showId) {
        Bundle args = new Bundle();
        args.putString(SHOW, showId);
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showPresenter = new ShowPresenter(this);
        showPresenter.getShow(getArguments().getString(SHOW));
        setupEventListeners();
    }

    private void setupUi(Show show) {

        // Setup fragment
        setTitle(show.getTitle());

        FullWidthDetailsOverviewRowPresenter rowPresenter = new FullWidthDetailsOverviewRowPresenter(
                new ShowDescriptionPresenter(getActivity())) {

            @Override
            protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup parent) {
                // Customize Actionbar and Content by using custom colors.
                RowPresenter.ViewHolder viewHolder = super.createRowViewHolder(parent);

                View actionsView = viewHolder.view.
                        findViewById(R.id.details_overview_actions_background);
                actionsView.setBackgroundColor(getActivity().getResources().
                        getColor(R.color.detail_view_actionbar_background));

                View detailsView = viewHolder.view.findViewById(R.id.details_frame);
                detailsView.setBackgroundColor(
                        getResources().getColor(R.color.detail_view_background));
                return viewHolder;
            }
        };

        FullWidthDetailsOverviewSharedElementHelper mHelper = new FullWidthDetailsOverviewSharedElementHelper();
        mHelper.setSharedElementEnterTransition(getActivity(), TRANSITION_NAME);
        rowPresenter.setListener(mHelper);
        rowPresenter.setParticipatingEntranceTransition(false);
        prepareEntranceTransition();

        ListRowPresenter shadowDisabledRowPresenter = new ListRowPresenter();
        shadowDisabledRowPresenter.setShadowEnabled(false);

        // Setup PresenterSelector to distinguish between the different rows.
        ClassPresenterSelector rowPresenterSelector = new ClassPresenterSelector();
        rowPresenterSelector.addClassPresenter(DetailsOverviewRow.class, rowPresenter);
        rowPresenterSelector.addClassPresenter(CardListRow.class, shadowDisabledRowPresenter);
        rowPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        mRowsAdapter = new ArrayObjectAdapter(rowPresenterSelector);

        // Setup action and detail row.
        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(show);
        Glide.with(getActivity())
                .load(show.getCover())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        detailsOverview.setImageDrawable(new BitmapDrawable(getResources(), resource));
                    }
                });
        ArrayObjectAdapter actionAdapter = new ArrayObjectAdapter();

        mActionPlay = new Action(ACTION_PLAY, getString(R.string.action_play));
        mActionRent = new Action(ACTION_RENT, getString(R.string.action_rent));
        mActionWishList = new Action(ACTION_WISHLIST, getString(R.string.action_wishlist));
        mActionRelated = new Action(ACTION_RELATED, getString(R.string.action_related));

        actionAdapter.add(mActionRent);
        actionAdapter.add(mActionWishList);
        actionAdapter.add(mActionRelated);
        detailsOverview.setActionsAdapter(actionAdapter);
        mRowsAdapter.add(detailsOverview);

        setAdapter(mRowsAdapter);
        new Handler().postDelayed(this::startEntranceTransition, 500);
        initializeBackground();
    }

    private void initializeBackground() {
        mDetailsBackground.enableParallax();
    }

    private void setupEventListeners() {
        setOnItemViewSelectedListener(this);
        setOnItemViewClickedListener(this);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                              RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (!(item instanceof Action)) return;
        Action action = (Action) item;
        long id = action.getId();

        if (id == ACTION_RENT) {

        }else if (action.getId() == ACTION_RELATED) {
            setSelectedPosition(1);
        } else {

        }
    }

    @Override
    public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                               RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (mRowsAdapter.indexOf(row) > 0) {
            int backgroundColor = getResources().getColor(R.color.default_background);
            getView().setBackgroundColor(backgroundColor);
        } else {
            getView().setBackground(null);
        }
    }

    @Override
    public void viewShow(Show show) {
        setupUi(show);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

