package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.awt.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtPointOfInterest;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtComment;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCrisisID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtDescription;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtGPSLocation;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLatitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLongitude;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPointOfInterestID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCategory;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDate;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtReal;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.ICrashUtils;

public class DbPointOfInterest extends DbAbstract {

	public static int getMaxPointOfInterestID() {
		int maxPointOfInterestID =0;

		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			log.debug("Connected to the database");

			/********************/
			//Select

		try{
			String sql = "SELECT MAX(CAST(id AS UNSIGNED)) FROM " + dbName
					+ ".PointOfInterest";
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet res = statement.executeQuery(sql);

			if (res.next()) {
				maxPointOfInterestID = res.getInt(1);
			}

		} catch (SQLException s) {
			log.error("SQL statement is not executed! " + s);
		}
		conn.close();
		log.debug("Disconnected from database");

		} catch (Exception e) {
			logException(e);
		}

	return maxPointOfInterestID;

}

	public static void insertPointOfInterest(CtPointOfInterest aCtPointOfInterest) {
		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			log.debug("Connected to the database");

			/********************/
			//Insert
			try {
				Statement st = conn.createStatement();

				String id = aCtPointOfInterest.id.value.getValue();
				String category = aCtPointOfInterest.Category.toString();
				double latitude = aCtPointOfInterest.location.latitude.value.getValue();
				double longitude = aCtPointOfInterest.location.longitude.value.getValue();
				String description =aCtPointOfInterest.Description.value.getValue();
				log.debug("[DATABASE]-Insert PointOfInterest");
				int val = st.executeUpdate("INSERT INTO " + dbName + ".PointOfInterest"
						+ "(id,category,latitude,longitude,description)"
						+ "VALUES(" + "'" + id + "'" + ",'" + category + "','"
						+ latitude + "',' " + longitude + "', '" + description + "')")
						;

				log.debug(val + " row affected");
			} catch (SQLException s) {
				log.error("SQL statement is not executed! " + s);
			}

			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			logException(e);
		}

	}
	
	public static void DeletePointOfInterest(CtPointOfInterest aCtPointOfInterest){
	try {
		conn = DriverManager
				.getConnection(url + dbName, userName, password);
		log.debug("Connected to the database");

		/********************/
		//Delete

		try {
			
			Statement st = conn.createStatement();
			String id = aCtPointOfInterest.id.value.getValue();
			int val = st.executeUpdate("DELETE FROM " + dbName + ".PointOfInterest WHERE Id =" + id);
			log.debug(val + " row deleted");
		} catch (SQLException s) {
			log.error("SQL statement is not executed! " + s);
		}

		conn.close();
		log.debug("Disconnected from database");
	} catch (Exception e) {
		
		logException(e);
	}
}
	public static void EditPointOfInterest(CtPointOfInterest aCtPointOfInterest){
		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			log.debug("Connected to the database");
			/********************/
			//Update

			try {
				log.debug("[DATABASE]-Update Point of Interest");
				String category = aCtPointOfInterest.Category.toString();
				double latitude = aCtPointOfInterest.location.latitude.value.getValue();
				double longitude = aCtPointOfInterest.location.longitude.value
						.getValue();
				String description = aCtPointOfInterest.Description.toString();
				String id = aCtPointOfInterest.id.value.getValue();

				String sql = "UPDATE "
						+ dbName
						+ ".PointOfInterest SET `category` = ?, `latitude` = ?, `longitude` = ?,"
						+ " `description` = ?  WHERE id = ?";
			
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, category);
				statement.setDouble(2, latitude);
				statement.setDouble(3, longitude);
				statement.setString(4, description);
				statement.setString(5, id);
				int rows = statement.executeUpdate();
				log.debug(rows + " row affected");
			} catch (SQLException s) {
				log.error("SQL statement is not executed! " + s);
			}

			conn.close();
			log.debug("Disconnected from database");
		} catch (Exception e) {
			logException(e);
		}
	}
	
	static public ArrayList<CtPointOfInterest> getAllCtPointOfInterest() {

		ArrayList<CtPointOfInterest> Collection = new ArrayList<CtPointOfInterest>();

		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			log.debug("Connected to the database");

			/********************/
			//Select

			try {
				
				String sql = "SELECT * FROM " + dbName + ".PointOfInterest";

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet res = statement.executeQuery(sql);

				while (res.next()) {

					CtPointOfInterest actPoint = new CtPointOfInterest();
					
					DtPointOfInterestID aId = new DtPointOfInterestID(new PtString(
							res.getString("id")));
					
					
					String theType = res.getString("category");
					EtCategory aType = null;
					if (theType.equals(EtCategory.Hospital.name()))
						aType = EtCategory.Hospital;
					if (theType.equals(EtCategory.PoliceStation.name()))
						aType = EtCategory.PoliceStation;
					if (theType.equals(EtCategory.Garage.name()))
						aType = EtCategory.Garage;
					if (theType.equals(EtCategory.Parking.name()))
						aType = EtCategory.Parking;
					if (theType.equals(EtCategory.InsuranceOffice.name()))
						aType = EtCategory.InsuranceOffice;
					if (theType.equals(EtCategory.FireStation.name()))
						aType = EtCategory.FireStation;


					
					DtLatitude aDtLatitude = new DtLatitude(new PtReal(
							res.getDouble("latitude")));
					DtLongitude aDtLongitude = new DtLongitude(new PtReal(
							res.getDouble("longitude")));
					DtGPSLocation aDtGPSLocation = new DtGPSLocation(
							aDtLatitude, aDtLongitude);

					DtDescription aDtDescription = new DtDescription(new PtString(
							res.getString("description")));
					actPoint.Category = aType;
					actPoint.id= aId;
					actPoint.Description = aDtDescription;
					actPoint.location = aDtGPSLocation;

				Collection.add(actPoint);
				
				}

			} catch (SQLException s) {
				log.error("SQL statement is not executed! " + s);
			}
			conn.close();
			log.debug("Disconnected from database");

		} catch (Exception e) {
			logException(e);
		}

		return Collection;

	}
	
	static public CtPointOfInterest getCtPointOfInterest(DtPointOfInterestID id) {

		CtPointOfInterest  point = new CtPointOfInterest();

		try {
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			log.debug("Connected to the database");

			/********************/
			//Select

			try {
				log.debug("Select");
				String sql = "SELECT * FROM " + dbName + ".PointOfInterest WHERE Id =" + id;

				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet res = statement.executeQuery(sql);

				if (res.next()) {

			
					
					DtPointOfInterestID aId = new DtPointOfInterestID(new PtString(
							res.getString("id")));
					
					
					String theType = res.getString("category");
					EtCategory aType = null;
					if (theType.equals(EtCategory.Hospital.name()))
						aType = EtCategory.Hospital;
					if (theType.equals(EtCategory.PoliceStation.name()))
						aType = EtCategory.PoliceStation;
					if (theType.equals(EtCategory.Garage.name()))
						aType = EtCategory.Garage;
					if (theType.equals(EtCategory.Parking.name()))
						aType = EtCategory.Parking;
					if (theType.equals(EtCategory.InsuranceOffice.name()))
						aType = EtCategory.InsuranceOffice;
					if (theType.equals(EtCategory.FireStation.name()))
						aType = EtCategory.FireStation;


					
					DtLatitude aDtLatitude = new DtLatitude(new PtReal(
							res.getDouble("latitude")));
					DtLongitude aDtLongitude = new DtLongitude(new PtReal(
							res.getDouble("longitude")));
					DtGPSLocation aDtGPSLocation = new DtGPSLocation(
							aDtLatitude, aDtLongitude);

					DtDescription aDtDescription = new DtDescription(new PtString(
							res.getString("description")));
					point.Category = aType;
					point.id= aId;
					point.Description = aDtDescription;
					point.location = aDtGPSLocation;

				
				
				}

			} catch (SQLException s) {
				log.error("SQL statement is not executed! " + s);
			}
			conn.close();
			log.debug("Disconnected from database");

		} catch (Exception e) {
			logException(e);
		}

		return point;

	}


	

}

