package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 采用 channel 的方式进行文件复制
 */
@Log4j2
public class ChannelTest03 {

    public static void main(String[] args) {
        try (FileChannel srcChannel = new FileInputStream("data.txt").getChannel();
             FileChannel dstChannel = new FileOutputStream("data-copy.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int flag = srcChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                buffer.flip();
                dstChannel.write(buffer);
                buffer.clear();
            }
            log.debug("read success!");
        } catch (IOException e) {
            log.debug("write fail! exception is {}", e.getMessage());
        }
    }
}
