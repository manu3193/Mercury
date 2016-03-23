package mercury.arm.assembler;

import java.util.ArrayList;

/**
 * Created by manzumbado on 22/03/16.
 */
public class TokenList {

    private ArrayList<Token> tokenList;


    public TokenList() {
        tokenList = new ArrayList<Token>();
    }

    public Token get(int pos) {
        return tokenList.get(pos);
    }

    public void set(int pos, Token replacement) {
        tokenList.set(pos, replacement);
    }


    public int size() {
        return tokenList.size();
    }


    public void add(Token token) {
        tokenList.add(token);
    }

    public void remove(int pos) {
        tokenList.remove(pos);
    }

    public boolean isEmpty() {
        return tokenList.isEmpty();
    }
}
