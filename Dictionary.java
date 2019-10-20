class Node{
    String key;
    Integer val;
    Node next;
    public Node(String key, int value){
        this.key = key;
        this.val = value;
    }
}

public class Dictionary implements DictionaryADT {
    Node[] dictionary;
    private int numElem = 0;
    private int size;

    public Dictionary(int size){
        dictionary = new Node[size];
        this.size = size;
    }

    /* Using polynomial hashing */
    
    private int hashCoding(String key){
        int i,c;
        int code = 0;
        int len = key.length();
        for (i = 0; i < len; i++){
            c = key.charAt(i);
            code += (c * Math.pow(33,len-i)) % this.size;
        }
        return code%this.size;
    }

    public int insert (Record pair) throws DictionaryException{
        int index = hashCoding(pair.getConfig());
        if (dictionary[index] == null) {
            dictionary[index] = new Node(pair.getConfig(),pair.getScore());
            return 0;
        }
        if (dictionary[index].key == null) {
            dictionary[index].key = pair.getConfig();
            dictionary[index].val = pair.getScore();
            return 0;
        }
        else if (dictionary[index].key.equals(pair.getConfig())){
            throw new DictionaryException();
        }
        else if (dictionary[index].key != null) {
            Node temp = dictionary[index];
            dictionary[index] = new Node(pair.getConfig(),pair.getScore());
            dictionary[index].next = temp;
            return 1;
        }
        return 0;
        
    }

 public void remove (String config) throws DictionaryException{
        int index = hashCoding(config);
        Node temp = dictionary[index];
        if (dictionary[index] == null) {
            throw new DictionaryException();
        }
        while (temp != null) {
            if (temp.key == config){
                temp.key = null;
                temp.val = null;
            }
            temp = temp.next;
    }
    }

    public int get(String config){
        int index = hashCoding(config);
        Node head = dictionary[index];
        while (head != null){
            if (head.key.equals(config)){
                return head.val;
            }
            head = head.next;
        }
        return -1;
    }

    public int numElements(){
        return this.numElem;
    }
}
