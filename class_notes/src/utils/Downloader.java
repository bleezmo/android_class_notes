package utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

public class Downloader {
	public static final byte[] downloadBytes(String strUrl){
		try {
			URL url = new URL(strUrl);
			ByteBuffer bb = ByteBuffer.allocate(1000);
			BufferedInputStream in = new BufferedInputStream(url.openStream(),5000);
			int more = 0;
			while(more > -1){
				int available = in.available();
				if(bb.remaining() < available){
					bb = resizeByteBuffer(bb,available * 2);
				}
				byte[] buf;
				if(available > 0){
					buf = new byte[available];
				}else{
					buf = new byte[1];
				}
				more = in.read(buf);
				bb.put(buf);
			}
			bb.flip();
			byte[] array = new byte[bb.limit()];
			bb.get(array);
			return array;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static final ByteBuffer resizeByteBuffer(ByteBuffer bb, int extra){
		ByteBuffer newbb = ByteBuffer.allocate(bb.capacity()+extra);
		newbb.put(bb.array(), 0, bb.position());
		return newbb;
	}
}
