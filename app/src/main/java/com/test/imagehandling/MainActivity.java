package com.test.imagehandling;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        //String encodedImageAsString = encodeImageToBase64();
        decodeImageString();//encodedImageAsString);
    }


    private String encodeImageToBase64() {

        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(), R.drawable.harry);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
        byte[] ba = bao.toByteArray();
        String encodedImageAsString = Base64.encodeToString(ba, Base64.DEFAULT);

        Firebase mFirebaseRef = new Firebase("https://shining-fire-3472.firebaseio.com/images/");

        String latitude = String.valueOf(14.25);
        String longtitude = String.valueOf(15.25);
        Image image = new Image(encodedImageAsString, latitude, longtitude);
        mFirebaseRef.push().setValue(image);


//        TextView encodedString = (TextView) findViewById(R.id.encoded);
//        encodedString.setText(encodedImageToString);
        return encodedImageAsString;
    }

    private void decodeImageString() {
        Firebase ref = new Firebase("https://shining-fire-3472.firebaseio.com/images/");
        Query queryRef = ref.orderByChild("encodedString");

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot imageSnapshot : dataSnapshot.getChildren()) {

                    Image image = imageSnapshot.getValue(Image.class);

                    byte[] decodedString = Base64.decode(image.getEncodedString(), Base64.DEFAULT);
                    Bitmap decodedByteBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                    ImageView imageview = (ImageView) findViewById(R.id.imageView);
                    imageview.setImageBitmap(decodedByteBitMap);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}