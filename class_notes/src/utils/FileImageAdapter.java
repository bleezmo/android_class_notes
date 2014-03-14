package utils;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.class_notes.R;
/**
 * set up an array of files stored in the cache dir, 
 * each containing the image downloaded
 * @author josh
 *
 */
public class FileImageAdapter  extends BaseAdapter{
	private File[] images;
	private Activity ctx;
	public FileImageAdapter(Activity ctx, int imageCount){
		this.ctx = ctx;
		images = new File[imageCount];
	}
	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView iv;
		if(convertView == null){
			LayoutInflater inflater = ctx.getLayoutInflater();
			iv = (ImageView) inflater.inflate(R.layout.myimage, null);
		}else{
			iv = (ImageView) convertView;
		}
		if(images[position] == null){
			//download the image in a separate thread
			new Thread(new FileImageDownloader(ctx,iv,images,position)).start();
		}else{
			iv.setImageBitmap(BitmapFactory.decodeFile(images[position].getPath()));
		}
		return iv;
	}
	private static class FileImageDownloader implements Runnable{
		private File[] images;
		private int position;
		private ImageView iv;
		private Context ctx;
		public FileImageDownloader(Context ctx, ImageView iv, File[] images, int position){
			this.images = images;
			this.position = position;
			this.iv = iv;
			this.ctx = ctx;
		}
		@Override
		public void run() {
			File file = DownloaderTempFile.downloadBytesToFile(ctx, "http://theoutlawlife.files.wordpress.com/2013/01/oh-the-huge-manatee.jpg");
			if(file != null){
				images[position] = file;
				iv.post(new Runnable(){
					@Override
					public void run() {
						iv.setImageBitmap(BitmapFactory.decodeFile(images[position].getPath()));
						iv.invalidate();
					}
				});
			}else{
				Log.e("image error","error occurred downloading the image");
			}
		}
	}
}
