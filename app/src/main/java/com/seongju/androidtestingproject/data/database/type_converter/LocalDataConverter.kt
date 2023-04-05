package com.seongju.androidtestingproject.data.database.type_converter

import androidx.room.TypeConverter
import java.time.LocalDate

/**
 * LocalDataConverter
 *
 * LocalDate to String or String to LocalDate
 *
 * @author SeongJu Moon
 */
class LocalDataConverter {

    @TypeConverter
    fun dateToString(date: LocalDate): String {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(string: String): LocalDate {
        return LocalDate.parse(string)
    }

}