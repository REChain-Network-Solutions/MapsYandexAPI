package ru.yandex.maps.workshop.common

import com.yandex.mapkit.kmp.GeoObject
import com.yandex.mapkit.kmp.geometry.Point
import com.yandex.mapkit.kmp.mpChildren
import com.yandex.mapkit.kmp.obj
import com.yandex.mapkit.search.kmp.Response
import com.yandex.mapkit.search.kmp.SearchOptionsFactory
import com.yandex.mapkit.search.kmp.SessionSearchListener
import com.yandex.mapkit.search.kmp.mpCollection
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.yandex.maps.workshop.common.internal.createBoundingBox
import kotlin.coroutines.resumeWithException

interface SearchManager {

    suspend fun search(
        query: String,
        center: Point,
        radius: Double,
    ): List<GeoObject>

    companion object {

        operator fun invoke(
            searchManager: com.yandex.mapkit.search.kmp.SearchManager
        ) : SearchManager = object : SearchManager {
            override suspend fun search(
                query: String,
                center: Point,
                radius: Double,
            ): List<GeoObject> = suspendCancellableCoroutine { continuation ->
                val geometry = createBoundingBox(center, radius)

                val session = searchManager.submit(
                    query,
                    geometry,
                    SearchOptionsFactory.Default,
                    object : SessionSearchListener {
                        override fun onSearchResponse(response: Response) {
                            val list: List<GeoObject> = response.mpCollection.mpChildren.mapNotNull { it.obj }
                            continuation.resumeWith(Result.success(list))

                        }

                        override fun onSearchError(error: com.yandex.runtime.kmp.Error) {
                            continuation.resumeWithException(RuntimeException("Search error: $error"))
                        }
                    }
                )

                continuation.invokeOnCancellation { _ ->
                    session.cancel()
                }
            }
        }
    }
}
