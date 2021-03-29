package org.maple.demo03_thread;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 每个Socket接收到，都会创建一个线程，线程的竞争、切换上下文影响性能；
 * 每个线程都会占用栈空间和CPU资源；
 * 并不是每个socket都进行IO操作，无意义的线程处理；
 * 客户端的并发访问增加时。服务端将呈现1:1的线程开销，访问量越大，系统将发生线程栈溢出，线程创建失败，最终导致进程宕机或者僵死，从而不能对外提供服务。
 */
@Log4j2
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        log.debug("服务器启动");
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            // accept 阻塞状态
            Socket socket = serverSocket.accept();
            // 多线程去处理每个 socket
            new ServerReadThread(socket).start();
            log.debug("{} 上线了", socket.getRemoteSocketAddress());
        }
    }
}
