
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

class Frame extends JFrame implements KeyListener, Runnable {

	int f_width;
	int f_height;

	User user;
	ArrayList ms_list;
	ArrayList en_list;

	boolean KeyUp = false;
	boolean KeyDown = false;
	boolean KeyLeft = false;
	boolean KeyRight = false;
	boolean KeySpace = false;
	boolean KeyH = false;
	boolean KeyM = false;
	boolean KeyV = false;
	boolean KeyShift = false;

	Thread th;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image me_img;
	Image Missile_img;
	Image Enemy_img;
	Image Enemy_missile_img;
	Image Map_img;
	Image Map_img2;
	Image Enemy2_img;
	Image Enemy_missile2_img;
	Image buffImage;
	Graphics buffg;

	Frame() {

		user = new User();
		f_width = 936;
		f_height = 600;
		me_img = tk.getImage("user.png");
		Missile_img = tk.getImage("missile.png");

		Enemy_img = tk.getImage("enemy.png");
		Enemy_missile_img = tk.getImage("enemy_missile.png");
		Enemy2_img = tk.getImage("enemy2.png");
		Enemy_missile2_img = tk.getImage("enemy_missile2.png");
		Map_img = tk.getImage("background.png");
		Map_img2 = tk.getImage("background2.jpg");
		sound();

	}

	public void SoundPlay(String file, boolean value) {
		Clip clip;
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if (value) {
				clip.loop(-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList msList() {
		return ms_list;
	}

	public void setMsList(ArrayList list) {
		ms_list = list;
	}

	public void setEnList(ArrayList list) {
		en_list = list;
	}

	public ArrayList enList() {
		return en_list;
	}

	public void setUser(User newUser) {
		user = newUser;
	}

	private void start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		th = new Thread(this);

		th.start();
	}

	public void run() {
	}

	public void paint(Graphics g) {
		buffImage = createImage(f_width, f_height);
		buffg = buffImage.getGraphics();

		paint_map(g);
		paint_user(g);
		paint_ms(g);
		paint_en(g);
		Draw_state();
		Draw_item();

		g.drawImage(buffImage, 0, 0, this);
	}

	private void sound() {

		SoundPlay("BGM.wav", true);

	}

	private void paint_map(Graphics g) {
		if (user.score < 100)
			buffg.drawImage(Map_img, 0, 0, this);
		if (user.score >= 100) {
			buffg.drawImage(Map_img2, 0, 0, this);
		}
	}

	private void paint_user(Graphics g) {
		buffg.drawImage(me_img, user.getX(), user.getY(), this);
	}

	private void paint_ms(Graphics g) {
		for (int i = 0; i < ms_list.size(); ++i) {
			Missile ms = (Missile) (ms_list.get(i));
			if (ms._who == 0) {
				buffg.drawImage(Missile_img, ms._x, ms._y, this);
			} else if (ms._who == 1) {
				buffg.drawImage(Enemy_missile_img, ms._x, ms._y, this);
			}
		}
	}

	private void paint_en(Graphics g) {
		for (int i = 0; i < en_list.size(); ++i) {
			Enemy en = (Enemy) (en_list.get(i));
			buffg.drawImage(Enemy_img, en.x, en.y, this);
			if (en.x < -200 || en.y < -200 || en.y > f_height)
				en_list.remove(i);
		}
	}

	public void gameOver() {
		SoundPlay("No_Score_1.wav", false);
		buffg.drawString("GAME OVER", 90, 70);
		JOptionPane.showMessageDialog(null, "GAME OVER!!\n  SCORE : " + user.Score());

		System.exit(0);
	}

	public void display() {

		start();
		setTitle("다람쥐 도토리 날리기");
		setSize(f_width, f_height);
		Dimension screen = tk.getScreenSize();

		int f_xpos = (int) (screen.getWidth() / 2 - f_width / 2);
		int f_ypos = (int) (screen.getHeight() / 2 - f_height / 2);

		setLocation(f_xpos, f_ypos);
		setResizable(false);
		setVisible(true);
	}

	public void Draw_state() {
		buffg.setColor(new Color(0, 0, 80));
		buffg.drawString("HEART : " + user.Heart(), 60, 70);
		buffg.drawString("Gold : " + user.Gold(), 60, 90);
		buffg.drawString("Score : " + user.Score(), 60, 110);
	}

	public void Draw_item() {
		buffg.drawString("Increase 2 hearts! : Press 'H' key, 200 gold", 650, 60);
		buffg.drawString("Enemy All Clear! : Press 'M' key, 300 gold", 650, 80);
		buffg.drawString("Increase FireSpeed! : Press 'V' Key 300 gold ", 650, 100);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = true;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = true;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = true;
			break;
		case KeyEvent.VK_SPACE:
			KeySpace = true;
			break;
		case KeyEvent.VK_H:
			KeyH = true;
			break;
		case KeyEvent.VK_M:
			KeyM = true;
			break;
		case KeyEvent.VK_V:
			KeyV = true;
			break;
		case KeyEvent.VK_SHIFT:
			KeyShift = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			KeyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			KeyDown = false;
			break;
		case KeyEvent.VK_LEFT:
			KeyLeft = false;
			break;
		case KeyEvent.VK_RIGHT:
			KeyRight = false;
			break;
		case KeyEvent.VK_SPACE:
			KeySpace = false;
			break;
		case KeyEvent.VK_H:
			KeyH = false;
			break;
		case KeyEvent.VK_M:
			KeyM = false;
			break;
		case KeyEvent.VK_V:
			KeyV = false;
			break;
		case KeyEvent.VK_SHIFT:
			KeyShift = false;
			break;
		}
	}

	public void KeyProcess() {
		if (KeyUp == true) {
			int y = user.getY();
			if (y <= 10)
				user.setY(10);
			else
				user.setY(y - 5);
		}
		if (KeyDown == true) {
			int y = user.getY();
			if (y >= f_height - 50)
				user.setY(f_height - 50);
			else
				user.setY(y + 5);
		}
		if (KeyLeft == true) {
			int x = user.getX();
			if (x <= 0)
				user.setX(0);
			else
				user.setX(x - 5);
		}
		if (KeyRight == true) {
			int x = user.getX();
			if (x >= f_width)
				user.setX(f_width);

			else
				user.setX(x + 5);
		}
		if (KeyH == true) {
			if (user.Gold() >= 200) {
				IncreasingHeartItem h_item = new IncreasingHeartItem();
				h_item.purchase(user);
				SoundPlay("Power_Up2.wav", false);
			} else
				return;
		}
		if (KeyM == true) {
			if (user.Gold() >= 300) {
				CleanEnemyItem m_item = new CleanEnemyItem();
				m_item.purchase(user);
				en_list.removeAll(en_list);
				SoundPlay("Emerge5.wav", false);
			} else
				return;
		}
		if (KeyV == true) {
			if (user.Gold() >= 300) {
				IncreasingContinousFire v_item = new IncreasingContinousFire();
				v_item.purchase(user);
				SoundPlay("Power_Up1.wav", false);
			} else {
				return;
			}
		}
		if(KeyShift == true){
			me_img = tk.getImage("user2.png");
		}
		if(KeyShift == false){
			me_img = tk.getImage("user.png");
		}
	}
}