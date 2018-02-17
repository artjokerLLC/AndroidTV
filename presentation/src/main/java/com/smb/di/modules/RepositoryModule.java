package com.smb.di.modules;

import com.smb.data.repositories.api.LocalUserRepository;
import com.smb.data.repositories.api.OrientationRepository;
import com.smb.data.repositories.api.RemoteUserRepository;
import com.smb.data.repositories.implementation.orientation.DeviceOrientationRepository;
import com.smb.data.repositories.implementation.user.GraphRemoteUserRepository;
import com.smb.data.repositories.implementation.user.SecureLocalUserRepository;

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
