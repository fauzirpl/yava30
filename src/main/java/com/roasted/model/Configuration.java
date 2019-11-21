package com.roasted.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Map;


/*
 * Written by Reyzaldy, 28/02/2019
 */
public class Configuration {
	@SerializedName("Config")
	@Expose
    private Config config;
	@SerializedName("type")
	@Expose
    private String type;
	@SerializedName("tag")
	@Expose
    private String tag;
	@SerializedName("version")
	@Expose
    private Integer version;
	@SerializedName("properties")
	@Expose
    private Properties properties;
	@SerializedName("properties_attributes")
	@Expose
    private PropertiesAttributes propertiesAttributes;

    private Map<String, Object> _properties;
    private Map<String, Object> _propertiesAttributes;

    
    public Map<String, Object> get_Properties() {
        return _properties;
    }

    public void set_Properties(Map<String, Object>  properties_as_map) {
        this._properties  = properties_as_map ;
    }
    
    
    public Map<String, Object> get_PropertiesAttributes() {
        return _propertiesAttributes;
    }

    public void set_PropertiesAttributes(Map<String, Object>  _propertiesAttributes) {
        this._propertiesAttributes  = _propertiesAttributes ;
    }
    
    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
/*
    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public PropertiesAttributes getPropertiesAttributes() {
        return propertiesAttributes;
    }

    public void setPropertiesAttributes(PropertiesAttributes propertiesAttributes) {
        this.propertiesAttributes = propertiesAttributes;
    }
*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Configuration.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("config");
        sb.append('=');
        sb.append(((this.config== null)?"<null>":this.config));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("tag");
        sb.append('=');
        sb.append(((this.tag == null)?"<null>":this.tag));
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("properties");
        sb.append('=');
        sb.append(((this.properties == null)?"<null>":this.properties));
        sb.append(',');
        sb.append("propertiesAttributes");
        sb.append('=');
        sb.append(((this.propertiesAttributes == null)?"<null>":this.propertiesAttributes));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

//    @Override
//    public int hashCode() {
//        int result = 1;
//        result = ((result* 31)+((this.propertiesAttributes == null)? 0 :this.propertiesAttributes.hashCode()));
//        result = ((result* 31)+((this.tag == null)? 0 :this.tag.hashCode()));
//        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
//        result = ((result* 31)+((this.config == null)? 0 :this.config.hashCode()));
//        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
//        result = ((result* 31)+((this.properties == null)? 0 :this.properties.hashCode()));
//        return result;
//    }

    @Override
    public boolean equals(Object other) {
        Boolean p=true;
    	
    	if (other == this) {
            p = true;
        }
        else if ((other instanceof Configuration) == false) {
            p = false;
        }
    	
		return p;
        
    }

	public String getCurrentConfigurationByType() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
       