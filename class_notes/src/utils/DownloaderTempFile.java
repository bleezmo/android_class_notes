package utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import android.content.Context;

public class DownloaderTempFile {
	/**
	 * download the image to file in the cache dir
	 * @param ctx - we need the context in order to get the cache dir for this application
	 * @param strurl
	 * @return
	 */
	public static File downloadBytesToFile(Context ctx, String strurl){
		try {
			URL url = new URL(strurl);
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			//assign the file a random id for a name and place it in the cache dir
			File file = new File(ctx.getCacheDir(),UUID.randomUUID().toString());
			//open the file and write to it
			FileOutputStream fis = new FileOutputStream(file);
			int more = 0;
			while(more > -1){
				int available = bis.available();
				if(available == 0) available = 1;
				byte[] buf = new byte[available];
				more = bis.read(buf);
				fis.write(buf);
			}
			bis.close();
			fis.flush();
			fis.close();
			return file;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
