import java.util.*;
import java.awt.Point;

// ABC 1-C
// http://abc001.contest.atcoder.jp/tasks/abc001_3
 
public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		double deg = in.nextInt() / 10.0;
		double dis = in.nextInt() / 60.0;
		
		solve(deg, dis);
	}
	
	public static void solve(double deg, double dis) {
		String s1 = "";
		String s2 = "";
		if (11.25 <= deg && deg < 33.75) {
			s1 = "NNE";
		} else if (33.75 <= deg && deg < 56.25) {
			s1 = "NE";
		} else if (56.25 <= deg && deg < 78.75) {
			s1 = "ENE";
		} else if (78.75 <= deg && deg < 101.25) {
			s1 = "E";
		} else if (101.25 <= deg && deg < 123.75) {
			s1 = "ESE";
		} else if (123.75 <= deg && deg < 146.25) {
			s1 = "SE";
		} else if (146.25 <= deg && deg < 168.75) {
			s1 = "SSE";
		} else if (168.75 <= deg && deg < 191.25) {
			s1 = "S";
		} else if (191.25 <= deg && deg < 213.75) {
			s1 = "SSW";
		} else if (213.75 <= deg && deg < 236.25) {
			s1 = "SW";
		} else if (236.25 <= deg && deg < 258.75) {
			s1 = "WSW";
		} else if (258.75 <= deg && deg < 281.25) {
			s1 = "W";
		} else if (281.25 <= deg && deg < 303.75) {
			s1 = "WNW";
		} else if (303.75 <= deg && deg < 326.25) {
			s1 = "NW";
		} else if (326.25 <= deg && deg < 348.75) {
			s1 = "NNW";
		} else {
			s1 = "N";
		}
		
		if (dis < 0.25) {
			s1 = "C";
			s2 = "0";
		} else if (0.25 <= dis && dis < 1.55) {
			s2 = "1";
		} else if (1.55 <= dis && dis < 3.25) {
			s2 = "2";
		} else if (3.25 <= dis && dis < 5.45) {
			s2 = "3";
		} else if (5.45 <= dis && dis < 7.95) {
			s2 = "4";
		} else if (7.95 <= dis && dis < 10.75) {
			s2 = "5";
		} else if (10.75 <= dis && dis < 13.85) {
			s2 = "6";
		} else if (13.85 <= dis && dis < 17.15) {
			s2 = "7";
		} else if (17.15 <= dis && dis < 20.75) {
			s2 = "8";
		} else if (20.75 <= dis && dis < 24.45) {
			s2 = "9";
		} else if (24.45 <= dis && dis < 28.45) {
			s2 = "10";
		} else if (28.45 <= dis && dis < 32.65) {
			s2 = "11";
		} else {
			s2 = "12";
		}
		
		System.out.printf("%s %s\n", s1, s2);
	}
}
