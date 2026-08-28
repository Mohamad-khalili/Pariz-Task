package com.pariz.task.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.pariz.task.R
import com.pariz.task.domain.model.Movies


@Composable
fun MoviesListScreen(
    modifier: Modifier,
    viewModel: MoviesViewModel = hiltViewModel()
) {

    MovieItem(modifier = modifier, viewModel)
}

@Composable
fun MovieItem(
    modifier: Modifier,
    viewModel: MoviesViewModel
) {
    val movies = viewModel.movies.collectAsLazyPagingItems()


    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        when {
            movies.loadState.refresh is LoadState.Loading -> {

                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            movies.loadState.refresh is LoadState.Error -> {
                val error = movies.loadState.refresh as LoadState.Error
                Text(
                    text = error.error.message ?: stringResource(R.string.something_went_wrong),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            movies.loadState.refresh is LoadState.NotLoading -> {
                LazyColumn(modifier = modifier) {
                    items(
                        movies.itemCount,
                        key = movies.itemKey { it.id }) { index ->
                        movies[index]?.let {
                            ItemRow(it)
                            if (index < movies.itemCount - 1) {
                                HorizontalDivider(
                                    Modifier.padding(horizontal = 24.dp),
                                    color = Color.LightGray,
                                    thickness = 1.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ItemRow(item: Movies) {
    val context = LocalContext.current
    Box(
        modifier = Modifier

    ) {

        Row(
            modifier = Modifier
                .padding(top = 8.dp, start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .height(110.dp)
        ) {
            AsyncImage(
                alignment = Alignment.Center,
                model = ImageRequest.Builder(context = context).data(item.poster).crossfade(true)
                    .build(),
                contentScale = ContentScale.FillBounds,

                placeholder = ColorPainter(Color.Gray),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column {
                item.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 16.sp,
                            maxFontSize = 20.sp,
                            stepSize = 1.sp
                        ),
                        maxLines = 1
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                item.year?.let { Text(it, style = MaterialTheme.typography.labelLarge) }
                Spacer(modifier = Modifier.height(8.dp))
                item.country?.let { Text(it, style = MaterialTheme.typography.labelMedium) }

            }

        }
    }
}
