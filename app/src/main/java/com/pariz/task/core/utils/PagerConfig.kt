package com.pariz.task.core.utils

import androidx.paging.PagingConfig

object PagerConfig {
    const val DEFAULT_PAGE_INDEX = 1
    private const val DEFAULT_PAGE_SIZE = 10
    private const val PREFETCH_DISTANCE = 2

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE
        )
    }
}