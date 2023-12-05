package com.example.roomdatabase.ui.halaman

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.roomdatabase.R
import com.example.roomdatabase.model.DetailSiswa
import com.example.roomdatabase.navigasi.DestinasiNavigasi

object DestinasiEntry : DestinasiNavigasi{
    override val route = "item_entry"
    override val titleRes = R.string.entry_siswa
}
@Composable
fun FormInputSiswa(
    detailSiswa: DetailSiswa,
    modifier: Modifier,
    onValueChange : (DetailSiswa) ->Unit = {},
    enabled:Boolean = true
){
    Column (

    ){

    }
}
