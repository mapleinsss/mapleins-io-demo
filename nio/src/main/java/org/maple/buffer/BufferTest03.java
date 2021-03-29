package org.maple.buffer;

import lombok.extern.log4j.Log4j2;

import java.nio.ByteBuffer;

@Log4j2
public class BufferTest03 {

    public static void main(String[] args) {
        String str = "org.maple.buffer";
        ByteBuffer buf = ByteBuffer.allocate(20);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 3);
        log.debug("get index [0,3), the data is : {}" , new String(dst, 0, 3));
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        log.debug("-----------------mark()----------------");
        // 标记当前的 position
        buf.mark();

        buf.get(dst, 3, 4);
        log.debug("get offset from 3 length is 4, the data is : {}" , new String(dst, 0, 7));
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        log.debug("-----------------reset()----------------");
        // reset 回到 mark 的 position
        buf.reset();
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        log.debug("-----------------hasRemaining()----------------");
        // 判断缓冲区中是否还有剩余数据
        log.debug("buffer hasRemaining: {}", buf.hasRemaining());

        log.debug("-----------------remaining()----------------");
        // 获取缓冲区中可以操作的数量, limit - position
        log.debug("buffer remain byte count is : {}", buf.remaining());
    }
}
