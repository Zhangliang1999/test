package com.jy.interceptor.activiti;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class FilterServletOutputStream extends ServletOutputStream {

  private DataOutputStream stream;
 

  public FilterServletOutputStream(OutputStream output) {
    stream = new DataOutputStream(output);
  }

  public void write(int b) throws IOException {
    stream.write(b);
  }

  public void write(byte[] b) throws IOException {
    stream.write(b);
  }

  public void write(byte[] b, int off, int len) throws IOException {
    stream.write(b, off, len);
  }
  

  public boolean isReady() {
    return true;
  }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}