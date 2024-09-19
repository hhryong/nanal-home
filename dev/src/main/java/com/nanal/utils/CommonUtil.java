/**
 * 기존 MGOV에서 사용 하는 암호화 하는데 필요한 유틸
 */

package com.nanal.utils;

public class CommonUtil {
	private final static String msgString = "i-heart";
	private final static String admString = "gw1234$#@!";

	// ----------- add (2012.03.13) --------------------------------
	public static String cutString(int intSize, String strSource) {
		byte[] strByte = strSource.getBytes();

		if (strByte.length < intSize)
			return strSource;

		int cnt = 0;

		for (int i = 0; i < intSize; i++) {
			if (strByte[i] < 0)
				cnt++;
		}

		String ret;

		if (cnt % 2 == 0) {
			ret = new String(strByte, 0, intSize);
		} else {
			ret = new String(strByte, 0, intSize + 1);
		}

		return ret;

	} // END cutString

	public static String getAESPWD(int intSize, String pwd, String cryptKey) throws Exception {
		String ret = "";

		if (pwd.length() > 8) {
			ret = cutString(8, pwd);
		} else {
			ret = pwd;
		}

		ret = ret + cutString(intSize - ret.length(), cryptKey);

		return ret;

	} // END getCryptKey

	public static byte[] getCryptKey(int intSize, byte[] pwd, byte[] cryptKey) throws Exception {
		byte[] ret = new byte[intSize];

		if (pwd.length > 8) {
			for (int i = 0; i < 8; i++)
				ret[i] = pwd[i];
		} else {
			for (int i = 0; i < pwd.length; i++)
				ret[i] = pwd[i];
		}

		int x = 0;

		for (int i = pwd.length; i < ret.length; i++) {
			ret[i] = cryptKey[x];
			x++;
		}

		return ret;

	} // END getCryptKey

	public static byte[] paddingStringtoByte(int intSize, String strPad, String strSource) throws Exception {
		int intDifference = intSize - strSource.getBytes().length;

		if (intDifference > 0) {
			for (int i = 0; i < intDifference; i++) {
				strSource += strPad;
			}
		} else {
			StringBuffer sb = new StringBuffer(strSource);

			sb.setLength(intSize);
			strSource = sb.toString();
		}

		byte[] ret = new byte[intSize];
		byte[] srcByte = strSource.getBytes();

		for (int i = 0; i < srcByte.length; i++) {
			ret[i] = srcByte[i];
		}

		return ret;

	} // END paddingString

	public static byte[] leftPaddingStringtoByte(int intSize, String strPad, String strSource) throws Exception {
		int intDifference = intSize - strSource.getBytes().length;

		if (intDifference > 0) {
			for (int i = 0; i < intDifference; i++) {
				strSource = strPad + strSource;
			}
		} else {
			StringBuffer sb = new StringBuffer(strSource);

			sb.setLength(intSize);
			strSource = sb.toString();
		}

		byte[] ret = new byte[intSize];
		byte[] srcByte = strSource.getBytes();

		for (int i = 0; i < srcByte.length; i++) {
			ret[i] = srcByte[i];
		}

		return ret;

	} // END leftPaddingString

	public static byte[] hexToByteArray(String hex) {
		if (hex == null || hex.length() == 0) {
			return null;
		}

		byte[] ba = new byte[hex.length() / 2];
		for (int i = 0; i < ba.length; i++) {
			ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return ba;
	}

	// byte[] to hex
	public static String byteArrayToHex(byte[] ba) {
		if (ba == null || ba.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer(ba.length * 2);
		String hexNumber;
		for (int x = 0; x < ba.length; x++) {
			hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

			sb.append(hexNumber.substring(hexNumber.length() - 2));
		}
		return sb.toString();
	}

	// AES (msg)
	public static String decryptString(String data) {
		try {
			if (data == null || data.equals(""))
				return "";
			return new String((new AES(msgString)).decrypt(hexToByteArray(data)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// AES (msg)
	public static String encryptString(String data) {
		try {
			if (data == null || data.equals(""))
				return "";
			return byteArrayToHex((new AES(msgString)).encrypt(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// AES (adm)
	public static String decryptAdmString(String data) {
		try {
			if (data == null || data.equals(""))
				return "";
			return new String((new AES(admString)).decrypt(hexToByteArray(data)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// AES (adm)
	public static String encryptAdmString(String data) {
		try {
			if (data == null || data.equals(""))
				return "";
			return byteArrayToHex((new AES(admString)).encrypt(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
