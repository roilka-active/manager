package com.roilka.active.api.chain;

/**
 * @ClassName Client
 * @Description TODO
 * @Author zhanghui1
 * @Date 2019/11/25 18:04
 **/
public class Client {
    static class HandlerA extends Handler{
        @Override
        public void handleProcess() {
            System.out.println("This is HandlerA!");
        }
    }
    static class HandlerB extends Handler{
        @Override
        public void handleProcess() {
            System.out.println("This is HandlerB!");
        }
    }
    static class HandlerC extends Handler{
        @Override
        public void handleProcess() {
            System.out.println("This is HandlerC!");
        }
    }

    public static void main(String[] args) {
        Handler HA = new HandlerA();
        Handler HB = new HandlerB();
        Handler HC = new HandlerC();
        HA.setSuccess(HB);
        HB.setSuccess(HC);
        HA.excute();
    }
}
