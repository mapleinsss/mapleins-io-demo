package org.maple.buffer;

import lombok.extern.log4j.Log4j2;

import java.nio.ByteBuffer;

@Log4j2
public class BufferTest02 {

    public static void main(String[] args) {
        String str = "org.maple.buffer";
        // 1. 分配一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(20);
        log.debug("-----------------allocate()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        // 2. 利用 put() 存入数据到缓冲区中
        buf.put(str.getBytes());
        log.debug("-----------------put()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        // 3. 切换读取数据模式
        buf.flip();
        log.debug("-----------------flip()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        // 4. 利用 get() 读取缓冲区中的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        log.debug("buffer str is : {}", new String(dst, 0, dst.length));
        log.debug("-----------------get()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        // 5. rewind() : 可重复读
        buf.rewind();
        log.debug("-----------------rewind()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());

        // 6. clear() : 清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        buf.clear();
        log.debug("-----------------clear()----------------");
        log.debug("position: {}", buf.position());
        log.debug("limit: {}", buf.limit());
        log.debug("capacity: {}", buf.capacity());
        log.debug("data still exist, get first is : {}", (char)buf.get());

    }
}
