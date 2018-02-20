package com.smb.di.modules;

import com.smb.core.repositories.LocalUserRepository;
import com.smb.core.repositories.OrientationRepository;
import com.smb.core.repositories.RemoteUserRepository;
import com.smb.data.repositories.orientation.DeviceOrientationRepository;
import com.smb.data.repositories.user.GraphRemoteUserRepository;
import com.smb.data.repositories.user.SecureLocalUserRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract RemoteUserRepository bindGraphRemoteUserRepository(GraphRemoteUserRepository impl);

    @Binds
    abstract LocalUserRepository bindSecureLocalUserRepository(SecureLocalUserRepository impl);

    @Binds
    abstract OrientationRepository bindDeviceOrientationRepository(DeviceOrientationRepository impl);
}
