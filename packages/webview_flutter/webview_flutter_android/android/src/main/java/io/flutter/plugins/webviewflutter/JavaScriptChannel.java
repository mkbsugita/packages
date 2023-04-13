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
  // これはつかえない（JavaScript側のpostMessageメソッドになるので）
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
    // 1
    sendToFlutter("init@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getValue(final String message) {
    // 2
    sendToFlutter("getValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void setValue(final String message) {
    // 3
    sendToFlutter("setValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void deleteValue(final String message) {
    // 4
    sendToFlutter("deleteValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void insertArchive(final String message) {
    // 5
    sendToFlutter("insertArchive@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getArchiveCount(final String message) {
    // 6
    sendToFlutter("getArchiveCount@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void selectProfile(final String message) {
    // 7
    sendToFlutter("selectProfile@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openArchive(final String message) {
    // 8
    sendToFlutter("openArchive@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getArchive(final String message) {
    // 9
    sendToFlutter("getArchive@" + message);
  }

  // TODO: 今まで使ったことがないので不要になります
  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openNotify(final String message) {
    sendToFlutter("openNotify@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openTextField(final String message) {
    // 10
    sendToFlutter("openTextField@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openDatePicker(final String message) {
    // 11
    sendToFlutter("openDatePicker@" + message);
  }

  // TODO: 今まで使ったことがないので不要になります
  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openTimePicker(final String message) {
    sendToFlutter("openTimePicker@" + message);
  }
  // TODO: 今まで使ったことがないので不要になります
  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openSelectPicker(final String message) {
    sendToFlutter("openSelectPicker@" + message);
  }

  // TODO: 今まで使ったことがないので不要になります
  @SuppressWarnings("unused")
  @JavascriptInterface
  public void updateProfile(final String message) {
    sendToFlutter("updateProfile@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void addFavorite(final String message) {
    // 12
    sendToFlutter("addFavorite@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void pay(final String message) {
    // 13
    sendToFlutter("pay@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openUrl(final String message) {
    // 14
    sendToFlutter("openUrl@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void checkNetwork(final String message) {
    // 15
    sendToFlutter("checkNetwork@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void showToast(final String message) {
    // 16
    sendToFlutter("showToast@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void downloadImage(final String message) {
    // 17
    sendToFlutter("downloadImage@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openChildApp(final String message) {
    // 18
    sendToFlutter("openChildApp@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openNativeApp(final String message) {
    // 19
    sendToFlutter("openNativeApp@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openSearch(final String message) {
    // 20
    sendToFlutter("openSearch@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void exitChildApp(final String message) {
    // 21
    sendToFlutter("exitChildApp@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void notifyOpenMenu(final String message) {
    // 22
    sendToFlutter("notifyOpenMenu@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void notifyOpenResult(final String message) {
    // 23
    sendToFlutter("notifyOpenResult@" + message);
  }

  private void sendToFlutter(final String message) {
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
