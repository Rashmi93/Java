package com.ilimi.jsonParser;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//@Entity
//@Table(name = "TELEMETRY", uniqueConstraints = {
//       @UniqueConstraint(columnNames = "uid"),
//       @UniqueConstraint(columnNames = "sid") })
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPojo {
	
///	@Column(name = "uid", unique = true, nullable = false)
    private String uid;
     
  //  @Column(name = "sid", unique = true, nullable = false, length = 100)
    private String sid;
    
   // @Column(name = "ts", unique = true, nullable = false, length = 100)
    private String ts;
    
  //  @Column(name = "did", unique = true, nullable = false, length = 100)
    private String did;
    
   // @Column(name = "ver", unique = true, nullable = false, length = 100)
    private String ver;
    
    //@Column(name = "type", unique = true, nullable = false, length = 100)
    private String type;
    
   // @Column(name = "tags", unique = true, nullable = false, length = 100)
    private String[] tags;
    
    //@Column(name = "eid", unique = true, nullable = false, length = 100)
    private String eid;
    
   // Column(name = "timestamp", unique = true, nullable = false, length = 100)
    private String timestamp;
    
   // @Column(name = "uuid", unique = true, nullable = false, length = 100)
    private String uuid;
    
    //@Column(name = "key", unique = true, nullable = false, length = 100)
    private String key;
    
//	 private String[] tags;
//	 private String uid;
//	 private String sid;
//	 private String ts;
//	 private String did;
//	// private HashMap gdata;
//	 private String ver;	
//	 private String type;
//	 private String eid;
//	 private String timestamp;
//	 private String uuid;
//	 private String key;
//	 
	 
//	public HashMap getGdata() {
//		return gdata;
//	}
//	public void setGdata(HashMap gdata) {
//		this.gdata = gdata;
//	}
//	 public String[] getTags() {
//		return tags;
//	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	 @Override
	public String toString() {
		return "JsonPojo [tags=" + Arrays.toString(tags) + ", uid=" + uid + ", sid=" + sid + ", ts=" + ts + ", did="
					+ did + ", ver=" + ver + ", type=" + type + ", eid=" + eid + ", timestamp=" + timestamp + ", uuid="
					+ uuid + ", key=" + key + "]";
		}
}
