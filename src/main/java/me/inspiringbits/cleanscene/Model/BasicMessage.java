package me.inspiringbits.cleanscene.Model;

/**
 * Created by Ivan on 2017/8/13.
 */

public class BasicMessage {
    public static final String NO_FACEBOOK_ID = "300";
    public static final String SUCCESS ="200" ;
    public static final String JOIN_VOLUNTEERING_FAILED = "501";
    public static final String DROP_OUT_FAILED ="502" ;
    public static final String ACTIVITY_CLOSED ="503" ;
    public static final String FEEDBACK_FAILED = "601";
    private String code;
    private boolean status;
    private String content;

    public BasicMessage(String code, boolean isSuccess, String content) {
        this.code = code;
        this.status = isSuccess;
        this.content = content;
    }

    public BasicMessage() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
