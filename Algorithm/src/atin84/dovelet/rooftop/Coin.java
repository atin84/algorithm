package atin84.dovelet.rooftop;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

/**
 * koi_coin
 * ���� �ٲ��ֱ�
 * http://183.106.113.109/pool/koi_coin/koi_coin.php?pname=koi_coin
 * @author atin84
 *
 */
public class Coin {

	/**
	 * T=20��, 5��¥�� 3��, 10��¥�� 2��, 1���ڸ� 5�� �� ���� ��,
	 * F(x, y) -> x���� y���� ������ �Ἥ ȯ���ϴ� ����� ���� �ϸ�,
	 * F(20, 3) = F(20-5*0, 2) + F(20-5*1, 2) + F(20-5*2, 2) + F(20-5*3, 2)   -> 5��¥�� 0��~3���� ���� ���� ���� ���� ���� 2���� ������ ������ ȯ���ϴ� ����� ������ ��.
	 * ���������� F(20,2) = F(20, 1) + F(10,1) + F(0, 1) �� ���ϸ� �ǰ�.......
	 */
	private HashMap<String, Integer> cache = new HashMap<String, Integer>();
	private Vector<Integer> p; 
	private Vector<Integer> n;
	
	public Coin(Vector<Integer> p, Vector<Integer> n) {
		this.p = p;
		this.n = n;
	}
	
	public int getChangeCount(int money, int k) {
		if(money < 0) return 0;
		if(money == 0) return 1;
		if(k == 0) return 0;
		
		Integer obj = cache.get(String.valueOf(money + ":" + k));
		if(obj != null) return obj;
		int result = 0;
		
		for(int i = 0; i <= n.get(k-1); i++) {
			result += getChangeCount(money - p.get(k-1) * i, k - 1);
		}
		cache.put(String.valueOf(money + ":" + k), result);
		
		
		return result;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int k = sc.nextInt();
		
		Vector<Integer> p = new Vector<Integer>(k, 0);
		Vector<Integer> n = new Vector<Integer>(k, 0);
		
		for(int i = 0; i < k; i++) {
			p.add(sc.nextInt());
			n.add(sc.nextInt());
		}
		System.out.println(new Coin(p, n).getChangeCount(T, k));

	}	
}