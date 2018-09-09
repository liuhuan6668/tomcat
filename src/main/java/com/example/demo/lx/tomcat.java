package com.example.demo.lx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tomcat {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		System.out.println("猫已经启动");
		Socket kehu = server.accept();//客户端请求
		InputStream input = kehu.getInputStream();//输入流
		OutputStream output = kehu.getOutputStream();//输出流
		//浏览器请求
		byte[] by=new byte[10000];//设置大小
		input.read(by);//读取到数组
		System.out.println("浏览器说:\r\n"+new String(by));
		//服务器返回
		String str="HTTP/1.1 200 OK\r\n"
		+"Content-Type: text/html;charset=utf-8\r\n"+//返回的内容和编码格式
		"\r\n"+//换行必须有,报文头和内容直接有空格
		"<button onclick='alert(666)'>添加</button>OK success~~~";
		output.write(str.getBytes());
		
		//关闭
		input.close();
		output.flush();
		output.close();
	}
}
