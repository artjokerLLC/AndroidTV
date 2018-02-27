package com.smb.core.models

import com.smb.core.models.complex.Influencer
import com.smb.core.models.complex.InfluencerContract

/**
 * Created by dev on 21.02.18.
 */

class TopInfluencer(impl: Influencer) : InfluencerContract by impl
