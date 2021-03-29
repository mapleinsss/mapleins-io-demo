package org.maple.demo04_threadpool;

import lombok.extern.log4j.Log4j2;
import org.maple.demo03_thread.ServerReadThread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步io采用了线程池实现，因此避免了为每个请求创建一个独立线程造成线程资源耗尽的问题，
 * 但由于底层依然是采用的同步阻塞模型，因此无法从根本上解决问题。
 * 如果单个消息处理的缓慢，或者服务器线程池中的全部线程都被阻塞，
 * 那么后续socket的i/o消息都将在队列中排队。新的Socket请求将被拒绝，客户端会发生大量连接超时。
 */
@Log4j2
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        try {
            log.debug("服务器启动");
            ServerSocket ss = new ServerSocket(8888);
            HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool(12, 1000);

            // 客户端可能有很多个
            while(true){
                Socket socket = ss.accept() ; // 阻塞式的！
                log.debug("{} 上线了", socket.getRemoteSocketAddress());
                // 从线程池中拿线程去处理，拿不到则排队
                handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
