package com.example.roomdatabase.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.repositori.RepositoriSiswa
import com.example.roomdatabase.ui.halaman.ItemEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHanle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel(){
    var siswaUiState by mutableStateOf(UIStateSiswa())
        private set

    private val itemId: Int = checkNotNull(savedStateHanle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            siswaUiState = repositoriSiswa.getSiswaStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateSiswa(true)
        }
    }

    suspend fun updateSiswa(){
        if (validasiInput(siswaUiState.detailSiswa)){
            repositoriSiswa.updateSiswa(siswaUiState.detailSiswa.toSiswa())
        }
        else {
            println("Data Tidak Valid")
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        siswaUiState = UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    private fun validasiInput(uiState: DetailSiswa = siswaUiState.detailSiswa) : Boolean {
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }
}