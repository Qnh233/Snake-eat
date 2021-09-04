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
	
	String direction;//����
	//��ʱ��
	javax.swing.Timer timer;
	boolean ifstart;//��ͣ���
	int foodx,foody;//ʳ������
	int score=0;//�÷�
	boolean ifdie =false;//�����ж� 
	boolean ifeat=false;//��ʳ���ж�
	int  fe=180;//�ٶȡ�
	public void init()//��ʼ��
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
		
		//���㶨λ �����
		this.setFocusable(true);
		//�������
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				int KC=e.getKeyCode();
				System.out.println(KC);
				if(KC==KeyEvent.VK_SPACE)//�ո�
				{
					if(ifdie)
					{
						//��ȫ������Ϸ
						
						init();
						ifdie=false;
						
					}
					else
					{
						ifstart=!ifstart;
					repaint();//�ػ�
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
			//��ֹԽ��
			if(snakeX[0]>=800||snakeX[0]<0||snakeY[0]<=190||snakeY[0]>800)
			{
				ifdie=true;
			}
			//�����ж�  ��������
			for(int s=1;s<length;s++)
			{
				if(snakeX[s]==snakeX[0]&&snakeY[s]==snakeY[0])
				{
					ifdie=true;
				}
			}
			//�����food ����ײ
			if(snakeX[0]==foodx&&snakeY[0]==foody)
			{
				ifeat=true;
				length++;
				fe-=5;
				//food��Ҫ������� ���ڷ�Χ�ڡ�
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
			
			repaint();//�ػ�
			}
			
		}
	});
			timer.start();
	}
	
	
	
	@Override  //ͼ�ΰ��main����
	// �Զ����á�
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//
		this.setBackground(new Color(1,50,6));//������ɫ
		
		//top
		Images.topimage.paintIcon(this, g, 0, 0); //����/��壬���ʣ�x,y;
		
		g.setColor(new Color(25,200,204));
		g.fillRect(0,180,800,10);
		//����
		//ͷ
		switch (direction) {
		case "R":Images.rightimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "L":Images.leftimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "U":	Images.upimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
		case "D":	Images.downimage.paintIcon(this, g, snakeX[0], snakeY[0]);break;
	}
		//������
		for(int i=1;i<length;i++)
		{
			Images.bodyimage.paintIcon(this, g, snakeX[i], snakeY[i]);
		}
		
		if(ifstart==false)	
		{
			//��ͣ���� 
			g.setColor(new Color(110,98,225));
			g.setFont(new Font("�����п�", Font.BOLD, 40));
			g.drawString("�ո�ʼ��Ϸ", 350, 150);
			
		}
		g.setColor(new Color(225,225,225));
		g.setFont(new Font("����", Font.BOLD, 40));
		g.drawString("�÷֣�"+score, 400, 100);
		//ʳ��
		Images.foodimage.paintIcon(this, g, foodx, foody);
		  
		     
		
		if(ifdie)
		{
			g.setColor(new Color(225,225,225));
			g.setFont(new Font("����", Font.BOLD, 25));
			g.drawString("�����ˣ����¿ո����¿�ʼ��Ϸ", 200, 330);
		}
	}

}