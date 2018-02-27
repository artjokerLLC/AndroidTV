package com.smb.di.modules;

import com.smb.core.repositories.InfluencersRepository;
import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.MagicHoursRepository;
import com.smb.core.repositories.OrientationRepository;
import com.smb.core.repositories.PrizesRepository;
import com.smb.core.repositories.RemoteUserRepository;
import com.smb.core.repositories.ShowRepository;
import com.smb.data.repositories.influencers.GraphInfluencersRepository;
import com.smb.data.repositories.mhours.GraphMagicHoursRepository;
import com.smb.data.repositories.orientation.DeviceOrientationRepository;
import com.smb.data.repositories.prizes.GraphPrizesRepository;
import com.smb.data.repositories.shows.GraphShowRepository;
import com.smb.data.repositories.user.GraphRemoteUserRepository;
import com.smb.data.repositories.user.SecureLocalUserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {
    @Binds
    abstract ShowRepository bindGraphShowRepository(GraphShowRepository impl);


    @Binds
    abstract RemoteUserRepository bindGraphRemoteUserRepository(GraphRemoteUserRepository impl);

    @Binds
    abstract LocalUserRepository bindSecureLocalUserRepository(SecureLocalUserRepository impl);

    @Binds
    abstract OrientationRepository bindDeviceOrientationRepository(DeviceOrientationRepository impl);

    @Binds
    abstract MagicHoursRepository bindGraphMagicHoursRepository(GraphMagicHoursRepository impl);

    @Binds
    abstract PrizesRepository bindGraphPrizesRepository(GraphPrizesRepository impl);

    @Binds
    abstract InfluencersRepository bindGraphInfluencersRepository(GraphInfluencersRepository impl);
}
