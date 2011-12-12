package QRCodeLibEx;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeLibEx {

	public static void CreateQRCode(FileOutputStream fos) throws WriterException, FileNotFoundException, IOException
	{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String text  ="홍길동\n연락처:010-1234-7777\n이건 중요한 것입니다. 돌려주시면 사례하겠습니다.";
		text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
		
		QRCodeWriter qrw = new QRCodeWriter();
		BitMatrix bitMatrix = qrw.encode(text, BarcodeFormat.QR_CODE, 200, 200);
				
		MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);		
	}
}


