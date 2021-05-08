package com.ed.edglide.common;

/**
 */

public interface OnResponseListener {

    void onSuccess();

    void onCancel();

    void onFail(String message);

}
