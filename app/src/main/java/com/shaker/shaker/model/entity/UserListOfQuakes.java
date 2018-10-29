package com.shaker.shaker.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;


@Entity(
    primaryKeys = {"quake_id"},
    foreignKeys = {
    @ForeignKey(
        entity = UserQuake.class,
        parentColumns = "quake_id",
        childColumns = "quake_id"
    )
}
)
public class UserListOfQuakes {
@ColumnInfo(name = "quake_id", index = true)
private long quakeId;

    public long getQuakeId() {
        return quakeId;
    }

    public void setQuakeId(long quakeId) {
        this.quakeId = quakeId;
    }
}
