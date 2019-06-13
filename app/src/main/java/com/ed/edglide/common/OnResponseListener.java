package com.ed.edglide.common;

/**
 * @author ：wuchao on 2018/9/27 17：10
 */

public interface OnResponseListener {

    void onSuccess();

    void onCancel();

    void onFail(String message);

}
