import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author LeiXinHai
 * @creat 2019/1/2
 */
public class Test implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("来自service方法");
        PrintWriter out=servletResponse.getWriter();
        String errorMessage = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: 15\r\n" +
                "\r\n" ;
        out.println(errorMessage);
        out.println("hello success");
//        out.print("test print");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
