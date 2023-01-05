package me.minutz.trv.eng;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import me.minutz.trv.Buton;
import me.minutz.trv.Dreptunghi;
import me.minutz.trv.MainMinutz;
import me.minutz.trv.eng.etc.BufferedImageLoader;
import me.minutz.trv.eng.etc.Camera;
import me.minutz.trv.eng.etc.KeyInput;
import me.minutz.trv.eng.etc.MouseInput;
import me.minutz.trv.eng.etc.MouseWListener;
import me.minutz.trv.eng.handler.FireworksPanel;
import me.minutz.trv.eng.handler.Handler;
import me.minutz.trv.eng.handler.TextHandler;
import me.minutz.trv.fereastra.AFereastra;
import me.minutz.trv.fereastra.Fereastra;
import me.minutz.trv.fereastra.JFereastra;

public class Game extends Canvas implements Runnable{

	private boolean mere = false;
	private Thread thread;
	static Handler hndl;
	static TextHandler thndl;
	static Camera cam;
	static FireworksPanel fp;
	public static boolean start = false;
	public static int LATIME,INALTIME,xr,xs,xo;
	public static Color border=new Color(102,106,134),bgc=new Color(52,54,71),butonCol=new Color(154,140,152);
	public static float xx,yy;
	public static BufferedImage bgimg;
	public static float ag = 10f;
	public static int tick,fps;
	public static Fereastra fer = null;
	
	public void init(){
		LATIME=getWidth();
		INALTIME=getHeight();
		hndl = new Handler();
		thndl = new TextHandler();
		fp = new FireworksPanel();
		cam=new Camera(0,0);
		BufferedImageLoader l = new BufferedImageLoader();
		addKeyListener(new KeyInput(hndl));
		addMouseListener(new MouseInput(hndl));
		addMouseWheelListener(new MouseWListener());
		requestFocusInWindow();
		
//		  Toolkit toolkit = Toolkit.getDefaultToolkit();
//		    Point hotSpot = new Point(0,0);
//		    BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
//		    Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "NUSEVEDE");        
//		    setCursor(invisibleCursor);
//		    
//	    BufferedImage image = l.loadImage("masa.jpg");
	}
	
	

	public synchronized void start(){
		if(mere){
			return;
		}
		mere = true;
		thread = new Thread(this);
		thread.start();
	}
	

	public void run() {
		init();
		long utimp = System.nanoTime();
		final double amTick = 60.0;
		double ns = 1000000000 / amTick;
		double delta = 0;
		int update = 0;
		int frame = 0;
		long timer = System.currentTimeMillis();
		MainMinutz.load=true;
		Dreptunghi resurse = new Dreptunghi(110+100,30,100,500,"Resurse",new Color(149,189,209));
		Dreptunghi scut = new Dreptunghi(LATIME/2-110/2,30,100,500,"Defensiva",new Color(231,201,164));
		Dreptunghi oameni = new Dreptunghi(LATIME-210-100,30,100,500,"Oameni",new Color(198,153,158));
		
		xr = resurse.getX();
		xs= scut.getX();
		xo = oameni.getX();
		
		hndl.addObj(resurse);
		hndl.addObj(scut);
		hndl.addObj(oameni);
		
		hndl.addObj(new Buton(xs/2+150,600,"Ataca"){

			@Override
			public void click() {
				fer=new AFereastra();
			}
			
		});
		hndl.addObj(new Buton(LATIME-(xs/2+150), 600, "Joc amical"){

			@Override
			public void click() {
				fer=new JFereastra();
			}
			
		});
		while(mere){
			long acm = System.nanoTime();
			delta += (acm-utimp) / ns;
			utimp=acm;
			if(delta >=1){
				tick();
				update++;
				delta--;
			}
			render();
			frame++;
			
			if(System.currentTimeMillis()-timer > 1000){
				timer += 1000;
				tick=update;
				fps=frame;
				update=0;
				frame=0;
			}
		}

	}

	public void tick(){
		if(mere){
			if(fer!=null){
			fer.tickk();
			}

			hndl.tick();
			thndl.tick();

			if(KeyInput.sfps){
				thndl.addText("FPS:"+fps, 0, 10, 1, Color.YELLOW, Font.BOLD, 10);
			}
		}
		if(!MainMinutz.start){
				if(MainMinutz.load){
					if(MainMinutz.connected){
					MainMinutz.start=true;
					}
				}
			}
	}


	public void render(){
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(bgc);
		g.fillRect(0, 0, getWidth(), getHeight());

		
		g.setColor(Color.WHITE);

		hndl.render(g);
		fp.render(g);
		thndl.render(g);	
		if(fer!=null){
		fer.renderr(g);
		}

		g.dispose();
		bs.show();
	}
	
	public static Handler getHndl(){
		return hndl;
	}
	
	public static TextHandler getTHndl(){
		return thndl;
	}

	
	public static FireworksPanel getFp(){
		return fp;
	}
	
	public static int getLatime(){
		return LATIME;
	}
	
	public static int getInaltime(){
		return INALTIME;
	}
	
	public static Camera getCam(){
		return cam;
	}
	
	
}
