package com.smb.data.repositories.tv;

import android.support.annotation.NonNull;

import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.data.repositories.AbstractRemoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import data.ShowsQuery;
import io.reactivex.Observable;

import static com.smb.data.http.graphql.GraphqlClientTypes.TEST;

public class TestShowsRepository extends AbstractRemoteRepository {
    private Random randomGenerator = new Random();

    @Inject
    public TestShowsRepository(Map<GraphqlClientTypes, ApolloClient> apollo) {
        super(apollo.get(TEST));
    }

    public Observable<Response<ShowsQuery.Data>> getShows() {
        ShowsQuery showsQuery = ShowsQuery.builder()
                .build();
        return query(showsQuery);
    }

    public Observable<String> getRandomHlsLink() {
        return getShows()
                .map(this::getLinksOfAllVideos)
                .map(this::getRandomLink);
    }

    private String getRandomLink(List<String> strings) {
        int index = randomGenerator.nextInt(strings.size());
        return strings.get(index);
    }

    @NonNull
    private List<String> getLinksOfAllVideos(Response<ShowsQuery.Data> dataResponse) {
        List<String> links = new ArrayList<>();
        List<ShowsQuery.Show> shows = dataResponse.data().shows();
        for (int i = 0; i < shows.size(); i++) {
            ShowsQuery.Show show = shows.get(i);
            List<ShowsQuery.Video> videos = show.video();
            for (int j = 0; j < videos.size(); j++) {
                ShowsQuery.Video video = videos.get(j);
                List<ShowsQuery.Chapter> chapters = video.chapter();
                for (int k = 0; k < chapters.size(); k++) {
                    ShowsQuery.Chapter chapter = chapters.get(k);
                    ShowsQuery.Media media = chapter.media();
                    links.add(media.fragments().mediaInfo().hls());
                }
            }
        }
        return links;
    }
}
