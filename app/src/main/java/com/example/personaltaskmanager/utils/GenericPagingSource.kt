package com.example.personaltaskmanager.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 0
class GenericPagingSource<T:Any>(private val getData:suspend (pageSize:Int, offset:Int )->List<T>) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val items = getData.invoke(10 , pageIndex*10)
           LoadResult.Page(
                data = items,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex-1,
                nextKey = if (items.isEmpty())null else pageIndex+1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}