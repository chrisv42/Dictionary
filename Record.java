public class Record {
    String config;
    int score;
    public Record(String config, int score){
        this.config = config;
        this.score = score;
    }
    public String getConfig(){
        return this.config;
    }
    public int getScore(){
        return this.score;
    }
}
