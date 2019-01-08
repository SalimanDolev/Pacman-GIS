package robots;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.mysql.fabric.xmlrpc.base.Array;

import Coords.coords_converter;
import Coords_converter.coordsToPixel;
import Geom.Point3D;
import Pixel.PointPixel;
import Player.me;

public class fruits {
	public BufferedImage fruitIcon;
	BufferedImage a;
	private PointPixel pixelP;
	private geom.Point3D gpsP;
	private int Weight;
	
	private ArrayList<Point3D> d3path;
	private ArrayList<PointPixel> Pixelpath;
	
	
//class builder
//////////////////////////////////////////////////////////////////////////////	
	public fruits() {
		try {
		this.fruitIcon = ImageIO.read(new File("C:\\\\Pictures_For_Ex3\\\\FRUIT.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
		
		this.pixelP = new PointPixel();
		this.gpsP = new geom.Point3D(0,0,0);
		this.Weight = 1;
		this.d3path = new ArrayList<Point3D>();
		this.Pixelpath = new ArrayList<PointPixel>();
	}
	public fruits(int x, int y) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP);
	}
//////////////////////////////////////////////////////////////////////////
	
//Setters	
//////////////////////////////////////////////////////////////////////////////
	public void setFruitsPositionFromPixel(PointPixel p ) {
		this.pixelP = p;
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(p);
	}
	public void setFruitsPosition(double x , double y ) {
		this.pixelP.setPointpixel(x, y);
		coordsToPixel con = new coordsToPixel();
		this.gpsP = con.convertFromPixelToGPS(this.pixelP);
	}
	
	public void setFruitPositionFromGps(geom.Point3D p ) {
		this.gpsP = p;
		coordsToPixel con = new coordsToPixel();
		this.pixelP = con.converterFromCoordsToPixel(p);
	}
	public void setWeight(int weight) {
		Weight = weight;
	}	
/////////////////////////////////////////////////////////////////////////////	
	
//Getters
//////////////////////////////////////////////////////////////////////////////	
	public geom.Point3D get3Dpoint() {
		return this.gpsP;
	}
	public PointPixel getPixelPoint() {
		return this.pixelP;
	}
	public double GetX() {
		return this.pixelP.GetX();
	}
	
	public double GetY() {
		return this.pixelP.GetY();
	}

	public int getWeight() {
		return Weight;
	}

//Arraylist
//////////////////////////////////////////////////////////////////////////////
	public void add3Dpath(Point3D p) {
		this.d3path.add(p);
	}

	public Iterator<Point3D> pathIterator() {
		return this.d3path.iterator();
	}

	public Point3D getStep(int i ) {
		return this.d3path.get(i);
	}
	public void addPixelPath(PointPixel p ) {
		this.Pixelpath.add(p);
	}
	public Iterator<PointPixel> pixelpathIterator(){
		return this.Pixelpath.iterator();
	}
	public PointPixel getPixelStep(int i) {
		return this.Pixelpath.get(i);
	}
	public int getPathSize () {
		System.out.println(this.Pixelpath.size());
		System.out.println(this.d3path.size());
		return this.Pixelpath.size();
	}
///////////////////////////////////////////////////////////////////////////////	

	public boolean ifEaten(ArrayList<packmen> list,me player) {
		double [] area = new double [4];
		area[0] = this.gpsP.x()+0.0001;
		area[1] = this.gpsP.x()-0.0001;
		area[2] = this.gpsP.y()+0.0001;
		area[3] = this.gpsP.y()-0.0001;
		System.out.println(area[0] +","+area[1]+","+area[2]+","+area[3]+",");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get3Dpoint().x() < area[0] && list.get(i).get3Dpoint().x() > area[1]
					&& list.get(i).get3Dpoint().y() < area[2] && list.get(i).get3Dpoint().y() > area[3]) {
				return true;
			}
		}
		if (player.get3Dpoint().x() < area[0] && player.get3Dpoint().x() > area[1]
				&& player.get3Dpoint().y() < area[2] && player.get3Dpoint().y() > area[3]) {
			player.Addscore(this.Weight);
			return true;
		}
		return false;
	}
	

}
