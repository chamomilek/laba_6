package bsu.rfe.java.group10.lab6.Osoprilko.var13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {
    // Динамический список скачущих мячей
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    // Флаг приостановленности движения
    private boolean paused;
    // Флаг уменьшения
    private boolean minimaze;
    // Класс таймер отвечает за регулярную генерацию события типа ActionEvent
// При создании его экземпляра используется анонимный класс,
// реализующий интерфейс ActionListener
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
// Задача обработчика события ActionEvent одна - перерисовка окна
            repaint();
        }
    });
    // Конструктор класса BouncingBall
    public Field() {
// Установить цвет заднего фона белым
        setBackground(Color.WHITE);
// Запустить таймер перерисовки области
        repaintTimer.start();
    }
    public void addBall() {
// Заключается в добавлении в список нового экземпляра BouncingBall
// Всю инициализацию BouncingBall выполняет сам в конструкторе
        balls.add(new BouncingBall(this));
    }
    // Унаследованный от JPanel метод перерисовки компонента
    public void paintComponent(Graphics g) {
// Вызвать версию метода, унаследованную от предка
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
// Последовательно запросить прорисовку от всех мячей, хранимых в списке
        for (BouncingBall ball: balls) {
            if(minimaze)
            {
                ball.setMinimaze(true);
            }
            else
                ball.setMinimaze(false);
            ball.paint(canvas);
        }
    }
    // Синхронизированный, т.е. только 1 поток может одновременно быть внутри
    public synchronized void pause() {
// Включить режим паузы
        paused = true;
    }
    // Синхронизированный метод проверки, может ли мяч двигаться
// (не включен ли режим паузы?)
    public synchronized void canMove(BouncingBall ball)
            throws InterruptedException {
        if (paused) {
// Если режим паузы включен, то поток, зашедший внутрь
// данного метода, засыпает
            wait();
        }
    }
    // Синхронизированный, т.е. только 1 поток может одновременно быть внутри
    public synchronized void resume() {
// Выключить режим паузы
        paused = false;
// Будим все ожидающие продолжения потоки
        notifyAll();
    }
    public synchronized void setMinimaze(boolean showMinimaze)
    {
        this.minimaze = showMinimaze;
    }
}
