package com.xiaoshabao.baseframework.exception;

import com.xiaoshabao.baseframework.enums.ErrorInterface;

public class MsgErrorException extends ServiceException{

  private static final long serialVersionUID = 1L;

  public MsgErrorException(String mess) {
    super(mess);
  }
  
  public MsgErrorException(ErrorInterface error) {
    super(error.getMessage());
  }

  public MsgErrorException(String message, Throwable cause) {
    super(message, cause);
  }

}
