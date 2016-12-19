package org.qcri.micromappers.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.json.JsonObject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.qcri.micromappers.models.CollectionTask;


/**
 * Main class to implement everything related to keywords filtering and validations.
 * 
 */
public class TrackFilter implements Predicate<JsonObject> {

	private static Logger logger = Logger.getLogger(TrackFilter.class.getName());

	private String[] toTrack = null;
	private Set<KeywordPredicate> simpleWordBasedPredicates = null;
	private Set<KeywordPredicate> phraseBasedPredicates = null;

	private String patternString = "([^\"]\\S*|\".+?\")\\s*";
	private String phrasePatternString = ".*\".*\".*";

	private Pattern pattern = null;

	public TrackFilter() {
		pattern = Pattern.compile(patternString);
	}

	public TrackFilter(CollectionTask task){
		this();
		if (task != null) {
			this.setToTrack(task.getToTrack());
		} else {
			logger.error("Collection can't be null!");
		}
	}

	public TrackFilter(String keywords) {
		this();
		this.setToTrack(keywords);
	}

	public String[] getToTrack() {
		return this.toTrack;
	}

	public void setToTrack(final String keywords) {
		if (keywords != null && !keywords.isEmpty()) {
			simpleWordBasedPredicates = new HashSet<KeywordPredicate>();
			phraseBasedPredicates = new HashSet<KeywordPredicate>();
			this.toTrack = keywords.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);	// split on comma, ignoring those within double quotations

			for (int i = 0; i < toTrack.length;i++) {
				this.toTrack[i] = this.toTrack[i].trim().toLowerCase();		// remove leading and trailing whitespaces, make all lower-case
				KeywordPredicate pred = new KeywordPredicate(this.toTrack[i]);
				// Now divide the list into two: simpleWordBased (strings without any quotes) and phraseBased (string of form "*"*)
				if (this.toTrack[i].matches(phrasePatternString)) {
					//System.out.println("Matched phrase pattern, string = " + this.toTrack[i]);
					this.phraseBasedPredicates.add(pred);
				} else {
					// simpleWord: either single or multi-word keyword
					simpleWordBasedPredicates.add(pred);
				}
			}
		} else {
			this.toTrack = null;
		}
	}

	private Set<String> createTweetSetOfWords(String tweetText) {
		Set<String> tweetTextSet = toLowerCase(new HashSet<String>(splitOnWhitespace(tweetText)));

		// first remove all punctuation
		Set<String> strippedPuncts = new HashSet<String>();
		Iterator<String> itr = tweetTextSet.iterator();
		while (itr.hasNext()) {
			String word = itr.next();
			int start = word.indexOf("\"");
			int end = word.lastIndexOf("\"");
			if (start != -1) {
				if (end > start) {
					String strippedWord = word.substring(start+1, end);
					strippedPuncts.add(strippedWord);
				} else {
					// Handle the special case of malformed quotations: runaway quote in text
					String strippedWord = word.substring(start+1);
					strippedPuncts.add(strippedWord);
				}
				itr.remove();
			}
		}
		tweetTextSet.addAll(strippedPuncts);

		// Next, handle all #-tagged words in the tweet text
		// For each #-tagged word, add also the word following the #-tag to the tweetTextSet 
		Set<String> hashWordSet = new HashSet<String>();
		
		hashWordSet = tweetTextSet.stream().filter(w -> w.startsWith("#")).map(w -> w.substring(1)).collect(Collectors.toSet());
		/*for (String w: tweetTextSet) {
//			String[] hashSplit = w.split("#");
//			for (int i = 0;i < hashSplit.length;i++) {
//				hashSplit[i] = hashSplit[i].trim();
//			}
//			Set<String> tokensSet = new HashSet<String>(Arrays.asList(hashSplit));
//			tokensSet.removeAll(Collections.singleton(""));
//			hashWordSet.addAll(tokensSet);
			if (w.startsWith("#")) {
				// This is stricter check than the above commented code
				String strippedHash = w.substring(1);
				hashWordSet.add(strippedHash);
			}
		}*/
		tweetTextSet.addAll(hashWordSet);
		return tweetTextSet;
	}

	private Set<String> toLowerCase(Set<String> wordSet) {
		Set<String> toLower = new HashSet<String>();
		toLower = wordSet.stream().map(word -> word.toLowerCase()).collect(Collectors.toSet());
		/*for (String word : wordSet) {
			toLower.add(word.toLowerCase());
		}*/
		return toLower;
	}

	@Override
	public boolean test(JsonObject t) {
		if (null == toTrack) return true;

		String tweetText = t.getString("text");
		if (StringUtils.isBlank(tweetText)) {
			return false;		// there are filter-keywords but no text and hence reject tweet
		}
		// Otherwise test the tweet text for matching at least one of the keywords
		boolean result = hasKeyWords(tweetText);
		//logger.info("Filtering result for tweet text : \"" + tweetText + "\": " + result);
		return result;
	}

	public boolean test(String text) {
		if (ArrayUtils.isEmpty(toTrack)){
			return true;
		}
		
		String tweetText = text.replaceAll("\"", "");
		if (StringUtils.isBlank(tweetText)) {
			return false;		// there are filter-keywords but no text and hence reject tweet
		}
		boolean result = hasKeyWords(tweetText);
		//logger.info("Filtering result for text : \"" + tweetText + "\": " + result);
		return result;
	}

	private boolean matchSimplePredicates(Set<String> tweetTextSet) {
		for (KeywordPredicate predicate: this.simpleWordBasedPredicates) {
			boolean flag = true;
			
			flag = predicate.getUnorderedWords().stream().allMatch(word -> tweetTextSet.contains(word));
			/*for (String word: predicate.getUnorderedWords()) {
				if (!tweetTextSet.contains(word)) {
					flag = false;
					break;
				}
			}*/
			if (flag) {
				return true;		// found a match!
			}	
		}
		return false;
	}

	private boolean matchPhrasePredicates(String tweetText, Set<String> tweetTextSet) {
		for (KeywordPredicate phrasePredicate: this.phraseBasedPredicates) {
			boolean flag = true;
			
			flag = phrasePredicate.getUnorderedWords().stream().allMatch(word -> tweetTextSet.contains(word));
			
			/*for (String word: phrasePredicate.getUnorderedWords()) {
				//System.out.println("For unordered keyword in phrase predicate = " + word + ", contained in = " + tweetTextSet.contains(word));
				if (!tweetTextSet.contains(word)) {
					flag = false;
					break;
				}
			}*/
			if (!flag) {
				//System.out.println("Simple word Predicate match NOT found ");
				return false;		// Didn't find a match
			}
			// Otherwise, check for phrases too,  in original tweet text
			for (String phrase: phrasePredicate.getPhraseSet()) {
				flag = false;
				//System.out.println("For phrase = " + phrase + ", contained in = " + tweetText.contains(phrase));
				if (tweetText.contains(phrase)) {
					//System.out.println("For phrase = " + phrase + " match found:  " + tweetText.contains(phrase));
					flag = true;
					break;
				}
			}
			if (flag) {
				//System.out.println("Phrase Predicate match found ");
				return true;		// found a match!
			}	
		}
		return false;
	}

	private boolean hasKeyWords(final String tweetText) {
		Set<String> tweetTextSet = createTweetSetOfWords(tweetText);	

		// first test simplePredicates
		boolean result = matchSimplePredicates(tweetTextSet);
		if (result) return result;		// Found a match

		// Otherwise, we need to check for phrasePredicates
		result = !this.phraseBasedPredicates.isEmpty() ? matchPhrasePredicates(tweetText, tweetTextSet) : false;
		return result;
	}

	/**
	 * 
	 * @param str string to split
	 * @return
	 */
	private List<String> splitOnWhitespace(String str) {
		List<String> list = new ArrayList<String>();

		Matcher m = pattern.matcher(str);
		while (m.find()) {
			list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.
		}
		return list;
	}
}

