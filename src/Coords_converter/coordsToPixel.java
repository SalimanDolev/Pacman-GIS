package Coords_converter;

import Map.MapOptimizer;
import Pixel.PointPixel;
import geom.Point3D;

public class coordsToPixel {

	//convert Gps point to Coords on the map
	public PointPixel converterFromCoordsToPixel(Point3D p) {
		PointPixel pp = new PointPixel();
		MapOptimizer m;
		try {
			m = new MapOptimizer();
			double MinMaxdiff_Y = m.LeftUpCorner.x() - m.RighttDownCorner.x();;
			double MinMaxdiff_X = m.RighttUpCorner.y() - m.LeftUpCorner.y();
			
			double X_diff_Gps = p.y() - m.LeftUpCorner.y();
			double Y_diff_Gps = m.LeftUpCorner.x() - p.x() ;

			double diffCoord_X = (X_diff_Gps*m.myImage.getWidth()) / (MinMaxdiff_X);
			double diffCoord_Y = (Y_diff_Gps*m.myImage.getHeight()) / (MinMaxdiff_Y);
			
			pp.setPointpixel((int) diffCoord_X,(int) diffCoord_Y);
			return pp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	// convert Pixel on map to GPS point
	public Point3D convertFromPixelToGPS (PointPixel p) {
			MapOptimizer m;
		
			try {
				m = new MapOptimizer();
				double MinMaxdiff_Y = m.LeftUpCorner.x() - m.RighttDownCorner.x();;
				double MinMaxdiff_X = m.RighttUpCorner.y() - m.LeftUpCorner.y();

				double diffCoord_X = (MinMaxdiff_X* p.GetX())/m.myImage.getWidth();
				double diffCoord_Y = (MinMaxdiff_Y* p.GetY())/m.myImage.getHeight();
				
			
				double FullCoord_X=m.LeftUpCorner.x() - diffCoord_Y;
				double FullCoord_Y=m.LeftDownCorner.y() + diffCoord_X;
				
				Point3D ThisPoint=new Point3D(FullCoord_X,FullCoord_Y,0);
				return ThisPoint;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	public Point3D convertFromPixelToGPS (double x , double y) {
		MapOptimizer m;
		
		try {
			m = new MapOptimizer();
			double MinMaxdiff_Y = m.LeftUpCorner.x() - m.RighttDownCorner.x();;
			double MinMaxdiff_X = m.RighttUpCorner.y() - m.LeftUpCorner.y();
			

			double diffCoord_X = (MinMaxdiff_X* x)/m.myImage.getWidth();
			double diffCoord_Y = (MinMaxdiff_Y* y)/m.myImage.getHeight();
			
			double FullCoord_X=m.LeftUpCorner.x() - diffCoord_Y;
			double FullCoord_Y=m.LeftDownCorner.y() + diffCoord_X;

			Point3D ThisPoint=new Point3D(FullCoord_X,FullCoord_Y,0);
			return ThisPoint;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}
	
	public static void main(String[]args) {
		MapOptimizer m;
		try {
			geom.Point3D p = new Point3D(32.10382279750779,35.20782481018841,0);
			m = new MapOptimizer();
			double MinMaxdiff_Y = m.LeftUpCorner.x() - m.RighttDownCorner.x();;
			double MinMaxdiff_X = m.RighttUpCorner.y() - m.LeftUpCorner.y();
			
			double X_diff_Gps = p.y() - m.LeftUpCorner.y();
			double Y_diff_Gps = m.LeftUpCorner.x() - p.x() ;

			double diffCoord_X = (X_diff_Gps*m.myImage.getWidth()) / (MinMaxdiff_X);
			double diffCoord_Y = (Y_diff_Gps*m.myImage.getHeight()) / (MinMaxdiff_Y);
			System.out.println(diffCoord_X +"," +diffCoord_Y);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

}
