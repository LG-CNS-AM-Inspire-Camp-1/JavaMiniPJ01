package org.example.musicManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MusicManagement {
	// 음악 정보
	private List<Music> musics = new ArrayList<>();
	
	// regNum => 동일 음악 존재하는지 확인
	private Set<String> regNumSet = new HashSet();
	
	// 조회수 관리 map
	private Map<String, Integer> musicPlayCount = new HashMap();
	
	// (기능 1) 음악 등록
	public void addMusic(Scanner scanner) {
		System.out.print("\n추가할 음악 등록 번호 입력 : ");
		String regNum = scanner.nextLine();
		
		// 등록 번호 중복 여부 확인
		if(regNumSet.contains(regNum)) {
			System.out.printf("%s와 동일한 등록 번호를 가진 음악이 이미 존재합니다.\n", regNum);
			return;
		}
		
		// 음악 정보 입력
		System.out.print("제목 입력 : ");
		String title = scanner.nextLine();
		
		System.out.print("가수 입력 : ");
		String singer = scanner.nextLine();
		
		System.out.print("년도 입력 : ");
		int year = scanner.nextInt();
		scanner.nextLine();		// 개행 문자 제거
		
		// 객체 생성 후, 추가
		Music music = new Music(regNum, title, singer, year);
		
		// 음악 제목, 가수 중복 여부 확인
		if(!musics.isEmpty()) {
			for(Music m : musics) {
				if(m.getMusicTitle().equals(title) 
						&& m.getMusicSinger().equals(singer)) {
					System.out.printf("%s의 노래 %s가 이미 존재합니다.\n", m.getMusicSinger(), m.getMusicTitle());
					return;
				}
			}
		}
		
		musics.add(music);		// 음악 정보 추가
		regNumSet.add(music.getMusicRegNum());	// 음악 등록 번호 추가
		musicPlayCount.put(music.getMusicRegNum(), 0);	// 음악 재생 횟수 0으로 초기화
		System.out.printf("등록번호 %s인, %s의 노래 %s를 추가했습니다.\n", regNum, music.getMusicSinger(), music.getMusicTitle());
	}
	
	// (기능 2) 전체 음악 출력
	public void printAllMusic() {
		if(musics.isEmpty()) {
			System.out.println("음악이 존재하지 않습니다");
			return;
		}
		
		// 전체 음악 출력
		musics.forEach(System.out::println);
	}
	
	// (기능 3) 음악 검색
	public void searchMusic(Scanner scanner) {
		System.out.print("\n검색어 입력 : ");
		String searchStr = scanner.nextLine();
		
		// searchStr 양쪽 공백 제거 후, 소문자로 변환
		searchStr = searchStr.trim().toLowerCase();
		boolean isFound = false;	// 음악 찾았는지 여부 저장
		
		for(Music m: musics) {
			if(m.getMusicTitle().toLowerCase().contains(searchStr) ||
					m.getMusicSinger().toLowerCase().contains(searchStr)) {
				System.out.println("음악 정보 ( " + m + " )");
				isFound = true;
				
				// 음악 재생 여부 질문
				System.out.print("해당 음악을 재생하시겠습니까 ? (y/n) ");
				boolean play = scanner.nextLine().trim().equals("y") ? true : false;
				if(play) {
					// 재생을 원하면, 재생 횟수 증가
					System.out.printf("%s의 노래 %s를 재생하였습니다.\n\n", m.getMusicSinger(), m.getMusicTitle());
					musicPlayCount.put(m.getMusicRegNum(), musicPlayCount.get(m.getMusicRegNum()) + 1);
				}
				else 
					System.out.println("음악을 재생하지 않았습니다.\n");
			}
		}
		
		if(!isFound) {
			System.out.printf("%s를 포함하는 음악이 존재하지 않습니다.\n", searchStr);
		}	
	}
	
	// (기능 4) 음악 삭제
	public void deleteMusic(Scanner scanner) {
		System.out.print("삭제하려는 음악 등록 번호 입력 : ");
		String regNum = scanner.nextLine();
		
		Iterator<Music> it = musics.iterator();
		while(it.hasNext()) {
			Music music = it.next();
			if(music.getMusicRegNum().equals(regNum)) {
				it.remove();
				regNumSet.remove(regNum);
				musicPlayCount.remove(regNum);
				System.out.printf("등록번호 %s인, %s의 노래 %s를 삭제했습니다.\n", regNum, music.getMusicSinger(), music.getMusicTitle());
				return;
			}
		}
		
		System.out.printf("등록번호가 %s인 음악을 찾을 수 없습니다.\n", regNum);
	}
	
	// (기능 5) 인기 음악 조회
	public void mostPlayedMusic() {
		int max = Collections.max(musicPlayCount.values());
		
		if(musicPlayCount.isEmpty() || max == 0) {
			System.out.println("많이 재생된 인기 음악이 존재하지 않습니다.");
			return;
		}
		
		System.out.printf("\n--- 재생횟수 %d 회 ---\n", max);
		
		// entrySet() 이용하여, Map의 엔드리 추출
		for(Map.Entry<String, Integer> entry : musicPlayCount.entrySet()) {
			// entry의 count 값과 max 값이 동일한 음악 엔트리만 추출
			if(entry.getValue() == max) {
				musics.stream()
					// 등록 번호가 일치하는 음악만 추출
					.filter(m -> m.getMusicRegNum().equals(entry.getKey()))
					.forEach(System.out::println);
			}
		}
	}
	
}
