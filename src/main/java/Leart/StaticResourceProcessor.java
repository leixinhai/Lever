package Leart;

import java.io.IOException;

/**
 * @Author LeiXinHai
 * @creat 2019/1/2
 */
public class StaticResourceProcessor {
    public void process(Request request,Response response){
        try {
            response.sendStaticResource();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
