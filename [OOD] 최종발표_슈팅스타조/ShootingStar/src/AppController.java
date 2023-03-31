import java.awt.*;
import java.util.ArrayList;

public class AppController {

	Frame frame;
	Missile ms;
	Enemy en;
	User user = new User();
	ArrayList<Missile> Missile_list;
	ArrayList<Enemy> Enemy_list;

	int count;

	int user_ms_speed = 6;
	int enemy_ms_speed = 6;

	AppController() {
		frame = new Frame();
		Missile_list = new ArrayList<Missile>();
		Enemy_list = new ArrayList<Enemy>();
		frame.display();

	}

	private void MissileProcess() {
		frame.setUser(user);
		if (frame.KeySpace && (count % user.firespeed) == 0) {
			ms = new Missile(user.getX() + 50, user.getY() + 30, 0, user_ms_speed, 0);
			Missile_list.add(ms);
			frame.setUser(user);
			frame.SoundPlay("Laser_Shoot8.wav", false);
		}
		for (int i = 0; i < Missile_list.size(); ++i) {
			ms = (Missile) Missile_list.get(i);
			ms.move();
			if (ms._x > frame.f_width - 20 || ms._x < 0 || ms._y < 0 || ms._y > frame.f_height) {
				// 해당미사일이화면밖으로나갔는가여부를확인

				Missile_list.remove(i);
				// 화면끝까지도달한미사일삭제
			}
			if (Crash(user.getX(), user.getY(), ms._x, ms._y, frame.me_img, frame.Enemy_missile_img) && ms._who == 1) {
				Missile_list.remove(i);
				frame.setMsList(Missile_list);
				user.setHeart(user.Heart() - 1);
				frame.setUser(user);
				frame.SoundPlay("Shut_Down4.wav", false);
			}
			for (int j = 0; j < Enemy_list.size(); ++j) {
				en = (Enemy) Enemy_list.get(j);
				if (Crash(ms._x, ms._y, en.x, en.y, frame.Missile_img, frame.Enemy_img) && ms._who == 0) {
					user.setGold(user.Gold() + 10); 
					user.setScore(user.Score() + 10);
					frame.setUser(user);
					Missile_list.remove(i);
					Enemy_list.remove(j);
					frame.setMsList(Missile_list);
					frame.setEnList(Enemy_list);
					frame.SoundPlay("Shut_Down7.wav", false);
				}
			}
		}
		frame.setMsList(Missile_list);
	}

	private void EnemyProcess() {
        for (int i = 0; i < Enemy_list.size(); ++i) {
            en = (Enemy_list.get(i));
            en.move();
            if (en.x < -200) {
                Enemy_list.remove(i);
            }
            if (count % 100 == 0) {
                if (user.Score() < 100) {
                    ms = new Missile(en.x, en.y + 25, 180, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                }

                if (user.Score() < 200 && user.Score() >= 100) {
                    frame.Enemy_img = frame.tk.getImage("enemy2.png");
                    frame.Enemy_missile_img = frame.tk.getImage("enemy_missile2.png");
                    ms = new Missile(en.x, en.y + 25, 185, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                    ms = new Missile(en.x, en.y + 25, 175, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                }
                if (user.Score() >= 200) {
                    ms = new Missile(en.x, en.y + 25, 185, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                    ms = new Missile(en.x, en.y + 25, 175, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                    ms = new Missile(en.x, en.y + 25, 165, enemy_ms_speed, 1);
                    Missile_list.add(ms);
                }

            }
            if (Crash(user.getX(), user.getY(), en.x, en.y, frame.me_img, frame.Enemy_img)) {
                Enemy_list.remove(i);
                frame.setEnList(Enemy_list);
                user.setHeart(user.Heart() - 1);
                frame.setUser(user);
                frame.SoundPlay("Shut_Down4.wav", false);
            }
        }
        if (count % 90 == 0) {
            en = new Enemy(frame.f_width + 100, (int) (frame.getHeight() * Math.random()), 3);
            Enemy_list.add(en);
            if (user.Score() >= 150) {
                en = new Enemy(frame.f_width + 100, (int) (frame.getHeight() * Math.random()), 3);
                Enemy_list.add(en);
            }
        }
        frame.setEnList(Enemy_list);
    }

	public void play() {
		try {
			while (true) {

				frame.KeyProcess();
				MissileProcess();
				EnemyProcess();
				frame.repaint();
				update();
				Thread.sleep(20);
				count++;
				if (user.Heart() <= 0) {
					frame.gameOver();
				}
			}
		} catch (Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	private void update() {
		Missile_list = frame.msList();
		Enemy_list = frame.enList();
	}

	public boolean Crash(int x, int y, int a, int b, Image image, Image image1) {

		if (Math.abs(x - a) <= image.getWidth(null) / 2 + image1.getWidth(null) / 2
				&& Math.abs(y - b) <= image.getHeight(null) / 2 + image1.getHeight(null) / 2) {
			return true;
		}

		return false;
	}
}