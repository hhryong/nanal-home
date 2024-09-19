package com.nanal.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class SocketUtils
{
	/**
	 * 데이터 보내기
	 *
	 * @param bs 소켓의 outputstream
	 * @param data 데이터
	 * @throws IOException
	 */
	public static void write(BufferedOutputStream bs, String data) throws IOException
	{
		bs.write(data.getBytes("UTF-8"));
		bs.flush();
	}

	/**
	 * 서버 연결이 되는지 확인
	 *
	 * @param host ip
	 * @param port port
	 * @return 성공 여부
	 */
	public static boolean connectionTest(String host, int port, int timeout)
	{
		Socket socket = null;
		try
		{
			SocketAddress socketAddress = new InetSocketAddress(host, port);
			socket = new Socket();
			socket.connect(socketAddress, timeout * 1000);
			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (socket != null)
			{
				try
				{
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static byte[] read(BufferedInputStream bis, int size) throws IOException
	{
		byte[] buffer = new byte[size];
		int total = 0;
		while (total < size)
		{
			int size2 = bis.read(buffer, total, size - total);
			if (size2 < 0)
			{
				throw new IOException(String.format("소켓 연결이 끊어져 %dB 만큼 읽는데 실패하였습니다.", size));
			}
			total += size2;
		}

		return buffer;
	}

	public static void read(byte[] buf, BufferedInputStream bis) throws IOException
	{
		for (int i = 0; i < buf.length; i++)
		{
			int b = bis.read();
			if (b < 0)
			{
				throw new IOException(String.format("소켓 연결이 끊어져 %dB 만큼 읽는데 실패하였습니다.", buf.length));
			}
			else
			{
				buf[i] = (byte) b;
			}
		}
	}

	public static void read(ByteBuffer buf, ByteChannel channel) throws IOException
	{
		buf.clear();
		while (buf.hasRemaining())
		{
			int r = channel.read(buf);
			if (r < 0)
			{
				throw new IOException(String.format("연결이 끊어져 %dB 만큼 읽는데 실패하였습니다.", buf.capacity()));
			}
		}
	}
}
