package com.arcosoft.arcLogChef;

/**
 * Created by princegupta on 22/11/17.
 */
public enum OS {
    WIN("Windows","fa fa-windows"),
    MAC("MAC OS","fa fa-apple"),
    LINUX("Linux","fa fa-linux"),
    OTHER("Other","fa fa-cubes");

    String osName;
    String icon;

    OS(String osName, String icon){
        this.osName = osName;
        this.icon = icon;
    }

    public static String getIcon(String osNameString){
        if(osNameString.toLowerCase().contains("win"))
            return WIN.icon;
        else if (osNameString.toLowerCase().contains("mac"))
            return MAC.icon;
        else if (osNameString.toLowerCase().contains("linux"))
            return LINUX.icon;
        else
            return OTHER.icon;
    }
}
