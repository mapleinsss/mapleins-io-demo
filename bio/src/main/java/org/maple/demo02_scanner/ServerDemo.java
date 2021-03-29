package org.maple.demo02_scanner;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端只能处理一个客户端的请求，因为服务端是单线程的。一次只能与一个客户端进行消息通信。
 */
@Log4j2
public class ServerDemo {

    public static void main(String[] args) throws Exception {
        log.debug("服务器启动");
        ServerSocket serverSocket = new ServerSocket(8888);
        // accept 阻塞状态
        Socket socket = serverSocket.accept();
        log.debug("接收到客户端连接");
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        // read 阻塞状态
        // 此处用 readLine 必须读取到客户端换行，才能收到消息
        while ((line = br.readLine()) != null) {
            System.out.println("服务端收到：" + line);
        }
    }
}
