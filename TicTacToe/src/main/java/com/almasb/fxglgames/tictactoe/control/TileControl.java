package com.almasb.fxglgames.tictactoe.control;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;
import com.almasb.fxglgames.tictactoe.TileValueComponent;
import com.almasb.fxglgames.tictactoe.TileValue;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
@Required(TileValueComponent.class)
public class TileControl extends Component {

    /**
     * @param value tile value
     * @return true if marking succeeded
     */
    public boolean mark(TileValue value) {
        TileValueComponent valueComponent = getEntity().getComponent(TileValueComponent.class);

        if (valueComponent.getValue() != TileValue.NONE)
            return false;

        valueComponent.setValue(value);

        return true;
    }
}
