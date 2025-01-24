package cn.ff.rpc.common.exception;

import java.io.Serial;

/**
 * 序列化异常类
 */

public class SerializerException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1L;

    public SerializerException(final Throwable e) {
        super(e);
    }

    public SerializerException(final String message) {
        super(message);
    }

    public SerializerException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
