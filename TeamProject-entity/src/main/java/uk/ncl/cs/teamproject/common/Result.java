package uk.ncl.cs.teamproject.common;

import java.io.Serializable;

/**
 * @author yantao xu
 */
public class Result<T> implements Serializable {

    protected static final long serialVersionUID = -4577255781088498763L;
    protected static final int OK = 0;
    protected static final int FAIL = 1;
    protected static final int UNAUTHORIZED = 2;

    protected T data; //Server-side data
    private int status = OK; //Status Code
    private String msg = ""; //Description information

    //APIS
    public static Result isOk() {
        return new Result();
    }

    public static Result isFail() {
        return new Result().status(FAIL);
    }

    public static Result isFail(Throwable e) {
        return isFail().msg(e);
    }

    public Result msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public Result msg(String s) {
        this.setMsg(s);
        return this;
    }

    public Result data(T data) {
        this.setData(data);
        return this;
    }

    public Result status(int status) {
        this.setStatus(status);
        return this;
    }


    //Constructors
    public Result() {

    }

   //Getter&Setters

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
