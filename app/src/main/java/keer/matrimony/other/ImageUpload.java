package keer.matrimony.other;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImageUpload extends AsyncTask<Void , Void , Void> {
    File file;
    int id ;
    String type = "gallery";

    public ImageUpload(File file, int id) {
        this.file = file;
        this.id = id;
    }

    public ImageUpload() {
    }

    //        final  RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("profile", System.currentTimeMillis()+".jpg", RequestBody.create(MediaType.parse("image/*"), file))
//                .addFormDataPart("first_name" , map.get("first_name"))
//                .addFormDataPart("last_name" , map.get("last_name"))
//                .addFormDataPart("email" , map.get("email"))
//                .addFormDataPart("country_code" , map.get("country_code"))
//                .addFormDataPart("mobile" , map.get("mobile"))
//                .addFormDataPart("dob" , map.get("dob"))
//                .addFormDataPart("subcaste" , map.get("subcaste"))
//                .addFormDataPart("country" , map.get("country"))
//                .addFormDataPart("state" , map.get("state"))
//                .addFormDataPart("city" , map.get("city"))
//                .addFormDataPart("password" , map.get("password"))
//                .addFormDataPart("gender" , map.get("gender"))
//                .build();
    @Override
    protected Void doInBackground(Void... voids) {

        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", System.currentTimeMillis()+".jpg", RequestBody.create(MediaType.parse("image/*"), file))
                .addFormDataPart("user_id" , String.valueOf(id))
                .addFormDataPart("type" , type)
                .build();
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"update-image").addHeader("authorization" , "[]").post(requestBody).build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Log.d("TAG", "onResponseImage: "+jsonResponse.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return null;
    }
}
