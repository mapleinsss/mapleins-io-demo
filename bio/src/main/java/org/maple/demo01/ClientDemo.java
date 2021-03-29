package org.maple.demo01;

import lombok.extern.log4j.Log4j2;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Java提供了基于套接字（端口）Socket的网络通信模式，我们基于这种模式就可以直接实现TCP通信。
 * 只要用Socket通信，那么就是基于TCP可靠传输通信。
 */
@Log4j2
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        log.debug("客户端启动");
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.println("我是客户端，我想约你吃小龙虾！！！");
        ps.flush();
    }
}
