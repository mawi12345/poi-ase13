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

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
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
    
}
