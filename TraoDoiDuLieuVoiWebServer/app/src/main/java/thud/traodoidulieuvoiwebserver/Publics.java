package thud.traodoidulieuvoiwebserver;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Publics {

    public static String URLNHANDULIEU = "https://api.imgflip.com/get_memes";
    public static String URLGOIDULIEU = "http://hmkcode.appspot.com/jsonservlet";

    public static boolean HasInternet(Context context) {
        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conn.getActiveNetworkInfo();
        return ((netInfo != null) && (netInfo.isConnected()));
    }

    public static String StreamToString(InputStream tream){
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(tream));
        String temp;
        try{
            while ((temp = reader.readLine()) != null){
                builder.append(temp).append('\n');
            }
        } catch (IOException e){

        }
        return builder.toString();
    }


}
