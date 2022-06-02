package utils;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPSocket {


    @Test
    public void client() throws IOException {
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"),9090);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(new File("OIP.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        socket.shutdownOutput();

        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bufferr = new byte[20];
        int len1;
        while((len1 = is.read(buffer)) != -1){
            baos.write(buffer,0,len1);
        }

        System.out.println(baos.toString());

        fis.close();
        os.close();
        socket.close();
        baos.close();
    }


    @Test
    public void server() throws IOException {

        ServerSocket ss = new ServerSocket(9090);

        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();

        FileOutputStream fos = new FileOutputStream(new File("OIP1.jpg"));

        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }

        System.out.println("图片传输完成");


        OutputStream os = socket.getOutputStream();
        os.write("图片已收到".getBytes());
        System.out.println("图片来自："+socket.getInetAddress().getHostAddress());

        //7.
        fos.close();
        is.close();
        socket.close();
        ss.close();
        os.close();

    }
}




