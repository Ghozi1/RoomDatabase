package com.example.roomdatabase.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface SiswaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa : Siswa)

    @Update
    suspend fun update(siswa: Siswa)

    @Delete
    suspend fun delete(siswa: Siswa)

    @Query("SELECT * From tblSiswa WHERE id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Query("SELECT * From tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>
}