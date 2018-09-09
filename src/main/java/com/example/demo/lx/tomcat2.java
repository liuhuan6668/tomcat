package com.example.demo.lx;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tomcat2 {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8080);
		System.out.println("猫已经启动");
		Socket kehu = server.accept();//客户端请求
		InputStream input = kehu.getInputStream();//输入流
		OutputStream output = kehu.getOutputStream();//输出流
		//浏览器请求图片
		byte[] by=new byte[10000];//设置大小
		input.read(by);//读取到数组
		String re = new String(by);
		System.out.println("浏览器说:\r\n"+re);
		String line1 = re.split("\r\n")[0];
		String path = line1.split(" ")[1];
		if(path.indexOf(".jpg")!=-1) {
			//报文头
			String str="HTTP/1.1 200 OK\r\n"
					+"Content-Type: image/jpg\r\n"+//返回的内容和编码格式
					"\r\n";//换行必须有,报文头和内容直接有空格
					output.write(str.getBytes());
			//
			FileInputStream fo = new FileInputStream("D:\\Work\\EclipseWork\\demo\\src\\main\\java\\com\\example\\demo\\lx\\"+path.substring(1));
			int b=fo.read();//读取一个int
			while(b!=-1) {
				output.write(b);
				b=fo.read();
			}
			
		}else {
			//服务器返回
			String str="HTTP/1.1 200 OK\r\n"
			+"Content-Type: text/html;charset=utf-8\r\n"+//返回的内容和编码格式
			"\r\n"+//换行必须有,报文头和内容直接有空格
			"<button onclick='alert(666)'>没有资源</button>";
			output.write(str.getBytes());
		}
		
		
		
		//关闭
		input.close();
		output.flush();
		output.close();
	}
}
