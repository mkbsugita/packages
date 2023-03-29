// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.webviewflutter;
// 仕方なく追加
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import androidx.annotation.NonNull;

/**
 * Added as a JavaScript interface to the WebView for any JavaScript channel that the Dart code sets
 * up.
 *
 * <p>Exposes a single method named `postMessage` to JavaScript, which sends a message to the Dart
 * code.
 */
public class JavaScriptChannel {
  private final Handler platformThreadHandler;
  final String javaScriptChannelName;
  private final JavaScriptChannelFlutterApiImpl flutterApi;

  /**
   * Creates a {@link JavaScriptChannel} that passes arguments of callback methods to Dart.
   *
   * @param flutterApi the Flutter Api to which JS messages are sent
   * @param channelName JavaScript channel the message was sent through
   * @param platformThreadHandler handles making callbacks on the desired thread
   */
  public JavaScriptChannel(
      @NonNull JavaScriptChannelFlutterApiImpl flutterApi,
      String channelName,
      Handler platformThreadHandler) {
    this.flutterApi = flutterApi;
    this.javaScriptChannelName = channelName;
    this.platformThreadHandler = platformThreadHandler;
  }

  // Suppressing unused warning as this is invoked from JavaScript.
  @SuppressWarnings("unused")
  @JavascriptInterface
  // これはつかわない
  public void postMessage(final String message) {
    final Runnable postMessageRunnable =
        () -> {
          flutterApi.postMessage(JavaScriptChannel.this, message, reply -> {});
        };

    if (platformThreadHandler.getLooper() == Looper.myLooper()) {
      postMessageRunnable.run();
    } else {
      platformThreadHandler.post(postMessageRunnable);
    }
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void init(final String message) {
    finalMessage("init@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getValue(final String message) {
    finalMessage("getValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void setValue(final String message) {
    finalMessage("setValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void deleteValue(final String message) {
    finalMessage("deleteValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void insertArchive(final String message) {
    finalMessage("insertArchive@" + message);
  }


  /*
   *   static const apiGetArchiveCount = 'getArchiveCount';

  static const apiSelectProfile = 'selectProfile';
  static const apiOpenArchive = 'openArchive';
  static const apiGetArchive = 'getArchive';
  // TODO: 今まで使ったことがないので不要になります
  static const apiOpenNotify = 'openNotify';
  static const apiOpenTextField = 'openTextField';
  static const apiOpenDatePicker = 'openDatePicker';
  // TODO: 今まで使ったことがないので不要になります
  static const apiOpenTimePicker = 'openTimePicker';
  // TODO: 今まで使ったことがないので不要になります
  static const apiOpenSelectPicker = 'openSelectPicker';
  // TODO: 今まで使ったことがないので不要になります
  static const apiUpdateProfile = 'updateProfile';

   */

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void apiAddFavorite(final String message) {
    finalMessage("apiAddFavorite@" + message);
  }

  /*
   *   static const apiPay = 'pay';
  static const apiOpenUrl = 'openUrl';
  static const apiCheckNetwork = 'checkNetwork';
  */
  @SuppressWarnings("unused")
  @JavascriptInterface
  public void showToast(final String message) {
    finalMessage("showToast@" + message);
  }

  /*
  static const apiDownloadImage = 'downloadImage';
  static const apiOpenChildApp = 'openChildApp';
  static const apiOpenNativeApp = 'openNativeApp';
  static const apiOpenSearch = 'openSearch';
  static const apiExitChildApp = 'exitChildApp';
   */
  
  private void finalMessage(final String message) {
    final Runnable postMessageRunnable =
            () -> {
              flutterApi.postMessage(JavaScriptChannel.this, message, reply -> {});
            };

    if (platformThreadHandler.getLooper() == Looper.myLooper()) {
      postMessageRunnable.run();
    } else {
      platformThreadHandler.post(postMessageRunnable);
    }
  }
}
