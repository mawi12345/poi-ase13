package at.fhj.poi.model;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

/**
 * POI PDO mapped entity
 * 
 * @author mawi
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Poi implements Serializable {

	@JsonIgnore
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
    @Persistent
    private String name;
    
    @Persistent
    private Double latitude;
    
    @Persistent
    private Double longitude;
    
    @Persistent
    private String creator;
    
    @Persistent
    private String description;
    
    @Persistent
    private String category;

	public Key getKey() {
		return key;
	}
	
	@JsonProperty("id")
	public Long getId() {
		if (null != this.getKey())
			return this.getKey().getId();
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String toString() {
		return getName()+" id: "+getId();
	}
	
	public void validate()
	{
    	if (getCreator() == null) throw new ResourceException(new Status(400), "POI creator is empty");
    	if (getLatitude() == null || getLatitude() < 0.001 || getLatitude() > 90.0)
    		throw new ResourceException(new Status(400), "POI latitude is empty or invalid");
    	if (getLongitude() == null || getLongitude() < -180.0 || getLongitude() > 180.0)
    		throw new ResourceException(new Status(400), "POI longitude is empty or invalid");
    	if (getName() == null || getName().length() < 2)
    		throw new ResourceException(new Status(400), "POI name is empty or to short");
	}
    
}
