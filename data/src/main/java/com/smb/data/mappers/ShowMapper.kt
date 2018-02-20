package com.smb.data.mappers

import com.apollographql.apollo.api.Response
import com.smb.data.models.Show
import data.ShowQuery
import data.ShowsQuery

class ShowMapper {
    companion object {
        fun map(data : Response<ShowQuery.Data>): Show {
            return show.invoke(data.data()?.show()!!)
        }

        fun map(data : ShowsQuery.Show): Show {
            return showFromShowsQuery.invoke(data)
        }
    }
}