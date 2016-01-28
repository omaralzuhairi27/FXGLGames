/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2016 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.spaceinvaders.tutorial;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.ServiceType;
import com.almasb.fxgl.ui.UIFactory;
import javafx.scene.text.Text;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class Tutorial {

    private Text uiText;
    private Runnable onFinish;

    private Queue<TutorialStep> tutorialSteps = new ArrayDeque<>();

    public Tutorial(Text uiText, Runnable onFinish, TutorialStep... steps) {
        this.uiText = uiText;
        this.onFinish = onFinish;
        tutorialSteps.addAll(Arrays.asList(steps));
    }

    public void play() {
        playStep(tutorialSteps.poll());
    }

    private void playStep(TutorialStep step) {
        uiText.setText(step.hint);
        step.action.run();

        GameApplication.getService(ServiceType.AUDIO_PLAYER).playMusic(step.fileName);

        GameApplication.getService(ServiceType.MASTER_TIMER).runOnceAfter(() -> {
            if (!tutorialSteps.isEmpty()) {
                playStep(tutorialSteps.poll());
            } else {
                onFinish.run();
            }
        }, step.duration);
    }
}
