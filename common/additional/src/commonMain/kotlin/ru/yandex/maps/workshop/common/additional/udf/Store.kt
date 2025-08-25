package ru.yandex.maps.workshop.common.additional.udf

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

typealias Reducer<TState> = (TState, Event) -> TState

class EmptyReducer<T>: Reducer<T> {
    override fun invoke(p1: T, p2: Event): T = p1
}

class Store<TState>(
    private val scope: CoroutineScope,
    initialState: TState,
    reduce: Reducer<TState>
) {
    private val statesSubj = MutableStateFlow(initialState)

    private val actionsSubj = MutableSharedFlow<Event>()


    init {
        actionsSubj
            .onEach { action -> emitState(reduce(currentState, action)) }
            .launchIn(scope)
    }

    fun states(): StateFlow<TState> = statesSubj.asStateFlow()

    fun dispatchAsync(action: Event) {
        scope.launch {
            dispatch(action)
        }
    }

    suspend fun dispatch(action: Event) {
        actionsSubj.emit(action)
    }

    private val currentState: TState
        get() = statesSubj.value

    private suspend fun emitState(state: TState) {
        statesSubj.emit(state)
    }
}
