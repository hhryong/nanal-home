package com.nanal.thread;

public class GateWayServerInfo
{
	public String ip;
	public int port;

	public GateWayServerInfo(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}

	@Override
	public String toString()
	{
		return String.format("%s:%d", ip, port);
	}
}
