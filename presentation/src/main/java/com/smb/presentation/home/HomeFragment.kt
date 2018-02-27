package com.smb.presentation.home

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.smb.R
import com.smb.base.BaseFragment
import com.smb.core.models.holders.HomeScreenDataHolder
import com.smb.presentation.home.adapters.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(), HomeView {

    var dialog: AlertDialog? = null

    override val viewId: Int
        get() = R.layout.fragment_home

    @InjectPresenter
    lateinit var mHomePresenter: HomePresenter

    @ProvidePresenter()
    fun providePresenter() = HomePresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genresRecycler.layoutManager = linearLayoutManager()
        bannersRecycler.layoutManager = linearLayoutManager()
        topShowsRecycler.layoutManager = linearLayoutManager()
        topPrizesRecycler.layoutManager = linearLayoutManager()
        newReleasesRecycler.layoutManager = linearLayoutManager()
        almostYoursRecycler.layoutManager = linearLayoutManager()
        followedShowsRecycler.layoutManager = linearLayoutManager()
        topInfluencersRecycler.layoutManager = linearLayoutManager()
        continueWatchingRecycler.layoutManager = linearLayoutManager()
        followedInfluencersRecycler.layoutManager = linearLayoutManager()

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(bannersRecycler)

    }

    private fun linearLayoutManager() = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

    override fun onPresenterReady() {
        showProgress()
        mHomePresenter.getData()
    }

    override fun onDataFetched(homeScreenDataHolder: HomeScreenDataHolder?) {

        val bannersAdapter = BannersAdapter(homeScreenDataHolder?.banners)
        val genresAdapter = GenresAdapter(homeScreenDataHolder?.categories)
        val topShowsAdapter = TopShowsAdapter(homeScreenDataHolder?.topShows)
        val topPrizesAdapter = TopPrizesAdapter(homeScreenDataHolder?.prizes)
        val newReleasesAdapter = NewReleasesShowsAdapter(homeScreenDataHolder?.newReleases)
        val followedShowsAdapter = FollowedShowsAdapter(homeScreenDataHolder?.followedShows)
        val continueWatchingAdapter = ContinueWatchingAdapter(homeScreenDataHolder?.unwatched)
        val topInfluencersAdapter = TopInfluencersAdapter(homeScreenDataHolder?.topInfluencers)
        val almostYoursPrizesAdapter = AlmostYoursPrizesAdapter(homeScreenDataHolder?.almostYours)
        val followedInfluencersAdapter = FollowedInfluencersAdapter(homeScreenDataHolder?.followedInfluencers)

        genresRecycler.adapter = genresAdapter
        bannersRecycler.adapter = bannersAdapter
        topShowsRecycler.adapter = topShowsAdapter
        topPrizesRecycler.adapter = topPrizesAdapter
        newReleasesRecycler.adapter = newReleasesAdapter
        followedShowsRecycler.adapter = followedShowsAdapter
        almostYoursRecycler.adapter = almostYoursPrizesAdapter
        topInfluencersRecycler.adapter = topInfluencersAdapter
        continueWatchingRecycler.adapter = continueWatchingAdapter
        followedInfluencersRecycler.adapter = followedInfluencersAdapter

        hideProgress()
    }

    fun showProgress() {
        dialog?.dismiss()
        dialog = activity?.let {
            AlertDialog.Builder(it)
                    .setCancelable(false)
                    .setMessage("Loading")
                    .create()
        }
        dialog?.show()
    }

    fun hideProgress() {
        dialog?.dismiss()
        dialog = null
    }

    companion object {
        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}
