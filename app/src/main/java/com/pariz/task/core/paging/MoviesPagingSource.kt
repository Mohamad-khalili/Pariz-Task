package com.pariz.task.core.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pariz.task.core.utils.PagerConfig
import com.pariz.task.data.remote.MoviesApiService
import com.pariz.task.domain.model.Movies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val api: MoviesApiService,
    private val dispatcher: CoroutineDispatcher
) : PagingSource<Int, Movies>() {
    override fun getRefreshKey(state: PagingState<Int, Movies>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movies> {
        val page = params.key ?: PagerConfig.DEFAULT_PAGE_INDEX

        return try {
            val response = withContext(dispatcher) {
                api.getMoviesList(page)
            }
            if (response.isSuccessful) {

                val movies = response.body()?.movies ?: emptyList()

                LoadResult.Page(
                    data = movies,
                    prevKey = if (page == PagerConfig.DEFAULT_PAGE_INDEX) null else page -1,
                    nextKey = if (movies.isEmpty()) null else page +1
                )
            } else {
                LoadResult.Error(Throwable(response.errorBody().toString()))
            }

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}