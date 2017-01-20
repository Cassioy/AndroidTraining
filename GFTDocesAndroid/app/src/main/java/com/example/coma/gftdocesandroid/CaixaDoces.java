package com.example.coma.gftdocesandroid;

/**
 * Created by coma on 19/01/2017.
 */

public class CaixaDoces {

    //Drawable ID Resource
    private int mImageResourceId;

    //Android Version Name
    private String mVersionName;

    //Android Doce
    private String mDoce;


    public CaixaDoces(String vName, String Doce, int ImageResourceId)

    {
        mVersionName = vName;
        mDoce = Doce;
        mImageResourceId = ImageResourceId;
    }

    /** get android version name **/
    public String getAndroidVersionName() {
        return mVersionName;
    }

    /** get Doces Android **/
    public String getDoce() {
        return mDoce;
    }

    //get Image Resource ID

    public int getImageResourceId() {
        return mImageResourceId;
    }
}