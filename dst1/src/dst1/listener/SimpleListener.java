package dst1.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;

public class SimpleListener {
	
	public static int countLoad = 0;
	public static int countUpdate = 0;
	public static int countRemove = 0;
	public static int countPersist = 0;
	
	public static Date tempOverallPersist = new Date();
	public static List<Long> avgPersistTimes = new ArrayList<Long>();
	
	public static long overallTime = 0;
	public static long avgTime = 0;
	
	public static Date tempDate;
	

   @PrePersist 
   public synchronized void onPrePersist(Object o) {
	
	   this.tempDate = new Date();
	   
   }
   @PostPersist
   public synchronized void onPostPersist(Object o) {
	   countPersist++;
	   
	   avgPersistTimes.add(new Date().getTime()-this.tempDate.getTime()); 
	   
	   //updateing overall time each time:
	   Date tempDate = new Date();
	   this.overallTime = tempDate.getTime()-this.tempOverallPersist.getTime();
	   
   }
   @PostLoad
   public synchronized void onPostLoad(Object o) {
	   countLoad++;
   }

   @PostUpdate
   public synchronized void onPostUpdate(Object o) {
	   countUpdate++;
   }
 
   @PostRemove
   public synchronized void onPostRemove(Object o) {
	   countRemove++;
   }
	   
	public static int getCountLoad() {
		return countLoad;
	}
	public static int getCountUpdate() {
		return countUpdate;
	}
	public static int getCountRemove() {
		return countRemove;
	}
	public static int getCountPersist() {
		return countPersist;
	}
	public static long getOverallTime() {
		return overallTime;
	}
	public static long getAvgTimetoPersist(){
		long tempLong = 0;
		long tempSum = 0;
		int i = 0;
		for(Long l : avgPersistTimes){
			i++;
			tempSum += l;
		}
		tempLong = tempSum/i;
		return tempLong;
	}
	   
	   
	   
	   

}
