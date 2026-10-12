package chain;

abstract class  Handler{
    protected Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }
    public abstract void handleRequest(String request);
}

class HandlerA extends Handler{
    @Override
    public void handleRequest(String request) {
        if(request.equals("A")){
            System.out.println("处理器A正在处理请求。。。");
        } else if(next!=null){
            next.handleRequest(request);
        }
    }
}

class HandlerB extends Handler{
    @Override
    public void handleRequest(String request) {
        if(request.equals("B")){
            System.out.println("处理器B正在处理请求。。。");
        } else if(next!=null){
            next.handleRequest(request);
        }
    }
}
public class Client {
    public static void main(String[] args) {
       Handler handlerA = new HandlerA();
       Handler handlerB = new HandlerB();
       handlerA.setNext(handlerB);
       //handlerA.handleRequest("A");
       handlerA.handleRequest("B");
    }
}


