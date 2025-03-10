// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
//
// Autogenerated from Pigeon (v9.1.2), do not edit directly.
// See also: https://pub.dev/packages/pigeon
// ignore_for_file: public_member_api_docs, non_constant_identifier_names, avoid_as, unused_import, unnecessary_parenthesis, prefer_null_aware_operators, omit_local_variable_types, unused_shown_name, unnecessary_import

import 'dart:async';
import 'dart:typed_data' show Float64List, Int32List, Int64List, Uint8List;

import 'package:flutter/foundation.dart' show ReadBuffer, WriteBuffer;
import 'package:flutter/services.dart';

enum ReplyType {
  success,
  error,
}

class NonNullFieldSearchRequest {
  NonNullFieldSearchRequest({
    required this.query,
  });

  String query;

  Object encode() {
    return <Object?>[
      query,
    ];
  }

  static NonNullFieldSearchRequest decode(Object result) {
    result as List<Object?>;
    return NonNullFieldSearchRequest(
      query: result[0]! as String,
    );
  }
}

class ExtraData {
  ExtraData({
    required this.detailA,
    required this.detailB,
  });

  String detailA;

  String detailB;

  Object encode() {
    return <Object?>[
      detailA,
      detailB,
    ];
  }

  static ExtraData decode(Object result) {
    result as List<Object?>;
    return ExtraData(
      detailA: result[0]! as String,
      detailB: result[1]! as String,
    );
  }
}

class NonNullFieldSearchReply {
  NonNullFieldSearchReply({
    required this.result,
    required this.error,
    required this.indices,
    required this.extraData,
    required this.type,
  });

  String result;

  String error;

  List<int?> indices;

  ExtraData extraData;

  ReplyType type;

  Object encode() {
    return <Object?>[
      result,
      error,
      indices,
      extraData.encode(),
      type.index,
    ];
  }

  static NonNullFieldSearchReply decode(Object result) {
    result as List<Object?>;
    return NonNullFieldSearchReply(
      result: result[0]! as String,
      error: result[1]! as String,
      indices: (result[2] as List<Object?>?)!.cast<int?>(),
      extraData: ExtraData.decode(result[3]! as List<Object?>),
      type: ReplyType.values[result[4]! as int],
    );
  }
}

class _NonNullFieldHostApiCodec extends StandardMessageCodec {
  const _NonNullFieldHostApiCodec();
  @override
  void writeValue(WriteBuffer buffer, Object? value) {
    if (value is ExtraData) {
      buffer.putUint8(128);
      writeValue(buffer, value.encode());
    } else if (value is NonNullFieldSearchReply) {
      buffer.putUint8(129);
      writeValue(buffer, value.encode());
    } else if (value is NonNullFieldSearchRequest) {
      buffer.putUint8(130);
      writeValue(buffer, value.encode());
    } else {
      super.writeValue(buffer, value);
    }
  }

  @override
  Object? readValueOfType(int type, ReadBuffer buffer) {
    switch (type) {
      case 128:
        return ExtraData.decode(readValue(buffer)!);
      case 129:
        return NonNullFieldSearchReply.decode(readValue(buffer)!);
      case 130:
        return NonNullFieldSearchRequest.decode(readValue(buffer)!);
      default:
        return super.readValueOfType(type, buffer);
    }
  }
}

class NonNullFieldHostApi {
  /// Constructor for [NonNullFieldHostApi].  The [binaryMessenger] named argument is
  /// available for dependency injection.  If it is left null, the default
  /// BinaryMessenger will be used which routes to the host platform.
  NonNullFieldHostApi({BinaryMessenger? binaryMessenger})
      : _binaryMessenger = binaryMessenger;
  final BinaryMessenger? _binaryMessenger;

  static const MessageCodec<Object?> codec = _NonNullFieldHostApiCodec();

  Future<NonNullFieldSearchReply> search(
      NonNullFieldSearchRequest arg_nested) async {
    final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
        'dev.flutter.pigeon.NonNullFieldHostApi.search', codec,
        binaryMessenger: _binaryMessenger);
    final List<Object?>? replyList =
        await channel.send(<Object?>[arg_nested]) as List<Object?>?;
    if (replyList == null) {
      throw PlatformException(
        code: 'channel-error',
        message: 'Unable to establish connection on channel.',
      );
    } else if (replyList.length > 1) {
      throw PlatformException(
        code: replyList[0]! as String,
        message: replyList[1] as String?,
        details: replyList[2],
      );
    } else if (replyList[0] == null) {
      throw PlatformException(
        code: 'null-error',
        message: 'Host platform returned null value for non-null return value.',
      );
    } else {
      return (replyList[0] as NonNullFieldSearchReply?)!;
    }
  }
}

class _NonNullFieldFlutterApiCodec extends StandardMessageCodec {
  const _NonNullFieldFlutterApiCodec();
  @override
  void writeValue(WriteBuffer buffer, Object? value) {
    if (value is ExtraData) {
      buffer.putUint8(128);
      writeValue(buffer, value.encode());
    } else if (value is NonNullFieldSearchReply) {
      buffer.putUint8(129);
      writeValue(buffer, value.encode());
    } else if (value is NonNullFieldSearchRequest) {
      buffer.putUint8(130);
      writeValue(buffer, value.encode());
    } else {
      super.writeValue(buffer, value);
    }
  }

  @override
  Object? readValueOfType(int type, ReadBuffer buffer) {
    switch (type) {
      case 128:
        return ExtraData.decode(readValue(buffer)!);
      case 129:
        return NonNullFieldSearchReply.decode(readValue(buffer)!);
      case 130:
        return NonNullFieldSearchRequest.decode(readValue(buffer)!);
      default:
        return super.readValueOfType(type, buffer);
    }
  }
}

abstract class NonNullFieldFlutterApi {
  static const MessageCodec<Object?> codec = _NonNullFieldFlutterApiCodec();

  NonNullFieldSearchReply search(NonNullFieldSearchRequest request);

  static void setup(NonNullFieldFlutterApi? api,
      {BinaryMessenger? binaryMessenger}) {
    {
      final BasicMessageChannel<Object?> channel = BasicMessageChannel<Object?>(
          'dev.flutter.pigeon.NonNullFieldFlutterApi.search', codec,
          binaryMessenger: binaryMessenger);
      if (api == null) {
        channel.setMessageHandler(null);
      } else {
        channel.setMessageHandler((Object? message) async {
          assert(message != null,
              'Argument for dev.flutter.pigeon.NonNullFieldFlutterApi.search was null.');
          final List<Object?> args = (message as List<Object?>?)!;
          final NonNullFieldSearchRequest? arg_request =
              (args[0] as NonNullFieldSearchRequest?);
          assert(arg_request != null,
              'Argument for dev.flutter.pigeon.NonNullFieldFlutterApi.search was null, expected non-null NonNullFieldSearchRequest.');
          final NonNullFieldSearchReply output = api.search(arg_request!);
          return output;
        });
      }
    }
  }
}
