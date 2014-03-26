package environment;

import java.util.Date;

public class BidTimer {
	private Date t = null;
	private boolean isFixedDate = false;
	
	public BidTimer(){
		refreshTime();
	}
	
	public BidTimer(Date d){
		Date check = new Date(System.currentTimeMillis());
		if(check.after(d))
			throw new IllegalArgumentException("Date previous to current");
		this.t = d;
		this.isFixedDate = true;
	}
	
	public void refreshTime(){
		if(!isFixedDate)
			t = new Date(System.currentTimeMillis());
	}
	
	public long getTime(){
		if(!isFixedDate)
			refreshTime();
		return t.getTime();
	}
	public boolean getFixedDate(){
		return isFixedDate;
	}
	public void setTime(Date d){
		if(isFixedDate)
			t = d;
	}
}
