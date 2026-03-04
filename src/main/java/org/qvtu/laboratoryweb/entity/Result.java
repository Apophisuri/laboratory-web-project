package org.qvtu.laboratoryweb.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean state;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(true, "操作成功", data);
    }

    public static Result success(String msg, Object data) {
        return new Result(true, msg, data);
    }

    public static Result error(String msg) {
        return new Result(false, msg, null);
    }

    public static Result error(String msg, Object data) {
        return new Result(false, msg, data);
    }
}
