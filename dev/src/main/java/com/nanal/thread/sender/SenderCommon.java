package com.nanal.thread.sender;

// import com.nanal.quartz.PackUniqueMgrJob;

import java.io.*;
import java.util.Map;

public class SenderCommon
{

	/**
	 * 파일에서 이때까지 보낸 갯수 가져오기(연락처 별)
	 *
	 * @param phoneCount 저장할 Map공간
	 * @return 파일에 저장된 일자 값
	 */
	public static int loadPhoneCount(Map<String, Integer> phoneCount)
	{
		int oldDay = 0;
		File phoneFile = new File("./env/phone.txt");
		if (phoneFile.exists())
		{
			try
			{
				FileReader fileReader = new FileReader(phoneFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line;
				while ((line = bufferedReader.readLine()) != null)
				{
					String[] keyValue = line.split(":");
					if (keyValue[0].equals("day"))
					{
						oldDay = Integer.parseInt(keyValue[1]);
					}
					else
					{
						phoneCount.put("0" + keyValue[0], Integer.parseInt(keyValue[1]));
					}
				}
				bufferedReader.close();
				phoneFile.delete();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return oldDay;
	}

	/**
	 * 연락처별로 오늘 보낸 메시지 개수를 파일에 저장
	 *
	 * @param phoneCount 연락처별 개수를 저장한 MAP
	 * @param oldDay 현재 일
	 */
	public static void savePhoneCount(Map<String, Integer> phoneCount, int oldDay)
	{
		File file = new File("./env/");
		if (!file.exists())
		{
			file.mkdirs();
		}

		try
		{
			FileWriter fileWriter = new FileWriter("./env/phone.txt");
			fileWriter.write(String.format("day:%d\n", oldDay));
			for (Map.Entry<String, Integer> entry : phoneCount.entrySet())
			{
				fileWriter.write(
						String.format("%s:%s\n", entry.getKey().substring(1), entry.getValue()));
			}
			fileWriter.flush();
			fileWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
