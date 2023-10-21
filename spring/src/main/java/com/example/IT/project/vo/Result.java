/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: response package that is used to return several type of messages
 * in JSON file back to the frontend
 */

package com.example.it.project.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    public int code;
    public String msg;
    public T data;

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
