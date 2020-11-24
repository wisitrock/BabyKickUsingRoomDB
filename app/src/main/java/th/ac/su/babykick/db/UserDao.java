package th.ac.su.babykick.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import th.ac.su.babykick.model.Babymodel;

@Dao
public interface UserDao { // คลาสที่เอาไว้สำหรับการ เรียกใช้คำสั่งเพีื่อใช้ จัดการข้อมูลใน database

    @Query("SELECT * FROM babycounttb")
    Babymodel [] getAllUser();

    @Query("SELECT * FROM babycounttb WHERE id=:id")
    Babymodel getUserById(int id);

    @Insert
    void addUser(Babymodel users);

    @Update
    void updateUser(Babymodel users);

    @Delete
    void deleteUser(Babymodel user);

}
