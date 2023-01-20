package mx.com.mundodafne.ssh.almacen.app.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    final static String EMPTY_SPACE = "";
    public static String parseJson(Context context, int fileName){
        String jsonString = EMPTY_SPACE;
        try {
            InputStream is = context.getResources().openRawResource(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch(Exception e) {
            if (e instanceof IOException) {
                e.printStackTrace();
                return null;
            }
            Log.e("ERROR_PARSEJSON","Error en metodo Utils.parseJson(): ",e);
        }
        return jsonString;
    }
}
