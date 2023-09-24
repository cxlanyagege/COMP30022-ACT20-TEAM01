/**
 * Class Name: Result
 * Description: Result indicator in attachment uploading
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.vo;

public class Result<T> {
    public int code;
    public String msg;
    public T data;

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result success(String msg){
        return new Result(0, msg, null);
    }

    public static <T> Result success(T data){
        return new Result(0, "suc", data);
    }

    public static <T> Result success(String msg, T data){
        return new Result(0, msg, data);
    }

    public static <T> Result fail() {
        return new Result(-1, "fail", null);
    }
}