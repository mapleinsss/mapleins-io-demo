package org.maple.demo02_scanner;

import lombok.extern.log4j.Log4j2;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

@Log4j2
public class ClientDemo {
    public static void main(String[] args) throws Exception {
        log.debug("客户端启动");
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        Scanner sc = new Scanner(System.in);
        while (true) {
            log.debug("请说:");
            String msg = sc.nextLine();
            ps.println(msg);
            ps.flush();
        }
    }
}
