package com.dealsonwheels.app.api;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mukesh on 11/11/17.
 */

public class BaseAPIResponse implements Serializable {
    @SerializedName("Success") boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
