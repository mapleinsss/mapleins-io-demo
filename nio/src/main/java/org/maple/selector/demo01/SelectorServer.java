package org.maple.selector.demo01;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 当调用 register(Selector sel, int ops) 将通道注册选择器时，选择器对通道的监听事件，
 * 需要通过第二个参数 ops 指定。可以监听的事件类型（用 可使用 SelectionKey 的四个常量 表示）：
 * 读 : SelectionKey.OP_READ （1）
 * 写 : SelectionKey.OP_WRITE （4）
 * 连接 : SelectionKey.OP_CONNECT （8）
 * 接收 : SelectionKey.OP_ACCEPT （16）
 * <p>
 * 若注册时不止监听一个事件，则可以使用“位或”操作符连接:
 * int interestSet = SelectionKey.OP_READ|SelectionKey.OP_WRITE
 */
@Log4j2
public class SelectorServer {
    public static void main(String[] args) {
        try (ServerSocketChannel ssChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            // 切换非阻塞模式
            ssChannel.configureBlocking(false);
            ssChannel.bind(new InetSocketAddress(9999));
            log.debug("server channel init success!");
            // 将通道注册到选择器上, 并且指定“监听接收事件”
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.debug("server channel had registered to selector success");
            // 轮询式的获取选择器上已经“准备就绪”的事件
            while (selector.select() > 0) {
                // 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    log.debug("server channel check out event!");
                    // 获取准备“就绪”的是事件
                    SelectionKey sk = it.next();
                    // 判断具体是什么事件准备就绪
                    if (sk.isAcceptable()) {
                        // 获取客户端连接
                        SocketChannel sChannel = ssChannel.accept();
                        // 切换非阻塞模式
                        sChannel.configureBlocking(false);
                        // 将该通道注册到选择器上
                        sChannel.register(selector, SelectionKey.OP_READ);
                        log.debug("server channel check out accept event and register this socket channel to selector");
                    } else if (sk.isReadable()) {
                        // 获取当前选择器上“读就绪”状态的通道
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        // 读取数据
                        ByteBuffer buf = ByteBuffer.allocate(32);
                        int len = 0;
                        while ((len = sChannel.read(buf)) > 0) {
                            buf.flip();
                            log.debug("socket channel is rdy to read, the data is ： {}",
                                    new String(buf.array(), 0, len));
                            buf.clear();
                        }
                    }
                    // 取消选择键 SelectionKey
                    it.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
