/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawpanel;

import java.awt.Graphics;

/**
 * Аргументы для передачи слушателям события отрисовки
 * @author alvah
 */
public class DrawEventArg {
    //Кто вызвал событие
    private final Object owner;
    //Инструмент рисования
    private final Graphics graphics;
    //Доступ к объекту вызвавшему событие
    public Object getOwner()
    {
        return owner;
    }
    //Доступ к инструменту рисования
    public Graphics getGraphics()
    {
        return graphics;
    }
    //Конструктор, в который передается объект вызвавший событие 
    //и инструмент для рисования
    public DrawEventArg(Object owner, Graphics graphics)
    {
        this.owner = owner;
        this.graphics = graphics;
    }
}
