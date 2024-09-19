package com.nanal.thread.log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nanal.setting.GaonSetting;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GaonLogFileDeleter implements Runnable
{

	private final GaonSetting setting;
	@Qualifier("rootLogger")
	private final Logger rootLogger;

	@Override
	public void run()
	{
		try
		{
			rootLogger.debug("로그파일 또는 임시 파일 삭제 시작");
			// tempFileDelete();
			logFileDelete();
			// recvCheckFileDelete();
			rootLogger.debug("로그파일 또는 임시 파일 삭제 완료");
		}
		catch (Exception e)
		{
			rootLogger.error("로그 파일 또는 임시 파일 삭제중 에러", e);
		}
	}

	// private void tempFileDelete()
	// {
	// 	long time = System.currentTimeMillis();
	// 	File folder = new File("./env/temp/");
	// 	if (folder.exists())
	// 	{
	// 		for (File file : folder.listFiles())
	// 		{
	// 			long elapsed = time - file.lastModified();
	// 			if (elapsed >= 90000000L)
	// 			{
	// 				file.delete();
	// 			}
	// 		}
	// 	}
	// }

	// private void recvCheckFileDelete()
	// {
	// 	long time = System.currentTimeMillis();
	// 	File folder = new File("./env/recv/");
	// 	if (folder.exists())
	// 	{
	// 		for (File file : folder.listFiles())
	// 		{
	// 			long elapsed = time - file.lastModified();
	// 			if (elapsed >= 600000L)
	// 			{
	// 				try {
	// 					file.delete();
	// 				} catch (Exception e) {

	// 				}
	// 			}
	// 		}
	// 	}
	// }

	private void logFileDelete() throws ParseException
	{
		long day = 86400000L;
		File directory = new File(setting.getLogFilePath());
		// 오늘 날짜
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());

		// 파일 리스트 불러오기
		if (directory.exists())
		{
			if (directory.listFiles() != null)
			{
				for (File file : directory.listFiles())
				{
					String[] logFile = file.getName().split("\\.");
					// 파일명.log.[날짜] 인 파일만 확인
					if (logFile.length == 3)
					{
						Calendar logDate = Calendar.getInstance();
						logDate.setTime(simpleDateFormat.parse(logFile[2]));
						long gap = (today.getTimeInMillis() - logDate.getTimeInMillis()) / day;
						if (gap >= setting.getLogSaveTerm())
						{
							rootLogger.debug(String.format("%s 로그 파일 삭제", file.getName()));
							file.delete();
						}
					}
				}
			}
		}
	}
}
