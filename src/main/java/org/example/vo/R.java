package org.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R {
  
    private int code; // 响应状态码  
    private String message; // 响应消息  
    private Object data; // 响应数据
  
    // 构造方法、getter和setter省略...  
  
    // 静态方法用于创建成功响应  
    public static R ok(Object data) {
        R r = new R();  
        r.setCode(200); // 设置成功状态码  
        r.setMessage("操作成功"); // 设置成功消息  
        r.setData(data); // 初始化数据Map
        return r;
    }

    public static R error(Object error) {
        R r = new R();
        r.setCode(500); // 设置成功状态码
        r.setMessage("操作失败"); // 设置成功消息
        r.setData(error); // 初始化数据Map
        return r;
    }

  
    // 其他方法...  
}