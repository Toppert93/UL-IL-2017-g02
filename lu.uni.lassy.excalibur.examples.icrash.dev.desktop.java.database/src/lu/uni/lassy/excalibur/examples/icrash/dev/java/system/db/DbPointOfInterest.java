package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtPointOfInterest;

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
						+ "VALUES(" + "'" + id + "'" + ",'" + category + "', "
						+ latitude + ", " + longitude + ", '" + description + "')'")
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
			String sql = "DELETE FROM " + dbName + ".PointOfInterest WHERE id = ?";
			String id = aCtPointOfInterest.id.value.getValue();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			int rows = statement.executeUpdate();
			log.debug(rows + " row deleted");
		} catch (SQLException s) {
			log.error("SQL statement is not executed! " + s);
		}

		conn.close();
		log.debug("Disconnected from database");
	} catch (Exception e) {
		logException(e);
	}
}
}

