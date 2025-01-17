package org.example.Pokemon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pokemon {
	private int pokemonId;
	private String pokemonName;
	private String pokemonNickname;
}
