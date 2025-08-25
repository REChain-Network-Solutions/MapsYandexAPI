package ru.yandex.maps.workshop.internal.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.yandex.maps.workshop.common.screen.PlacemarkViewState

@Composable
fun PlacemarkPager(
    placemarks: List<PlacemarkViewState>,
    selectedPlacemarkId: String?,
    onCardSelected: (String) -> Unit = {},
    onCardClick: (String) -> Unit = {},
    onGenerateClick: (String) -> Unit = {}
) {
    // Найти индекс выбранной карточки, fallback на первую
    val initialIndex = placemarks.indexOfFirst { it.id == selectedPlacemarkId }.coerceAtLeast(0)
    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        pageCount = { placemarks.size }
    )

    var isProgrammaticScroll by remember { mutableStateOf(false) }

    // Автоматический скролл если selectedPlacemarkId сменился вне pager'а
    LaunchedEffect(selectedPlacemarkId, placemarks) {
        val pageIndex = placemarks.indexOfFirst { it.id == selectedPlacemarkId }
        if (pageIndex != -1 && pageIndex != pagerState.currentPage) {
            isProgrammaticScroll = true
            pagerState.animateScrollToPage(pageIndex)
            isProgrammaticScroll = false
        }
    }

    // Обработка смены страницы
    LaunchedEffect(pagerState.currentPage) {
        val selected = placemarks.getOrNull(pagerState.currentPage)?.id
        if (selected != null && selected != selectedPlacemarkId && !isProgrammaticScroll) {
            onCardSelected(selected)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) { page ->
        val placemark = placemarks[page]
        PlacemarkCard(
            placemark = placemark,
            isSelected = placemark.id == selectedPlacemarkId,
            onClick = { onCardClick(placemark.id) },
            onGenerateClick = { onGenerateClick(placemark.id) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun PlacemarkCard(
    placemark: PlacemarkViewState,
    isSelected: Boolean,
    onClick: () -> Unit,
    onGenerateClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, Color.Blue) else null,
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = placemark.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = placemark.description ?: "Пока без подробностей",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 15,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))

            if (placemark.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(36.dp)
                )
            } else if (placemark.description == null) {
                Button(
                    onClick = onGenerateClick,
                ) {
                    Text("Сгенерировать описание")
                }
            }
        }
    }
}
