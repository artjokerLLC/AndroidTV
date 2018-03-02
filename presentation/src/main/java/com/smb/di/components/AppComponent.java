package com.smb.di.components;

import android.content.Context;

import com.smb.core.BaseUseCase;
import com.smb.data.di.modules.ApolloModule;
import com.smb.data.di.modules.ContextModule;
import com.smb.data.http.graphql.ApolloBuilder;
import com.smb.data.http.graphql.GraphqlClientTypes;
import com.smb.di.modules.CiceroneModule;
import com.smb.di.modules.RepositoryModule;
import com.smb.di.modules.SchedulersModule;
import com.smb.navigation.NavigationLifecycle;
import com.smb.presentation.auth.AuthPresenter;
import com.smb.presentation.home.HomePresenter;
import com.smb.presentation.home.activity.HomeRootPresenter;
import com.smb.presentation.influencers.InfluencersPresenter;
import com.smb.presentation.influencers.activity.InfluencersRootPresenter;
import com.smb.presentation.player.PlayerPresenter;
import com.smb.presentation.shows.ShowsPresenter;
import com.smb.presentation.shows.activity.ShowsRootPresenter;
import com.smb.presentation.test.TestPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * Created by dev on 24.01.18.
 */
@Singleton
@Component(modules = {ContextModule.class, ApolloModule.class, RepositoryModule.class, CiceroneModule.class, SchedulersModule.class})
public interface AppComponent {

    Context getContext();

    Map<GraphqlClientTypes, ApolloBuilder> getApollo();

    Router getRouter();

    NavigatorHolder getNavigationHolder();

    Map<BaseUseCase.SchedulerType, Scheduler> getShedulers();

    void inject(@NotNull AuthPresenter authPresenter);

    void inject(@NotNull TestPresenter testPresenter);

    void inject(@NotNull PlayerPresenter playerPresenter);

    void inject(NavigationLifecycle navigationLifecycle);

    void inject(HomeRootPresenter navigationPresenter);

    void inject(HomePresenter homePresenter);

    void inject(ShowsRootPresenter showsRootPresenter);

    void inject(ShowsPresenter showsPresenter);

    void inject(InfluencersRootPresenter influencersRootPresenter);

    void inject(InfluencersPresenter influencersPresenter);
}
