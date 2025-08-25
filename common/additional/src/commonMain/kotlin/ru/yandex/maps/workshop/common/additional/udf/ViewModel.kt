package ru.yandex.maps.workshop.common.additional.udf

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class ViewModel<TState>(
    initialState: TState,
    reducer: Reducer<TState> = EmptyReducer<TState>()
) {
    companion object {
        private const val MAIN_JOB_KEY = "main.viewmodel.shared.coroutine.job"
    }

    private val coroutineTags = hashMapOf<String, CoroutineScope>()
    private val mainCoroutineContext = SupervisorJob() + Dispatchers.Main.immediate

    val viewModelScope: CoroutineScope
        get() = coroutineTags[MAIN_JOB_KEY] ?: launchNewScope()

    val store = Store(
        initialState = initialState,
        reduce = reducer,
        scope = viewModelScope
    )

    fun viewStates(): StateFlow<TState> = store.states()

    fun dispatch(viewEvent: Event) {
        sideEffect(viewEvent)
        store.dispatchAsync(viewEvent)
    }

    open fun sideEffect(viewEvent: Event) {}

    protected open fun onCleared() {}

    /**
     * Convenient method to perform work in [viewModelScope] scope.
     */
    protected fun withViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }

    // Launch view model scope except you provide a new key
    fun launchNewScope(
        key: String = MAIN_JOB_KEY,
        coroutineContext: CoroutineContext = mainCoroutineContext
    ): CoroutineScope =
        coroutineTags.getOrPut(key) {
            CoroutineScope(coroutineContext)
        }

    fun clear() {
        coroutineTags.forEach { it.value.cancel() }
        onCleared()
    }
}
