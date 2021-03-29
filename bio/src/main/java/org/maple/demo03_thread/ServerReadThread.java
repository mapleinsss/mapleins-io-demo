package org.maple.demo03_thread;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

@Log4j2
public class ServerReadThread extends Thread {

    private final Socket socket;

    public ServerReadThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                log.debug("当前线程 {} 的服务端收到：{} : {}", Thread.currentThread().getName(), socket.getRemoteSocketAddress(), line);
            }
        } catch (Exception e) {
            log.debug("{} 下线了", socket.getRemoteSocketAddress());
        }
    }
}
