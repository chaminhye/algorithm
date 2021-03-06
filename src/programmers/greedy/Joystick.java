package programmers.greedy;

public class Joystick {
	/***
	 * 문제 설명
			조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
			ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

			조이스틱을 각 방향으로 움직이면 아래와 같습니다.

			▲ - 다음 알파벳
			▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
			◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
			▶ - 커서를 오른쪽으로 이동
			예를 들어 아래의 방법으로 JAZ를 만들 수 있습니다.

			- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
			- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
			- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
			따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
			만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

		제한 사항
			name은 알파벳 대문자로만 이루어져 있습니다.
			name의 길이는 1 이상 20 이하입니다.

		입출력 예
			name	return
			JEROEN	 56
			JAN	     23

		문제 풀이
	 		https://velog.io/@ming/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4%EC%A1%B0%EC%9D%B4%EC%8A%A4%ED%8B%B1Level2
	*/
	static public void main(String args[]) {
		String name = "JAN";

		System.out.println("answer : "+solution(name));
	}
	
	public static int solution(String name) {
		int answer = 0;
		int len = name.length();
		// 위, 아래 판단
		for(int i=0; i<len; i++) {
			char ch = name.charAt(i);

			// ( 'A' -> 'Z' 방향, 'Z' - > 'A' 역방향 중에 최소로 움직이는 방향 선택)
			int move = Math.min(ch - 'A', 'Z' - ch + 1);
			answer += move;
//			System.out.println("move : "+move);
		}
		// 좌, 우 판단
		// 좌 -> 우 방향으로 이동한다 가정.
		int right_left = len - 1;

		// 우 -> 좌 방향으로 이동한다 가정.(A가 중간에 있거나, AAABA 인 경우)
		for(int i=0; i<len; i++) {
			int next = i+1;

			// A가 중간에 있는 경우
			while(next < len && name.charAt(next) == 'A'){
				next++;
			}
			// left = 우 -> 좌로 가는 거리
			// name 길이 중에 A를 지나간 갯수 : left - next
			// i만큼 왓다갓다 움직인 거리 : i *2
			int left = (i*2) + len - next;
//			System.out.println("right : "+right_left +" // left : "+left + " // next : "+next);

			// 좌우 움직임중에 최소 거리를 선택
			right_left = Math.min(right_left, left);
		}

		return answer + right_left;
	}
	
}