package com.accenture.santander.dataManager.dao;

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
abstract class IDao<T>{

    @Insert
    abstract fun insert(type: T) : Long?

    @Update
    abstract fun update(type: T)

    @Delete
    abstract fun delete(type: T)
}