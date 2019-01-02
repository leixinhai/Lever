package Leart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author LeiXinHai
 * @creat 2019/1/2
 */
public class HttpServer1 {
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer1 server = new HttpServer1();
        server.await();
    }

    private void await(){
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try{
                socket=serverSocket.accept();
                input=socket.getInputStream();
                output=socket.getOutputStream();

                //创建Request对象 然后解析input
                Request request=new Request(input);
                request.parse();

                //创建Response对象
                Response response=new Response(output);
                response.setRequest(request);

                //判断请求的是静态资源还是一个servlet
                //如果是servlet的话要写成这种格式"/servlet/"
                if (request.getUri().startsWith("/servlet/")){
                    System.out.println(request.getUri());
                    ServletProcessor1 processor=new ServletProcessor1();
                    processor.process(request,response);
                }
                else{
                    StaticResourceProcessor processor=new StaticResourceProcessor();
                    processor.process(request,response);
                }

                socket.close();

                shutdown=request.getUri().equals(SHUTDOWN_COMMAND);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }

    }
}
