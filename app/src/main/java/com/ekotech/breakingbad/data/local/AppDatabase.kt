package com.ekotech.breakingbad.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BreakingBadCharactersLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(), BreakingBadDatabase