package tw.com.eeit.shop.utils;

public class CommonUtil {

	/**
	 * 判斷傳入的 byte[] 是否有值。
	 */
	public static boolean isByteArrayNotBlank(byte[] bytes) {
		return !isByteArrayBlank(bytes);
	}

	/**
	 * 判斷傳入的 byte[] 是否為空。
	 */
	public static boolean isByteArrayBlank(byte[] bytes) {
		return bytes == null || bytes.length == 0;
	}
}
