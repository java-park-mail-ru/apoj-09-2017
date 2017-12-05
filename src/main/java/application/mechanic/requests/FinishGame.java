package application.mechanic.requests;

import application.mechanic.Config;
import application.websocket.Message;

public class FinishGame extends Message {
    private String type = Config.FINAL_STEP;
    private boolean result;

    public FinishGame(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }
}