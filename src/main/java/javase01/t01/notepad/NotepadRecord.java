package javase01.t01.notepad;

import java.time.LocalDateTime;

/**
 * Created by m-levin on 24.04.2017.
 */

class NotepadRecord {
    private String text;
    private LocalDateTime timePoint;

    NotepadRecord(String text) {
        this.text = text;
        timePoint = LocalDateTime.now();
    }

    void setText(String text) {
        this.text = text;
        timePoint = LocalDateTime.now();
    }

    String getText() {
        return text;
    }

    LocalDateTime getTimePoint() { return timePoint; }

}
