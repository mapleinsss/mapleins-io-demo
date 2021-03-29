package org.maple.demo01;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在以上通信中，服务端会一致等待客户端的消息，如果客户端没有进行消息的发送，服务端将一直进入阻塞状态。
 * 同时服务端是按照行获取消息的，这意味着客户端也必须按照行进行消息的发送，否则服务端将进入等待消息的阻塞状态！
 * 客户端的 Socket 退出，服务端会抛出异常而结束
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
