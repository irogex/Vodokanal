//package com.example.vodokanalmainactivity.utils;
//
//import android.annotation.SuppressLint;
//import android.net.Uri;
//import android.os.Build;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebView;
//
//class OpenFileChromeClient extends WebChromeClient {
//
//    // Версия от Android 2.2 (уровень API 8) до версии Android 2.3 (уровень API 10) вызовет метод скрытия при выборе файла
//    @SuppressWarnings("unused")
//    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//        openFileChooser(uploadMsg, null);
//    }
//
//    // От Android 3.0 (уровень API 11) до Android 4.0 (уровень API 15)) будет запускаться при выборе файла. Этот метод является скрытым.
//    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//        openFileChooser(uploadMsg, acceptType, null);
//    }
//
//    // Android 4.1 (уровень API 16) - версия Android 4.3 (уровень API 18) срабатывает при выборе файла. Этот метод является скрытым.
//    @SuppressWarnings("unused")
//    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
//        openFileInput(uploadMsg, null, false);
//    }
//
//    // Android 5.0 (уровень API 21) и выше вызовет этот метод, который является общедоступным.
//    @SuppressWarnings("all")
//    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
//        if (Build.VERSION.SDK_INT >= 21) {
//            final boolean allowMultiple = fileChooserParams.getMode () == FileChooserParams.MODE_OPEN_MULTIPLE; // Следует ли поддерживать множественный выбор
//
//            openFileInput(null, filePathCallback, allowMultiple);
//
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//}
//
//    @SuppressLint("NewApi")
//    protected void openFileInput(final ValueCallback<Uri> fileUploadCallbackFirst, final ValueCallback<Uri[]> fileUploadCallbackSecond, final boolean allowMultiple) {
//        // Версии ниже Android 5.0
//        if (mFileUploadCallbackFirst != null) {
//            mFileUploadCallbackFirst.onReceiveValue(null);
//        }
//        mFileUploadCallbackFirst = fileUploadCallbackFirst;
//
//        // Android 5.0 и выше
//        if (mFileUploadCallbackSecond != null) {
//            mFileUploadCallbackSecond.onReceiveValue(null);
//        }
//        mFileUploadCallbackSecond = fileUploadCallbackSecond;
//
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.addCategory(Intent.CATEGORY_OPENABLE);
//
//        if (allowMultiple) {
//            if (Build.VERSION.SDK_INT >= 18) {
//                i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            }
//        }
//
//        i.setType(mUploadableFileTypes);
//
//        startActivityForResult (Intent.createChooser (я, «Выбрать файл»), REQUEST_CODE_FILE_PICKER);
//
//    }
//
//
//
//}
