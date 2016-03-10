package com.test.imagehandling;

/**
 * Created by 高橋　信史 on 2016/02/29.
 */
public class Image {
    private String encodedString;
    private String latitude;
    private String longtitude;

    public Image() {
    }

    public Image(String encodedString, String latitude, String longtitude) {
        this.encodedString = encodedString;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public String getLatitude() {
        return latitude;
    }
    public String getLongtitude() {
        return longtitude;
    }
}
