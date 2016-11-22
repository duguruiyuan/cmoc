package com.xuequ.cmoc.utils;

public class ZTRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 510L;

    /**
     * 异常类型
     */
    public static enum ErrorCode {
        /**
         * 数据不是最新的：1
         */
        ERROR_DATA_MODIFIED(1),
        /**
         * 其他数据异常：0
         */
        ERROR_OTHERS(0);

        private final int code;

        private ErrorCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    /**
     * 错误代码
     */
    private final ErrorCode code;

    /**
     * @param message 消息
     */
    public ZTRuntimeException(final String message) {
        super(message);
        this.code = ErrorCode.ERROR_OTHERS;
    }

    public ZTRuntimeException(Throwable cause) {
        super(cause);
        this.code = ErrorCode.ERROR_OTHERS;
    }

    /**
     * 构造器.
     * 
     * @param message 异常信息内容
     * @param cause 错误和异常类的父类
     * @param code 异常编码
     */
    public ZTRuntimeException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }
    /**
     * 构造器.
     * 
     * @param cause 错误和异常类的父类
     * @param code 异常编码
     */
    public ZTRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = ErrorCode.ERROR_OTHERS;
    }
    /**
     * 构造器.
     * 
     * @param cause 错误和异常类的父类
     * @param code 异常编码
     */
    public ZTRuntimeException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }

    /**
     * 构造器.
     * 
     * @return ErrorCode
     */
    public ErrorCode getErrorCode() {
        return code;
    }
}
