package com.example.groupproject.database.typeConverters;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listTypeConverter {
    @TypeConverter
    public static String fromList(List<String> list) {
        return list != null ? TextUtils.join(",", list) : null;
    }

    @TypeConverter
    public static List<String> toList(String data) {
        return data != null ? Arrays.asList(data.split(",")) : new ArrayList<>();
    }
}
