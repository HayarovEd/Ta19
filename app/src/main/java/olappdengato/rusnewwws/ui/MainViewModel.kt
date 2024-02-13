package olappdengato.rusnewwws.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import olappdengato.rusnewwws.domain.repository.RemoteRepository
import olappdengato.rusnewwws.domain.utils.Resource
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteRepository) : ViewModel() {

    private var _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }


    private fun loadData() {
        viewModelScope.launch {
            when (val resultLoad = repository.getRemoteData()) {
                is Resource.Error -> {
                    _state.value.copy(
                        error = resultLoad.message,
                        isLoading = false
                    )
                        .updateStateUI()
                }
                is Resource.Success -> {
                    val result = resultLoad.data
                    _state.value.copy(
                        loans = result?: emptyList(),
                        isLoading = false,
                    )
                        .updateStateUI()
                }
            }
        }
    }


    private fun MainState.updateStateUI() {
        _state.update {
            this
        }
    }
}