package Coords_converter;
import Geom.Point3D;

public class myCoords {


	private double earthrhRadius = 6371000;

	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double lonNorm = Math.cos((gps.x()*Math.PI)/180);

		double lat_change = Math.asin(local_vector_in_meter.x()/earthrhRadius)*(180/Math.PI);
		lat_change = lat_change + gps.x();

		double lon_change = Math.asin(local_vector_in_meter.y()/earthrhRadius*lonNorm)*(180/Math.PI);
		lon_change = lon_change + gps.y();
		if(lat_change>180) {
			lat_change=((lat_change+180)%360)-180;
		}
		if(lat_change<-180) {
			lat_change=(lon_change+180)+180;
		}
		Point3D p = new Point3D(lat_change, lon_change, gps.z()+local_vector_in_meter.z());
		return p;	
	}

	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(geom.Point3D gps0, geom.Point3D gps1) {
		double lat_diff = Math.toRadians(gps0.x() - gps1.x());
		double lon_diff = Math.toRadians(gps0.y() - gps1.y());
		double distance = Math.sin(lat_diff/2)*Math.sin(lat_diff/2) + 
				Math.cos(Math.toRadians(gps0.x()))*Math.cos(Math.toRadians(gps1.x()))
				*Math.sin(lon_diff/2) * Math.sin(lon_diff/2);
		distance = 2 * Math.atan2(Math.sqrt(distance), Math.sqrt(1-distance));
		return distance*earthrhRadius;
	}
	/** computes the 3D vector (in meters) between two gps like points */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double lonNorm = Math.cos((gps0.x()*Math.PI)/180);

		double lat_diff = gps0.x() - gps1.x();
		double lon_diff = gps0.y() - gps1.y();
		double lat_radian = (Math.PI*lat_diff) / 180;
		double lon_radian = (Math.PI*lon_diff) / 180;
		lat_radian = (Math.sin(lat_radian))*lonNorm*earthrhRadius;
		lon_radian = (Math.sin(lon_radian))*lonNorm*earthrhRadius;
		Point3D p = new Point3D(lat_radian,lon_radian,gps1.z() - gps0.z());
		return p;
	}

	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	public double[] azimuth_elevation_dist(geom.Point3D gps0, geom.Point3D gps1) {
		double dX = gps0.x() - gps1.x();
		double dY = gps0.y() - gps1.y();
		double dZ = gps0.z() - gps1.z();
		double [] azimuth = new double [3]; 
		azimuth[2] = distance3d(gps0, gps1); // computes the distance beetwean 2 points.
		azimuth[1] = Math.toDegrees(Math.asin((gps1.z()-gps0.z())/azimuth[2])); // computes the elevation beetwean the 2 points.
		azimuth[0]=  getAzimuth(gps0, gps1); // computes the azimuth beetwean 2 points.
		return azimuth;
	}
	// this fuction computes the azimuth between two points.
	private double getAzimuth(geom.Point3D gps1, geom.Point3D gps2) {
		double azimuth;
		double dY = gps2.y() - gps1.y();
		azimuth = Math.atan2(Math.sin(dY) * Math.cos(gps2.x()) , 
				Math.cos(gps1.x()) * Math.sin(gps2.x()) - (Math.sin(gps1.x()) * Math.cos(gps2.x()) * Math.cos(dY)));
		return Math.toDegrees(azimuth) < 0 ? Math.toDegrees(azimuth)+360.00 : Math.toDegrees(azimuth);
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		if(p.x() > -90 && p.x() < 90 && p.y() > -90 && p.y() < 90 && p.z()<6000 && p.z() > -400)
			return true;
		else throw new RuntimeException("** Error: Point3D coordinates wrong **");

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * @param azimuth
	 * @param distance
	 */
	public geom.Point3D AddAzimuthAndVector(geom.Point3D p,double azimuth,double distance) {
		double azimuth2 = Math.toRadians(azimuth);
		double lat1 = Math.toRadians(p.x());
		double lon1 = Math.toRadians(p.y());
		double dR = distance/this.earthrhRadius;

		double a = Math.sin(dR) * Math.cos(lat1);
		double lat2 = Math.asin(Math.sin(lat1)*Math.cos(dR)+a*Math.cos(azimuth2));
		double lon2 = lon1 + Math.atan2(Math.sin(azimuth2)*a , Math.cos(dR) - Math.sin(lat1)*Math.sin(lat2));

		double new_X = Math.toDegrees(lat2);
		double new_Y = Math.toDegrees(lon2);
		System.out.println(new_X + " , " + new_Y + "the coords of the full path");
		p.setPoint(new_X, new_Y); // it defined in the class point3D
		geom.Point3D temp = new geom.Point3D(new_X, new_Y,0);
		return temp;
	}

}


