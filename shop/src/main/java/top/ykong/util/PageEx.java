package top.ykong.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageEx<T> extends Page {

    private final String DEFAULT_MSG = "成功";

    int code;

    String msg;

    public PageEx() {
        this.code = 0;
        this.msg = DEFAULT_MSG;
    }

    public PageEx(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public PageEx(int page, int limit) {
        super(page, limit);
    }


    T data;

    public PageEx(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public List<T> getData() {
        return super.getRecords();
    }

    public void setData(T data) {
        this.data = data;
    }

}
