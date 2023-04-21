package com.niniconi.mrm;

import java.io.*;
import java.util.*;
class Disk {
    public static void main(String[] args){
        System.out.println(getHdInfo());

        File part = new File("/run/media/arch/00E4-B74D");
        double unit = Math.pow(1024,3);
        // System.out.println(Math.ceil(10 * part.getFreeSpace() / unit) / 10);
        // System.out.println(Math.ceil(10 * part.getUsableSpace() / unit) / 10);
        // System.out.println(Math.ceil(10 * part.getTotalSpace() / unit) / 10);
    }
    /**
     * get the info of HD
     * @return Map<String, String>: key:磁盘盘符, value:磁盘剩余空间
     */
    public static Map<String, String> getHdInfo() { 
        Map<String,String> map = new TreeMap<String, String>();
        File[] roots = File.listRoots();
        double unit = Math.pow(1024, 3);
        for (int i = 0; i < roots.length; i++) {
            String hd = roots[i].getPath();
            double freespace = roots[i].getFreeSpace() / unit;
            // freespace = Math.ceil((freespace * 10)) / 10;
            map.put(hd, String.valueOf(freespace));
        }
        return map;
    }
}
