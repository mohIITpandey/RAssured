package Utlility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/*
	 * This method is used to give Random Number
	 * @author Mohit Pandey
	 */
 public int getRandomNum() {
	Random random= new Random();
	int randNum=random.nextInt(1000);
	return randNum;
	 }
/*
 * This method is used to give current system date
 * @author Mohit Pandey
 */
 public String SystemDate() {
	 Date date=new Date();
	 String sysDate = date.toString();
	 return sysDate;
 }
 /*
  * This method is used to give data in current system dateFormat'
  * @author Mohit Pandey
  */
 public String getSystemDateInFormat() {
	 Date date=new Date();
	 String fDate = date.toString();
	 String[] d = fDate.split(" ");
	 int month=date.getMonth();
	 String week = d[0];
	 String cdate = d[2];
	 String year = d[5];
	 String dateFormat = month+" "+week+" "+cdate+" "+year;
	 return dateFormat;	 
 }
}
