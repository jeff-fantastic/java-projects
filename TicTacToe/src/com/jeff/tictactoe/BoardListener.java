package com.jeff.tictactoe;

import java.util.EventListener;

public interface BoardListener extends EventListener {
    void boardComplete(int player);
}
