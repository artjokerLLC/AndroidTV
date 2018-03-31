/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smb.ui.player;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v17.leanback.app.VideoFragment;
import android.support.v17.leanback.app.VideoFragmentGlueHost;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.smb.R;
import com.smb.models.Chapter;


/**
 * Plays selected video, loads playlist and related videos, and delegates playback to {@link
 * VideoPlayerGlue}.
 */
public class PlaybackFragment extends VideoFragment {

    private static final int UPDATE_DELAY = 16;
    public static final String CHAPTER_TAG = "CHAPTER_TAG";

    private VideoPlayerGlue mPlayerGlue;
    private LeanbackPlayerAdapter mPlayerAdapter;
    private SimpleExoPlayer mPlayer;
    private TrackSelector mTrackSelector;
//    private PlaylistActionListener mPlaylistActionListener;

    private Chapter chapter;
//    private Playlist mPlaylist;
//    private VideoLoaderCallbacks mVideoLoaderCallbacks;
//    private CursorObjectAdapter mVideoCursorAdapter;

    public static PlaybackFragment newInstance(Chapter chapter) {
        Bundle args = new Bundle();
        args.putParcelable(CHAPTER_TAG, chapter);
        PlaybackFragment fragment = new PlaybackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chapter = getActivity().getIntent().getParcelableExtra(CHAPTER_TAG);
//        mPlaylist = new Playlist();

//        mVideoLoaderCallbacks = new VideoLoaderCallbacks(mPlaylist);
//
//        // Loads the playlist.
//        Bundle args = new Bundle();
//        args.putString(VideoContract.VideoEntry.COLUMN_CATEGORY, chapter.category);
//        getLoaderManager()
//                .initLoader(VideoLoaderCallbacks.QUEUE_VIDEOS_LOADER, args, mVideoLoaderCallbacks);
//
//        mVideoCursorAdapter = setupRelatedVideosCursor();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || mPlayer == null)) {
            initializePlayer();
        }
    }

    /** Pauses the player. */
    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onPause() {
        super.onPause();

        if (mPlayerGlue != null && mPlayerGlue.isPlaying()) {
            mPlayerGlue.pause();
        }
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        mTrackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        mPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), mTrackSelector);
        prepareMediaForPlaying(chapter.getMedia().getHls());
        mPlayerAdapter = new LeanbackPlayerAdapter(getActivity(), mPlayer, UPDATE_DELAY);
        mPlayerGlue = new VideoPlayerGlue(getActivity(), mPlayerAdapter);
        mPlayerGlue.setHost(new VideoFragmentGlueHost(this));
        mPlayerGlue.playWhenPrepared();
        play(chapter);
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            mTrackSelector = null;
            mPlayerGlue = null;
            mPlayerAdapter = null;
        }
    }

    private void play(Chapter chapter) {
        mPlayerGlue.setTitle(chapter.getTitle());
//        mPlayerGlue.setSubtitle(video.description);
//        prepareMediaForPlaying(chapter.getMedia().getHls());
        mPlayerGlue.play();
    }

    private void prepareMediaForPlaying(String hls) {
//        String userAgent = Util.getUserAgent(getActivity(), "VideoPlayerGlue");
//        MediaSource mediaSource =
//                new ExtractorMediaSource(
//                        mediaSourceUri,
//                        new DefaultDataSourceFactory(getActivity(), userAgent),
//                        new DefaultExtractorsFactory(),
//                        null,
//                        null);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), getString(R.string.app_name)));
// This is the MediaSource representing the media to be played.
        HlsMediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(hls));

        mPlayer.prepare(videoSource);
    }

//    public void setupExoPlayer(String hls) {
//        // 1. Create a default TrackSelector
//        Handler mainHandler = new Handler();
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelection.Factory videoTrackSelectionFactory =
//                new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector =
//                new DefaultTrackSelector(videoTrackSelectionFactory);
//
//        // 2. Create the player
//        SimpleExoPlayer player =
//                ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//
//        // Bind the player to the view.
//        simpleExoPlayerView.setPlayer(player);
//
//// Produces DataSource instances through which media data is loaded.
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, getString(R.string.app_name)));
//// This is the MediaSource representing the media to be played.
//        HlsMediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(hls));
//// Prepare the player with the source.
//        player.prepare(videoSource);
//    }

//    private ArrayObjectAdapter initializeRelatedVideosRow() {
//        /*
//         * To add a new row to the mPlayerAdapter and not lose the controls row that is provided by the
//         * glue, we need to compose a new row with the controls row and our related videos row.
//         *
//         * We start by creating a new {@link ClassPresenterSelector}. Then add the controls row from
//         * the media player glue, then add the related videos row.
//         */
//        ClassPresenterSelector presenterSelector = new ClassPresenterSelector();
//        presenterSelector.addClassPresenter(
//                mPlayerGlue.getControlsRow().getClass(), mPlayerGlue.getPlaybackRowPresenter());
//        presenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
//
//        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(presenterSelector);
//
//        rowsAdapter.add(mPlayerGlue.getControlsRow());
//
//        HeaderItem header = new HeaderItem(getString(R.string.related_movies));
//        ListRow row = new ListRow(header, mVideoCursorAdapter);
//        rowsAdapter.add(row);
//
//        setOnItemViewClickedListener(new ItemViewClickedListener());
//
//        return rowsAdapter;
//    }

//    private CursorObjectAdapter setupRelatedVideosCursor() {
//        CursorObjectAdapter videoCursorAdapter = new CursorObjectAdapter(new CardPresenter());
//        videoCursorAdapter.setMapper(new VideoCursorMapper());
//
//        Bundle args = new Bundle();
//        args.putString(VideoContract.VideoEntry.COLUMN_CATEGORY, chapter.category);
//        getLoaderManager().initLoader(RELATED_VIDEOS_LOADER, args, mVideoLoaderCallbacks);
//
//        return videoCursorAdapter;
//    }

    public void skipToNext() {
        mPlayerGlue.next();
    }

    public void skipToPrevious() {
        mPlayerGlue.previous();
    }

    public void rewind() {
        mPlayerGlue.rewind();
    }

    public void fastForward() {
        mPlayerGlue.fastForward();
    }

//    /** Opens the video details page when a related video has been clicked. */
//    private final class ItemViewClickedListener implements OnItemViewClickedListener {
//        @Override
//        public void onItemClicked(
//                Presenter.ViewHolder itemViewHolder,
//                Object item,
//                RowPresenter.ViewHolder rowViewHolder,
//                Row row) {
//
//            if (item instanceof Video) {
//                Video video = (Video) item;
//
//                Intent intent = new Intent(getActivity(), VideoDetailsActivity.class);
//                intent.putExtra(VideoDetailsActivity.VIDEO, video);
//
//                Bundle bundle =
//                        ActivityOptionsCompat.makeSceneTransitionAnimation(
//                                        getActivity(),
//                                        ((ImageCardView) itemViewHolder.view).getMainImageView(),
//                                        VideoDetailsActivity.SHARED_ELEMENT_NAME)
//                                .toBundle();
//                getActivity().startActivity(intent, bundle);
//            }
//        }
//    }

//    /** Loads a playlist with videos from a cursor and also updates the related videos cursor. */
//    protected class VideoLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
//
//        static final int RELATED_VIDEOS_LOADER = 1;
//        static final int QUEUE_VIDEOS_LOADER = 2;
//
//        private final VideoCursorMapper mVideoCursorMapper = new VideoCursorMapper();
//
//        private final Playlist playlist;
//
//        private VideoLoaderCallbacks(Playlist playlist) {
//            this.playlist = playlist;
//        }
//
//        @Override
//        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//            // When loading related videos or videos for the playlist, query by category.
//            String category = args.getString(VideoContract.VideoEntry.COLUMN_CATEGORY);
//            return new CursorLoader(
//                    getActivity(),
//                    VideoContract.VideoEntry.CONTENT_URI,
//                    null,
//                    VideoContract.VideoEntry.COLUMN_CATEGORY + " = ?",
//                    new String[] {category},
//                    null);
//        }
//
//        @Override
//        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//            if (cursor == null || !cursor.moveToFirst()) {
//                return;
//            }
//            int id = loader.getId();
//            if (id == QUEUE_VIDEOS_LOADER) {
//                playlist.clear();
//                do {
//                    Video video = (Video) mVideoCursorMapper.convert(cursor);
//
//                    // Set the current position to the selected video.
//                    if (video.id == chapter.id) {
//                        playlist.setCurrentPosition(playlist.size());
//                    }
//
//                    playlist.add(video);
//
//                } while (cursor.moveToNext());
//            } else if (id == RELATED_VIDEOS_LOADER) {
//                mVideoCursorAdapter.changeCursor(cursor);
//            }
//        }
//
//        @Override
//        public void onLoaderReset(Loader<Cursor> loader) {
//            mVideoCursorAdapter.changeCursor(null);
//        }
//    }

//    class PlaylistActionListener implements VideoPlayerGlue.OnActionClickedListener {
//
//        private Playlist mPlaylist;
//
//        PlaylistActionListener(Playlist playlist) {
//            this.mPlaylist = playlist;
//        }
//
//        @Override
//        public void onPrevious() {
//            play(mPlaylist.previous());
//        }
//
//        @Override
//        public void onNext() {
//            play(mPlaylist.next());
//        }
//    }
}
