package Images;

import java.net.URL;

import javax.swing.ImageIcon;

public class Images {
	//在新路径运行需要调试图片路径。
	 public static URL bodyURL = Images.class.getResource("body.png");
	 public static ImageIcon bodyimage =new ImageIcon(bodyURL);
	 public static URL upURL = Images.class.getResource("up.png");
	 public static ImageIcon upimage =new ImageIcon(upURL);
	 public static URL downURL = Images.class.getResource("down.png");
	 public static ImageIcon downimage =new ImageIcon(downURL);
	 public static URL leftURL = Images.class.getResource("left.png");
	 public static ImageIcon leftimage =new ImageIcon(leftURL);
	 public static URL rightURL = Images.class.getResource("right.png");
	 public static ImageIcon rightimage =new ImageIcon(rightURL);
	 public static URL foodURL = Images.class.getResource("food.png");
	 public static ImageIcon foodimage =new ImageIcon(foodURL);
	 public static URL topURL = Images.class.getResource("OIP.png");
	 public static ImageIcon topimage =new ImageIcon(topURL);
}
