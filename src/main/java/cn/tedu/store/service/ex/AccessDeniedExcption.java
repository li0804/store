package cn.tedu.store.service.ex;

/**
 * 非法访问异常
 */
public class AccessDeniedExcption extends ServiceException {

	private static final long serialVersionUID = 2187156790961238675L;

	public AccessDeniedExcption() {
		super();
	}

	public AccessDeniedExcption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccessDeniedExcption(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDeniedExcption(String message) {
		super(message);
	}

	public AccessDeniedExcption(Throwable cause) {
		super(cause);
	}

}
