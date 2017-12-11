package application.mechanic;

import application.mechanic.avatar.Player;
import application.mechanic.internal.GameSessionService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings("MissortedModifiers")
public class MultiGameSession {
    @NotNull
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
    @NotNull
    private final Long sessionId;
    @NotNull
    private final Player singer;
    @NotNull
    private Player listener;
    @NotNull
    private String songName;
    @NotNull
    private String status;
    @NotNull
    private final GameSessionService gameSessionService;
    private boolean result = false;


    public MultiGameSession(@NotNull Player singer,
                            @NotNull Player listener,
                            @NotNull String songName,
                            @NotNull String status,
                            @NotNull GameSessionService gameSessionService) {
        this.sessionId = ID_GENERATOR.getAndIncrement();
        this.singer = singer;
        this.listener = listener;
        this.gameSessionService = gameSessionService;
        this.songName = songName;
        this.status = status;
        singer.setRole(Config.SINGER_ROLE);
        listener.setRole(Config.LISTENER_ROLE);
    }

    @NotNull
    public Player getSinger() {
        return singer;
    }

    @NotNull
    public Player getListener() {
        return listener;
    }

    public long getId() {
        return sessionId;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final MultiGameSession other = (MultiGameSession) object;

        return sessionId.equals(other.sessionId);
    }

    @Override
    public int hashCode() {
        return sessionId.hashCode();
    }

    public boolean tryFinishGame() {
        if (status.equals(Config.FINAL_STEP)) {
            gameSessionService.finishMultiGame(this);
            return true;
        }
        return false;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @NotNull
    public String getSongName() {
        return songName;
    }

    @NotNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NotNull String status) {
        this.status = status;
    }

    public long getSingerId() {
        return singer.getId();
    }

    public long getListenerId() {
        return listener.getId();
    }
}
