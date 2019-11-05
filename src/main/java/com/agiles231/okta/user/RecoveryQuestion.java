package com.agiles231.okta.user;

public class RecoveryQuestion {

    private final String question;
    private final String answer;
    
    public RecoveryQuestion(String question, String answer) {
        super();
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
