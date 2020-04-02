package org.szh.core;

public enum DataSourceType {
	
	write("write","主库"),
	read("read","从库");
	
	public String type;
	
	public String name;
	
	private DataSourceType(String type,String name) {
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
