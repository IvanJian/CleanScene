package me.inspiringbits.cleanscene.Model;

/**
 * Created by IvanJian on 2017/9/11.
 */
public class Feedback {
    private FeedbackId feedbackId;
    private String date;
    private String content;
    public static String CONTENT_POSITIVE = "positive";
    public static String CONTENT_NEGATIVE = "negative";

    public FeedbackId getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(FeedbackId feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
