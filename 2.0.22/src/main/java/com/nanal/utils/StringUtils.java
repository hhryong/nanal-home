package com.nanal.utils;

public class StringUtils
{
	/**
	 * 문자열 안에 \\r -> \r \\n -> \n \r\n -> \n 으로 변경
	 *
	 * @param s 문자열
	 * @return 변경된 문자열
	 */
	public static String replaceNewLineChar(String s)
	{
		s = s.replaceAll("\\\\r", "\r");
		s = s.replaceAll("\\\\n", "\n");
		s = s.replaceAll("\r\n", "\n");
		return s;
	}

	/**
	 * 문자열이 NULL 혹은 비어있는지 확인
	 *
	 * @param s 문자열
	 * @return NULL 혹은 비어있는지 여부
	 */
	public static boolean isNullOrEmpty(String s)
	{
		return s == null || s.trim().isEmpty();
	}

	/**
	 * 마지막 두글자 *로 가리기
	 * 
	 * @param s
	 * @return
	 */
	public static String hiddenEnd2Char(String s)
	{
		return s.substring(0, s.length() - 2) + "**";
	}
}
