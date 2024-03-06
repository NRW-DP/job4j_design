package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.*;

import static java.lang.System.out;

public class EchoServer {
    public static final Logger LOGGER = LoggerFactory.getLogger(EchoServer.class.getName());

    private static Map<String, String> parse(String message) {
        Map<String, String> params = new HashMap<>();
        String[] pairs = message.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(keyValue[0], keyValue[1]);
            }
        }
        return params;
    }

    public static void main(String[] args)  {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = input.readLine();
                    String queryString = message.split("\\s")[1].split("\\?")[1];
                    Map<String, String> params = parse(queryString);

                    if ("Exit".equals(params.get("msg"))) {
                        server.close();
                    } else if ("Hello".equals(params.get("msg"))) {
                        output.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write("HTTP/1.1 200 What\r\n\r\n".getBytes());
                    }

                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        out.println(string);
                    }
                    output.flush();
                }
            }
        } catch (IOException exception) {
            LOGGER.error("Server or send/receive error", exception);
        }
    }
}
