package base.model;

import java.util.List;

public class Kanji {
	private String kanji;
	private List<String> meanings;
	
	public Kanji(String kanji, List<String> meanings) {
		this.kanji = kanji;
		this.meanings = meanings;
	}
	
	public String toString() {
		String meaningsString = "";
		for(String meaning: meanings) {
			meaningsString += " " + meaning;
		}
		return String.format("Kanji: %s - Significado:%s", kanji, meaningsString);
	}
}
