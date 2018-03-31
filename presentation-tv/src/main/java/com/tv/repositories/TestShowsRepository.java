package com.tv.repositories;

import com.tv.models.Chapter;
import com.tv.models.Media;
import com.tv.models.Show;
import com.tv.models.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TestShowsRepository {

    private static final int MOCK_SHOWS_COUNT = 10;
    private static final int MOCK_VIDEOS_COUNT = 10;
    private static final int MOCK_CHAPTERS_COUNT = 10;
    private List<String> showsCovers;
    private ArrayList<String> covers;
    private ArrayList<String> hlsLinks;

    @Inject
    public TestShowsRepository() {
        createShowsCovers();
        createCovers();
        createHlsLinks();
    }

    public Observable<List<Show>> getShows() {
        return buildMockShows();
    }

    private Observable<List<Show>> buildMockShows() {
        List<Show> shows = new ArrayList<>();
        for (int i = 0; i < MOCK_SHOWS_COUNT; i++) {
            Show show = new Show();
            show.setId(UUID.randomUUID().toString());
            show.setTitle("Show #" + (i + 1));
            show.setDescription("Show description #" + (i + 1));
            show.setCover(getRandomData(showsCovers));
            show.setVideos(buildMockVideos(i + 1));
            shows.add(show);
        }
        return Observable.just(shows);
    }

    private ArrayList<Video> buildMockVideos(int iterator) {
        ArrayList<Video> videos = new ArrayList<>();
        for (int i = 0; i < MOCK_VIDEOS_COUNT; i++) {
            Video video = new Video();
            video.setId(UUID.randomUUID().toString());
            video.setCover(getRandomData(covers));
            video.setCoverTv(getRandomData(showsCovers));
            video.setTitle("Video #" + iterator + "." + (i + 1));
            video.setDescription("Video description #" + iterator + "." + (i + 1));
            video.setChapters(buildMockChapters());
            videos.add(video);
        }
        return videos;
    }

    private ArrayList<Chapter> buildMockChapters() {
        ArrayList<Chapter> chapters = new ArrayList<>();
        for (int i = 0; i < MOCK_VIDEOS_COUNT; i++) {
            Chapter chapter = new Chapter();
            chapter.setTitle("Chapter #" + (i + 1));
            chapter.setMedia(new Media(getRandomData(covers), getRandomData(hlsLinks)));
            chapters.add(chapter);
        }
        return chapters;
    }

    private void createShowsCovers() {
        showsCovers = new ArrayList<>();
        showsCovers.add("http://www.52dazhew.com/data/out/224/587439566-tv-series-hd-wallpapers.jpg");
        showsCovers.add("http://globalmedicalco.com/photos/globalmedicalco/2/5563.jpg");
        showsCovers.add("http://www.wallpapers4u.org/wp-content/uploads/lost_tv_show_series_2023_1920x1080.jpg");
        showsCovers.add("https://images4.alphacoders.com/806/806712.jpg");
        showsCovers.add("https://images5.alphacoders.com/408/408876.jpg");
        showsCovers.add("http://getwallpapers.com/wallpaper/full/d/9/6/1017940-amazing-friends-tv-show-wallpapers-1920x1080.jpg");
        showsCovers.add("https://hdqwalls.com/download/cress-williams-as-black-lightning-2018-tv-series-t7-1920x1080.jpg");
        showsCovers.add("http://hdqwalls.com/wallpapers/mr-robot-full-hd-poster.jpg");
        showsCovers.add("https://jackgamingandlife.files.wordpress.com/2015/03/forever-tv-series-wallpapers-hd-hdwallwide-com.jpg");
        showsCovers.add("https://img1.goodfon.ru/original/1920x1080/0/f9/daredevil-netflix-marvel.jpg");
        showsCovers.add("http://bsnscb.com/data/out/128/39519502-misfits-wallpapers.jpg");
    }

    private void createCovers() {
        covers = new ArrayList<>();
        covers.add("https://www.hdwallpapers.in/walls/marvel_deadpool_movie-wide.jpg");
        covers.add("https://www.hdwallpapers.in/walls/marvel_deadpool_movie-wide.jpg");
        covers.add("http://kb4images.com/images/movie-wallpaper/36551681-movie-wallpaper.jpg");
        covers.add("http://www.4kwallpapers.cf/web/wallpapers/joker-movie-wallpaper/thumbnail/lg.jpg");
        covers.add("https://wallpapersite.com/images/pages/pic_w/2361.jpg");
        covers.add("https://i.pinimg.com/originals/a5/97/2d/a5972d4de4fec90502e309a81a9ed5fa.jpg");
        covers.add("https://www.hdwallpapers.in/walls/pirates_of_the_caribbean_movie-HD.jpg");
        covers.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1bhovw4b6UDtbaIU82t9NO9TIg79ZKHkIrCd-x6I9B8hSff5D");
        covers.add("http://www.intrawallpaper.com/static/images/Earth-To-Echo-Movie-Wallpaper.jpg");
        covers.add("https://hdwallsource.com/img/2014/6/movie-wallpapers-6718-6967-hd-wallpapers.jpg");
        covers.add("https://wallpapercave.com/wp/VmN49xl.jpg");
        covers.add("https://i.pinimg.com/originals/ca/d3/d3/cad3d3b4a4098dd95ea1b02f42665c8e.jpg");
        covers.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7fX1CMJiFGdXtNuwNAb9aws-PKNRNMJrW2D0nrn-eTr3QpXlT");
        covers.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDveb4tvX-_XfZ4xWZc8qg74EAUFf09tnOxT8jIXLmVrp88BPmsQ");
        covers.add("http://www.100hdwallpapers.com/wallpapers/2880x1800/i_frankenstein_2014_movie-widescreen_wallpapers.jpg");
        covers.add("https://i.pinimg.com/originals/db/1a/76/db1a7696e38f9ca40c599b6585e1bdfc.jpg");
        covers.add("http://picsnook.com/wp-content/uploads/2017/12/Black-Panther-2018-Movie-Fantasy-Science-Fiction-Marvel-Film-Superhero.jpg");
        covers.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSlEBTKaRzVJHOUemb1gqJzpnXcpKZJhG4bSOT8yg-4oSBWmri");
        covers.add("http://4.bp.blogspot.com/-jjHA7irSLH8/T6htaxjixHI/AAAAAAAAEM0/2htfWMBBzys/s1600/the-chronicles-of-riddick-1366x768-wallpaper-5609.jpg");
        covers.add("http://www.designbolts.com/wp-content/uploads/2013/11/Thor_2-Official-Wallpaper-HD1.jpg");
        covers.add("https://wallpaperbits.com/wp-content/uploads/2018/01/2013-movie-wallpaper-oblivion-movie-2013-wide.jpg");
        covers.add("https://wallpaperfx.com/uploads/wallpapers/2016/03/24/18497/preview_the-jungle-book-movie.jpg");
        covers.add("http://fungyung.com/data/out/114/63400743-the-peanuts-movie-wallpapers.jpg");
        covers.add("https://images.wallpapersden.com/image/wxl-jason-momoa-aquaman-2018-movie_61193.jpg");
        covers.add("http://www.fotothing.com/photos/b60/b60ae36c909fdee789945d5d4083e052.jpg");
        covers.add("https://wallpaperfx.com/uploads/wallpapers/2016/03/11/18478/preview_doctor-strange-2016-movie.jpg");
        covers.add("https://cdn.photoshopstar.com/media/2010/01/avatar_movie_34full.jpg");
        covers.add("https://i.imgur.com/ZmvvmS6.jpg");
        covers.add("http://www.10wallpaper.com/wallpaper/medium/1703/Pirates_of_the_caribbean_dead_men_tell_no_tales-2017_Movie_Wallpaper_medium.jpg");
        covers.add("http://images5.fanpop.com/image/photos/26500000/Monster-House-Movie-Wallpaper-monster-house-26514290-500-400.jpg");
        covers.add("https://wallpaperfx.com/uploads/wallpapers/2015/06/23/17139/preview_tomorrowland-2015-movie.jpg");
        covers.add("https://avante.biz/wp-content/uploads/Hollywood-Movie-Wallpapers-HD/Hollywood-Movie-Wallpapers-HD-001.jpg");
    }

    private void createHlsLinks() {
        hlsLinks = new ArrayList<>();
        hlsLinks.add("http://www.streambox.fr/playlists/test_001/stream.m3u8");
        hlsLinks.add("https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8");
        hlsLinks.add("https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8");
        hlsLinks.add("https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8");
    }

    private String getRandomData(List<String> data) {
        int i = ThreadLocalRandom.current().nextInt(0, data.size());
        return data.get(i);
    }

    public Observable<Show> getRandomShow() {
        return buildMockShows()
                .map(list -> list.get(ThreadLocalRandom.current().nextInt(0, list.size())));
    }
}
