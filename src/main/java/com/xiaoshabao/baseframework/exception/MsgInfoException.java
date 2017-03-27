package com.xiaoshabao.baseframework.exception;

import com.xiaoshabao.baseframework.enums.ErrorInterface;

public class MsgInfoException extends ServiceException{

  private static final long serialVersionUID = 1L;

  public MsgInfoException(String mess) {
    super(mess);
  }
  
  public MsgInfoException(ErrorInterface error) {
    super(error.getMessage());
  }

  public MsgInfoException(String message, Throwable cause) {
    super(message, cause);
  }

}
