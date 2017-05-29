package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

public class PointOfInterest {
	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	String id;
	public String getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getDescription() {
		return description;
	}

	String category;
	String latitude;
	String longitude;
	String description;
	
	public PointOfInterest(String id,String category,String latitude,String longitude, String description){
		this.id=id;
		this.category=category;
		this.latitude=latitude;
		this.longitude=longitude;
		this.description=description;
	}
}
