/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawpanel;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Панель с событием отрисовки
 * @author alvah
 */
public class DrawPanel extends JPanel {
    //Список слушателей
    private final List<IDrawListener> drawListeners;
    //Конструктор
    public DrawPanel()
    {
        super();
        drawListeners = new ArrayList<>();
    }
    //Добавляет слушателя в список
    public void addDrawListener(IDrawListener listener)
    {
        if (drawListeners.indexOf(listener) == -1)
            drawListeners.add(listener);
    }
    //Удаляет слушателя из списка
    public void removeDrawListener(IDrawListener listener)
    {
        drawListeners.remove(listener);
    }
    //Возвращает массив слушателей
    public IDrawListener[] getDrawListeners()
    {
        return drawListeners.toArray(new IDrawListener[drawListeners.size()]);
    }
    //Вызывает событие отрисовки
    protected void fireDrawListener(Graphics graphics)
    {
        DrawEventArg ev = new DrawEventArg(this, graphics);
 	for (IDrawListener listener : drawListeners)
            listener.DrawEvent(ev);
    }
    //Перекрытие метода отрисовки компонента
    @Override
    public void paintComponent(Graphics g) {
        //Вызываем стандартную отрисовку 
        super.paintComponent(g);
        //Вызываются обработчики события
        fireDrawListener(g);
    }
}
