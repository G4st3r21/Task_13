package ru.vsu.cs.PoryadinAV;

import ru.vsu.cs.PoryadinAV.utils.SwingUtils;

import java.util.Locale;


public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        java.awt.EventQueue.invokeLater(() -> {
            try {
                new GameFrame().setVisible(true);
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
}
