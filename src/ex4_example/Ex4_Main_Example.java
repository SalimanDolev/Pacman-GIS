package ex4_example;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.sun.jdi.event.Event;

import Coords_converter.coordsToPixel;
import Coords_converter.myCoords;
import Geom.Point3D;
import Pixel.PointPixel;
import Player.me;
import Robot.Packman;
import Robot.Play;
import robots.Box;
import robots.fruits;
import robots.ghosts;
import robots.packmen;
import geom.*;
/**
 * This is the basic example of how to work with the Ex4 "server" like system:
 * 1. Create a "play" with one of the 9 attached files 
 * 2. Set your ID's - of all the group members (numbers only);
 * 3. Get the GPS coordinates of the "arena" - as in Ex3.
 * 4. Get the game-board data
 * 5. Set the "player" init location - should be a valid location
 * 6. Start the Server
 * 7. while (the game is running):
 * 7.1 "Play" as long as there are "fruits" and time
 * 7.2 get the current score of the game
 * 7.3 get the game-board current state
 * 7.4 goto 7
 * 8. done - report the results to the DB.
 * @author ben-moshe
 *
 */
public class Ex4_Main_Example extends JFrame implements MouseInputListener,ActionListener {

	private BufferedImage myImage;
	public ArrayList<packmen> listPackmans = new ArrayList<packmen>();
	public ArrayList<fruits> listFruits = new ArrayList<fruits>();
	public ArrayList<ghosts> listGhosts = new ArrayList<ghosts>();
	public ArrayList<Box> listBoxes = new ArrayList<Box>();
	public static boolean playerset = false;
	private me player;
	private geom.Point3D temp = new geom.Point3D(0, 0, 0); 
	public boolean flag = false;


	public Ex4_Main_Example(){
		initGUI();
		this.addMouseListener(this);
		player = new me();
	}
	public void initGUI() {
		try {
			this.myImage = ImageIO.read(new File("C:\\Users\\salim\\Desktop\\μιξεγιν\\Projects\\GIS_Ex02\\Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int x = 2;
	int y = 2;
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Paint function that show the icon on the screen
	double direction =0;
	public void paint(Graphics g)
	{

		g.drawImage(myImage, 0, 0, this);
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
		}
		//function that draw boxes
		if(!listBoxes.isEmpty()) {

			for (int i = 0; i < listBoxes.size(); i++) {
				g.fillRect(this.listBoxes.get(i).getPixelX(), this.listBoxes.get(i).getPixelY(),
						this.listBoxes.get(i).getWidth(),this.listBoxes.get(i).getHight() );

			}
		}

		if(!this.listPackmans.isEmpty())
		{
			for(int i=0 ; i<listPackmans.size() ;i++)
			{
				int x_pacman =(int) listPackmans.get(i).getPixelPoint().GetX();
				int y_pacman = (int)listPackmans.get(i).getPixelPoint().GetY();
				g.drawImage(this.listPackmans.get(i).PackmenIcon,x_pacman, y_pacman, 25, 25,this);
			}
			this.listPackmans.clear();
		}	
		if(!this.listFruits.isEmpty())
		{
			for(int i=0 ; i<listFruits.size() ;i++)
			{
				int x_fruit =(int) listFruits.get(i).getPixelPoint().GetX();
				int y_fruit = (int)listFruits.get(i).getPixelPoint().GetY();
				g.drawImage(this.listFruits.get(i).fruitIcon,x_fruit, y_fruit, 25, 25,this);
			}
		}	
		if(!this.listGhosts.isEmpty())
		{
			for(int i=0 ; i<listGhosts.size() ;i++)
			{
				int x_ghost =(int) listGhosts.get(i).getPixelPoint().GetX();
				int y_ghost = (int)listGhosts.get(i).getPixelPoint().GetY();
				g.drawImage(this.listGhosts.get(i).ghostIcon,x_ghost, y_ghost, 25, 25,this);
			}
			this.listGhosts.clear();
		}	
		// this function paint the player on the map.
		if(playerset) {
			if(!player.IfInsideBox(listBoxes)){
			player.movePlayer(direction);
			}else { direction = direction+180;player.movePlayer(direction);};
			int x_pacman =(int) this.player.getPixelPoint().GetX();
			int y_pacman = (int)this.player.getPixelPoint().GetY();
			g.drawImage(player.meIcon,x_pacman, y_pacman, 25, 25,this);

		}
		if(flag) {
			Font font = g.getFont().deriveFont( 20.0f );
		    g.setFont( font );
			g.drawString("Score:" + player.getScore() ,500, 500);//fix the location
			
		}


	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {

		Ex4_Main_Example frame = new Ex4_Main_Example();
		frame.setVisible(true);
		frame.setSize(frame.myImage.getWidth(), frame.myImage.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setResizable(false);
		int direction =0;


		// 1) Create a "play" from a file (attached to Ex4)
		String file_name = "data/Ex4_OOP_example8.csv";
		Play play1 = new Play(file_name);

		// 2) Set your ID's - of all the group members
		play1.setIDs(204365951);

		// 3)Get the GPS coordinates of the "arena"
		String map_data = play1.getBoundingBox();
		System.out.println("Bounding Box info: "+map_data);

		// 4) get the game-board data
		ArrayList<String> board_data = play1.getBoard();
		for(int i=0;i<board_data.size();i++) {
			System.out.println(board_data.get(i));
		}
		System.out.println();
		System.out.println("Init Player Location should be set using the bounding box info");

		// 5) Set the "player" init location - should be a valid location
		play1.setInitLocation(32.10334184830721 , 35.20647474758702);

		// 6) Start the "server"
		play1.start(); // default max time is 100 seconds (1000*100 ms).
		frame.flag = true;
		// 7) "Play" as long as there are "fruits" and time
		//for(int i=0;i<10;i++) {
		int i=0;
		while(play1.isRuning()) {
			// 7.1) this is the main command to the player (on the server side)
			//bug in rotate fuction/** *//*/*/*/*/*/*/*/*/*/*/
			play1.rotate(i*36); 
			System.out.println("***** "+i+"******");

			// 7.2) get the current score of the game
			String info = play1.getStatistics();
			System.out.println(info);
			// 7.3) get the game-board current state
			board_data = play1.getBoard();
			for(int a=0;a<board_data.size();a++) {
				System.out.println(board_data.get(a));
				String splited [] = board_data.get(a).split(",");
				if(splited[0].equals("P")) {
					packmen m1 = new packmen();
					geom.Point3D p = new geom.Point3D(Double.parseDouble(splited[2]), Double.parseDouble(splited[3]) , 0);
					m1.setPackmenPosition(p);
					frame.listPackmans.add(m1);
				}
				if (i<1) {
					if(splited[0].equals("F")) {
						fruits f = new fruits();
						geom.Point3D p = new geom.Point3D(Double.parseDouble(splited[2]), Double.parseDouble(splited[3]) , 0);
						f.setFruitPositionFromGps(p);
						System.out.println(splited[5]);
						f.setWeight((int)Double.parseDouble(splited[5]));
						frame.listFruits.add(f);
					}
				}

				if(splited[0].equals("G")) {
					ghosts m = new ghosts();
					geom.Point3D p = new geom.Point3D(Double.parseDouble(splited[2]), Double.parseDouble(splited[3]) , 0);
					m.setghostPositionFromGps(p);
					frame.listGhosts.add(m);
				}
				if(splited[0].equals("B")) {
					Box b = new Box(Double.parseDouble(splited[2]), Double.parseDouble(splited[3]),
							Double.parseDouble(splited[5]), Double.parseDouble(splited[6]));
					frame.listBoxes.add(b);
				}
				// checks if the fruit got eaten and remove it.
				for (int j = 0; j < frame.listFruits.size(); j++) {	
					if (frame.listFruits.get(j).ifEaten(frame.listPackmans,frame.player)) {
						System.out.println(play1.getBoard().size());
						play1.getBoard().remove(frame.listPackmans.size()+frame.listGhosts.size()+ 1 + j);
						frame.listFruits.remove(j);//calculate the raw of the fruit in the file
					}
				}
			}
			frame.repaint();
			
			
			i++;
			System.out.println();
		}

		// 8) stop the server - not needed in the real implementation.
		//play1.stop();
		System.out.println("**** Done Game (user stop) ****");

		// 9) print the data & save to the course DB
		String info = play1.getStatistics();
		System.out.println(info);
	}
	int i =0;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////mouse functions///////////////////////////////////////////////////////////////////////////////
	@Override
	public void mouseClicked(MouseEvent e) {

		coordsToPixel m = new coordsToPixel();
		double x = e.getX();
		double y = e.getY();
		geom.Point3D temp = new geom.Point3D(0, 0, 0);

		temp = m.convertFromPixelToGPS(e.getX(),e.getY());
		Play play1 = new Play();
		this.temp.setPoint(temp);
		if (i==0) {
			play1.setInitLocation(x, y);
			player.setPackmenPosition(x, y);
			this.playerset = true;
		}i++;
		myCoords l = new myCoords();
		double azimuth [] = l.azimuth_elevation_dist(player.get3Dpoint(),temp);
		if(player.IfInsideBox(listBoxes)){
			System.out.println(true);
		}
		//else player.movePlayer(azimuth[0]);
		System.out.println(player.get3Dpoint()+" my location");
		this.direction = azimuth[0];
		this.repaint();
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
