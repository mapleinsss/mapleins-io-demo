package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 从目标通道中去复制原通道数据
 */
@Log4j2
public class TransferFromTest {

    public static void main(String[] args) {
        try (FileChannel inputChannel = new FileInputStream("d1.txt").getChannel();
             FileChannel outputChannel = new FileOutputStream("d2.txt").getChannel()) {
            outputChannel.transferFrom(inputChannel, inputChannel.position(), inputChannel.size());
        } catch (IOException e) {
            log.debug("transferFrom fail! exception is {}", e.getMessage());
        }
    }
}
