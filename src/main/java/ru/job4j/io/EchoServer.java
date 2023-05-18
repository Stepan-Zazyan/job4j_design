package ru.job4j.io;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String[] strin = in.readLine().split("=");
                    String[] words = strin[1].split(" ");
                    switch (words[0]) {
                        case ("Hello") -> out.write("Hello, dear friend.\r\n\r\n".getBytes());
                        case ("Bye") -> server.close();
                        default -> out.write((words[0] + ".\r\n\r\n").getBytes());
                    }
                    System.out.println(Arrays.toString(strin));
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}
