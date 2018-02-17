package com.smb.data.repositories.implementation.orientation

import android.content.Context
import android.view.OrientationEventListener
import com.smb.data.RxBus
import com.smb.data.repositories.api.OrientationRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by dev on 17.02.18.
 */

class DeviceOrientationRepository @Inject
constructor(context: Context) : OrientationRepository {

    private val orientationEventListener: OrientationEventListener
    private val orientationRxBus = RxBus<OrientationRepository.Orientation>()
    private var orientation: OrientationRepository.Orientation? = null

    init {
        orientationEventListener = object : OrientationEventListener(context) {
            override fun onOrientationChanged(angle: Int) {
                val isLandscape = isLandscape(angle)
                val newOrientation = if (isLandscape) OrientationRepository.Orientation.LANDSCAPE else OrientationRepository.Orientation.PORTRAIT
                if (orientation == null || orientation?.ordinal != newOrientation.ordinal) {
                    orientation = newOrientation
                    orientationRxBus.send(orientation)
                }
            }
        }
    }

    override fun get(): Observable<OrientationRepository.Orientation> {
        return orientationRxBus.toObservable()
    }

    override fun start() {
        orientationEventListener.enable()
    }

    override fun stop() {
        orientationEventListener.disable()
    }

    fun isLandscape(orientation: Int): Boolean {
        return orientation in 46..134 || orientation in 226..314//360 degree
    }


}
