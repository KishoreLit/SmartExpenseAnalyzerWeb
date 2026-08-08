package com.saikishore.expenseanalyzer.util;

public class AvatarUtil {

    private static final String[] COLORS = {
            "avatar-blue",
            "avatar-green",
            "avatar-purple",
            "avatar-orange",
            "avatar-red",
            "avatar-teal",
            "avatar-pink",
            "avatar-indigo"
    };

    public static String getAvatarClass(String name){

        if(name == null || name.isBlank()){
            return "avatar-blue";
        }

        int index = Math.abs(name.hashCode()) % COLORS.length;

        return COLORS[index];
    }
}