import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String WRAP_PATTERN = "\n";
    public static final String SPACE_STRING = " ";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTERN).length==1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(SPACE_PATTERN);

                List<Input> inputList = new ArrayList<>();
                for (String word : words) {
                    Input input = new Input(word, 1);
                    inputList.add(input);
                }

                Map<String, List<Input>> wordsInfoMap =getListMap(inputList);

                List<Input> wordsInfoList = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : wordsInfoMap.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    wordsInfoList.add(input);
                }
                inputList = wordsInfoList;

                inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner(WRAP_PATTERN);
                for (Input w : inputList) {
                    String s = w.getValue() + SPACE_STRING +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList){
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }
            else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
