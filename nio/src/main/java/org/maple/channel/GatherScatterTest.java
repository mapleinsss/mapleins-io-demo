package org.maple.channel;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散读取 (Scatter)：是指把 Channel 的数据读入到多个 buffer 中去
 * 聚集写入 (Gathering)：是指将多个 Buffer 中的数据“聚集”到 Channel。
 */
@Log4j2
public class GatherScatterTest {

    public static void main(String[] args) {
        try (FileChannel gatherChannel = new RandomAccessFile("gather.txt", "rw").getChannel();
             FileChannel scatterChannel = new RandomAccessFile("scatter.txt", "rw").getChannel()) {
            ByteBuffer buf1 = ByteBuffer.allocate(7);
            ByteBuffer buf2 = ByteBuffer.allocate(10);

            ByteBuffer[] bufs = {buf1, buf2};
            gatherChannel.read(bufs);

            for (ByteBuffer byteBuffer : bufs) {
                byteBuffer.flip();
            }

            log.debug("buf1 data is : {} ", new String(bufs[0].array(), 0, bufs[0].limit()));
            log.debug("buf2 data is : {} ", new String(bufs[1].array(), 0, bufs[1].limit()));

            scatterChannel.write(bufs);
        } catch (IOException e) {
            log.debug("gather scatter fail! exception is {}", e.getMessage());
        }
    }
}
