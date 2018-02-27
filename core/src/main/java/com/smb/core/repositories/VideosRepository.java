package com.smb.core.repositories;

import com.smb.core.models.Video;
import com.smb.core.models.util.Size;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by dev on 26.02.18.
 */

public interface VideosRepository {
    Observable<List<Video>> getUnwatchedVideos(Size size);
}
