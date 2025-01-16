package org.example.musicManagement;

// 음악 정보 저장 클래스
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Music {
	// 노래 등록 번호, 제목, 가수, 제작년도 저장 클래스
	private String musicRegNum;
	private String musicTitle;
	private String musicSinger;
	private int productinoYear;
}
