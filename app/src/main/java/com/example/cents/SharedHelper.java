package com.example.cents;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SharedHelper {
    private Context mContext;

    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
    }

//    public void save(String key, int value) {
//        save(key, Integer.toString(value));
//    }

    //定义一个保存数据的方法
    public void save(String key, int vale) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, vale);
        editor.commit();
        Toast.makeText(mContext, "key: " + key + ", vale: " + vale, Toast.LENGTH_SHORT).show();
    }

//    //定义一个读取SP文件的方法
//    public String read(String key) {
//        Map<String, String> data = new HashMap<String, String>();
//        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
//        return sp.getString(key, "");
//    }

    public int read(String key) {
        SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        int result = sp.getInt(key, 0);
        if (result == 0) {
            switch(key) {
                case "currentLevel":
                case "progress":
                    result = 0;
                    break;
                case "ability":
                case "threshold":
                default:
                    result = 20;
            }
        }
        return result;
    }
}
