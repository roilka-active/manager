package com.roilka.active.api.chain;

/**
 * @ClassName Handler
 * @Description TODO
 * @Author zhanghui1
 * @Date 2019/11/25 10:17
 **/
public abstract class Handler {

    private Handler success;

    public Handler getSuccess() {
        return success;
    }

    public void setSuccess(Handler success) {
        this.success = success;
    }
    public void excute(){
        handleProcess();
        if (success != null){
            success.excute();
        }
    }
    public abstract void handleProcess();
}
