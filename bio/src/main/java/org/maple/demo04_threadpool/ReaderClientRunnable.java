package org.maple.demo04_threadpool;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Log4j2
public class ReaderClientRunnable implements Runnable {

    private final Socket socket;

    public ReaderClientRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.debug("数据线程为 {} 的服务端数据收到了： {}", Thread.currentThread().getName(), line);
            }
        } catch (IOException e) {
            log.debug("线程为 {} 下线了", Thread.currentThread().getName());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
