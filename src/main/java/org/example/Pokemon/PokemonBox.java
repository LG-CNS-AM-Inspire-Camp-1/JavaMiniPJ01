package org.example.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import java.util.*;

public class PokemonBox {
    private List<Pokemon> ownedPokemons = new ArrayList<>();
    private List<String> pokemonList = new ArrayList<>();
    private Set<String> pokemonNicknameSet = new HashSet<>();
    private int nextId = 1;

    public PokemonBox() {
        // 1번부터 151번까지 포켓몬 도감 초기화
        String[] pokemonNames = {
                "이상해씨", "이상해풀", "이상해꽃", "파이리", "리자드", "리자몽", 
                "꼬부기", "어니부기", "거북왕", "캐터피", "단데기", "버터플", 
                "뿔충이", "딱충이", "독침붕", "구구", "피죤", "피죤투", 
                "꼬렛", "레트라", "깨비참", "깨비드릴조", "아보", "아보크", 
                "피카츄", "라이츄", "모래두지", "고지", "니드런♀", "니드리나", 
                "니드퀸", "니드런♂", "니드리노", "니드킹", "삐삐", "픽시", 
                "식스테일", "나인테일", "푸린", "푸크린", "주뱃", "골뱃", 
                "뚜벅쵸", "냄새꼬", "라플레시아", "파라스", "파라섹트", "콘팡", 
                "도나리", "이브이", "샤미드", "부스터", "쥬피썬더", "메타몽", 
                "망나뇽", "망나니", "푸린볼트", "뮤츠", "뮤"
            };
        Collections.addAll(pokemonList, pokemonNames);
    }

    // 포켓몬 만나기
    public void catchPokemon(Scanner scanner) {
        Random random = new Random();
        int randomIndex = random.nextInt(pokemonList.size());
        String randomPokemon = pokemonList.get(randomIndex);

        System.out.println(randomPokemon + "(이)가 나타났다.");
        System.out.println("1. 잡기");
        System.out.println("2. 도망치기");
        System.out.print("어떻게 할까? : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("잡은 포켓몬의 이름을 지어주세요! : ");
            String nickname = scanner.nextLine();
            if (pokemonNicknameSet.contains(nickname)) {
                System.out.println("이미 존재하는 닉네임입니다. 포켓몬을 잡지 못했습니다.");
                return;
            }
            ownedPokemons.add(new Pokemon(nextId++, randomPokemon, nickname));
            pokemonNicknameSet.add(nickname);
            System.out.println(randomPokemon + "(을)를 잡았다!");
        } else {
            System.out.println("무사히 도망쳤다!");
        }
    }

    // 전체 포켓몬 조회
    public void listPokemons() {
        if (ownedPokemons.isEmpty()) {
            System.out.println("보유한 포켓몬이 없습니다.");
            return;
        }
        ownedPokemons.forEach(System.out::println);
    }

    // 보유 포켓몬 검색
    public void searchPokemons(Scanner scanner) {
        System.out.print("검색어를 입력하세요: ");
        String keyword = scanner.nextLine().trim();

        boolean found = false;
        for (Pokemon pokemon : ownedPokemons) {
            if (pokemon.getPokemonName().toLowerCase().contains(keyword) || pokemon.getPokemonNickname().toLowerCase().contains(keyword)) {
                System.out.println(pokemon);
                found = true;
            }
        }
        if (!found) {
            System.out.printf("'%s'(을)를 포함한 포켓몬을 찾을 수 없습니다.\n", keyword);
        }
    }

 // 포켓몬 닉네임 변경
    public void changePokemonNickname(Scanner scanner) {
        System.out.print("닉네임을 변경할 포켓몬 닉네임을 입력하세요: ");
        String oldNickname = scanner.nextLine().trim();

        for (Pokemon pokemon : ownedPokemons) {
            if (pokemon.getPokemonNickname().equalsIgnoreCase(oldNickname)) {
                System.out.print("새로운 닉네임을 입력하세요: ");
                String newNickname = scanner.nextLine().trim();
                if (pokemonNicknameSet.contains(newNickname)) {
                    System.out.println("이미 존재하는 닉네임입니다.");
                    return;
                }
                // 닉네임 업데이트
                pokemonNicknameSet.remove(oldNickname);
                pokemonNicknameSet.add(newNickname);
                pokemon.setPokemonNickname(newNickname);
                System.out.printf("'%s'의 닉네임이 '%s'(으)로 변경되었습니다.\n", oldNickname, newNickname);
                return;
            }
        }
        System.out.printf("'%s'(와)과 일치하는 포켓몬을 찾을 수 없습니다.\n", oldNickname);
    }

    // 포켓몬 놓아주기
    public void releasePokemon(Scanner scanner) {
        System.out.print("놓아줄 포켓몬 닉네임을 입력하세요: ");
        String nickname = scanner.nextLine().trim();

        Iterator<Pokemon> iterator = ownedPokemons.iterator();
        while (iterator.hasNext()) {
            Pokemon pokemon = iterator.next();
            if (pokemon.getPokemonNickname().equalsIgnoreCase(nickname)) {
                iterator.remove();
                pokemonNicknameSet.remove(nickname);
                System.out.printf("포켓몬 '%s(%s)'(을)를 놓아주었습니다.\n", pokemon.getPokemonName(), nickname);
                return;
            }
        }
        System.out.printf("'%s'(와)과 일치하는 포켓몬을 찾을 수 없습니다.\n", nickname);
    }
}
