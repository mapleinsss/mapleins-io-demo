package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 采用 channel 的方式读取
 */
@Log4j2
public class ChannelTest02 {

    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(50);
            channel.read(buffer);
            buffer.flip();
            log.debug("read success! value is {}", new String(buffer.array(), 0, buffer.remaining()));
        } catch (IOException e) {
            log.debug("write fail! exception is {}", e.getMessage());
        }
    }
}
