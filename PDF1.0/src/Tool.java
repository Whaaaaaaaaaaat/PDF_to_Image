import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Tool {
	
	PDDocument doc;
	int x,y;
	int sum;
	public Tool()
	{
		
	}
	
	
	
//��PDFת��ΪͼƬ
	public ArrayList<BufferedImage> toImage(String path)
	{
		ArrayList<BufferedImage> bis=null;
		try {
			doc=PDDocument.load(path);
			List<?> pages=doc.getDocumentCatalog().getAllPages();
			bis=new ArrayList<BufferedImage>();
			
			for(int i=0;i<pages.size();i++)
			{
				PDPage page=(PDPage)pages.get(i);
				bis.add(page.convertToImage());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sum=bis.size();
		return bis;
	}
	public Image getImage(BufferedImage bi,JPanel jp)
{
	Image im=null;
	int iw=bi.getWidth();
	int ih=bi.getHeight();
	double ipro=1.0*ih/iw;
	int jh=jp.getHeight();
	int jw=jp.getWidth();
	double jpro=1.0*jh/jw;
	
	if(ipro>jpro)
	{
		ih=(int)(jh*0.8);
		iw=(int)(ih/ipro);
	}
	else
	{
		iw=(int)(jw*0.8);
		ih=(int)(ipro*iw);
	}
	im=bi.getScaledInstance(iw, ih, Image.SCALE_SMOOTH);
	x=(jw-iw)/2;
	y=(jh-ih)/2;
	
	return im;
}
	
	public void save(ArrayList<BufferedImage> bis,String path)
	{
		
		for(int i=0;i<bis.size();i++)
		{
			try {
				
				ImageIO.write(bis.get(i), "jpg", new File(path+"\\"+(i+1)+".jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Tool().toImage("C:\\Users\\��\\Desktop\\�߶����������ڿγ̵�04�� ���������ʾ-1.pdf");
	}
	
}
