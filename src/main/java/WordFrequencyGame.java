import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String WRAP_PATTERN = "\n";
    private static final String SPACE_STRING = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        try {
            String[] words = sentence.split(SPACE_PATTERN);

            List<Input> inputList = getWordInfoList(words);

            StringJoiner joiner = new StringJoiner(WRAP_PATTERN);
            for (Input wordInfo : inputList) {
                String result = wordInfo.getValue() + SPACE_STRING + wordInfo.getWordCount();
                joiner.add(result);
            }
            return joiner.toString();
        } catch (Exception e) {
            return CALCULATE_ERROR;
        }
    }

    private List<Input> getWordInfoList(String[] words) {
        List<Input> inputList = new ArrayList<>();
        for (String word : words) {
            Input input = new Input(word, 1);
            inputList.add(input);
        }

        Map<String, List<Input>> wordsInfoMap = getResultMap(inputList);

        List<Input> wordsInfoList = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : wordsInfoMap.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            wordsInfoList.add(input);
        }
        inputList = wordsInfoList;

        inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
        return inputList;
    }

    private Map<String, List<Input>> getResultMap(List<Input> inputList) {
        Map<String, List<Input>> resultMap = new HashMap<>();
        for (Input input : inputList) {
            if (!resultMap.containsKey(input.getValue())) {
                List<Input> wordCountArr = new ArrayList<>();
                wordCountArr.add(input);
                resultMap.put(input.getValue(), wordCountArr);
            } else {
                resultMap.get(input.getValue()).add(input);
            }
        }
        return resultMap;
    }
}
