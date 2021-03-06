package atin84.dovelet.stairs3;

import java.util.Scanner;

/**
 * complete
 * http://183.106.113.109/30stair/complete/complete.php?pname=complete
 * @author atin84
 *
 */
public class Perfect {
	
	private String DEFICIENT = "DEFICIENT";
	private String PERFECT = "PERFECT";
	private String ABUNDANT = "ABUNDANT";
	
	public String getResult(int n) {
		int sum = 0;
		for(int i = 1; i < n; i++) {
			if(n % i == 0) {
				sum += i;
				if(sum == n) {
					return PERFECT;
				}
				if(sum > n) {
					return ABUNDANT;
				}
			}
		}
		return DEFICIENT;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.format("%5d  " + new Perfect().getResult(n), n);
	}
}
