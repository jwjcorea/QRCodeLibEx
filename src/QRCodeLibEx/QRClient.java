package QRCodeLibEx;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;

public class QRClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtNickname;
	private JTextField txtContact;
	private JTextArea txtComment;
	private JLabel label_QRImage;
	BufferedImage m_bufImg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String path = "d:/qrcode.png";
				    BufferedImage image = ImageIO.read(new File(path));
				    
					QRClient frame = new QRClient(image);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	void createQRImage(String _strContent) throws WriterException, FileNotFoundException, IOException 
	{
		String strContent = new String(_strContent.getBytes("UTF-8"), "ISO-8859-1");
	
		QRCodeWriter qrw = new QRCodeWriter();
		BitMatrix bitMatrix = qrw.encode(strContent, BarcodeFormat.QR_CODE, 100, 100);
	
		FileOutputStream fos = new FileOutputStream("d:/qrcode.png");
		MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
		fos.close();
		
		String path = "d:/qrcode.png";
	    BufferedImage image = ImageIO.read(new File(path));
	    ImageIcon icon = new ImageIcon(image);
	    label_QRImage.setIcon(icon);
	    
	    showIcon(strContent);		
	}
	
	 private static void showIcon(String strContent) throws WriterException, IOException {
		 	QRCodeWriter qrw = new QRCodeWriter();
			BitMatrix bitMatrix = qrw.encode(strContent, BarcodeFormat.QR_CODE, 250, 250);
		
			FileOutputStream fos = new FileOutputStream("d:/qrcode2.png");
			MatrixToImageWriter.writeToStream(bitMatrix, "png", fos);
			fos.close();
			
			String path = "d:/qrcode2.png";
		    BufferedImage image = ImageIO.read(new File(path));
		
		    
	        ImageIcon icon = new ImageIcon(image);
	        JLabel label = new JLabel(icon, JLabel.CENTER);	        
	        JOptionPane.showMessageDialog(null, label, "icon", -1);
	 }

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public QRClient(BufferedImage bufImg) throws IOException {
		m_bufImg = bufImg;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnConfirm = new JButton("\uC2A4\uD2F0\uCEE4 \uBC1C\uBD80");
		btnConfirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String strNickname = txtNickname.getText();
				String strContact = txtContact.getText();
				String strComment = txtComment.getText();
				String strContent = strNickname + "\n" + strContact + "\n" + strComment + "\n\n-한양대 임베디드시스템 미아방지 프로젝트-";
				
				try {
					createQRImage(strContent);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtNickname = new JTextField();
		txtNickname.setColumns(10);		
		
		txtContact = new JTextField();
		txtContact.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uB2C9\uB124\uC784");
		
		JLabel lblTel = new JLabel("\uC5F0\uB77D\uCC98");
		
		JLabel lblNewLabel_1 = new JLabel("\uAE30\uD0C0");
		
		txtComment = new JTextArea();
		
		JLabel label = new JLabel("<\uC815\uBCF4 \uC785\uB825>");
		label.setFont(new Font("굴림", Font.BOLD, 20));
		
		label_QRImage = new JLabel("");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_1)
										.addComponent(lblTel))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtContact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(label_QRImage, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
										.addComponent(txtComment, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(168)
									.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(173)
							.addComponent(label)))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtContact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_QRImage, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtComment, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1))
					.addGap(62))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
