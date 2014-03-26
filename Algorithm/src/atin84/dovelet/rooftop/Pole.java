package atin84.dovelet.rooftop;
import java.util.Scanner;
import java.util.Vector;

/**
 * koi_pole
 * ������
 * http://183.106.113.109/pool/koi_pole/koi_pole.php?pname=koi_pole
 * @author atin84
 *
 */
public class Pole {

	private static Vector<Integer> p;
	
	@SuppressWarnings("static-access")
	public Pole(Vector<Integer> p) {
		this.p = p;
	}

	/**
	 * ������ �ִ밪�� ������ �Ŀ� ����Ž������ �¿츦 ���ذ��鼭 �������� ã�ư�
	 * 
	 * @param n
	 * @return
	 */
	public long jub(int n) {
		if(n < 2) return 0;
		
		long leftVal = 0;
		long rightVal = 0;
		long centerVal = 0;
		
		/**
		 * �ִ밪�� ����
		 * ��ü ������ ����� ���� �Ŀ� �������� ����-1�� ����
		 * ���� �ʹ� ũ�� �߸��� ���� ������ �� �ִ� �Ϳ� ���� ��� ����
		 */
		long max = 0;
		for(int i = 0; i < n; i++) {
			max += p.get(i);
		}
		max = max / (n-1);
		
		// �̵��� ��
		long idx = max / 2;
		
		// �¿� �ִ밪
		long leftMax = 0;
		long rightMax = max;
		while(true) {
			leftVal = getMoveCount(n, idx -1);
			centerVal= getMoveCount(n, idx);
			rightVal = getMoveCount(n, idx + 1);
			
			/**
			 * �¿찪�� ���Ͽ� ����� ���� ������ �̵��Ѵ�.
			 */
			if(leftVal < rightVal) {
				// left
				rightMax = idx;
				idx = leftMax + ((rightMax - leftMax) /2);
			} else {
				// right
				leftMax = idx;
				idx = rightMax + ((rightMax - leftMax) /2);
			}
			
			/**
			 * �ߴ� ���� : �¿찪�� ��뺸�� ��� ���� ����� ���� ���ٸ� �������� ã�� ������ �Ǵ�
			 */
			if(leftVal > centerVal && rightVal > centerVal) {
				break;
			}
		}
		
		return centerVal;
	}

	/**
	 * p �迭�� n���� ���� ���� �� duration�� ���� ������ �־��� ��� �̵��Ÿ��� ���Ѵ�.
	 * @param n
	 * @param duration
	 * @return
	 */
	public long getMoveCount(int n, long duration) {		
		long result = 0;
		for(int i = 1; i < n; i++) {
			result += Math.abs((duration * i) - p.get(i));
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Vector<Integer> p = new Vector<Integer>(n, 0);
		for(int i = 0; i < n; i++) {
			p.add(sc.nextInt());
		}		
		System.out.println(new Pole(p).jub(n));
	}	
}