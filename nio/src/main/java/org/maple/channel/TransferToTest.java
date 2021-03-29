package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

@Log4j2
public class TransferToTest {
    public static void main(String[] args) {
        try (FileChannel inputChannel = new FileInputStream("d1.txt").getChannel();
             FileChannel outputChannel = new FileOutputStream("d2.txt").getChannel()) {
            inputChannel.transferTo(inputChannel.position() , inputChannel.size() , outputChannel);
        } catch (IOException e) {
            log.debug("transferTo fail! exception is {}", e.getMessage());
        }
    }
}
