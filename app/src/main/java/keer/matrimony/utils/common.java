package keer.matrimony.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import keer.matrimony.R;
import keer.matrimony.database.userDatabaseHelper;
import keer.matrimony.database.userDatabaseModel;
import keer.matrimony.models.data;
import keer.matrimony.other.CONSTANTS;
import keer.matrimony.other.ImageUpload;
import keer.matrimony.ui.Activitys.HomeActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class common {
    public static void setParams(TextView view, String str) {
        if (str!=null){
            view.setText(str);
        }
    }
    public static void startCall(String mNum , Context context){
       try {
           Intent intent = new Intent(Intent.ACTION_CALL);
           intent.setData(Uri.parse("tel:"+mNum));
           context.startActivity(intent);
       }catch (Exception ignore){}
    }
//    uri to bitmap converter
    public static Bitmap uriToBitmap(Uri selectedFileUri, Context context) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);;
            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    upload bitmap image to server
    public static void uploadImage(Bitmap bm , Context context , int id){
        File file = new File(context.getExternalCacheDir(), "keerProfile.jpg");
        try {
            boolean f = file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        ImageUpload imageUpload = new ImageUpload(file , id);
        imageUpload.execute();
    }
    public static void Verify(Context context , String id) {
        Gson gson = new Gson();
        Map<String , String > map = new HashMap<>();

        String jsonString = gson.toJson(map);
        final RequestBody requestBody = RequestBody.create(jsonString , MediaType.get(CONSTANTS.mediaType));
        Request request = new Request.Builder().url(CONSTANTS.BASEURL +"user-detail/"+id).addHeader("authorization" , "[]").get().build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) { }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    JSONObject jsonResponse = new JSONObject(response.body().string());
                    Handler mHandler = new Handler(Looper.getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!jsonResponse.optBoolean("error")) {
                                data userData = gson.fromJson(jsonResponse.optString("data").toString() , data.class);
                                userDatabaseHelper db = new userDatabaseHelper(context);
                                db.insertUser(userData);
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public static void showProfile(String name , Context context){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.image_viewer);
        dialog.getWindow().setBackgroundDrawable(null);
        ImageView profile = dialog.findViewById(R.id.profile);
        Glide.with(context).load(name).placeholder(R.drawable.plaholder).into(profile);
        dialog.show();
    }

}
