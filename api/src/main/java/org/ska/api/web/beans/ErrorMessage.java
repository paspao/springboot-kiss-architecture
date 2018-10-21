package org.ska.api.web.beans;

/**
 * Created by <a href="mailto:pasquale.paola@eng.it">Pasquale Paola</a> on 08/10/18.
 */
public class ErrorMessage {

    private String message;



    public ErrorMessage(){
        super();
    }
    public ErrorMessage(String msg){
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
