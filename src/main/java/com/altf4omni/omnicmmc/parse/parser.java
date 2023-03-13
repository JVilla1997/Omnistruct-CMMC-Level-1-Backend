package com.altf4omni.omnicmmc.parse;

import java.util.List;

public class parser {
    private String answer;
    private String description;
    private String prompt;
    private String result;
    private List <ExtendedQuestionAnswer> extendedQuestionAnswers;

    public class ExtendedQuestionAnswer{
        private String prompt;
        private String description;
        private String answer;
        private String result;

    }
    public class QuestionAnswers{
        private String sectionName;
        private List<parser> answerList;

    }
}
