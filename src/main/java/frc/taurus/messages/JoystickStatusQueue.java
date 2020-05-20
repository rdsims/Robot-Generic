package frc.taurus.messages;

import java.nio.ByteBuffer;
import java.util.Optional;

import com.google.flatbuffers.FlatBufferBuilder;

public class JoystickStatusQueue extends MessageQueue<JoystickStatus> {

    static final int size = 32;

    public JoystickStatusQueue() {
        super(size);
    }

    public void write(FlatBufferBuilder builder, int offset) {
        JoystickStatus.finishJoystickStatusBuffer(builder, offset);
        ByteBuffer bb = builder.dataBuffer();
        mQueue.write(bb);
    }

    public JoystickStatusReader makeReader() {
        return new JoystickStatusReader(this.mQueue);
    }



    public class JoystickStatusReader extends MessageReader<JoystickStatus> {

        private JoystickStatusReader(GenericQueue<ByteBuffer> queue) {
            super(queue);
        }
    
        public Optional<JoystickStatus> read() {
            Optional<ByteBuffer> obb = mReader.read();
            if (obb.isEmpty()) {
                return Optional.empty();
            }
            var out = JoystickStatus.getRootAsJoystickStatus(obb.get());
            return Optional.of(out);
        }
    
        public Optional<JoystickStatus> readLast() {
            Optional<ByteBuffer> obb = mReader.readLast();
            if (obb.isEmpty()) {
                return Optional.empty();
            }
            var out = JoystickStatus.getRootAsJoystickStatus(obb.get());
            return Optional.of(out);
        }    
    }    
}