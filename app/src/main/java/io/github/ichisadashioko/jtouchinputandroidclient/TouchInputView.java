package io.github.ichisadashioko.jtouchinputandroidclient;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchInputView extends View {
    public boolean isFingerDown;
    public boolean isStreamingTouchEvents;

    public TouchInputView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.isFingerDown = false;
        this.isStreamingTouchEvents = false;
    }

    /**
     * TODO This is a naive implementation for simplicity. It only supports a single finger and
     * there will be some unexpected behaviors when you try to stress it with multiple fingers.
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            if (!this.isFingerDown) {
                this.isFingerDown = true;
                if (this.isStreamingTouchEvents) {
                    // TODO
                }
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (this.isFingerDown) {
                this.isFingerDown = false;
                if (this.isStreamingTouchEvents) {
                    // TODO
                }
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            if (this.isFingerDown) {
                if (this.isStreamingTouchEvents) {
                    // TODO
                }
            }
        }

        return true;
    }
}
