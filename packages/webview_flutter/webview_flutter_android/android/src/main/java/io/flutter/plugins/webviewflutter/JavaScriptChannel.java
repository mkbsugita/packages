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
    sendToFlutter("init@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getValue(final String message) {
    sendToFlutter("getValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void setValue(final String message) {
    sendToFlutter("setValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void deleteValue(final String message) {
    sendToFlutter("deleteValue@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void insertArchive(final String message) {
    sendToFlutter("insertArchive@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getArchiveCount(final String message) {
    sendToFlutter("getArchiveCount@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void selectProfile(final String message) {
    sendToFlutter("selectProfile@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openArchive(final String message) {
    sendToFlutter("openArchive@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void getArchive(final String message) {
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
    sendToFlutter("openTextField@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openDatePicker(final String message) {
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
  public void apiAddFavorite(final String message) {
    sendToFlutter("apiAddFavorite@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void pay(final String message) {
    sendToFlutter("pay@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openUrl(final String message) {
    sendToFlutter("openUrl@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void checkNetwork(final String message) {
    sendToFlutter("checkNetwork@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void showToast(final String message) {
    sendToFlutter("showToast@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void downloadImage(final String message) {
    sendToFlutter("downloadImage@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openChildApp(final String message) {
    sendToFlutter("openChildApp@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openNativeApp(final String message) {
    sendToFlutter("openNativeApp@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void openSearch(final String message) {
    sendToFlutter("openSearch@" + message);
  }

  @SuppressWarnings("unused")
  @JavascriptInterface
  public void exitChildApp(final String message) {
    sendToFlutter("exitChildApp@" + message);
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
