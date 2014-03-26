package atin84.dovelet.rooftop;
import java.util.Scanner;
import java.util.Vector;

/**
 * �������� N ���� �����밡 �� �ٷ� ���ִ�. ���ǻ�, �������� x-���̶� �ϰ�, �����밡 �� �ִ� ��ġ x0,x1,...,xN-1 �� x-�� ���� x-��ǥ��� ����. x0�� �׻� 0�̰� xi(i �� 1)�� ���� ������� �����Ѵ�
 * �� ��������� �̿��� �� ������ ������ �Ÿ��� ��� �����ϵ��� �Ϻ� ��������� �ű���� �Ѵ�. �̶� �̵��ؾ��ϴ� ��������� �Ÿ��� ���� �ּҰ� �ǵ��� �ؾ� �Ѵ�. ��, x0 �� ��ġ�� ������� ������ �� ����, �̵��ϴ� ��������� ���� ��ǥ ��ġ�θ� �̵� �����ϴ�.
 * ���� ���, �Ʒ��� �׸� 1�� ���� �����밡 �־��� �ִٰ� ����.
 * �� ��� �׸� 2������ ���� x-��ǥ 6�� 9�� ��ġ�� �����븦 ���� x-��ǥ 8�� 12�� ������ �̵��ϸ�, ��� �̿��� ��������� �Ÿ��� 4�� ���� �������� �̵� �Ÿ��� ���� 5�̴�.
 * ������ �׸� 3�� ���� x-��ǥ 4�� ��ġ�� ���� �븸�� x-��ǥ 3�� ������ �̵��ϸ�, �̿��� �� ������� �Ÿ��� ��� 3�̰� �������� �̵� �Ÿ��� ���� 1�̴�.
 * ��������� ��ġ x0,x1,x2, .. , xN-1 �� �־�����, ��� �̿��� ��������� �Ÿ��� ������ ��������� �̵��� ��(x0 �� ��ġ�� ������� ����), �̵� �Ÿ��� ���� �ּҰ� �ǵ��� �ϴ� ���α׷��� �ۼ� �Ͻÿ�.
 * ����ð��� 1�ʸ� ���� �� ����.
 * �Է�
 * �Է��� ù ���� �������� �� N ( 1 �� N �� 100,000 )�� �־�����.
 * �� ��° �ٿ��� �������� ��ġ�� ��Ÿ���� N ���� ���� �ٸ� x-��ǥ xi (i = 0 , ... , N-1)�� ��ĭ�� ���̿� �ΰ� ������������ �־�����. xi�� �����̰�, 0 �� xi �� 1 000 000 000.
 * ���
 * ����� �� �� ���̸�, ��� �̿��� ��������� �Ÿ��� ������ ��������� �̵��Ÿ� ���� �ּڰ��� ����Ѵ�.
 * �߰� ��� ����� ����� ���� 32��Ʈ ������ ������ ��� �� ������ 64��Ʈ �������� �̿� �� ���� �����Ѵ�.
 * ����� ��
 * �Է�
 * 4
 * 0 4 6 9
 * ���
 * 1
 * �Է�
 * 7
 * 0 5 12 15 16 22 23
 * ���
 * 11
 * @author Chang-Hwan Han
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