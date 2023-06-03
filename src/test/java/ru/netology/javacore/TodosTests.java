package ru.netology.javacore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.Socket;

public class TodosTests {
    public static OutputStream outputStream;
    public static InputStream inputStream;
    public static PrintWriter out;
    public static BufferedReader in;

    @BeforeEach
    public void openConnection() {
        String host = "127.0.0.1";
        int port = 8989;
        try {
            Socket clientSocket = new Socket(host, port);
            outputStream = clientSocket.getOutputStream();
            out = new PrintWriter(outputStream, true);
            inputStream = clientSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void doCommand1() throws IOException {
        String command1 = "{\"type\":\"ADD\",\"task\":\"Первая\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Первая", result);
    }

    @Test
    public void doCommand2() throws IOException {
        String command1 = "{\"type\":\"ADD\",\"task\":\"Вторая\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Вторая Первая", result);
    }

    @Test
    public void doCommand3() throws IOException {
        String command1 = "{\"type\":\"ADD\",\"task\":\"Третья\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Вторая Первая Третья", result);
    }

    @Test
    public void doCommand4() throws IOException {
        String command1 = "{\"type\":\"REMOVE\",\"task\":\"Первая\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Вторая Третья", result);
    }

    @Test
    public void doCommand5() throws IOException {
        String command1 = "{\"type\":\"REMOVE\",\"task\":\"Вторая\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Третья", result);
    }

    @Test
    public void doCommand6() throws IOException {
        String command1 = "{\"type\":\"RESTORE\",\"task\":\"\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Вторая Третья", result);
    }

    @Test
    public void doCommand7() throws IOException {
        String command1 = "{\"type\":\"RESTORE\",\"task\":\"\"}";
        out.println(command1);
        String result = in.readLine();
        System.out.println(result);
        Assertions.assertEquals("Вторая Первая Третья", result);
    }

    @AfterEach
    public void closeConnection() throws IOException {
        outputStream.close();
        inputStream.close();
    }
}