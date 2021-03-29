package org.maple.buffer;

import java.nio.ByteBuffer;

/**
 * isDirect 断此字节缓冲区是否为直接缓冲区
 * 一种是基于直接内存（也就是非堆内存）：本地IO-->直接内存-->本地IO
 * 另一种是非直接内存（也就是堆内存）：本地IO-->直接内存-->非直接内存-->直接内存-->本地IO
 *
 * 很明显，在做IO处理时，比如网络发送大量数据时，直接内存会具有更高的效率。
 * 直接内存使用allocateDirect创建，但是它比申请普通的堆内存需要耗费更高的性能。
 * 不过，这部分的数据是在JVM之外的，因此它不会占用应用的内存。
 * 所以呢，当你有很大的数据要缓存，并且它的生命周期又很长，那么就比较适合使用直接内存。
 *
 * 使用直接内存的情况：
 * - 1 有很大的数据需要存储，它的生命周期又很长
 * - 2 适合频繁的IO操作，比如网络并发场景
 */
public class BufferTest01 {

    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);
        System.out.println(buf.isDirect());
    }
}
