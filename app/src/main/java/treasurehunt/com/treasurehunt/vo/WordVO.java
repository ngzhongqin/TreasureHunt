package treasurehunt.com.treasurehunt.vo;

/**
 * Created by zhongqinng on 25/1/15.
 */
public class WordVO {
    private String TAG = "WordVO";
    private String word;

    public WordVO(String word){
        this.word=word;

    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
