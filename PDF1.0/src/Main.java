import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	//���
	JFrame jf ;
	ArrayList<BufferedImage> bis=new ArrayList<BufferedImage>();
	int index=0;
	Image im;
	MyListener listener;
	JLabel jl;
	JPanel jp=new JPanel() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public void paint(Graphics g)
		{
			super.paint(g);
			if(bis.size()==0)
			{
				previous.setEnabled(false);
				next.setEnabled(false);
				
			}
			else
			{
				next.setEnabled(true);
				convert.setEnabled(true);
				im=listener.tool.getImage(bis.get(index), jp);
				g.drawImage(im,listener.tool.x,listener.tool.y,null);
				jl.setText(index+1+"/"+listener.tool.sum);
				if(index==0)
				{
					previous.setEnabled(false);
				}
				if(index>0&&index<bis.size())
				{
					previous.setEnabled(true);
					next.setEnabled(true);
				}
				if(index==bis.size()-1)
				{
					next.setEnabled(false);
				}
			}
			
		}
	};
	JButton open;
	JButton next;
	JButton previous;
//	JButton save;
	JButton convert;
	
	//���췽��
	public Main()
	{
		//JFrame����
		jf=new JFrame("С��PDF������");
		jf.setSize(600,500);
//		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage("pdfͼ��.jpg"));
		//JPanel����
		jp.setBackground(Color.white);
		jp.setSize(jf.getWidth(), jf.getHeight());
		jf.add(jp,"Center");
		//��ť����
		open=new JButton("��");
		previous=new JButton("��һҳ");
		next=new JButton("��һҳ");
		previous.setEnabled(false);
		next.setEnabled(false);
//		save=new JButton("����");
		convert=new JButton("ת��ΪͼƬ");
//		convert.setEnabled(false);
		listener=new MyListener();
		listener.setMain(this);
		open.addActionListener(listener);
		previous.addActionListener(listener);
		next.addActionListener(listener);
		convert.addActionListener(listener);
		//��ǩ
		jl=new JLabel();
		JPanel jp2=new JPanel();
		jp2.add(jl);
		jp2.add(open);
		jp2.add(previous);
		jp2.add(next);
		jp2.add(convert);
		jf.add(jp2, "North");
		((FlowLayout)(jp2.getLayout())).setHgap(36);
		//��ʾJFrame
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		Main m=new Main();

	}
}
