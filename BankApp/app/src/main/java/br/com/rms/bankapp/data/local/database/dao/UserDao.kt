package br.com.rms.bankapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.rms.bankapp.data.local.database.entity.USER_ID
import br.com.rms.bankapp.data.local.database.entity.User
import io.reactivex.Single

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id =$USER_ID")
    abstract fun selectUser() : User

    @Query("SELECT userLogin FROM user WHERE id=$USER_ID")
    abstract fun selectUserName(): LiveData<String>

    @Query("SELECT accountId FROM user WHERE id=$USER_ID")
    abstract fun selectUserAccountId(): LiveData<Int>

}