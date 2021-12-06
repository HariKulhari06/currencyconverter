import com.example.asssignmentsdktesttask.model.LoadState
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    emit(LoadState.Loading)
    val data = query().first()

    val flow: Flow<LoadState<ResultType>> = if (shouldFetch(data)) {
        emit(LoadState.Loading as LoadState<ResultType>)

        try {
            saveFetchResult(fetch())
            query().map { LoadState.Loaded(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { LoadState.Error(throwable) }
        }
    } else {
        query().map { LoadState.Loaded(it) }
    }

    emitAll(flow)
}