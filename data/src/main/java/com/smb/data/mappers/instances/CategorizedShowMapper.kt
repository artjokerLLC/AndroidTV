package com.smb.data.mappers.instances

import com.smb.core.models.CategorizedShow
import com.smb.core.models.Category
import com.smb.core.models.Show
import java.util.*

/**
 * Created by dev on 27.02.18.
 */

object CategorizedShowMapper {

    private fun toList(byCategory: Map<Category, List<Show>>): ArrayList<CategorizedShow> {
        val categorizedShows = ArrayList<CategorizedShow>()
        for ((key, value) in byCategory) {
            val categorizedShow = CategorizedShow(key)
            categorizedShow.shows = value
            categorizedShows.add(categorizedShow)
        }
        return categorizedShows
    }

    private fun toMap(shows: List<Show>): Map<Category, List<Show>> {
        val byCategory = HashMap<Category, MutableList<Show>>()

        for (i in shows.indices) {
            val show = shows[i]
            val categories = show.categories
            categories?.let {
                for (j in categories.indices) {
                    val category = categories[j]
                    var showList: MutableList<Show>? = byCategory[category]
                    if (showList == null) {
                        showList = ArrayList()
                    }
                    showList.add(show)
                    byCategory[category] = showList
                }
            }


        }
        return byCategory
    }

    fun map(shows: List<Show>): ArrayList<CategorizedShow> {
        return toList(toMap(shows))
    }

}
