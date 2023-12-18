package com.example.message.domain;


import java.util.List;
import org.ahocorasick.trie.Token;
import org.ahocorasick.trie.Trie;

public class BannedWordJudge {
    private Trie trie;

    public BannedWordJudge(List<String> bannedWords){
        this.trie = Trie.builder()
                .addKeywords(bannedWords)
                .build();
    }

    public String judge(String message){
        StringBuilder sb = new StringBuilder();
        for(Token token : trie.tokenize(message)){
            if(token.isMatch()){
                sb.append(MaskTag.ABUSE_START_TAG.getTag());
            }
            sb.append(token.getFragment());
            if(token.isMatch()){
                sb.append(MaskTag.ABUSE_END_TAG.getTag());
            }
        }
        return sb.toString();
    }
}
