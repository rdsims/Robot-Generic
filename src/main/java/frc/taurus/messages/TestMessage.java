// automatically generated by the FlatBuffers compiler, do not modify

package frc.taurus.messages;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class TestMessage extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_1_12_0(); }
  public static TestMessage getRootAsTestMessage(ByteBuffer _bb) { return getRootAsTestMessage(_bb, new TestMessage()); }
  public static TestMessage getRootAsTestMessage(ByteBuffer _bb, TestMessage obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public TestMessage __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public int value() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createTestMessage(FlatBufferBuilder builder,
      int value) {
    builder.startTable(1);
    TestMessage.addValue(builder, value);
    return TestMessage.endTestMessage(builder);
  }

  public static void startTestMessage(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addValue(FlatBufferBuilder builder, int value) { builder.addInt(0, value, 0); }
  public static int endTestMessage(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishTestMessageBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedTestMessageBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public TestMessage get(int j) { return get(new TestMessage(), j); }
    public TestMessage get(TestMessage obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

