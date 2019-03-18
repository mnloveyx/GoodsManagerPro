package com.amuse.util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.protocol.HttpContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public  class CustomRequestRetryHandler implements HttpRequestRetryHandler{

	@Override
	public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
		
		 Boolean r = false;
		 if (executionCount > 3) //超过重试次数，就放弃
	         return false;
	      if (exception instanceof NoHttpResponseException) {//没有响应，重试
//	         return true;
	    	  r = true;
	      }else if (exception instanceof ConnectTimeoutException) {//连接超时，重试
	         r = true;
	      } else if (exception instanceof SocketTimeoutException) {//连接或读取超时，重试
	         r = true;
	      }else if (exception instanceof SSLHandshakeException) {//本地证书异常
	         r = false;
	      } else if (exception instanceof InterruptedIOException) {//被中断
	         r = false;
	      } else if (exception instanceof UnknownHostException) {//找不到服务器
	         r = false;
	      }  else if (exception instanceof SSLException) {//SSL异常
	         r = false;
	      } else {
	         log.error("未记录的请求异常：" + exception.getClass());
	      }
	      HttpClientContext clientContext = HttpClientContext.adapt(context);
	      HttpRequest request = clientContext.getRequest();
	      // 如果请求是幂等的，则重试
	      if (!(request instanceof HttpEntityEnclosingRequest)) return true;
	      
	      //如果是重试那么这里模拟重试间隔
	      if(r) {
	    	  try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	  return r;
	      }
	      
	      return false;
	   }
}
