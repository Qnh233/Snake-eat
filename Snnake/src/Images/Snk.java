package Images;

import java.awt.Toolkit;

import javax.swing.*;

public class Snk {
	public static void main(String args[])
	{
		JFrame jf = new JFrame();
	
		jf.setTitle("贪吃蛇！");
		
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		jf.setBounds((width-800)/2, (height-750)/2, 800, 750);//x,y,width,height
		
		jf.setResizable(false);  //窗口关闭 程序退出
		
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//不可调整大小
		
		//加入画笔   画板操作
		GamePanel gp=new GamePanel();
		jf.add(gp);
		
		jf.setVisible(true);
	}
	

}
