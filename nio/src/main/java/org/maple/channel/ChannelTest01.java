package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 采用 channel 的方式写入文件
 */
@Log4j2
public class ChannelTest01 {

    public static void main(String[] args) {
        try (FileChannel channel = new FileOutputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(50);
            buffer.put("package org.maple.channel;".getBytes());
            buffer.flip();
            channel.write(buffer);
            log.debug("write success!");
        } catch (IOException e) {
            log.debug("write fail! exception is {}", e.getMessage());
        }
    }
}
