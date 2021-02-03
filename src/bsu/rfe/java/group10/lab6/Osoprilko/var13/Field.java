package bsu.rfe.java.group10.lab6.Osoprilko.var13;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {
    // Динамический список скачущих мячей
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    // Флаг приостановленности движения
    private boolean paused;
    // Класс таймер отвечает за регулярную генерацию события типа ActionEvent
// При создании его экземпляра используется анонимный класс,
// реализующий интерфейс ActionListener
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
// Задача обработчика события ActionEvent одна - перерисовка окна
            repaint();
        }
    });


}
