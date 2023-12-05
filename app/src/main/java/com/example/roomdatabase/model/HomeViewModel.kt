package com.example.roomdatabase.model

import androidx.lifecycle.ViewModel
import com.example.roomdatabase.repositori.RepositoriSiswa

class HomeViewModel(private val repositoriSiswa: RepositoriSiswa): ViewModel(){
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUiState : StateFlow<HomeUiState> = repositoriSiswa.getAllSiswaStream().filterNotNull()
        .map { HomeUiState(listSiswa = it.toList()) }
        .stateIn(scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    data class HomeUiState(
        val listSiswa: List<Siswa> = listOf()
    )
}