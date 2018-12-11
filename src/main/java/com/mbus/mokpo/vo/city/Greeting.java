package com.mbus.mokpo.vo.city;

public class Greeting {

    private final long id;
    private final String content;
    private final String year;

    public Greeting(long id, String content, String year) {
        this.id = id;
        this.content = content;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

	public String getYear() {
		return year;
	}
    
}

