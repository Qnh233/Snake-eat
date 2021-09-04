package Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int length;
	int [] snakeX =new int[200];
	int [] snakeY=new int[200];
	
	String direction;//方向
	//定时器
	javax.swing.Timer timer;
	boolean ifstart;//暂停标记
	int foodx,foody;//食物坐标
	int score=0;//得分
	boolean ifdie =false;//死亡判断 
	boolean ifeat=false;//吃食物判断
	int  fe=180;//速度。
	public void init()//初始化
	{
		direction="R";
		length=3;
		score=0;
		ifdie =false;		
		fe=180;	
		ifeat=false;	
		snakeX[0]=175;
		snakeY[0]=275;
		
		snakeX[1]=150;
		snakeY[1]=275;
		
		snakeX[2]=125;
		snakeY[2]=275;
		foodx=300;
		foody=200;
		
	}
	public GamePanel() {
		// TODO Auto-generated constructor stub
		init();
		
		//焦点定位 面板上
		this.setFocusable(true);
		//加入监听
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				int KC=e.getKeyCode();
				System.out.println(KC);
				if(KC==KeyEvent.VK_SPACE)//空格
				{
					if(ifdie)
					{
						//完全重置游戏
						
						init();
						ifdie=false;
						
					}
					else
					{
						ifstart=!ifstart;
					repaint();//重绘
					}
					
				}
				switch (KC) {
				case KeyEvent.VK_UP:if(!direction.equals("D"))
				direction="U";
					break;
				case KeyEvent.VK_DOWN:if(!direction.equals("U"))
					direction="D";
					break;
				case KeyEvent.VK_LEFT:if(!direction.equals("R"))
					direction="L";break;
				case KeyEvent.VK_RIGHT:if(!direction.equals("R"))
					direction="R";break;
				
				}
				
								
			}
		});
		
	timer=new javax.swing.Timer(fe, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(ifstart&&ifdie==false)
			{
				int i=length-1;
			for(;i>0;i--)
			{
				snakeX[i]=snakeX[i-1];
				snakeY[i]=snakeY[i-1];
			}
			switch (direction) {
			case "R": snakeX[0]+=25;break;
			case "L": snakeX[0]-=25;break;
			case "U":snakeY[0]-=25;break;
			case "D":snakeY[0]+=25;break;

			}		
			//防止越界
			if(snakeX[0]>=800||snakeX[0]<0||snakeY[0]<=190||snakeY[0]>800)
			{
				ifdie=true;
			}
			//死亡判定  遍历蛇身
			for(int s=1;s<length;s++)
			{
				if(snakeX[s]==snakeX[0]&&snakeY[s]==snakeY[0])
				{
					ifdie=true;
				}
			}
			//监测与food 的碰撞
			if(snakeX[0]==foodx&&snakeY[0]==foody)
			{
				ifeat=true;
				length++;
				fe-=5;
				//food需要随机生产 且在范围内。
				//Math.random() - 0.0,1.0
				//Math.random()*30   0.0,30.0
				//(int)(Math.random()*30)  0,29
				//(int)(Math.random()*30)+1    1,30
				foodx=((int)(Math.random()*30)+1)*25;//25-750
				foody=(new Random().nextInt(5)+2)*100;//200-700
				score+=10;
			}
			if(ifeat)
			{
				if(fe>=25)
				{
					timer.setDelay(fe);
				ifeat=false;
				}
				ifeat=false;
			}
			if(ifdie)
	timer.setDelay(180);	
			
			repaint();//重绘
			}
			
		}
	});
			timer.start();
	}
	
	
	
	@Override  //图形版的main方法
	// 自动调用。
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//
		this.setBackground(new Color(1,50,6));//背景颜色
		
		//top
		Images.topimage.paintIcon(this, g, 0, 0); //窗口/面板，画笔，x,y;
		
		g.setColor(new Color(25,200,204));
		g.fillRect(0,180,800,10);
		//画蛇
		//头
		switch (direction) {
		case "R":Images.rightimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "L":Images.leftimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "U":	Images.upimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "D":	Images.downimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
	}
		//画蛇身
		for(int i=1;i<length;i++)
		{
			Images.bodyimage.paintIcon(this, g, snakeX[i], snakeY[i]);
		}
		
		if(ifstart==false)	
		{
			//暂停动画 
			g.setColor(new Color(110,98,225));
			g.setFont(new Font("华文行楷", Font.BOLD, 40));
			g.drawString("空格开始游戏", 350, 150);
			
		}
		g.setColor(new Color(225,225,225));
		g.setFont(new Font("宋体", Font.BOLD, 40));
		g.drawString("得分："+score, 400, 100);
		//食物
		Images.foodimage.paintIcon(this, g, foodx, foody);
		  
		     
		
		if(ifdie)
		{
			g.setColor(new Color(225,225,225));
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.drawString("你输了，按下空格重新开始游戏", 200, 330);
		}
	}

}