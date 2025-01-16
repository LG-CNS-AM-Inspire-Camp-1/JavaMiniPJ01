package org.example.movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieManagement {
    // 영화 정보 리스트
    private List<Movie> movies = new ArrayList<>();

    // 영화 고유 ID 중복 확인
    private Set<String> idSet = new HashSet<>();

    // 조회수 관리 (영화 ID -> 조회수)
    private Map<String, Integer> movieViews = new HashMap<>();

    // 영화 등록
    public void addMovie(Scanner scanner) {
        System.out.print("영화 ID: ");
        String id = scanner.nextLine();

        if (idSet.contains(id)) {
            System.out.printf("ID %s과 동일한 영화가 이미 등록되어 있습니다.\n", id);
            return;
        }

        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("감독: ");
        String director = scanner.nextLine();

        System.out.print("제작 연도: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Movie movie = new Movie(id, title, director, year);
        movies.add(movie);
        idSet.add(id);
        movieViews.put(id, 0);
        System.out.println("정상적으로 등록되었습니다." + movie);
    }

    // 전체 영화 목록 조회
    public void listMovies() {
        if (movies.isEmpty()) {
            System.out.println("등록된 영화가 없습니다.");
            return;
        }

        movies.forEach(System.out::println);
    }

    // 영화 검색 => 제목이나 감독에 키워드 포함 여부 검색
    public void searchMovies(Scanner scanner) {
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(keyword) ||
                    movie.getDirector().toLowerCase().contains(keyword)) {
                System.out.println(movie);
                movieViews.put(movie.getId(), movieViews.get(movie.getId()) + 1);
                found = true;
            }
        }

        if (!found) {
            System.out.printf("%s를 포함하는 영화를 찾을 수 없습니다.\n", keyword);
        }
    }

    // 영화 삭제
    public void deleteMovie(Scanner scanner) {
        System.out.print("삭제할 영화의 ID를 입력하세요: ");
        String id = scanner.nextLine();

        movies = movies.stream()
                .filter(movie -> !movie.getId().equals(id))
                .collect(Collectors.toList());

        if (idSet.remove(id)) {
            movieViews.remove(id);
            System.out.printf("ID %s 영화를 삭제했습니다.\n", id);
        } else {
            System.out.printf("ID %s와 일치하는 영화를 찾을 수 없습니다.\n", id);
        }

        System.out.printf("ID %s와 일치하는 영화를 찾을 수 없습니다.\n", id);
    }

    // 인기 영화 조회
    public void mostViewedMovies() {
        if (movieViews.isEmpty() || Collections.max(movieViews.values()) == 0) {
            System.out.println("등록된 인기 영화가 없습니다.");
            return;
        }

        int maxViews = Collections.max(movieViews.values());
        movies.stream()
                .filter(movie -> movieViews.getOrDefault(movie.getId(), 0) == maxViews)
                .forEach(System.out::println);

    }
}
