package io.explore.nio;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

@Log4j2
public class MemoryMappedFileTest {

    @Test
    public void byteBufferDemo() {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
    }
}
