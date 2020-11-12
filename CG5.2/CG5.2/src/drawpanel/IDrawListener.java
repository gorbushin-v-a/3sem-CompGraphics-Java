/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawpanel;

import java.util.EventListener;

/**
 * Интерфес слушателя события отрисовки
 * @author alvah
 */
public interface IDrawListener extends EventListener {
    //Обработчик события отрисовки
    public void DrawEvent(DrawEventArg e);
}
