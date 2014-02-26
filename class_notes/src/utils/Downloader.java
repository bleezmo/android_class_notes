package utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

public class Downloader {
	public static final Either<StringBuffer> downloadText(String strUrl){
		try {
			URL url = new URL(strUrl);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(url.openStream()));
			StringBuffer sb = new StringBuffer();
			String line = in.readLine();
			while(line != null){
				sb.append(sb);
				line = in.readLine();
			}
			return new Success<StringBuffer>(sb);
		} catch (MalformedURLException e) {
			return new Failure<StringBuffer>(e);
		} catch (IOException e) {
			return new Failure<StringBuffer>(e);
		}
	}
	public static final Either<byte[]> downloadBytes(String strUrl){
		try {
			URL url = new URL(strUrl);
			ByteBuffer bb = ByteBuffer.allocate(1000);
			BufferedInputStream in = new BufferedInputStream(url.openStream());
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
			return new Success<byte[]>(array);
		} catch (MalformedURLException e) {
			return new Failure<byte[]>(e);
		} catch (IOException e) {
			return new Failure<byte[]>(e);
		}
	}
	private static final ByteBuffer resizeByteBuffer(ByteBuffer bb, int extra){
		ByteBuffer newbb = ByteBuffer.allocate(bb.capacity()+extra);
		newbb.put(bb.array(), 0, bb.position());
		return newbb;
	}
}