package com.example.vodokanalmainactivity.activities;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.vodokanalmainactivity.App;
import com.example.vodokanalmainactivity.R;
import com.example.vodokanalmainactivity.data.preferences.DataPreferences;
import com.example.vodokanalmainactivity.data.preferences.JavaScriptInterface;

public class WebViewActivity extends AppCompatActivity {
    DataPreferences dataPreferences;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webiview_activity);
        final android.webkit.WebView webView = findViewById(R.id.webview);
        dataPreferences = ((App) getApplication()).dataPreferences;
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_blue));
        getWindow().getDecorView().setSystemUiVisibility(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(this), "AndroidFunction");
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebChromeClient(new WebChromeClient(){
       //     @Override
//            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//            {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Context context = view.getContext();
//               // Intent intent = new Intent(Intent.ACTION_VIEW);
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/viewer?url="+data));
//            //    WebView.loadUrl(data);
//               // WebViewActivity.startActivity(intent);
//                //WebView.loadUrl(browserIntent);
//                Log.d("LOGLOG","br-"+data);
//                return false;
//            }
       @Override
       public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
           webView.removeAllViews();
           WebView newView = new WebView(WebViewActivity.this);
           newView.setWebViewClient(new WebViewClient());
           // Create dynamically a new view
           newView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

           webView.addView(newView);

           WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
           transport.setWebView(newView);
           resultMsg.sendToTarget();
           return true;
//           WebView newWebView = new WebView(WebViewActivity.this);
//           WebSettings webSettings = newWebView.getSettings();
//           webSettings.setJavaScriptEnabled(true);
//           webSettings.setSupportMultipleWindows(true);
//           webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//           // Other configuration comes here, such as setting the WebViewClient
//
//           final Dialog dialog = new Dialog(WebViewActivity.this);
//           dialog.setContentView(newWebView);
//           dialog.show();
//
//           newWebView.setWebChromeClient(new WebChromeClient() {
//               @Override
//               public void onCloseWindow(WebView window) {
//                   dialog.dismiss();
//               }
//           });
//
//           ((WebView.WebViewTransport)resultMsg.obj).setWebView(newWebView);
//           resultMsg.sendToTarget();
//           return true;
       }
        });


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //super.onPageFinished(view, url);
                view.getSettings().setDomStorageEnabled(true);
                view.getSettings().setAppCacheEnabled(true);
                view.getSettings().setDatabaseEnabled(true);
                view.getSettings().setDomStorageEnabled(true);
                view.getSettings().setSupportMultipleWindows(true);
                view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                view.loadUrl("javascript:var elem=document.getElementById('old'); elem.parentNode.removeChild(elem);");
                view.loadUrl("javascript:var elem=document.getElementById('old'); elem.parentNode.removeChild(elem);");
                if (view.getUrl().contains("https://vdk03.m-pays.ru/Login2")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("var formxxx = document.getElementsByTagName('form')[0];");
                    sb.append("if(!(formxxx === undefined || formxxx === null)){");
                    sb.append(" formxxx.onsubmit = function () {");
                    sb.append(" var l3=document.getElementById('login').value;");
                    sb.append(" var l5=document.getElementById('password').value;");
                   // sb.append(" var e1=document.getElementById('old'); e1.style.display='none';");
                    sb.append(" window.AndroidFunction.processHTML(l3,l5);");
                    sb.append(" return true;");
                    sb.append(" };");
                    sb.append("}");
                    view.loadUrl("javascript:" + sb.toString());
                    if (dataPreferences.getLogin() != null) {
                        view.loadUrl("javascript:var l1=document.getElementById('login').value='" + dataPreferences.getLogin() + "'," +
                                "a1=document.getElementById('password').value='" + dataPreferences.getPassword() + "'");
                    }
//                    else {
//                        view.loadUrl("javascript:var l1=document.getElementById('login').value='9140000000'," +
//                                 "a1=document.getElementById('password').value='314159'");
//                    }
                }
                if (view.getUrl().contains("https://vdk03.m-pays.ru/Prints2?id=61213")) {
                    StringBuilder sb1 = new StringBuilder();
                    sb1.append("var btn1 = document.getElementById('submit1');");
                    sb1.append("btn1.onclick = function(){");
                    sb1.append("var token = document.getElementsByName('__RequestVerificationToken')[0];");
                    //sb1.append("var body = 'id=61213' + '&month=2022-05';");
                    sb1.append("var body = 'id=61213' + '&month=2022-05';");
                    sb1.append("var req = new XMLHttpRequest();");
                    sb1.append("req.open('POST', '/Prints2?handler=ChargePrint', true);");
                    sb1.append("req.setRequestHeader('RequestVerificationToken', token.value);");
                    sb1.append("req.setRequestHeader('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8');");
                    sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
                    sb1.append("req.setRequestHeader('User-Agent', 'Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1');");
                   // sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
                   // Content-Disposition: attachment; filename*=UTF-8
                   // sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
                  //  Content-Disposition: form-data; name="field2"; filename="example.txt"
                  //  sb1.append("req.setRequestHeader('Content-Disposition', 'attachment; filename=\"report.pdf\"');");
//                    sb1.append("req.responseType = 'blob';");
                   //sb1.append("req.responseType = 'Text';");
                    //response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf\"");
                    sb1.append("req.responseType = 'arraybuffer';");
                    sb1.append("req.send(body);"); //Должен быть конечным //Искать саму реализацию принтера// Посмотреть по скрптам как возвращает ответ
                  //  sb1.append("var reader = new FileReader();"); //Должен быть конечным //Искать саму реализацию принтера// Посмотреть по скрптам как возвращает ответ
                    sb1.append("req.onload = function () {\n" +
//                             " var actual_contents = reader.result.slice(reader.result.indexOf(',') + 1);\n" +
                             //" var blob = new Blob([req.response], { type: 'application/pdf' });\n" +
                           // "window.AndroidFunction.showPdf(typeof(blob))\n" +
//                            "window.AndroidFunction.log(req.response);\n" +
//                            "window.AndroidFunction.showPdf(JSON.stringify(req.response, null, 4))\n" +
//                           " var url = window.URL.createObjectURL(req.response.data);\n" +
                            "window.AndroidFunction.showPdf(new Uint8Array(req.response));\n" +

                           // " var Base64='{_keyStr:\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=\",encode:function(e){var t=\"\";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t=\"\";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\\+\\/\\=]/g,\"\");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\\r\\n/g,\"\\n\");var t=\"\";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t=\"\";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}'\n" +
                        //   " var decodedString = Base64.decode(req.response);\n" +
                            //" alert(atob(req.response));\n" +
                            //"handleScriptingMessage(1);\n" +
                          //  "window.AndroidFunction.showPdf(b);\n" +
                           // "var js = \"document.getElementById('id1').innerHTML = ' Done!';\";\n" +
                            //"var blob = new Blob( [js], {type: 'data:application/octet-stream'} ); // pass a useful MIME-type here\n" +
                            //"window.URL = window.URL || window.webkitURL;\n" +
                            //"var url = window.URL.createObjectURL(blob);\n" +
                            //"document.getElementById('tmp').innerHTML = 'created';\n" +
                           // "document.getElementById('blob-url').src = url;" +
                                  //  "document.body.innerHTML = b;\n" +
                            //"saveAs(b, \"pretty image.pdf\");\n" +
                              //      "saveAs(new Blob([req.response], {type: 'application/pdf'}), \"BusinessCard.pdf\");\n" +
//                            " var blob = new Blob([req.response], { type: 'application/pdf' });\n" +
//                            "  if (window.navigator && window.navigator.msSaveOrOpenBlob) {\n" +
//                            "                    window.navigator.msSaveOrOpenBlob(blob);\n" +
//                            "                }\n" +
//                            "                else {\n" +
//                            "                    var fileURL = URL.createObjectURL(blob);\n" +
//                         //   "                    saveAs(fileURL);\n" +
//                            "                    var newWin = window.open(fileURL);\n" +

                          //  " window.AndroidFunction.showPdf(newWin.text());\n" +
//                            "                    newWin.focus();\n" +
//                            "                    newWin.reload();\n" +
//                            "                }" + //Это тоже работает но файл приходит не тот

//                            " const a = document.createElement('a');\n" +
//                            " a.href = url;\n" +
//                            " a.download = name || 'text.txt';\n" +
//                            " a.type = 'text/plain';\n" +
//                            " a.addEventListener('click', () => {\n" +
//                            " setTimeout(() => window.URL.revokeObjectURL(url), 10000);\n" +
//                            " });\n" +
//                            " a.click();\n" +
                           // "var files = req.response;\n" +
                                 //   "for (var i = 0; i < files.length; i++) {alert(\"Filename \" + files[i].name);}\n" +
                            ///"alert(file.size);" +
                           //191418/1024= "alert(file.ReadableStream());" +

                            //"var otv = new Response(file, { headers: { \"Content-Type\": \"text/html\" }" +
                           // "document.body.innerHTML = b;" +
                           // " var reader = new FileReader();\n" +

                                   // "reader.addEventListener(\"loadend\", function() {  });\n" +
                                  //  "reader.readAsArrayBuffer(file);\n" +
                           // "document.body.innerHTML = reader.readAsArrayBuffer(file);" +
                          //  "var body2 = 'str='+req.response;\n" +
//                     "var body2 = \"ids=61213\";\n" +
//                     "var req2 = new XMLHttpRequest();\n" +
//                     "req2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n" +
//                     "req2.open('POST', 'https://irogex.ru/api/test', true);\n" +
//                     "req2.send(body2);\n" +
//                     "req2.onload = () => alert(req2.response); }; \n" +
                            //" var file = new Blob([req.response], { type: 'application/pdf' });\n" +
                           // " var file = new Blob([this.response], { type: 'application/pdf' });\n" +
                           // " var fileURL = URL.createObjectURL(file);\n" +
                          //  var blob = new Blob([pdfBuffer], {type: 'application/pdf'});
                   // var blobURL = URL.createObjectURL(blob);
                    //window.open(blobURL);
                           // "saveAs(file, 'newPdf.pdf');\n" +
                                  //  saveAs(file, 'newPdf.pdf');
                            //const pdfBlob = new Blob([res.data], { type: 'application/pdf' });
                            //saveAs(pdfBlob, 'newPdf.pdf');
                            //" window.open(fileURL, \"EPrescription\");" +
                            //"document.body.innerHTML = encodeURI(fileURL);" +
                           // "location.href = 'https://irogex.ru/api/test';" +
                            //"var blob = new Blob([this.response], {type: 'application/pdf'});\n" +
                            //"const url = window.URL.createObjectURL(blob);\n" +
                            //"document.body.innerHTML =file;" +
                           // "window.open(fileURL);" +
                         // "window.AndroidFunction.showPdf(saveAs(file, 'newPdf.pdf'));\n" +
                          // "window.AndroidFunction.showPdf(fileURL);\n" +

//                            "document.body.innerHTML = '<html><head><title>Test</title><style>body{background:white}</style></head><body>" +
//                             "<embed width=300 height=300 src=data:application/pdf;base64,\"'+encodeURI(url)+'\"></embed></body></html>';" +


                           // "document.body.innerHTML = '<iframe width=100% height=500 src=data:application/pdf;base64,/Prints2?handler=ChargePrint?id=61213&month=2022-05&__RequestVerificationToken=\"'+ token.value +'\"></iframe>';" +

                            //"window.open(url); \n" +
                           // "document.body.innerHTML = url; \n" +
                           // "            if (this.status == 200) {\n" +

                            //"                var blob = new Blob([this.response], {type: 'application/pdf'});\n" +
                           // "s.innerHTMLthis.response); \n" +

                           // "let div = document.createElement('div'); \n" +
                          //  "let div1 = pdfWindow.document.write(\"<html<head><title>\"+fileName+\"</title><style>body{margin: 0px;}iframe{border-width: 0px;}</style></head>\");\n" +
                          //  "let div1 = document.createElement('<html><head><title>000</title><style>body{margin: 20px;}iframe{border-width: 0px;}</style></head>');\n" +
                         //   "let div2 = pdfWindow.document.write(\"<body><embed width='100%' height='100%' src='data:application/pdf;base64, \" + encodeURI(this.response)+\"#toolbar=0&navpanes=0&scrollbar=0'></embed></body></html>\"); \n" +
                           // "let div2 = document.write(\"<body><embed width='100%' height='100%' src='data:application/pdf;base64, \" + encodeURI(this.response)+\"#toolbar=0&navpanes=0&scrollbar=0'></embed></body></html>\"); \n" +
                           // "document.body.innerHTML = '<strong>TEST</strong>'; \n" +

                           // "document.body.append(div1); \n" +
                           // "document.body.append(div2); \n" +

                          //  "window.open(\"data:application/pdf,\" + this.response);" +

//                            "let pdfWindow = document.body.innerHTML;\n" +
//                            "pdfWindow=(\n" +
//                            "document.body.innerHTML =  \n" +
//                            " \"<iframe width='100%' height='100%' src='data:application/pdf;base64, \" +\n" +
//                           "    encodeURI(this.response) + \"'></iframe>\"\n" +
//                            ");" +

                           // "document.body.innerHTML = this.response; \n" +
   //  ("<body><embed width='100%' height='100%' src='data:application/pdf;base64, " + encodeURI(base64Data)+"#toolbar=0&navpanes=0&scrollbar=0'></embed></body></html>");

                         //   "document.body.innerHTML = '<html><head><title>Test</title><style>body{margin: 0px; background:white;}</style></head><body><embed width=300 height=300 src=data:application/pdf;base64,\"' + req.response+'\"></embed></body></html>';" +
                         //   "document.body.innerHTML = '<iframe width=100% height=500 src=\"/Prints2?handler=ChargePrint?id=61213&month=2022-05&__RequestVerificationToken='+ token.value +'\"></iframe>';" +
                          //  "window.open(\"data:application/pdf,\" + url);\n" +
                          //  "window.AndroidFunction.showPdf(url);\n" +
//                            "var a = document.createElement('a');\n" +
//                           "a.href = url;\n" +
//                            "document.body.append(a); \n" +
//                            "a.download = 'myFile.pdf';\n" +
//                            "a.click();\n" +
//                            "setTimeout(function () {\n" +
//
//                            "            window.URL.revokeObjectURL(body)\n" +
//                            "                , 100\n" +
//                            "        });\n" +



                            //"            }\n" +
                            "        };};");
                   // Log.d("LOGLOG","press-"+PDF);
//                    sb1.append("req.onload = () => {" +
//                            "var blob = new Blob([req.response]);\n" +
//                            "                var link = document.createElement('a');\n" +
//                            "                link.href = window.URL.createObjectURL(blob);\n" +
//                            "                window.open(link);}; }; ");
           //         "                window.AndroidFunction.showPdf(url);\n" +
                  //  sb1.append("req.onload = () => print(req.response); }; ");
                   // sb1.append("req.onload = () => window.AndroidFunction.showPdf(req.response); };");
                   // sb1.append("req.onload = () => window.open(\"https://docs.google.com/viewer?url=req.response\"); }; ");



//                  sb1.append("req.onload = getHeaderResult(req.response);  };");

//                    sb1.append("   let blob = new Blob([data], {type: 'application/pdf'}); \n" +
//                            "                let link = document.createElement('a'); \n" +
//                            "                let objectURL = window.URL.createObjectURL(blob); \n" +
//                            "                link.href = objectURL; \n" +
//                            "                link.target = '_blank'; \n" +
//                            "                link.download = \"fileName.pdf\"; \n" +
//                            "                (document.body || document.documentElement).appendChild(link); \n" +
//                            "                link.click(); ");
//                    sb1.append(" }; ");
//                    let blob = new Blob([data], {type: 'application/pdf'}); //mime type is important here
//                    let link = document.createElement('a'); //create hidden a tag element
//                    let objectURL = window.URL.createObjectURL(blob); //obtain the url for the pdf file
//                    link.href = objectURL; // setting the href property for a tag
//                    link.target = '_blank'; //opens the pdf file in  new tab
//                    link.download = "fileName.pdf"; //makes the pdf file download
//                    (document.body || document.documentElement).appendChild(link); //to work in firefox
//                    link.click(); //imitating the click event for opening in new tab
                   // https://docs.google.com/viewer?url=
                    //sb1.append("req.onload = () => alert(req.responseText); }; ");

//                    sb1.append("var http = new XMLHttpRequest();");
//                    sb1.append("var url = 'Prints2?id=61213';");
//                    sb1.append("var params = 'id=61213&month=2022-04';");
//
//                    sb1.append("http.open('POST', url, true);");
//                    sb1.append("http.setRequestHeader('RequestVerificationToken', token.value);");
//                    sb1.append("http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');");
//                    sb1.append("http.onreadystatechange = function() {\n" +
//                            "                        if(http.readyState == 4 && http.status == 200) {\n" +
//                            "                           alert(http.responseText);\n" +
//                            "                       }\n" +
//                            "                    }");
//                    sb1.append("http.send(params);");
//                    sb1.append("http.onload = () => alert(http.response); }; ");
//                    sb1.append("req.open('POST', '/Prints2?id=61213', true);");
//                    sb1.append("req.setRequestHeader('RequestVerificationToken', token.value);");
//                    sb1.append("req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');");
//                    sb1.append("req.send(body);");
//                    sb1.append("req.onload = () => alert(req.response); }; ");
//                    var http = new XMLHttpRequest();
//                    var url = 'get_data.php';
//                    var params = 'orem=ipsum&name=binny';
//                    http.open('POST', url, true);
//
//                    http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
//
//                    http.onreadystatechange = function() {//Call a function when the state changes.
//                        if(http.readyState == 4 && http.status == 200) {
//                            alert(http.responseText);
//                        }
//                    }
//                    http.send(params);

















                    // sb1.append("req.open(\"POST\", \"/Prints2?id=61213\", true);");
                    // sb1.append("var body = 'id=61213' + '&month=2022-04' + '&__RequestVerificationToken=' + token.value;");
                    // sb1.append("var body = 'id=' + encodeURIComponent(61213) + '&month=' + encodeURIComponent(2022-04);"); __RequestVerificationToken
                    //  sb1.append("var body = 'id=' + encodeURIComponent(61213) + '&month=' + encodeURIComponent(2022-04) + '&__RequestVerificationToken=' + encodeURIComponent(token.value);");

                    //    var token = document.getElementsByName('__RequestVerificationToken')[0];
                    //    var req = new XMLHttpRequest();
                    //    req.open("POST", "/Prints2....", true);
                    //    req.setRequestHeader("RequestVerificationToken", token.value);
//                  sb1.append("var xhr = new XMLHttpRequest();\n" +
//                          "\n" +
//                          "var body = 'id=' + encodeURIComponent(61213) +\n" +
//                          "  '&month=' + encodeURIComponent(2022-04);\n" +
//                          "\n" +
//                          "xhr.open(\"POST\", '/Prints2?handler=ChargePrint', true);\n" +
//                          "xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');\n" +
//                          "\n" +
//                          "xhr.onreadystatechange = ...;\n" +
//                          "\n" +
//                          "xhr.send(body);");

                   // sb1.append("}");

                    view.loadUrl("javascript:" + sb1.toString());
                }
                //Log.d("LOGLOG",url);
            }
        });

       webView.loadUrl("https://vdk03.m-pays.ru/Login2");

   }
}



