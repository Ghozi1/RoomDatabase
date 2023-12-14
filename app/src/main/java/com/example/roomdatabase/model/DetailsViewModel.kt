package com.example.roomdatabase.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.repositori.RepositoriSiswa
import com.example.roomdatabase.ui.halaman.DetailDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel (
    savedStateHandle : SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel(){

    private val siswaId: Int = checkNotNull(savedStateHandle[DetailDestination.siswaIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriSiswa.getSiswaStream(siswaId)
            .filterNotNull()
            .map{
                ItemDetailsUiState(detailSiswa = it.toDetailSiswa())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    suspend fun deletedItem(){
        repositoriSiswa.deleteSiswa(uiState.value.detailSiswa.toSiswa())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailSiswa: DetailSiswa = DetailSiswa()
)