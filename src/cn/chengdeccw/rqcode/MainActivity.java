package cn.chengdeccw.rqcode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageView image0;
	private Button btnscan;
	private TextView text;
	private EditText input;
	private Button btngen;
	private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        text = (TextView) findViewById(R.id.text);
        input = (EditText) findViewById(R.id.input);
        img = (ImageView) findViewById(R.id.img);
        
        image0 = (ImageView) findViewById(R.id.image0);
        btnscan = (Button) findViewById(R.id.btnscam);
        btnscan.setOnClickListener(new View.OnClickListener() {//点击事件
			@Override
			public void onClick(View arg0) {   //借助开源库zxing启动二维码扫描
				Toast.makeText(MainActivity.this, "可以扫描二维码或者条形码", Toast.LENGTH_SHORT).show();
				Intent btnscan = new Intent(MainActivity.this, CaptureActivity.class);
				startActivityForResult(btnscan, 0);
			}
		});
        
        btngen = (Button) findViewById(R.id.btngen);
        btngen.setOnClickListener(new View.OnClickListener() {  //点击事件
			
			@Override
			public void onClick(View arg0) {
				String in = input.toString();
				if (in.equals("")) {
					Toast.makeText(MainActivity.this, "请输入文本", Toast.LENGTH_SHORT).show();
				}else {
					Bitmap qrcode;
					try {
						qrcode = EncodingHandler.createQRCode(in, 500);
						img.setImageBitmap(qrcode);
					} catch (WriterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
        
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode == RESULT_OK) {
			String result = data.getExtras().getString("result");
			text.setText(result);
		}
    }
    
}
