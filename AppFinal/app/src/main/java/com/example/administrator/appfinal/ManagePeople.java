package com.example.administrator.appfinal;

import java.util.ArrayList;

/**
 * Created by bafs on 23/01/2017.
 */

public class ManagePeople {
    String vSimage;
    String vName;
    String vBirth;

    public ManagePeople(String vSimage, String vName, String vBirth) {
        this.vSimage = vSimage;
        this.vName = vName;
        this.vBirth = vBirth;
    }

    public String getvSimage() {
        return vSimage;
    }

    public String getvName() {
        return vName;
    }

    public String getvBirth() {
        return vBirth;
    }
}
